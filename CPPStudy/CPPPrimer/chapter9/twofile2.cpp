// twofile2.cpp -- variables with internal and external linkage
#include <iostream>

extern int tom;    //tom defined elsewhere
static int dick;   //overrides external dick
int harry = 200;   //external variable definition, no conflict with twofile1 harry

void remote_access()
{
	using namespace std;
	cout << "remote_access() reports the following addresses:" << endl;
	cout << &tom << " = &tom, " << &dick << " = &dick, ";
	cout << & harry << " = harry " << endl;
	
}