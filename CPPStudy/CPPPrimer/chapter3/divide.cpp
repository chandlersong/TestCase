// divide.cpp -- integer and floating-point division
#include <iostream>
int main(){
    using namespace std;
    
    cout.setf(ios_base::fixed, ios_base::floatfield);
    cout << "Interger division: 9/5 = " << 9 / 5 << endl;
    cout << "Floating point division: 9.0/5.0 = " << 9.0 / 5.0 <<endl;
    cout << "mixed division: 9.0/5 " << 9.0 / 5 << endl;
    cout << "double constrants: 1e7 /9.0 " << 1.e7/ 9.0 << endl;
    cout << "float constrants: 1e7 /9.0 " << 1.e7/ 9.0f << endl;

    return 0;
}