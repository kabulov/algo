//obyedinenniy abstraktniy container verxnego urovnya dlya podgruppi

#pragma once 
#include "Container.h"

template<class Elem, class Key> class Container_3_3: public Container<Elem> 
{
public:
	Container_3_3(int sz):Container<Elem>(sz){}
	Container_3_3(Container_3_3& source):Container<Elem>(source){}
	virtual Elem& operator[](Key key) = 0;
	virtual Key indexOf(Elem& elem) = 0;
	virtual Key lastIndexOf(Elem& elem) = 0;
	virtual bool isEmpty() = 0;
};

//other functionality is represented in Container