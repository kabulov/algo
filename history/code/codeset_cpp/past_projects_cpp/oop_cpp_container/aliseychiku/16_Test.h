#pragma once
#include <iostream>
#include "16_Container.h"

using namespace std;

template <class Elem> class Test_16
{
    Memory_16 m;
    Vector_16<Elem> v;
	Vector_16<Elem> vv;
public:
    Test_16(): v(m,0), vv(m, 0) {}

	int test()
        {
	
			cout<<"emptystate: "<<v.isEmpty() << "\n"; 

			cout<<"Enter number of elements and then elements(space-delimited):"<<endl;
			cin>>v;
			cout<<endl<<"Enter number of elements(integer) and then elements(space-delimited) you wish to find in container\n";
			int num;
			cin>>num;
			Elem elem;
			for (int i = 0; i < num; i++) {
				cin >> elem;			
				cout << ((v.find(elem)>0)?"\n in container \n":" \n not in container \n");
			}

			cout << "\nadd elements to container : first - amount, then - elements space - delimited \n";
			cin >> num;
			for (int i = 0; i < num; i ++){
				cin>> elem;
				v.append(elem);
			}
            cout<<"\nContainer size (the 1st number) and elements: \n"<<v<<endl;
			
            cout<<"\nEnter number of elements and then elements(space-delimited) of second container:"<<endl;
			cin>>vv;

			{
				cout<<"\nlogic multiplication of containers :\n";
				Vector_16<Elem> vvv(v);
				vvv.operator &=(vv);
				cout<<vvv;
			}

			{
				cout<<"\nlogic sum of containers :\n";
				Vector_16<Elem> vvv(v);
				vvv.operator |=(vv);
				cout<<vvv;
			}

			{
				cout<<"\nremove last n elements\n";
				Vector_16<Elem> vvv(v);
				num = vvv.size();
				for (int i = 0; i < num; i++)
					vvv.remove(vvv.getAt(vvv.size() - 1));
			}

			{
				cout<<"\nremove first element n times \n";
				Vector_16<Elem> vvv(v);
				num = vvv.size();
				for (int i = 0; i < num; i++)
					vvv.remove(vvv.getAt(0));
			}

			{
				cout << "\nadd and remove n times\n";
				Vector_16<Elem> vvv(v);
				num = vvv.size();
				srand(1);
				srand(rand());
				for (int i = 0; i < num; i++) {
					vvv.append(elem);
					vvv.remove(vvv.getAt(rand()%num));
				}
			}

			{
				cout << "\n1. enter n; 2. n pairs : (position,space-delimiter,element) to change corresponding elements\n";
				cin >> num;
				int pos;
				Vector_16<Elem> vvv(v);				
				for (int i = 0; i < num; i++) {
					cin>>pos>>elem;
					vvv.setAt(Elem(elem), pos);
				}
				cout<<"\n elemts \n"<<vvv;
			}

			return 0;
        }
};
