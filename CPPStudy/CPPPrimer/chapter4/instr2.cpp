// instr2.cpp -- reading more than one word with getline
#include <iostream>

int main()
{
    using namespace std;
    const int ArSize = 20;
    
    char name[ArSize];
    char dessert[ArSize];
    
    cout << "Enter your name : " << endl;
    cin.getline(name,ArSize);
    cout << "Enter your favorite dessert:\n";
    cin.getline(name,ArSize);
    cout << "Enter you favorite dessert :" << endl;
    cout << "I have some delicious " << dessert;
    cout << " for you. " << name << "\n";
    
    return 0;
}