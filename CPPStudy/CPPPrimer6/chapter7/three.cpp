/*
Here is a structure declaration:
struct box
    {
        char maker[40];
        float height;
        float width;
        float length;
        float volume;
    };
a. Write a function that passes a boxstructure by value and that displays the
value of each member.
b. Write a function that passes the address of a boxstructure and that sets the
volumemember to the product of the other three dimensions.
c. Write a simple program that uses these two functions
*/

#include <iostream>

using namespace std;

struct Box
{
    char maker[40];
    float height;
    float width;
    float length;
    float volume;
};

void DisplayBox(Box box);
void CalcualteVolume(Box* box);

int main(){
    
    Box box = {
        "abc",
        1,
        2,
        3,
        0   
    };
    
    CalcualteVolume(&box);
    DisplayBox(box);
    return 0;
}

void DisplayBox(Box box){
    cout << "Box maker:" << box.maker << endl;
    cout << "Box height:" << box.height << endl;
    cout << "Box width:" << box.width << endl;
    cout << "Box length:" << box.length << endl;
    cout << "Box volume:" << box.volume << endl;
}


void CalcualteVolume(Box* box){

    box->volume = box->height * box->width * box->length;
}


