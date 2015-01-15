//vipolnyaet tolko funkcionalnost, upravleniem pamyatyu ne vedaet ni v kakoy setepeni
#pragma once
#include <iostream>

using namespace std;

template <class Elem> class Container {
protected:
	int _size, _maxSize;

	virtual void write(ostream& s) {
		s<<_size;
		for (Iterator* i = newIterator(); i->hasNext();) 
			s<<' '<<i->getNext();
	}

	virtual void read(istream& s) = 0;

	void copyFrom(Container<Elem>& c, int addDuplicates) {
		for (Iterator* i = c.newIterator(); i->hasNext();) {
			Elem& e = i->getNext();
			if (addDuplicates || find(e)<0)
				append(e);
		}
	}

public:
	class Error {};
	class ENotSupported: public Error {};

	class Iterator {
	protected:
		Container<Elem>& _owner;
	public:
		Iterator(Container<Elem>& owner): _owner(owner) {}
		virtual int hasNext() = 0;
		virtual Elem& getNext() = 0;
	};

	virtual Iterator* newIterator() = 0;
	int size() {return _size;}
	int getMaxSize() {return _maxSize;}
	virtual int setMaxSize(int newMaxSize) = 0;
	virtual Elem& append(Elem& elem) = 0; //adds new block if _size == _maxSize
	virtual void remove(Elem& elem) = 0;
	virtual int find(Elem& elem) = 0;

	Container<Elem>& operator |= (Container<Elem>& c) {
		copyFrom(c, 0);
		return *this;
	}

	virtual Container<Elem>& operator &= (Container<Elem>& c) = 0;

	Container(int maxSize): _size(0) {//changed
		if (maxSize < 0)
			throw Error();
	}

	Container(Container<Elem>& c): _size(0) {} //changed

	virtual void sort() = 0;
};
