/*
Writea short program that asks for your height in integer inches and then converts
your height to feet and inches.Have the program use the underscore character to
indicate where to type the response. Also use a constsymbolic constant to repre-sent the conversion factor.
*/

#include <iostream>

using namespace std;

int main(){

    const int inch_per_feet = 12;
    
    int input_inches;
    int inches;
    int feet;
    
    cout << "please input you feets:";
    cin  >> input_inches;
    
    feet = input_inches / inch_per_feet;
    inches = input_inches % inch_per_feet;
    
    cout << " you are " << feet << " feet and " << inches << " inches.";
    
    return 0;
}