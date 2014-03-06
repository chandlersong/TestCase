#ifndef CPPTESTCASE_STDSTUDY_DEFINETEST_H_
#define CPPTESTCASE_STDSTUDY_DEFINETEST_H_



namespace cpp_test_case_stdstudy {

//1） 前置函数的定义
class Example;
class DefineTest {

 public:
  /*
  * 如果在定义函数的这里。exmaple有参数。就会涉及一些莫名其妙的问题。
  * 比如说，Example有一个string的参数，编译能够通过。但是在使用时，传入string，就会
  * 自动转换成Example.这样会有问题。
  */
  void testDefine(const Example& foo);
 private:
  Example* ref;
  Example& point;

  static Example a;
};
}


#endif