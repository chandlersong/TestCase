// calling.cpp -- defining, prototyping, and calling a function
#include <iostream>

void simple();   //function prototype

int main()
{
    using namespace std;
    cout << "main() will cal the simple() function:" << endl;
    simple();     //function call
    return 0 ;

}

//function definition
void simple()
{
    using namespace std;
    cout << "I'm but a simple function." << endl;
}