// funtemp.cpp -- using a function template
#include <iostream>
//function template prototype
template <class Any> //or typename Any
void Swap(Any &a, Any &b);

int main()
{
	using namespace std;
	int i = 10;
	int j = 20;
	cout << "i,j = " << i << "," << j << endl;
	cout << "Using compiler generated int swapper:" << endl;
	Swap(i,j); //generates void(int &,int &)
	cout << "Now i, j = " << i << ", " << j << endl;
	
	double x = 24.5;
	double y = 81.7;
	cout << "x,y = " << x << "," << y << endl;
	cout << "Usin compiler generated double swapper:" << endl;
	Swap(x,y); // generates void Swap(double &, double &)
	cout << "Now x, y = " << x << ", " << y << ".\n";
	
	return 0;

}

//function
template <class Any>   // or typename Any
void Swap(Any &a, Any & b)
{
	Any temp;    // temp a variable of type Any
	temp = a;
	a = b;
	b = temp;
}