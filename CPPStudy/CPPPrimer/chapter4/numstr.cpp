// numstr.cpp -- following number input with line input
#include <iostream>

int main()
{
    using namespace std;
    cout << "What year was your house build? " << endl;
    
    int year;
    (cin >> year).get();
    cout << "what is its street address?" << endl;
    
    char address[80];
    cin.getline(address, 80);
    cout << "Year build: " << year << endl;
    cout << "Address:"     << address << endl;
    cout << "Done!" << endl;
    
    return 0;

}