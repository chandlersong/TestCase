// strtref.cpp -- using structure references
#include <iostream>
using namespace std;
struct sysop
{
    char name[26];
    char quote[64];
    int used;
};

const sysop & use(sysop & sysopref); // function with a reference return type

int main()
{
    //NOTE:some implementations require using the keyword static
    //in the two structure declarations to enable initialization
    sysop looper =
    {
        "Rick \"Fortran\" Looper",
        "I'm a goto kind of guy.",
        0
    };
    
    use(looper);             //looper is type sysop
    cout << "Looper "<<looper.used << " use(s) " << endl;
    sysop copycat;
    copycat = use(looper);
    cout << "Looper: " << looper.used << " use(s)" << endl;
    cout << "Copycat: " << copycat.used << " use(s)" << endl;
    cout << "use(looper): " << use(looper).used << " use(s)" << endl;
    return 0;
}

//use() returns the reference passed to it
const sysop & use(sysop & sysopref)
{
    cout << sysopref.name << " says:" << endl;
    cout << sysopref.quote << endl;
    sysopref.used++;
    return sysopref;
}