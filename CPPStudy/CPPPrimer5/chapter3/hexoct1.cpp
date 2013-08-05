#include <iostream>

int main()
{
    using namespace std;
    
    int chest = 42;     // decimal integer constant
    int waist = 0x42;   // hexadecimal integer constant
    int inseam = 042;   // octal integer constant
    
    cout << "Moniseur cuts a striking figure!" << endl;
    cout << "chest = " << chest << endl;
    cout << "waist = " << waist << endl;
    cout << "inseam = " << inseam << endl;
    
    return 0;

}