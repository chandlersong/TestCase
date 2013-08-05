#include <iostream>
 
void printscr (char * mystring, int n = 0);
 
int main()
{
    using namespace std;
     
    char myname[] = "John Doe";
     
    printscr(myname);
    printscr(myname,5);
    printscr(myname);
    printscr(myname, -2);
     
    cin.get();
    cin.get();
    return 0;
}
 
void printscr (char * mystring, int n)
{
    static int called = 0;               //this is the point. when you define a static in method, it won't be destory after program exits
    if (n)
        for (int i = 0; i < called; i++)
            std::cout << mystring << std::endl;
    else
        std::cout << mystring << std::endl;
    called++;
}


