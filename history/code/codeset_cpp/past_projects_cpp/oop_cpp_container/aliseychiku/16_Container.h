//predpolagaetsa chto dlya Elem opredeleno:
//operator ==
//operator =

#pragma once
#include "Container.h"
#include "16_Memory.h"

using namespace std;

template <class Elem> class Vector_16: public Container<Elem> {
protected:
	int blockSize;
	int blockCount;
	Memory::Handle handle;

	virtual void read(istream& s) {
		cin>>_size;
		if (_size > _maxSize)
			setMaxSize(_size);

		Elem elem;
		for (int i = 0; i < _size; i++) {
			cin>>elem;
			At(i) = elem;
		}
	}

	virtual Elem& At(int pos) {
		return (((Elem**)handle.getPtr())[pos/blockSize])[pos%blockSize]; 
	}

public:
	class outOfBounds: public Error {};

	virtual Elem& getAt(int pos) {
		if (pos <0 || pos>_size)
			throw outOfBounds();
		return At(pos);
	}

	virtual Elem& setAt(Elem& elem, int pos) {
		if (pos <0 || pos>_size)
			throw outOfBounds();
		return At(pos) = elem;
	}

	class Vector_Iterator: public Iterator {
	protected:
		int pos;
	public:
		Vector_Iterator(Vector_16<Elem>& V): Iterator(V), pos(0) {}
		virtual int hasNext() {
			return pos < _owner.size();
		}
		virtual Elem& getNext() {
			return ((Vector_16<Elem>&)(_owner)).getAt(pos++);
		}
	};

	virtual Iterator* newIterator() {
		return new Vector_Iterator(*this);	
	}
//
	Vector_16(Memory_16& mem, int sz, int blckSize = 1024): Container<Elem>(mem), blockCount(0), blockSize(0) {
		if (blckSize < 256)
			blckSize = 256;

		if (sz < 0)
			sz = 0;

		blockSize = blckSize;
		blockCount = (sz / blockSize) + (((sz%blockSize) == 0) ? 0 : 1);
		_maxSize = _size = sz;

		if (sz > 0) {
			handle = memory.alloc(blockCount*sizeof(Elem*));	//
			for (int i = 0; i < blockCount; i++){
				Elem** buffer = (Elem**)memory.getPtr(handle);
				buffer[i] = (Elem*)malloc(blockSize*sizeof(Elem));
			}
		}
	} 

	Vector_16(Vector_16<Elem>& v): Container<Elem>(v.memory), blockCount(0), blockSize(v.blockSize) {
		setMaxSize(v.size()); //v.getMaxSize()
		copyFrom(v, 1);
	}

	virtual int setMaxSize(int newMaxSize) { //changes only _maxSize, but _size too if needed
		if (newMaxSize < 0)
			return -1;
		if (newMaxSize == _maxSize)
			return newMaxSize;

		int i;
		Elem** buffer;

		int newBlockCount = (newMaxSize/blockSize) + (((newMaxSize%blockSize) == 0)?0:1);
		if (newMaxSize > _maxSize) {
			if (newBlockCount > blockCount) {
				buffer = (Elem**)malloc(newBlockCount*sizeof(Elem*));
				for (i = 0; i < blockCount; i++) 
					buffer[i] = ((Elem**)memory.getPtr(handle))[i];
				for (; i < newBlockCount; i++)
					buffer[i] = (Elem*)malloc(blockSize*sizeof(Elem));
				if (memory.getPtr(handle) != NULL)
					memory.free(handle);
				memory.setPtr(handle, (void*)buffer);
			}
		} else {//newMaxSize < _maxSize
			if (newMaxSize < _size)
				_size = newMaxSize;
			if (newBlockCount < blockCount) {
				buffer = (Elem**)malloc(newBlockCount*sizeof(Elem*));
				for (i = 0; i < newBlockCount; i++)
					buffer[i] = ((Elem**)memory.getPtr(handle))[i];
				for (; i < blockCount; i++)
					::free(((Elem**)memory.getPtr(handle))[i]);
				if (memory.getPtr(handle) != NULL)
					memory.free(handle);
				memory.setPtr(handle, (void*)buffer);
			}
		}

		blockCount = newBlockCount;
		return _maxSize = newMaxSize;
	}

	virtual Elem& append(Elem& elem) { //changes only _size, but _maxSize too if needed
		if (_size == _maxSize) //_size always <= _maxSize
			setMaxSize(_size + 1);
		At(_size)=elem;
		return At(_size++); 
	}

	virtual void remove(Elem& elem) { //changes only _size
		int pos = indexOf(elem);

		if (pos < 0)
			return;

		_size--;	
		for (int i = pos; i < _size; i++) 
			At(i) = At(i+1);
	}
	
	virtual int find(Elem& elem) { //if empty return -1;
		if (indexOf(elem) >= 0)
			return 1;
		else
			return -1;
	}

	virtual int indexOf(Elem& elem, int pos = 0) { //if empty return -1;
		//if (_size == 0)
		//	return -1;

		int position = -1;
		for (Iterator* i = newIterator(); i->hasNext();) {
			position++;
			if (i->getNext() == elem && position >= pos)
				return position - pos;
		}
		return -1;
	}

	virtual int lastIndexOf(Elem& elem, int pos = _size - 1) {	
		//if (_size == 0)
		//	return -1;

		int position = -1;
		int p = -1;
		for (Iterator* i = newIterator(); i->hasNext() && position<pos;) {
			position++;
			if (i->getNext() == elem)
				p = position;
		}
		return p;
	}

	virtual Container<Elem>& operator &= (Container<Elem>& c) {
		int i = 0;
		while (i < _size)
			if (c.size() > 0 && c.find(At(i)) > 0)
				i++;
			else
				remove(At(i));
		return *this;
	}

	virtual int isEmpty() {
		return _size == 0;
	}

	virtual void sort() {
		throw ENotSupported();
	}

    friend ostream& operator<< (ostream &s, Vector_16<Elem>& c);
    friend istream& operator>> (istream &s, Vector_16<Elem>& c);

	//destructor
};

ostream& operator<< (ostream &s, Vector_16<int>& c)
{
    c.write(s);    
    return s;
}

istream& operator>> (istream &s, Vector_16<int>& c)
{
    c.read(s);    
    return s;
}

