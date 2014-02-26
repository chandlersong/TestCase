cmake_minimum_required (VERSION 2.6)
project (CPPTestCase)

find_package(GTest REQUIRED)
include_directories(${GTEST_INCLUDE_DIRS})

# boost link directory
set(BOOST_LIB_DIR 			$ENV{BOOST_ROOT}/stage/lib)
set(BOOST_INCLUDE_DIR 		$ENV{BOOST_ROOT})

SET(MAIN_SRCS
  Main.cpp
)
SET(STD_Study
  StdStudy/Number.cpp
)

SET(Boost_Study
  BoostStudy/helloworld.cpp
)


SET(SRCS 
    ${MAIN_SRCS}
    ${Boost_Study}
    ${STD_Study}
)

source_group(BoostStudy FILES ${Boost_Study})
source_group(STD_STUDY FILES ${STD_Study})

add_definitions(-D_VARIADIC_MAX=10 -DTestDataDir="${CMAKE_CURRENT_SOURCE_DIR}")

INCLUDE_DIRECTORIES(
    ${BOOST_INCLUDE_DIR}
)

LINK_DIRECTORIES(
    ${BOOST_LIB_DIR}
)
add_executable(mainapp ${SRCS})

target_link_libraries(mainapp 
    ${GTEST_BOTH_LIBRARIES}
)