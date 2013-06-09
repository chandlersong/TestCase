// twoarg.cpp -- a function with 2 arguments
#include <iostream>
using namespace std;
void n_char(char,int);
int main()
{
    int times;
    char ch;
    
    cout << "Enter a character: ";
    cin  >> ch;
    
    while(ch != 'q')    // q to quit
    {
        cout << "Enter an interger: ";
        cin  >> times;
        n_char(ch,times); //function with two arguments
        cout << "\nEnter another character or press the q-key to quit: ";
        cin  >> ch;
    }
    cout << "The value of times is " << times << ".\n";
    cout << "Bye\n";
    return 0;
}

void n_char(char c, int n) //displays c n times
{
    while(n-- > 0)               //continue until n reaches 0
    {
        cout << c;
    }
}