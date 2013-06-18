/*
Write a program that repeatedly asks the user to enter pairs of numbers until at
least one of the pair is 0.For each pair,the program should use a function
 to calcu-late the harmonic mean of the numbers.The function should return the answer to
main(),which should report the result.The harmonic mean of the numbers is the
inverse of the average of the inverses and can be calculated as follows:
harmonic mean = 2.0 ¡Áx¡Áy / (x + y)
*/

#include <iostream>

using namespace std;

double CalcualteMean(double x, double y);

int main(){

    double mean = -1;
    
    double x;
    double y;
    
    while(mean != 0 && mean == mean){
        
        cout << "Please input x :";
        cin  >> x;
        
        cout << "Please input y :";
        cin  >> y;
        
        mean = CalcualteMean(x,y);
        
        cout << "harmonic mean :" << mean << endl;
        
        if(mean == 0 || mean != mean){
           cout << "programe quit" << endl;
        }
    }
    return 0;
}

double CalcualteMean(double x, double y){

    double result = 2.0 * x  * y;
    result = result /(x + y);
    
    return result;
}

