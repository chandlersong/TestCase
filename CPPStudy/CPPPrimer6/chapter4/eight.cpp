/*
    Do Programming Exercise 7 but use newto allocate a structure instead of declaring
    a structure variable. Also have the program request the pizza diameter before it
    requests the pizza company name
*/

#include <iostream>

using namespace std;

struct pizza{
    char company_name[30];
    double diameter;
    double weight;
};

int main(){
    
   pizza * mypizza = new pizza;

   cout << "pizze's company name :";
   cin.getline(mypizza->company_name,30);
   
   cout << "pizze's diameter :";
   cin  >> mypizza->diameter;
   
   cout << "pizze's weight :";
   cin  >> mypizza->weight;
   
   cout << "pizze's company name :" << mypizza->company_name << endl;
   
   cout << "pizze's diameter :" << mypizza->diameter << endl ;
   
   cout << "pizze's weight :" << mypizza->weight << endl ;
    
}