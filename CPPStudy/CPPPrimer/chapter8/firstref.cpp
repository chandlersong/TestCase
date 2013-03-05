// firstref.cpp -- defining and using a reference
#include <iostream>
int main()
{
    using namespace std;
    int rats = 101;
    int & rodents = rats;   //rodents is a reference
    int newRats = rats;
    
    
    cout << "rats = " << rats;
    cout << ",rodents = " << rodents;
    cout << ",newRats = " << newRats << endl;
    rodents++;
    cout << "rats = " << rats;
    cout << ", rodents = " << rodents;
    cout << ",newRats = " << newRats << endl;
    
    //some implementations require type casting the following addressed to type unsigned
    cout << "rats address = " << &rats;
    cout << ", rodents address = " << &rodents;
    cout << ",newRats address= " << &newRats << endl;
    return 0;

}