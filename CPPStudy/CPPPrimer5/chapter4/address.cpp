// address.cpp -- using the & operator to find addresses
#include <iostream>

int main()
{
    using namespace std;
    
    int donuts = 6;
    double cups = 4.5;
    
    unsigned int unsigned_donuts = 6;
    cout << "donuts value = " << donuts;
    cout << " and donuts address = " << &donuts << endl;
   
    
    cout << "unsigned_donuts value = " << unsigned_donuts;
    cout << " and donuts address = " << &unsigned_donuts << endl;
    
    // NOTE: you may need to use unsigned (&donuts)
    // and unsigned (&cups)
    cout << "cups value = " << cups;
    cout << " and cups address = " << &cups << endl;
    
}