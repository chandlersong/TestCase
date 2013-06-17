/*
Write a C++ program that requests and displays information as shown in the fol-lowing example of output:
What is your first name? Betty Sue
What is your last name? Yewe
What letter grade do you deserve? B
What is your age? 22
Name: Yewe, Betty Sue
Grade: C
Age: 22
*/

#include <iostream>

using namespace std;

int main(){

    char first_name[30];
    char last_name[30];
    char grade[2];
    char age[10];
    
    cout << "What is your first name?";
    cin.getline(first_name,30);
    
    cout << "What is your last name?";
    cin.getline(last_name,30);
    
    cout << "What letter grade do you deserve?";
    cin.getline(grade,30);
    
    cout << "What is your age?";
    cin.getline(age,30);
    
    cout << "Name : "<<last_name<<" , "<< first_name << endl;
    cout << "Grade : " << grade << endl;
    cout << "Age : " << age << endl;
    
    return 0;
}