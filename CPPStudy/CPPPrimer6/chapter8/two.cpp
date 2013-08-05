#include <iostream>

using namespace std;

struct CandyBar {
    char* brand_name;
    double  weight;
    int     calories;
};

CandyBar&  setCandyBarValue(CandyBar & candy_bar,char * brand_name = "Millennium Munch",double weight = 2.85, int calories = 350 ); 
void displayCandyBar(const CandyBar & candy_bar);


int main(){

    CandyBar candy_bar;
    char  brand_name[30] = "aaaaaaa";
    candy_bar = setCandyBarValue(candy_bar,brand_name,220.0,3);
    displayCandyBar(candy_bar);
    
    return 0;
}

CandyBar&  setCandyBarValue(CandyBar & candy_bar,char *  brand_name, double weight, int calories){

    candy_bar.brand_name = brand_name;
    candy_bar.weight = weight;
    candy_bar.calories = calories;
    
    return candy_bar;
}


void displayCandyBar(const CandyBar & candy_bar){
    cout << "CandyBar's brand_name:"  << candy_bar.brand_name<< endl;
    cout << "CandyBar's weight:"  << candy_bar.weight<< endl;
    cout << "CandyBar's calories:"  << candy_bar.calories<< endl;

}
