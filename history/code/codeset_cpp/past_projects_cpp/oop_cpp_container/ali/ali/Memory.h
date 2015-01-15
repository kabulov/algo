#pragma once
#include <stdlib.h>
#include <iostream>
using namespace std;

class Memory
{
public:
    class Handle 
    {
        void* p;
    public:
        Handle(void* ptr=NULL): p(ptr) {}
        inline int bad() { return p==NULL; }
        inline int good() { return !bad(); }
        void* getPtr() { return p; }
		void* setPtr(void* ptr) {return p=ptr;}
        friend class Memory;
    };

protected:
    virtual void* allocMem(size_t sz)=0; 
    virtual void freeMem(void* ptr)=0;

public:
    class Error {};
    class HandleError: public Error {};
    
	class SizeError: public Error
    {
    public:
        size_t size;
        SizeError(size_t sz): size(sz) {}
    };

    class AllocError: public Error
    {
     public:
        size_t size;
        AllocError(size_t sz): size(sz) {}
    };

    Handle alloc(size_t sz)
    {
        if(sz<0)
            throw SizeError(sz);
        Handle h(allocMem(sz));
        if(h.bad())
            throw AllocError(sz);
        return h;
    }

	void free(Handle& h)
    {
        if(h.bad())
            throw HandleError();
        freeMem(h.p);
    }

	virtual void* getPtr(Handle h) { return h.p; }
    virtual void* setPtr(Handle& h, void* ptr) { return h.p=ptr; }
};