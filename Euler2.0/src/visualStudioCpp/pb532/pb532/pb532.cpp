// pb532.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <ql/quantlib.hpp>
using namespace QuantLib;

#include "pb532.h"

int _tmain(int argc, _TCHAR* argv[])
{
	try{
		pb532();
		} catch (std::exception& e) {
        std::cout << e.what() << std::endl;
		    return 1;
    } catch (...) {
        std::cout << "unknown error" << std::endl;
	       return 1;
    }
	return 0;
}

