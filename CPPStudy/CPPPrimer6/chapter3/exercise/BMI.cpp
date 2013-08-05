/*
Write a short program that asks for your height in feet and inches and your weight
in pounds.(Use three variables to store the information.) Have the program report
your body mass index (BMI).To calculate the BMI,first convert your height in feet
and inches to your height in inches (1 foot = 12 inches).Then convert your height
in inches to your height in meters by multiplying by 0.0254.Then convert your
weight in pounds into your mass in kilograms by dividing by 2.2.Finally,compute
your BMI by dividing your mass in kilograms by the square ofyour height in
meters.Use symbolic constants to represent the various conversion factors.
*/

#include <iostream>

using namespace std;

int main(){
  
    const int inches_per_feed = 12;
    const double inches_to_meters = 0.0254;
    const double pound_to_kilograms = 2.2;
    
    
    double _feet;
    double _inches;
    double _pound;
    
    
    double meters;
    double kilograms;

    double BMI;
    
    cout << "your feet: ";
    cin  >> _feet;
    cout << "your inches: ";
    cin  >> _inches;
    cout << "your pound: ";
    cin  >> _pound;
    

    meters = (_feet * inches_per_feed + _inches) * inches_to_meters;
    cout << "meters:" << meters << endl;
    kilograms = _pound / pound_to_kilograms;
    cout << "kilograms:" << kilograms << endl;;
    
    BMI = kilograms /(meters * meters);
    
    cout << "BMI:" << BMI;
     
    return 0;
}