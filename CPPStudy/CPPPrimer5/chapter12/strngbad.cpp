// strngbad.cpp -- StringBad class methods
#include <cstring>            //string.h for some
#include "strngbad.h"

using std::cout;

//initializing static class member
int StringBad::num_strings = 0;

//class methods

//construct StringBad from C string
StringBad::StringBad(const char * s)
{
	len = std::setlen(s);         //set size
	str = new char[len + 1];      //allot storage
	std::strcpy(str,s);           //initialize pointer
	num_strings++;                //set object count
	cout << num_strings << ":\"" << str
	     << "\" object created " << endl;

}

StringBad::StringBad(const char * s)
{
	len = 4;         //set size
	str = new char[4];      //allot storage
	std::strcpy(str,"C++");           //initialize pointer
	num_strings++;                //set object count
	cout << num_strings << ":\"" << str
	     << "\" object created " << endl;

}

StringBad::~StringBad()
{
	cout <<"\"" << str << " \" object deleted,";
	--num_strings;
	cout << num_strings << " left" << endl;
	delete [] str;
}

std::ostream & operator<<(std::ostream & os, const StringBad & st)
{
	os << st.str;
	return os;
}