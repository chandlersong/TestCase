#include <iostream>
using namespace std;

template <typename T>
void max5(T* array);


int main(){

    int int_array[5] = {1,2,3,4,5};
    cout << "int array" << endl;
    max5(int_array);
    
    double double_array[5] = {1.0,2.0,3.0,4.0,5.0};
    cout << "double array" << endl;
    max5(double_array);
    
    
    return 0;
}

template <typename T>
void max5(T* array){
     T max;
     max = array[0];
     for(int i = 0; i < 5 ; i ++){
        if(max < array[i]){
            max = array[i];
        }
     }
     
     cout << "max five is : " << max << endl;
}
