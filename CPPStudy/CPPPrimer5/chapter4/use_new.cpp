// use_new.cpp -- using the new operator
#include <iostream>

int main()
{

    using namespace std;
    int * pt = new int; // allocate space for an int
    *pt = 1001;         // store a value there
    
    cout << "int ";
    cout << "value = " << *pt << ": location = " << pt << endl;
    
    double * pd = new double; // allocate space for a double
    *pd = 10000001.0;          // store a double there

    cout << "double ";
    cout << "value = " << *pd << ": location = " << pd << endl;
    cout << "size of *pt = " << sizeof(pt);
    cout << ": size of *pt = " << sizeof(*pt) << endl;
    cout << "size of pd = " << sizeof pd;
    cout << ":size of *pd = " << sizeof(*pd) << endl;
    
    /*
    can't work
    double * ptest;
    *ptest =  111.22;
    cout << "size of ptest = " << sizeof ptest;
    cout << ":size of *ptest = " << sizeof(*ptest) << endl;
    
    cout << "value of ptest = " <<  ptest;
    cout << ":value of *ptest = " << *ptest << endl;
    */
    return 0;
    

}