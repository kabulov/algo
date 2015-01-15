//itogoviy konteyner : vector

#pragma once
#include "3_3_Container.h"
#include "16_Memory.h"

using namespace std;

template <class Elem> class Vector_16 : public Container_3_3<Elem, int> {
protected:
	Memory_16<Elem> memory;//!!!

	virtual void read(istream& s) {
		cin>>_size;
		if (_size > _maxSize)
			setMaxSize(_size);

		Elem elem;
		for (int i = 0; i < _size; i++) {
			cin>>elem;
			(*this)[i] = elem;
		}
	}

public:
	Vector_16(int sz, int blockSize = Memory_16<Elem>::defaultBlockSize): Container_3_3<Elem,int>(sz), memory(sz, blockSize){
		_maxSize = sz;
	}

	Vector_16(Vector_16& source):Container_3_3<Elem,int>(source), memory(source.size(), source.memory.getBlockSize()) {
		_maxSize = source.getMaxSize();
		copyFrom(source, 1);
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
			return ((Vector_16<Elem>&)(_owner))[pos++];
		}
	};

	virtual Iterator* newIterator() {
		return new Vector_Iterator(*this);	
	}

	virtual Elem& operator[](int key){
		return memory[key];	
	}

	virtual bool isEmpty() {
		return _size == 0;
	}

	virtual void sort() {
		for (int i = 0; i < _size - 1; i++)
			for (int j = i + 1; j < _size; j++)
				if ((*this)[i] > (*this)[j]) {
					Elem temp = (*this)[i];
					(*this)[i] = (*this)[j];
					(*this)[j] = temp;
				}
	}

	virtual int setMaxSize(int newMaxSize) {
		int prevMaxSize = _maxSize;

		if (newMaxSize != _maxSize) {
			if (newMaxSize < _size)
				_size = newMaxSize;
			memory.setMaxSize(newMaxSize);
			_maxSize = newMaxSize;
		}

		return prevMaxSize; 
	}

	virtual int indexOf(Elem& elem){
		//if (_size == 0)
		//	return -1;
		int position = -1;
		for (Iterator* i = newIterator(); i->hasNext();) {
			position++;
			if (i->getNext() == elem)
				return position;
		}
		return -1;
	
	}
	virtual int lastIndexOf(Elem& elem) {
		//if (_size == 0)
		//	return -1;
		int position = -1;
		int p = -1;
		for (Iterator* i = newIterator(); i->hasNext();) {
			position++;
			if (i->getNext() == elem)
				p = position;
		}
		return p;
	}
	
	virtual int find(Elem& elem) {
		if (indexOf(elem) >= 0)
			return 1;
		else
			return -1;
	}

	virtual Elem& append(Elem& elem) { //adds new block if _size == _maxSize
		if (_size == _maxSize) //_size always <= _maxSize
			setMaxSize(_size + 1);
		(*this)[_size]=elem;
		return (*this)[_size++]; 
	}

	virtual void remove(Elem& elem) {//does not change maxSize
		int pos = indexOf(elem);

		if (pos < 0)
			return;

		_size--;	
		for (int i = pos; i < _size; i++) 
			(*this)[i] = (*this)[i+1];
	}

	virtual Container<Elem>& operator &= (Container<Elem>& c){
		int i = 0;
		while (i < _size)
			if (c.size() > 0 && c.find((*this)[i]) > 0)
				i++;
			else
				remove((*this)[i]);
		return *this;
	}

    friend ostream& operator<< (ostream &s, Vector_16<Elem>& c);
    friend istream& operator>> (istream &s, Vector_16<Elem>& c);

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


//nashcet remove bili zamechaniya Pavla Alenksandrovicha :
//naschet togo chtobi sdvigat kuda blije : v nachale dirki poyavlyayutsa, po lyubomu sdvigat prixoditsa tak chto bi uplotnyalos k nachalu
//esli est mnogo neispolzuemoy pamyati , to u polzovatelya est zamechatelnaya funkciya setMaxSize, pust sam sledit za neispolzuemoy pamyatyu

