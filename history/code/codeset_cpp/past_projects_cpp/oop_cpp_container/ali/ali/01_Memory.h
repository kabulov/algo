#pragma once
#include "Memory.h"

class Memory_01:public Memory {
protected:
	void* allocMem(size_t sz) {
		return malloc(sz);
	}
    
	void freeMem(void* ptr) {
		::free(ptr);
	}
};