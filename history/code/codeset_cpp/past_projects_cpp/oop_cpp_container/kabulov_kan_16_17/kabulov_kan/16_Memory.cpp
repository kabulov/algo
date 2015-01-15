#include "16_Memory.h"

template<class Elem> Elem& Memory_16<Elem>::operator[](int pos) {
	return matrix[pos / blockSize][pos % blockSize];
}

template<class Elem> void Memory_16<Elem>::allocate() {
	handle = alloc(blockCount * sizeof(Elem*)); //may throw exception
	matrix = (Elem**)handle.getPtr();

	for (i = 0; i < blockCount; i++) {
		handle = alloc(blockSize * sizeof(Elem));
		matrix[i] = (Elem*)handle.getPtr();
	}
}

template<class Elem> Memory_16<Elem>::Memory_16(int sz, int blockSize): matrix(NULL), blockCount(0), size(sz) {
	this->blockSize = blockSize;
		
	if (sz == 0)
		blockCount = 0;
	else {
		blockCount = sz / blockSize;
		if (sz % blockSize > 0)
			blockCount++;
	}
	
	allocate();
}

template<class Elem> Memory_16<Elem>::Memory_16(Memory_16& source): matrix(NULL), blockCount(source.blockCount), blockSize(source.blockSize), size(source.size) {
	allocate();

	for (int i = 0; i < size; i++)
		this->
} 
