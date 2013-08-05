/*
The CandyBarstructure contains three members,as described in Programming
Exercise 5.Write a program that creates an array of three CandyBarstructures,
ini-tializes them to values of your choice,and then displays the contents of each struc-ture.
*/

#include <iostream>

using namespace std;

struct CandyBar{
    string name;
    double weight;
    int  calories;
};

int main(){

    CandyBar snack[2] = {
                            {
                                "ocha Munch",
                                2.3,
                                350,  
                            },
                            {
                                "aaaaaaa",
                                3.2,
                                530,
                            }
                         };
    
    cout << "snack1's name :" << snack[0].name << endl;
    cout << "snack1's weight :" << snack[0].weight << endl;
    cout << "snack1's calories :" << snack[0].calories << endl;
    
    
    cout << "snack2's name :" << snack[1].name << endl;
    cout << "snack2's weight :" << snack[1].weight << endl;
    cout << "snack2's calories :" << snack[1].calories << endl;
    return 0;
}