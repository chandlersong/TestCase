#include <iostream>
#include <string>

#include "gtest/gtest.h"

TEST(STDSTUDY_NUMBER, CONVERT_TO_STRING) {
  int n = 30;
  char c[3];
  sprintf(c, "%X", n);

  /*
  % ӡ���ٷֱȷ��ţ���ת����

  b ����ת�ɶ���λ��

  c ����ת�ɶ�Ӧ�� ASCII ��Ԫ��

  d ����ת��ʮ��λ��

  f ����ȷ������ת�ɸ�������

  o ����ת�ɰ˽�λ��

  s ����ת���ִ���

  x ����ת��Сдʮ����λ��

  X ����ת�ɴ�дʮ����λ
  */
  for (int i = 0; i < 3 ; i ++) {
    std::cout << c[i] << std::endl;
  }

  std::string s(c);
  std::cout << s << std::endl;
}