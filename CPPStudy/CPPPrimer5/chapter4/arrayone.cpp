// arrayone.cpp -- small arrays of integers
#include <iostream>

int main()
{
    using namespace std;
    int yam[3];
    yam[0] = 7;         // creates array with three elements
    yam[1] = 8;         // assign value to first element
    yam[2] = 6;
    
    int yamcosts[3] = {20,30,5};
    // NOTE: If your C++ compiler or translator can¡¯t initialize
    // this array, use static int yamcosts[3] instead of
    // int yamcosts[3]
    
    cout << "Total yams = ";
    cout << yam[0] + yam[1] + yam [2] << endl;
    cout << "The package with " << yam[1] << "yams costs " ;
    cout << yamcosts[1] << " cents per yam."<< endl;
    
    int total = yam[0]*yamcosts[0] + yam[1]*yamcosts[1] + yam[2]*yamcosts[2];
    cout << "The total yam expense is " << total << " cents " << endl << endl;
    
    cout << " Size of yams array = " << sizeof yam << " bytes." << endl;
    cout << " Size of one element = " << sizeof yam[0] << " bytes." << endl;
    
    return 0;
}