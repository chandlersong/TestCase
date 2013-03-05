// swaps.cpp -- swapping with references and with pointers
#include <iostream>
void swapr(int & a, int & b);  //a,b are aliases for ints
void swapp(int * p, int * q);  //p,q are address of ints
void swapv(int a, int b);  //a,b are new variables

int main()
{
    using namespace std;
    int wallet1 = 300;
    int wallet2 = 350;
    
    cout << "wallet1 = $" << wallet1;
    cout << ",wallet2 = $" << wallet2<< endl;
    
    cout << "Using references to swap contents:" << endl;
    swapr(wallet1,wallet2); //pass variables
    cout << "wallet1 = $" << wallet1;
    cout << ",wallet2 = $" << wallet2<< endl;
    
    wallet1 = 500;
    wallet2 = 550;
    cout << endl;
    cout << "wallet1 = $" << wallet1;
    cout << ",wallet2 = $" << wallet2<< endl;
    cout << "Using pointers to swap contents again:" << endl;
    swapp(&wallet1,&wallet2);       //pass addresses of variables
    cout << "wallet1 = $" << wallet1;
    cout << ",wallet2 = $" << wallet2<< endl;
    
     wallet1 = 700;
     wallet2 = 750;
     cout << endl;
     cout << "wallet1 = $" << wallet1;
     cout << ",wallet2 = $" << wallet2<< endl;
     cout << "Trying to use passing by value:\n";
     swapv(wallet1,wallet2);       //pass values of variables
     cout << "wallet1 = $" << wallet1;
     cout << ",wallet2 = $" << wallet2<< endl;
     
     return 0;
}

void swapr(int & a, int & b)  //a,b are aliases for ints
{
    int temp;
    
    temp = a;
    a = b;
    b = temp;
}
void swapp(int * p, int * q)  //p,q are address of ints
{
    int temp;
    temp = *p;
    *p = *q;
    *q = temp;
    
}
void swapv(int a, int b) //a,b are new variables
{
    int temp;
    
    temp = a; //using a,b for values of variables
    a = b;
    b = temp;
}