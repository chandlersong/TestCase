#include <iostream>
#include <utility>

using namespace std;


int* GenerateArray();
void QuickSort(int* int_array,int start, int end);
void VerifyResult(int* int_array,bool display);
int Partition(int* int_array,int start, int end);
void PrintArray(int* int_array);

const int array_size = 100;

int main(){
	cout << "origin array" << endl;
	int* int_array = GenerateArray();
	cout << "sort array" << endl;
	QuickSort(int_array,0,array_size-1);
	VerifyResult(int_array,true);
	delete int_array;
	return 0;
}

int* GenerateArray(){
	int* return_array = new int[array_size];
	
	int temp;
	for(int i = 0;i<array_size ; i ++){
		temp = rand();
		cout << temp << endl;
		return_array[i] = temp;
	}

	return return_array;
}

void QuickSort(int* int_array,int start, int end){
	if(start < end){
		int pivot_idx = Partition(int_array,start,end);
		QuickSort(int_array,pivot_idx + 1,end);
		QuickSort(int_array,start,pivot_idx - 1);
	}
}

int Partition(int* int_array,int start, int end){
	int pivot_idx = (end - start) / 2 + start;
	int pivot = int_array[pivot_idx];
	swap(int_array[pivot_idx],int_array[end]); // move pivot to the end of partition

	pivot_idx = start;
	for(int i = start; i < end; i ++){

		if(int_array[i] < pivot){
			swap(int_array[pivot_idx],int_array[i]);  //move the value which is smaller than pivot to the left 
			pivot_idx ++;                             //move the index to next level,to make sure the value point is bigger than piovt
		}
	}

	swap(int_array[pivot_idx],int_array[end]);
	return pivot_idx;
}

void VerifyResult(int* int_array,bool display){
	int length = array_size - 1 ;

	bool sort_success = true;
	for(int i = 0; i < length; i ++){
		
		if(int_array[i] > int_array[i+1]){
			sort_success = false;
			
		}

		if(display){
			cout << int_array[i] << endl;
		}
	}

	if(!sort_success){
		cout << "sort failed";
	}else{
		cout << "sort success";
	}
}

int arrayLength(int* int_array){
	return sizeof(int_array)/sizeof(int);
}

void PrintArray(int* int_array){
	cout  << "print array during sorting" << endl;
	for(int i = 0; i < array_size ; i ++){
		cout << int_array[i] <<endl;
	}
}
