/*
The CandyBar structure contains three members.The first member holds the brand name of a candy bar.
The second member holds the weight (which may have a frac-tional part) of the candy bar,and the third member holds the number of calories
(an integer value) in the candy bar.Write a program that declares such a structure and creates a CandyBarvariable 
called snack,initializing its members to "Mocha Munch",2.3,and 350,respectively.The initialization should be part 
of the declara-tion for snack.Finally,the program should display the contents of the snackvari-able.
*/

#include <iostream>
#include <string>

using namespace std;

struct CandyBar{
    string name;
    double weight;
    int  calories;
};

int main(){
   
    CandyBar snack = {
        "ocha Munch",
        2.3,
        350,  
    };
    
    cout << "snack's name :" << snack.name << endl;
    cout << "snack's weight :" << snack.weight << endl;
    cout << "snack's calories :" << snack.calories << endl;
    return 0;
}