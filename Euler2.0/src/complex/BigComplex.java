package complex;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BigComplex{
	
	private BigDecimal real;
	private BigDecimal imag;
	private int scale = 32;
	private RoundingMode rnd = RoundingMode.FLOOR;
	private double epsilon = 1e-16;
	private static final BigDecimal TWO = new BigDecimal("2");
	private final static BigDecimal ZERO = BigDecimal.ZERO;
	private final static BigDecimal ONE = BigDecimal.ONE;
	private static final MathContext ctx = new MathContext(128);
	
	public int compareTo(BigComplex c, double precision)
	{
		double d = this.subtract(c).abs().doubleValue();
		return (d<precision?0:((Double)this.abs().doubleValue()>(Double)c.abs().doubleValue())?1:-1);
	}
	
	public BigComplex(BigDecimal r_) 
	{
		real = r_;
		imag = ZERO;
	}
		
	public BigComplex(BigDecimal r_,BigDecimal i_) 
	{
		real = r_;
		imag = i_;
	}
	
	public BigComplex(double d) 
	{
		 real = new BigDecimal(""+d);
		 imag = ZERO;
	}

	public BigComplex(double r_, double i_) {
		 real = new BigDecimal(""+r_);
		 imag = new BigDecimal(""+i_);
	}

	public BigComplex copy()
	{
		return new BigComplex(real, imag);
	}

	public BigComplex negate()
	{
		return new BigComplex(real.negate(),imag.negate());
	}

	public BigComplex conjugate()
	{
		return new BigComplex(real,imag.negate());
	}
	
	public BigComplex add(BigComplex c)
	{
		return new BigComplex(real.add(c.getReal()), imag.add(c.getImag()));
	}
	
	public BigComplex subtract(BigComplex c)
	{
		return new BigComplex(real.subtract(c.getReal()), imag.subtract(c.getImag()));
	}

	public BigComplex add(BigDecimal a)
	{
		return new BigComplex( real.add(a), imag);
	}
	
	public BigComplex subtract(BigDecimal a)
	{
		return new BigComplex( real.subtract(a), imag);
	}
	
	public BigComplex multiply(BigDecimal a)
	{
		return new BigComplex(real.multiply(a), imag.multiply(a));
	}
	
	public BigComplex divide(BigDecimal a)
	{
		return new BigComplex(real.multiply(a), imag.multiply(a));
	}
	
	public BigComplex divide(int a)
	{
		return new BigComplex(real.divide(new BigDecimal(""+a), ctx), imag.divide(new BigDecimal(""+a), ctx));
	}
	
	public BigComplex divide(BigComplex c)
	{
		BigComplex c_bar = c.conjugate().divide(c.sqrNorm());
		return this.multiply(c_bar);
	}

	public BigComplex ZERO()
	{
		return new BigComplex(ZERO, ZERO);
	}
	
	public BigComplex ONE()
	{
		return new BigComplex(ONE, ZERO);
	}
	
	public BigComplex I()
	{
		return new BigComplex(ZERO, ONE);
	}
	
	public BigComplex multiply(BigComplex c)
	{		
		return new BigComplex
				(
						real.multiply(c.getReal()).subtract(imag.multiply(c.getImag())), //subtract(multiply(ar, br), multiply(ai, bi)), 
						real.multiply(c.getImag()).subtract(imag.multiply(c.getReal()))//add(multiply(ar, bi), multiply(ai, br)) 
				);
	}
	
	public BigDecimal sqrNorm()
	{
		return real.multiply(real).add(imag.multiply(imag));
	}

	public BigDecimal abs()
	{
		return sqrt(sqrNorm());
	}

	private BigDecimal sqrt(BigDecimal x) 
	{
		BigDecimal high = x;
		BigDecimal low  = ZERO;
		BigDecimal sqrt = high.add(low).divide(TWO , ctx);
		
		while (high.subtract(low).doubleValue()>epsilon)
		{
			if (sqrt.pow(2).compareTo(x)>=0)
			{
				high = sqrt;
			}
			else
			{
				low = sqrt;
			}
			sqrt = high.add(low).divide(TWO, ctx);
		}
		return sqrt.setScale(scale, rnd);
	}

	public BigDecimal getReal() {
		return real;
	}

	public void setReal(BigDecimal real) {
		this.real = real;
	}

	public BigDecimal getImag() {
		return imag;
	}
	
	public String toString()
	{
		String sep = (imag.signum()<0?" ":" +");
		return ""+toString(real)+sep+toString(imag)+"i";
	}

	public String toString(BigDecimal x)
	{
		DecimalFormat df = new DecimalFormat();

		df.setMaximumFractionDigits(16);
		df.setMinimumFractionDigits(0);
		df.setGroupingUsed(false);

		return df.format((BigDecimal)x);
	}
		
	
}
