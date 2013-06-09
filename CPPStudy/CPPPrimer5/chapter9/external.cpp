// external.cpp -- external variables
#include <iostream>
using namespace std;
//external variable
double warming = 0.3;

//function prototypes
void update(double dt);
void local();

int main()            //uses global variable
{
	cout << "Global warming is " << warming << " degress." << endl;
	update(0.1);                   // call function to change warming
	cout << "Global warming is " << warming << " degress." << endl;
	local();				      // call function with local warming
	cout << "Global warming is " << warming << " degress." << endl; 
	
	return 0;
}

void update(double dt)          //modifies global variable
{
	extern double warming;         //optional redeclaration
	warming += dt;
	cout << "Updating global warming to " << warming;
	cout << " degrees." << endl;
}

void local()  //uses local variable
{
	double warming = 0.8;      // new variable hides external one
	
	cout << "Local warming is " << warming << " degress." << endl; 
	// Access global variable with the scope resolution operator
	cout << "But global warming = " << ::warming;
	cout << " degrees.\n";
}