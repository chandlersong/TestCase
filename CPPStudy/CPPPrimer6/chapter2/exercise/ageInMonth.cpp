/*
Write a program that asks the user to enter his or her age.The program then should
display the age in months:
Enter your age: 29
Your age in months is 384.
*/

#include <iostream>

using namespace std;

int main(){

    int ageInYear;
    int ageInMonth;
    
    cout << "enter your age:";
    cin  >> ageInYear;
    
    ageInMonth = ageInYear * 12;
    
    cout << "your age in months is " << ageInMonth;
    
    
}