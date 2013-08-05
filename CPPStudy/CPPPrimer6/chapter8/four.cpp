#include <iostream>
#include <cstring> // for strlen(), strcpy()
#include <string>
 
using namespace std;

struct stringy {
    char * str; // points to a string
    int ct; // length of string (not counting '\0')
};



// prototypes for set(), show(), and show() go here
void set(stringy& str, char* origin);
void show(const stringy& str, int n = 1);
void show(const string str, int n  = 1);

int main(){
    stringy beany;
    char testing[] = "Reality isn't what it used to be.";
    set(beany, testing); // first argument is a reference,
    // allocates space to hold copy of testing,
    // sets str member of beany to point to the
    // new block, copies testing to new block,
    // and sets ct member of beany
    show(beany); // prints member string once
    show(beany, 2); //prints member string twice
    testing[0] = 'D';
    testing[1] = 'u';
    show(testing); // prints testing string once
    show(testing, 3); // prints testing string thrice
    show("Done!");
    
    delete beany.str;  //should delete the value, even it's container is auto
    return 0;
}

void set(stringy& str, char* origin){
    str.ct = strlen(origin);
    str.str = new char[str.ct + 1]; //+1 for '\0' end of string char
    strcpy(str.str,origin);

}

void show(const stringy& str, int n){
    for(int i = 0 ; i < n ; i ++){
        cout << "print stringy:" << endl;
        cout << "stringy's str:" << str.str<< endl;
        cout << "stringy's ct:" << str.ct<< endl;
    }
}


void show(const string str, int n){
    for(int i = 0 ; i < n ; i ++){
       cout << "print string:" << endl;
       cout << str << endl;
   }    
}