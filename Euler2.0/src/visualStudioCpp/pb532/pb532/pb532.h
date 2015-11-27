static const int n=827;

#include <boost/function.hpp>
#include <boost/math/distributions.hpp>


Real nanobotLength(const Real& x){
	Real g = std::sin(2*M_PI/n)/(std::cos(2*M_PI/n)-1)/std::cos(x);
	return std::sqrt(1.0+g*g);
}


void pb532(){
	Real absAcc=0.0000001;
	Size maxEval=1000;

	boost::function<Real (Real)> ptrF(nanobotLength);
	Real a=0.0, b=std::asin(0.999); 

	TrapezoidIntegral				numInt1(absAcc, TrapezoidIntegral::Method::MidPoint, maxEval);
	SimpsonIntegral					numInt2(absAcc, maxEval);

	std::cout.precision(17);

	std::cout << "Total length: "	<< n * numInt2(ptrF,a,b)<< std::endl;

}