#include <iostream>

using namespace std;


int* GenerateArray();

int main(){

	int* int_array = GenerateArray();

	cout << endl;
	int stop;
	cin >> stop;
	return 0;
}

int* GenerateArray(){
	int return_array[100];
	
	int temp;
	for(int i = 0;i<100 ; i ++){
		temp = rand();
		cout << temp << endl;
		return_array[i] = temp;
	}

	return &return_array[0];
}