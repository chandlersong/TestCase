/*
Write a C++ program that requests and displays information as shown in the fol-lowing example of output:
What is your first name? Betty Sue
What is your last name? Yewe
What letter grade do you deserve? B
What is your age? 22
Name: Yewe, Betty Sue
Grade: C
Age: 22

2. Rewrite Listing 4.4,using the C++ stringclass instead of chararrays.
*/

#include <iostream>
#include <string>
#include <cstring> // C-style string library

using namespace std;

int main(){

    string first_name;
    string last_name;
    string grade;
    string age;
    
    cout << "What is your first name?";
    getline(cin,first_name);
    
    cout << "What is your last name?";
    cin  >> last_name;
    
    cout << "What letter grade do you deserve?";
    cin  >> grade;
    
    cout << "What is your age?";
    cin  >> age;
    
    cout << "Name : "<<last_name<<" , "<< first_name << endl;
    cout << "Grade : " << grade << endl;
    cout << "Age : " << age << endl;
    
    return 0;
}