#ifndef CPPTESTCASE_STDSTUDY_DEFINETEST_H_
#define CPPTESTCASE_STDSTUDY_DEFINETEST_H_



namespace cpp_test_case_stdstudy {

//1�� ǰ�ú����Ķ���
class Example;
class DefineTest {

 public:
  /*
  * ����ڶ��庯�������exmaple�в������ͻ��漰һЩĪ����������⡣
  * ����˵��Example��һ��string�Ĳ����������ܹ�ͨ����������ʹ��ʱ������string���ͻ�
  * �Զ�ת����Example.�����������⡣
  */
  void testDefine(const Example& foo);
 private:
  Example* ref;
  Example& point;

  static Example a;
};
}


#endif