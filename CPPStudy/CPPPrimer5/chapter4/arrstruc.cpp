// arrstruc.cpp -- an array of structures
#include <iostream>
struct inflatable
{
    char name[20];
    float volume;
    double price;
};

int main()
{
    using namespace std;
    inflatable guest[2] =                // initializing an array of structs
    {
        {"Bambi",0.5,21.99},             // first structure in array
        {"Godzilla", 2000,565.99}        // next structure in array      
    };
    
    cout << "The guest " << guest[0].name << " and " << guest[1].name
         << "\nhave a combine volume of "
         << guest[0].volume + guests[1].volume << " cubic feet." << endl;
         
    return 0;

}

