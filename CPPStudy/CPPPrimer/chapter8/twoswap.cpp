// twoswap.cpp -- specialization overrides a template
#include <iostream>
//function template prototype
template <class Any> //or typename Any
void Swap(Any &a, Any &b);

struct job
{
	char name[40];
	double salary;
	int floor;
};

//explicit specilization
template <> void Swap<job>(job &j1, job &j2);
void Show(job &j);
int main()
{
	using namespace std;
	int i = 10;
	int j = 20;
	cout << "i,j = " << i << "," << j << endl;
	cout << "Using compiler generated int swapper:" << endl;
	Swap(i,j); //generates void(int &,int &)
	cout << "Now i, j = " << i << ", " << j << endl;
	
	job sue = {"Susan Yafee", 73000.60,7};
	job sidney = {"Sidney Taffe", 78060.72,9};
	cout << "Before job swapping: " << endl;
	Show(sue);
	Show(sidney);
	Swap(sue,sidney);   //uses void Swap(job &,job &)
	cout << "After job swapping:" << endl;
	Show(sue);
	Show(sidney);
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

// swaps just the salary and floor fields of a job structure
template <> void Swap<job>(job &j1,job &j2)  //specilizatin
{
	double t1;
	int t2;
	
	t1 = j1.salary;
	j1.salary = j2.salary;
	j2.salary = t1;
	
	t2 = j1.floor;
	j1.floor = j2.floor;
	j2.floor = t2;
}

void Show(job &j)
{
	using namespace std;
	cout << j.name << ": $" << j.salary
	     << " on floor " << j.floor << endl;
}