/*
Write a C++ program that asks for a distance in furlongs and converts it to yards.
(One furlong is 220 yards.)
*/
#include <iostream>

using namespace std;

int main(){

    long furlong;
    long yard;
    
    cout << "Please input furlong you want to convert:" << endl;
    cin  >> furlong;
    cout << endl;
    yard = furlong * 220;
    
    cout << yard << " yards:";

    return 0;
}