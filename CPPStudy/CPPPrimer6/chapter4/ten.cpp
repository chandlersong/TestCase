/*
10. Write a program that requests the user to enter three times for the 40-yd dash (or
40-meter,if you prefer) and then displays the times and the average.Use an array
object to hold the data.(Use a built-in array if arrayis not available.)
*/

#include <iostream>

using namespace std;

int main(){

     double* performance =  new double[3];
       
    cout << "1st performance :" ;
    cin  >> performance[0];
    
    cout << "2nd performance :" ;
    cin  >> performance[1];
    
    cout << "3rd performance :" ;
    cin  >> performance[2];
    
    
    
    double average = (performance[0] + performance[1] +performance[2] ) / 3 ;
    
    cout <<  average;
    delete []performance;
    return 0;
}