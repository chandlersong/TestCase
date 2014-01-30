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

SET(Boost_Study
  BoostStudy/helloworld.cpp
)


SET(SRCS 
    ${MAIN_SRCS}
    ${Boost_Study}
)

source_group(BoostStudy FILES ${Boost_Study})

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