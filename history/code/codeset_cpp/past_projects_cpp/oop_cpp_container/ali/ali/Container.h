#pragma once
#include "Memory.h"
using namespace std;

template <class Elem> class Container
{
protected:
	int _size, _maxSize;
    Memory& memory;

    virtual void write(ostream &s)
    {
        s<<size();
        for(Iterator* i=newIterator();i->hasNext();)
            s<<' '<<i->getNext();
    }

    virtual void read(istream &s)=0;

    void copyFrom(Container<Elem>& c, int addDuplicates)
    {
        for(Iterator* i=c.newIterator();i->hasNext();)
        {
            Elem& e=i->getNext();
            if(addDuplicates || find(e)<0)
                append(e);
        }
    }
public:
    class Error {};
    class ENotSupported: public Error {};

    class Iterator
    {
    protected:
        Container<Elem>& _owner;
    public:
        Iterator(Container<Elem>& owner): _owner(owner) {}
        virtual int hasNext()=0;
        virtual Elem& getNext()=0;
    };

    virtual Iterator* newIterator()=0;
    virtual int size() { return _size; }
    int getMaxSize() { return _maxSize; } 
    virtual int setMaxSize(int newMaxSize)=0;
    virtual Elem& append(Elem& elem)=0;
    virtual Elem& remove(Elem&  elem)=0;
   	virtual int find(Elem& elem)=0;

    Container<Elem>& operator |=(Container<Elem>& c)
    {
        copyFrom(c,0);
        return *this;
    }

    virtual Container<Elem>& operator &=(Container<Elem>& c)=0;
    Container(Memory& mem): _size(0), memory(mem), _maxSize(0) {}

    Container(Container<Elem>& c): _size(c._size), memory(c._memory), _maxSize(c._maxSize)
    {
        copyFrom(c,1);
    }

    virtual void sort()=0;
};