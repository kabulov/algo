#pragma once
#include "01_Memory.h"
#include "Container.h"

using namespace std;

template <class Elem> class Vector_01:public Container<Elem>
{
protected:
	Elem** matrix;
	
	virtual void read(istream &s){
        int siz;
		Elem e;
        s>>siz;

		for(int i=0;i<siz;i++)
        {
            s>>e;
            (*this)[i]=e;
        }
		_size = siz;
    }

	class Vector_Iterator:public Iterator
    {
    private:
        int pos;
    public:
        Vector_Iterator(Vector_01<Elem>& v):Iterator(v), pos(0) {}
        int hasNext() { return (pos<_owner.size()); }
        Elem& getNext() { return ((Vector_01<Elem>&)_owner)[pos++]; }
    };

	//newMaxSize = kolichestvo blokov
	virtual int setMaxSize(int newMaxSize) {
		Elem** tmp = (Elem**) malloc(newMaxSize*sizeof(Elem*));
		for (int i = 0; i < _maxSize; i++)
			tmp[i] = matrix[i];
		tmp[_maxSize] = (Elem*)malloc(4096*sizeof(Elem));
		::free(matrix);
		matrix = tmp;
		return (_maxSize = newMaxSize);
	}

	Elem& operator[] (int i) {
		return matrix[i / 4096][i % 4096];
	}

public:
	Iterator* newIterator(){
		return new Vector_Iterator(*this);
	}

	Elem& append(Elem& elem) {
		if (_size >= (_maxSize*4096))
			setMaxSize(_maxSize + 1);
		(*this)[_size] = elem;
		_size++;
		return (*this)[_size - 1];
	}

	class NoSuchElement:public Error{};

	Elem& remove(Elem& elem){
		int i;
		for (i = 0; i < _size; i++)
			if ((*this)[i] = elem)
				break;
		if (i == _size)
			throw NoSuchElement();
		int j = i;
		for (;i < _size - 1; i++)
			(*this)[i] = (*this)[i + 1];
		_size--;
		return elem; 
	}

	int find(Elem& elem){
		for (Iterator *i = newIterator(); i->hasNext();) 
			if (i->getNext() == elem) 
				return 1;
		return -1;
	}

	Container<Elem>& operator &=(Container<Elem>& c) {
			throw ENotSupported();
	}
    
	Vector_01(Memory& mem): Container<Elem>(mem){
		_maxSize = 1;
		matrix = (Elem**)malloc(sizeof(Elem*));
		matrix[0] = (Elem*)malloc(4096*sizeof(Elem));
	}

	void sort() {
		int i, j;
		for (i = 0; i < _size - 1; i++) 
			for (j = i + 1; j < _size; j++)
				if ((*this)[i] < (*this)[j]) {
					Elem tmp = (*this)[i];
					(*this)[i] = (*this)[j];
					(*this)[j] = tmp;
				}
					
	}

	int getMaxSize() {
		return _maxSize * 4096;
	}

	friend ostream& operator<< (ostream &s, Vector_01<Elem>& c);
    friend istream& operator>> (istream &s, Vector_01<Elem>& c);

	virtual ~Vector_01(){
		for (int i = 0; i < _maxSize; i++)
			::free(matrix[i]);

		::free(matrix);
	}
};

ostream& operator<< (ostream &s, Vector_01<int>& c)
{
    c.write(s);    
    return s;
}

istream& operator>> (istream &s, Vector_01<int>& c)
{
    c.read(s);    
    return s;
}
