#include <iostream>
#include <cctype>
#include <cstring>

using namespace std;

void toUpperCase(char * input);

int main(){
    
    char* input;
    
    while(true){
        cout << "Enter a string (q to quit):";
        cin.getline(input,30);
    
        if(*input == 'q' && strlen(input) == 1){
            cout << "Bye.";
            break;
        }else{
            toUpperCase(input);
        }
    }
    return 0;
}

void toUpperCase(char * input){
    int i = 0;
    while(input[i] != '\0'){
        //cout << *input << endl;
        input[i] = toupper(input[i]);
        i++;
    }

    cout << input << endl;
}