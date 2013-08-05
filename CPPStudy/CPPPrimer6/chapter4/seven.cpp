/*
    William Wingate runs a pizza-analysis service.For each pizza,he needs to record
    the following information:
    n The name of the pizza company,which can consist of more than one word
    n The diameter of the pizza
    n The weight of the pizza
    Devise a structure that can hold this information and write a program that uses a
    structure variable of that type.The program should ask the user to enter each of the
    preceding items of information,and then the program should display that informa-tion.Use cin(or its methods) and cout.
*/

#include <iostream>

using namespace std;

struct pizza{
    char company_name[30];
    double diameter;
    double weight;
};

int main(){
    
   pizza mypizza;

   cout << "pizze's company name :";
   cin.getline(mypizza.company_name,30);
   
   cout << "pizze's diameter :";
   cin  >> mypizza.diameter;
   
   cout << "pizze's weight :";
   cin  >> mypizza.weight;
   
   cout << "pizze's company name :" << mypizza.company_name << endl;
   
   cout << "pizze's diameter :" << mypizza.diameter << endl ;
   
   cout << "pizze's weight :" << mypizza.weight << endl ;
    
   delete mypizza;
   
   return 0;
}