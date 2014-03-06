#ifndef CPPTESTCASE_STDSTUDY_DEFINETEST_H_
#define CPPTESTCASE_STDSTUDY_DEFINETEST_H_



namespace cpp_test_case_stdstudy {

class Example;
class DefineTest {

 public:
  /*
  *
  */
  void testDefine(const Example& foo);
 private:
  Example* ref;
  Example& point;

  static Example a;
};
}


#endif