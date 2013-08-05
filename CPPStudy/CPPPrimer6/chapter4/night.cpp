/*
Do Programming Exercise 6,but instead of declaring an array of three CandyBar
structures,use newto allocate the array dynamically.
*/

#include <iostream>

using namespace std;

struct CandyBar{

    CandyBar(){};
    CandyBar(string name_,double weight_,int  calories_){
        name = name_;
        weight = weight_;
        calories = calories_;
    };
    string name;
    double weight;
    int  calories;
    
};

int main(){

    CandyBar*  snack =  new CandyBar[2];
   
     snack[0] = CandyBar("1",3.2,320);
     snack[1] = CandyBar("2",3.2,320);
    
    cout << "snack1's name :" << snack[1].name << endl;
    cout << "snack1's weight :" << snack[1].weight << endl;
    cout << "snack1's calories :" << snack[1].calories << endl;
    
    
    cout << "snack1's name :" << snack->name << endl;
    cout << "snack1's weight :" << snack->weight << endl;
    cout << "snack1's calories :" << snack->calories << endl;
    
    snack = snack + 1;
    cout << "snack2's name :" << snack->name << endl;
    cout << "snack2's weight :" << snack->weight << endl;
    cout << "snack2's calories :" << snack->calories << endl;
    
    delete []snack;
    return 0;
}