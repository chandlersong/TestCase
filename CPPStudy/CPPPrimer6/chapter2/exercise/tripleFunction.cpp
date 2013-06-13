/**
Write a C++ program that uses three user-defined functions (counting main()as
one) and produces the following output:
Three blind mice
Three blind mice
See how they run
See how they run
One function,called two times,should produce the first two lines,and the remain-ing function,also called twice,should produce the remaining output.
**/

#include <iostream>

using namespace std;


void printFirstLine();
void printSecondLine();

int main(){
     
    
    printFirstLine();
    printFirstLine();
    
    printSecondLine();
    printSecondLine();
    
    return 0;
}

void printFirstLine(){
    cout << "Three blind mice" << endl;
}

void printSecondLine(){
    cout << "See how they run" << endl;
}