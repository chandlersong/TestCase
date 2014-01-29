cmake_minimum_required (VERSION 2.6)
project (CPPTestCase)

find_package(GTest REQUIRED)
include_directories(${GTEST_INCLUDE_DIRS})

SET(MAIN_SRCS
  Main.cpp
)

SET(SRCS 
    ${MAIN_SRCS}
)

add_executable(mainapp ${SRCS})

target_link_libraries(mainapp 
    ${GTEST_BOTH_LIBRARIES}
)