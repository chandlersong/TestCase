/*
Write a program that asks the user to enter up to 10 golf scores,which are to be
stored in an array.You should provide a means for the user to terminate input prior
to entering 10 scores.The program should display all the scores on one line and
report the average score.Handle input,display,and the average calculation with
three separate array-processing functions.
*/

#include <iostream>

using namespace std;

double* InputScore();
void displayScore(double* score_list);

int main(){
    
    double* score_list = InputScore();
    
    displayScore(score_list);
    
    
    delete score_list;
    return 0;
}

double* InputScore(){

    cout << "Please input golf scores" << endl;
    
    int times = 10;
    double* result = new double[10];
    
    for(int i =0; i < times ; i ++){
         cout <<  i << " :";
         cin  >> result[i];  
    }
    
    return result;  
}

void displayScore(double* score_list){
    
    double total = 0;
    
    int times = 10;
    for(int i =0; i < times ; i ++){
         cout <<  i << " :" << score_list[i] << endl;
         total = total +  score_list[i];
    }
    
    double mean = total/ 10;
    
    cout << "mean:" << mean;
}

