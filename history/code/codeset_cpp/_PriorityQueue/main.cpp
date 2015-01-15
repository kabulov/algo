
#include <iostream>

#include "Test.hpp"

int testAll() {
	try {
		Test<int, std::less<int> > test_int_less;
		test_int_less.RunAllTests();

		Test<int, std::greater<int> > test_int_greater;
		test_int_greater.RunAllTests();

		Test<long long, std::less<long long> > test_long_less;
		test_long_less.RunAllTests();

		Test<long long, std::greater<long long> > test_long_greater;
		test_long_greater.RunAllTests();
	} catch (std::exception& e) {
		std::cout << e.what() << std::endl;
		return 1;

	}
	return 0;
}

void usage();
void RunManual();

int main() {
#ifdef TEST_MODE
	return testAll();
#endif

	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	
	usage();

	try {
		RunManual();
	} catch (std::exception& e) {
		std::cout << e.what() <<std::endl;
		return 1;
	}

	return 0;
}

void usage() {
	std::cout << "Manual input \n" 
		" 3 query types : \n"
		" 0 integer <- push \n"
		" 1 <- pop \n"
		" 2 <- print size \n";
	std::cout << "Format : \n"
		" n <- input size \n"
		" query 1 \n"
		" query 2 \n"
		" ... \n"
		" query n \n";
}

void RunManual() {
	const int PUSH = 0;
	const int POP = 1;
	const int SIZE = 2;

	size_t size;
	std::cin >> size;

	int value;
	int query_type;
	PriorityQueue<int> priority_queue;

	for (size_t query = 0; query < size; ++query) {
		std::cin >> query_type;
		switch(query_type) {
		case PUSH:
			std::cin >> value;
			priority_queue.push(value);
			break;
		case POP:
			value = priority_queue.top();
			priority_queue.pop();
			std::cout << value << std::endl;
			break;
		case SIZE:
			std::cout << priority_queue.size() << std::endl;
			break;
		default:
			throw std::runtime_error("undefined query");
		}
	}
}