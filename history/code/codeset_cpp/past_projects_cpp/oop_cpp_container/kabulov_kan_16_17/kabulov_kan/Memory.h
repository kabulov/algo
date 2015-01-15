//vipolnyaet toklo funkcionalnost , no ne neset v sebe kakoy-libo uchastok pamyati
#pragma once 

class Memory {
public:
	class Handle {
		void* p;
	public:
		Handle(void* ptr = NULL):p(ptr) {}
		inline int bad() const { return p == NULL; }
		inline int good() const { return !bad(); }
		virtual void* getPtr() const { return p; }
		virtual void* setPtr(void* ptr){return p = ptr;}
		//no destructor needed: Handle only manipulates with given slice of memory, but does not represent it
	};
	//Handle and Memory are not related
protected:
	virtual void* allocMem(size_t size) = 0;
	virtual void freeMem(void* ptr) = 0;

public:
	class Error{};
	class HandleError: public Error {};

	class SizeError: public Error {
	public:
		size_t size;
		SizeError(size_t sz): size(sz) {}
	};

	class AllocError: public Error {
	public:
		size_t size;
		AllocError(size_t sz): size(sz) {}
	};

	Handle alloc(size_t sz) {
		if (sz < 0)
			throw SizeError(sz);
		Handle h(allocMem(sz));
		if (h.bad())
			throw AllocError(sz);
		return h;
	}

	void free(Handle& h) {
		if (h.bad())
			throw HandleError();
		freeMem(h.getPtr());
		h.setPtr(NULL);
	}
};