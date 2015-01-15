#pragma once
#include <iostream>
#include "01_Container.h"

using namespace std;

template <class Elem> class Test_01
{
    Memory_01 m;
    Vector_01<Elem> v;

public:
    Test_01(): v(m){}

	int test()
        {
	
			cout<<"vvedite kolichestvo elementov a zatem sami elementi"<<endl;
			cin>>v;
			cout<<endl<<"vvedite kolichestvo i sami elementi kotorie vi xotite nayti v konteynere\n";
			
			int number;
			cin>>number;
			Elem element;
			for (int i = 0; i < number; i++) {
				cin >> element;			
				cout << ((v.find(element) == 1)?"\n v konteynere est takoy element \n":" \n v konteynere takogo elementa net \n");
			}

			cout << "\n dobavit elementi v konteyner, snachala kolichestvo zatem sami elementi\n";
			cin >> number;
			
			for (int i = 0; i < number; i ++){
				cin>> element;
				v.append(element);
			}

            cout<<"\n razmer i elementi konteynera  \n"<<v<<endl;
			return 0;
        }
};
