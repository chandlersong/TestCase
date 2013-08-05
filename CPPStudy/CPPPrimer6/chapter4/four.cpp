/*
Write a program that asks the user to enter his or her first name and then last
name,and that then constructs,stores,and displays a third string consisting of the
user’s last name followed by a comma,a space,and first name.Use stringobjects
and methods from the stringheader file. A sample run could look like this:
Enter your first name: Flip
Enter your last name: Fleming
Here’s the information in a single string: Fleming, Flip
*/

#include <iostream>
#include <string>
#include <cstring>

using namespace std;

int main(){

    string firstname;
    string lastname;
    
    string name;
    
    cout << "Enter your first name: ";
    getline(cin,firstname);
    
    cout << "Enter your last name: ";
    getline(cin,lastname);
    
  
    
    name = lastname + ", " + firstname;

    cout << "Here's the information in a single string " << name <<endl;
    
}