#pragma once 
#include "Memory.h"

using namespace std;

class Memory_16: public Memory {
protected:
	virtual void* allocMem(size_t size) {return malloc(size);}
	virtual void freeMem(void* ptr) {::free(ptr);}
};