#pragma once
#include "16_Test.h"
#include <stdio.h>

int main() {
	Test_16<int> test;
	if (test.test())
		printf("errors occured!!");
	fflush(stdin);
	getchar();
	return 0;
}