/*
Write a program that asks the user to enter his or her first name and then last
name,and that then constructs,stores,and displays a third string,consisting of the
user’s last name followed by a comma,a space,and first name.Use chararrays and
functions from the cstringheader file. A sample run could look like this:
Enter your first name: Flip
Enter your last name: Fleming
Here’s the information in a single string: Fleming, Flip
*/

#include <iostream>
#include <string>
#include <cstring>

using namespace std;

int main(){

    char firstname[30];
    char lastname[30];
    
    string name;
    
    cout << "Enter your first name: ";
    cin.getline(firstname,30);
    
    cout << "Enter your last name: ";
    cin.getline(lastname,30);
    
    
    strcat(lastname, ", ");         
    strcat(lastname, firstname);   // append contents of firstname to lastname
    
    name = lastname;

    cout << " Here’s the information in a single string " << name <<endl;
    
}