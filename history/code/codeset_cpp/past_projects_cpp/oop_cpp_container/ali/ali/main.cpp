#include "01_Test.h"
#include "stdlib.h"

int main() {
	Test_01<int> test;
	test.test();
	fflush(stdin);
	getchar();
	return 0;
}