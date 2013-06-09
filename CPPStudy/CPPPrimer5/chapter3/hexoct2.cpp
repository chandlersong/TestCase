#include <iostream>

int main()
{
    using namespace std;
    
    int chest = 42;     // decimal integer constant
    int waist = 42;   // hexadecimal integer constant
    int inseam = 42;   // octal integer constant
    
    cout << "Moniseur cuts a striking figure!" << endl;
    cout << "chest = " << chest << "(decimal)" <<endl;
    cout << hex;       // manipulator for changing number base
    cout << "waist = " << waist << "(hexadecimal) "<<endl;
    cout << oct;       // manipulator for changing number base 
    cout << "inseam = " << inseam << "(octal)"<<endl;
    
    return 0;

}