// addpntrs.cpp -- pointer addition
#include <iostream>

int main()
{
    using namespace std;
    double wages[3]  = {10000.0,20000.0,30000.0};
    short  stacks[3] = {3,2,1};
    
    //here are two ways to get the address of an array
    double * pw = wages;      //name of an array = address
    short  * ps = &stacks[0]; //or use address operator
    
    //with array element 
    cout << "pw = "  << pw << ", *pw = " << *pw << endl;
    pw = pw + 1;
    cout << "add 1 to the pw pointer: " << endl;
    cout << "pw = "  << pw << ", *pw = " << *pw << endl << endl;
    
    cout << "ps = "  << ps << ", *ps = " << *ps << endl;
    ps = ps + 1;
    cout << "add 1 to the ps pointer: " << endl;
    cout << "ps = "  << ps << ", *ps = " << *ps << endl << endl;
    
    cout << "access two elements with array notation " << endl;
    cout << "stacks[0] = " << stacks[0]
         << ", stacks[1] " << stacks[1] << endl;
      
    cout << "access two elements with pointer notation " << endl;
    cout << "*stacks = " << *stacks
         << ",*(stacks + 1 )" << *(stacks + 1 ) << endl;
         
    cout << sizeof(wages) << " = size of wages arrage " << endl;
    cout << sizeof(pw)  << " = size of pw pointer " << endl;
    
    return 0;
}