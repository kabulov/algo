
#pragma once

#include <vector>	
#include <stdexcept>

template<class Type, class Comparator = std::less<Type> > 
class PriorityQueue {
public:
	PriorityQueue(Comparator compare = Comparator());
	PriorityQueue(size_t size, const Type& value, Comparator compare = Comparator());
	PriorityQueue(const std::vector<Type>& values, Comparator compare = Comparator());

	void push(const Type& value);
	const Type& top() const;
	void pop();
	size_t size() const;
	bool empty() const;
	void clear();

private:
	Comparator compare_;
	std::vector<Type> elements_;

	size_t parent(size_t index) const;
	size_t leftSon(size_t index) const;
	size_t rightSon(size_t index) const;

	bool compareElements(size_t first_position, size_t second_position) const;
	void swapElements(size_t first_position, size_t second_position);
	void siftUp(size_t position);  
	void siftDown(size_t position);  
};

template<class Type, class Comparator> 
PriorityQueue<Type, Comparator>::PriorityQueue(Comparator compare = Comparator()) : compare_(compare) 
{
}

template<class Type, class Comparator> 
PriorityQueue<Type, Comparator>::PriorityQueue(size_t size, 
											   const Type& value, 
											   Comparator compare = Comparator()) : compare_(compare)
{
	elements_.assign(size, value);	
}

template<class Type, class Comparator>
PriorityQueue<Type, Comparator>::PriorityQueue(const std::vector<Type>& values, 
											   Comparator compare = Comparator()) : elements_(values) 
																					compare_(compare) 
{
	const size_t first_index = elements_.size() / 2;
	for (int index = first_index; index >= 0; --index) {
		siftDown(index);	
	}
}

template<class Type, class Comparator>
void PriorityQueue<Type, Comparator>::push(const Type& value) {
	elements_.push_back(value);
	const size_t index = elements_.size() - 1;
	siftUp(index);
}

template<class Type, class Comparator>
void PriorityQueue<Type, Comparator>::pop() { 
	if (empty()) {
		throw std::runtime_error("calling pop() but empty()");
	}
	const size_t last_index = elements_.size() - 1;
	if (last_index != 0) {
		swapElements(0, last_index);
		elements_.pop_back();
		siftDown(0);
	} else {
		elements_.pop_back();
	}
}

template<class Type, class Comparator>
const Type& PriorityQueue<Type, Comparator>::top() const { 
	if (empty()) {
		throw std::runtime_error("calling top() but empty()"); 
	}
	return elements_[0];
}

template<class Type, class Comparator>
size_t PriorityQueue<Type, Comparator>::size() const {
	return elements_.size();
}

template<class Type, class Comparator>
bool PriorityQueue<Type, Comparator>::empty() const {
	return size() == 0;
}

template<class Type, class Comparator>
void PriorityQueue<Type, Comparator>::clear() {
	elements_.clear();
}

template<class Type, class Comparator>
size_t PriorityQueue<Type, Comparator>::parent(size_t position) const {
	return position / 2;
}

template<class Type, class Comparator>
size_t PriorityQueue<Type, Comparator>::leftSon(size_t position) const {
	const size_t result = position * 2;
	return result;
}

template<class Type, class Comparator>
size_t PriorityQueue<Type, Comparator>::rightSon(size_t position) const {
	const size_t result = position * 2 + 1;
	return result;
}

template<class Type, class Comparator>
bool PriorityQueue<Type, Comparator>::compareElements(size_t first_position, size_t second_position) const {
	return compare_(elements_[first_position], elements_[second_position]);
}

template<class Type, class Comparator>
void PriorityQueue<Type, Comparator>::swapElements(size_t first_position, size_t second_position) {
	std::swap(elements_[first_position], elements_[second_position]);
}

template<class Type, class Comparator>
void PriorityQueue<Type, Comparator>::siftUp(size_t position) {
	size_t parent_position = parent(position);
	while (position != 0 && compareElements(parent_position, position)) { 
		swapElements(position, parent_position);
		position = parent_position;
		parent_position = parent(position);
	}
}

template<class Type, class Comparator >
void PriorityQueue<Type, Comparator>::siftDown(size_t position) {
	size_t bound = elements_.size();
	while (2 * position < bound) {
		size_t next_position = position;
		
		const size_t left_son_position = leftSon(position);
		if (compareElements(position, left_son_position)) { 
			next_position = left_son_position;
		}

		const size_t right_son_position = rightSon(position);
		if (right_son_position < bound) {
			if (compareElements(next_position, right_son_position)) { 
				next_position = right_son_position;
			}
		}

		if (position == next_position) {
			break;
		} else {
			swapElements(position, next_position);
			position = next_position;
		}
	}
}
