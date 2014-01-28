// A simple program that computes the square root of a number
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <iostream>
#include "CMakeStudyConfig.h"

#ifdef USE_MYMATH
#include "MathFunctions.h"
#endif

int main() {

  std::cout << "CMakeStudy_VERSION_MAJOR:" << CMakeStudy_VERSION_MAJOR << std::endl;
  std::cout << "CMakeStudy_VERSION_MINOR:" << CMakeStudy_VERSION_MINOR << std::endl;

  double inputValue = 2;
  // USE_MYMATH will be define at cmake file. i don't do it manually
  #ifdef USE_MYMATH
  double outputValue = mysqrt(inputValue);
  #else
  double outputValue = sqrt(inputValue);
  #endif

  fprintf(stdout, "The square root of %g is %g\n",
          inputValue, outputValue);
  return 0;
}