cmake_minimum_required (VERSION 2.6)
project (CMakeStudy)

# The version number.
set (CMakeStudy_VERSION_MAJOR 1)
set (CMakeStudy_VERSION_MINOR 0)


# configure a header file to pass some of the CMake settings
# to the source code
# the cofigure file can be used as coppy file, and you can use your own paramteres changed.
configure_file (
  "${PROJECT_SOURCE_DIR}/CMakeStudyConfig.h.in"
  "${PROJECT_BINARY_DIR}/CMakeStudyConfig.h"
)

# should we use our own math functions?
option (USE_MYMATH "Use tutorial provided math implementation" ON)

if (USE_MYMATH)
  include_directories ("${PROJECT_SOURCE_DIR}/MathFunctions")
  add_subdirectory (MathFunctions)
  set (EXTRA_LIBS ${EXTRA_LIBS} MathFunctions)
endif (USE_MYMATH)
  
# add the binary tree to the search path for include files
# so that we will find cppTestCase.h
include_directories("${PROJECT_BINARY_DIR}")

# add the executable
add_executable(CMakeStudy CMakeStudy.cxx)
target_link_libraries (CMakeStudy  ${EXTRA_LIBS})