#include <iostream>
#include <string>

#include "gtest/gtest.h"

TEST(STDSTUDY_NUMBER, CONVERT_TO_STRING) {
  int n = 30;
  char c[3];
  sprintf(c, "%X", n);

  /*
  % 印出百分比符号，不转换。

  b 整数转成二进位。

  c 整数转成对应的 ASCII 字元。

  d 整数转成十进位。

  f 倍精确度数字转成浮点数。

  o 整数转成八进位。

  s 整数转成字串。

  x 整数转成小写十六进位。

  X 整数转成大写十六进位
  */
  for (int i = 0; i < 3 ; i ++) {
    std::cout << c[i] << std::endl;
  }

  std::string s(c);
  std::cout << s << std::endl;
}