
#pragma once

#include <queue>
#include <random>
#include <functional>

#include "PriorityQueue.hpp"

template<class Type, class Comparator = std::less<Type> > 
class Test {
public:
	void RunAllTests();
	void SmallTest();
	void SingleValueTest(size_t query_sequence_size, 
						const Type& push_query_value);
	void AlternatingQueriesTest(size_t query_sequence_size, 
								size_t same_query_segment_size);
	void RandomTest(size_t query_sequence_size, 
					const Type& min_value = std::numeric_limits<Type>::min(), 
					const Type& max_value = std::numeric_limits<Type>::max());
private:
	static const int PUSH_QUERY = 0;
	static const int POP_QUERY = 1;
	static const size_t DEFAULT_TEST_SIZE = 1000;
	static const size_t VALUE_SEED = 6969;
	static const size_t QUERY_SEED = 9696;

	void TestOneCase(const std::vector<int>& query_sequence, 
					 const std::vector<Type>& push_values_sequence); 
};

template<class Type, class Comparator>
void Test<Type, Comparator>::RunAllTests() {
	SmallTest();
	RandomTest(DEFAULT_TEST_SIZE);
	SingleValueTest(DEFAULT_TEST_SIZE, Type());
	AlternatingQueriesTest(DEFAULT_TEST_SIZE * 2, DEFAULT_TEST_SIZE);
	for (size_t same_query_segment_size = 1; 
				same_query_segment_size <= 10; 
				++same_query_segment_size) 
	{
		AlternatingQueriesTest(DEFAULT_TEST_SIZE, same_query_segment_size);
	}
}

template<class Type, class Comparator>
void Test<Type, Comparator>::SmallTest() {
	for (size_t test_size = 1; test_size <= 10; ++test_size) {
		RandomTest(test_size);
		SingleValueTest(test_size, Type());
	}
}

template<class Type, class Comparator>
void Test<Type, Comparator>::SingleValueTest(size_t query_sequence_size,
											 const Type& push_query_value) {
	RandomTest(query_sequence_size, push_query_value, push_query_value);
}

template<class Type, class Comparator>
void Test<Type, Comparator>::AlternatingQueriesTest(size_t query_sequence_size, 
													size_t same_query_segment_size) {
	std::default_random_engine generator(VALUE_SEED);
	std::uniform_int_distribution<Type> distribution(std::numeric_limits<Type>::min(), 
													 std::numeric_limits<Type>::max());

	std::vector<int> query_sequence;
	std::vector<Type> push_values_sequence;

	int query_type = PUSH_QUERY;
	size_t query_type_amount = 0;
	for (size_t index = 0; index < query_sequence_size; ++index) {
		query_sequence.push_back(query_type);
		if (query_type == PUSH_QUERY) {
			Type push_query_value = distribution(generator);
			push_values_sequence.push_back(push_query_value);
		} 
		++query_type_amount;
		if (query_type_amount == same_query_segment_size) {
			query_type_amount = 0;
			query_type = ((query_type == PUSH_QUERY) ? POP_QUERY : PUSH_QUERY);
		}
	}

	TestOneCase(query_sequence, push_values_sequence);
}

template<class Type, class Comparator>
void Test<Type, Comparator>::RandomTest(size_t query_sequence_size, 
										const Type& min_value, 
										const Type& max_value) {
	std::default_random_engine query_generator(QUERY_SEED);
	std::uniform_int_distribution<int> query_distribution(0, 1);

	std::default_random_engine value_generator(VALUE_SEED);
	std::uniform_int_distribution<Type> value_distribution(min_value, max_value); 
	
	std::vector<int> query_sequence;
	std::vector<Type> push_values_sequence;

	int push_pop_balance = 0;
	for (size_t index = 0; index < query_sequence_size; ++index) {
		int query_type = query_distribution(query_generator);
		if (query_type == POP_QUERY && push_pop_balance == 0) {
			query_type = PUSH_QUERY;
		}
		query_sequence.push_back(query_type);
		if (query_type == PUSH_QUERY) {
			Type push_query_value = value_distribution(value_generator);
			push_values_sequence.push_back(push_query_value);
		}
	}

	TestOneCase(query_sequence, push_values_sequence);
}

template<class Type, class Comparator>
void Test<Type, Comparator>::TestOneCase(const std::vector<int>& query_sequence, 
										 const std::vector<Type>& push_values_sequence) {
	PriorityQueue<Type, Comparator> priority_queue;
	std::priority_queue<Type, std::vector<Type>, Comparator> std_priority_queue;

	size_t push_values_index = 0;
	size_t query_sequence_size = query_sequence.size();
	for (size_t index = 0; index < query_sequence_size; ++index) {
		int query_type = query_sequence[index];
		if (query_type == PUSH_QUERY) {
			Type push_query_value = push_values_sequence[push_values_index];
			++push_values_index;
			priority_queue.push(push_query_value);
			std_priority_queue.push(push_query_value);
		} else {
			priority_queue.pop();
			std_priority_queue.pop();
		}

		if (!(priority_queue.size() == std_priority_queue.size())) {
			throw std::runtime_error("!(PriorityQueue<>.empty() == std::priority_queue<>.empty)");
		}
		if (!(std_priority_queue.empty()) && 
			!(priority_queue.top() == std_priority_queue.top())) 
		{
			throw std::runtime_error("!(PriorityQueue<>.top() == std::priority_queue<>.top())");
		}
	}	
}
	 
