//etot memory shablonniy, xranit uchastok pamyati v sebe
#pragma once
#include <stdlib.h>
#include "Memory.h"

template<class Elem> class Memory_16 : public Memory {
	void allocate();

protected:
	Memory::Handle handle;
	Elem** matrix;
	int blockCount;
	int blockSize;
		
	virtual Elem& At(int pos);

	virtual void* allocMem(size_t size) {
		return malloc(size);
	}

	virtual void freeMem(void* ptr) {
		::free(ptr);
	}
	
public:
	static const int defaultBlockSize = 4096;
	class outOfBounds : public Error{};
	class illegalOperation : public Error{};
	Memory_16(int sz, int blockSize = defaultBlockSize);
	Memory_16(Memory_16& source);
	Elem& operator[](int pos); 
	virtual void setMaxSize(int newMaxSize);
	virtual int getBlockSize(){return blockSize;}

};

template<class Elem> Elem& Memory_16<Elem>::At(int pos) {
	return matrix[pos / blockSize][pos % blockSize];
}

template<class Elem> Elem& Memory_16<Elem>::operator[](int pos) {
	if (pos <0 || pos >= blockSize*blockCount)
		throw outOfBounds();
	return At(pos); 
}

template<class Elem> void Memory_16<Elem>::allocate() {
	handle = alloc(blockCount * sizeof(Elem*)); //may throw exception
	matrix = (Elem**)handle.getPtr();

	for (int i = 0; i < blockCount; i++) {
		handle = alloc(blockSize * sizeof(Elem));
		matrix[i] = (Elem*)handle.getPtr();
	}
}

template<class Elem> Memory_16<Elem>::Memory_16(int sz, int blockSize): matrix(NULL), blockCount(0) {
	if (sz < 0 || blockSize < defaultBlockSize)
		throw Error();

	this->blockSize = blockSize;
		
	if (sz == 0)
		blockCount = 1;
	else {
		blockCount = sz / blockSize;
		if (sz % blockSize > 0)
			blockCount++;
	}
	
	allocate();
}

template<class Elem> Memory_16<Elem>::Memory_16(Memory_16& source): matrix(NULL), blockCount(source.blockCount), blockSize(source.blockSize) {
	allocate();

	int tempSize = blockCount * blockSize;
	for (int i = 0; i < tempSize; i++)
		this->At(i) = source.At(i);
} 

template<class Elem> void Memory_16<Elem>::setMaxSize(int newMaxSize) {
	if (newMaxSize < 0)
		throw illegalOperation();
	
	int newBlockCount;
	if (newMaxSize == 0)
		newBlockCount = 1;
	else {
		newBlockCount = newMaxSize / blockSize;
		if (newMaxSize % blockSize > 0)
		newBlockCount++;
	}
	
	if (newBlockCount == blockCount)
		return;
	
	Elem** buf;
	if (newBlockCount < blockCount) {
		for (int i = newBlockCount; i < blockCount; i++) {
			handle.setPtr((void*)matrix[i]);
			Memory::free(handle);
		}

		handle = alloc(newBlockCount * sizeof(Elem*));
		buf = (Elem**)handle.getPtr();
		for (int i = 0; i < newBlockCount; i++) 
			buf[i] = matrix[i];
	} else {
		handle = alloc(newBlockCount * sizeof(Elem*));
		buf = (Elem**)handle.getPtr();
		for (int i = 0; i < blockCount; i++)
			buf[i] = matrix[i];

		for (int i = blockCount; i < newBlockCount; i++) {
			handle = alloc(blockSize * sizeof(Elem));
			buf[i] = (Elem*)handle.getPtr();
		}
	}

	handle.setPtr((void*)matrix);
	Memory::free(handle);

	matrix = buf;

	blockCount = newBlockCount;
}