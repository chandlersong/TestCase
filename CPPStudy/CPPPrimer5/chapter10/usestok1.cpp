// usestok1.cpp -- using the Stock class
#include <iostream>
#include "stock1.h" 

int main()
{
	using std::cout;
	using std::ios_base;
	using std::endl;
	
	cout.precision(2);
	cout.setf(ios_base::fixed,ios_base::floatfield);
	cout.setf(ios_base::showpoint);
	
	cout << "Using constructors to create new objects" << endl;
	Stock stock1("NanoSmart",12,20.0);
	stock1.show();
	Stock stock2 = Stock("Boffo Objects",2,2.0);
	stock2.show();
	
	cout << "Assigning stock1 to stock2:" << endl;
	stock2 = stock1;
	cout << "Listing stock1 and stock2:" << endl;
	stock1.show();
	stock2.show();
	
	cout << "Using constructors to reset an object" << endl;
	stock1 = Stock("Nifty Foods",10,50.0);
	stock1.show();
	cout << "Done" << endl;
	return 0;

}