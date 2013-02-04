#include <iostream>
#include <climits>

int main(){	
    using namespace std;

    int n_int = INT_MAX;
    short n_short = SHRT_MAX;
    long n_long = LONG_MAX;

    // sizeof operator yields size of type or of variable
    cout << "int is " << sizeof (int) << " bites" << endl;
    cout << "short is " << sizeof n_short << " bites" << endl;
    cout << "long is " << sizeof n_long << " bites" << endl;
    
    cout << "Max values "  << endl;
    cout << "int : "<< n_int << endl;
    cout << "short : "<< n_short << endl;
    cout << "long : "<< n_long << endl;
    
    cout << "min int values "<< INT_MIN << endl;
    cout << "bits per bite "<< CHAR_BIT << endl;
    
    return 0;
}
