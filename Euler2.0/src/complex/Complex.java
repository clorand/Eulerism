package complex;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Complex<T>{
	
	private T real;
	private T imag;
	private T ZERO;
	private T ONE;
	private static final BigDecimal PI = new BigDecimal("3.14159265358979323846264338327950288419716939937510582");
	private static final MathContext ctx = new MathContext(128);
	
	public int compareTo(Complex c, double precision)
	{
		double d = this.subtract(c).abs();
		// TODO review comparison
		return (d<precision?0:((Double)this.abs()>(Double)c.abs())?1:-1);
	}
	
	public Complex(T r_) 
	{
		setReal(r_);
		setImag(ZERO(r_));
		ZERO = ZERO(r_);
		ONE  = ONE(r_);
	}
	
	public T PI()
	{
		return (T) PI;
	}
	
	public Complex(T r_, T i_) 
	{
		setReal(r_);
		setImag(i_);
		ZERO = ZERO(r_);
		ONE  = ONE(r_);
	}
	
	public Complex copy()
	{
		return new Complex(real, imag);
	}

	public Complex negate()
	{
		return new Complex(negate(getReal()), negate(getImag()));
	}

	public Complex conjugate()
	{
		return new Complex(getReal(), negate(getImag()));
	}
	
	public Complex add(Complex c)
	{
		return new Complex(add(getReal(),(T) c.getReal()), add(getImag(),(T) c.getImag()));
	}
	
	public Complex subtract(Complex c)
	{
		return new Complex(add(getReal(),negate((T) c.getReal())), add(getImag(),negate((T) c.getImag())));
	}

	public Complex add(T a)
	{
		return new Complex(add(real,a), imag);
	}
	
	public Complex subtract(T a)
	{
		return new Complex(subtract(real,a), imag);
	}
	
	public Complex multiply(T a)
	{
		return new Complex(multiply(real,a), multiply(imag, a));
	}
	
	public Complex divide(T a)
	{
		return new Complex(divide(real,a), divide(imag, a));
	}
	
	public Complex divide(int a)
	{
		return new Complex(divide(real,a), divide(imag, a));
	}
	
	public Complex divide(Complex c)
	{
		Complex c_bar = c.conjugate().divide(c.norm());
		return this.multiply(c_bar);
	}

	public Complex ZERO()
	{
		return new Complex(ZERO, ZERO);
	}
	
	public Complex ONE()
	{
		return new Complex(ONE, ZERO);
	}
	
	public Complex I()
	{
		return new Complex(ZERO, ONE);
	}
	
	public Complex multiply(Complex c)
	{
		T ar = getReal();
		T ai = getImag();
		T br = (T) c.getReal();
		T bi = (T) c.getImag();
		
		T r = subtract(multiply(ar, br), multiply(ai, bi));
		T i = add(multiply(ar, bi), multiply(ai, br));
		
		
		return new Complex
				(
						subtract(multiply(ar, br), multiply(ai, bi)), 
						add(multiply(ar, bi), multiply(ai, br)) 
				);
	}
	
	public T norm()
	{
		return add(multiply(real, real), multiply(imag, imag));
	}

	public double abs()
	{
		return sqrt(norm());
	}

	private double sqrt(T x) {
		return  (Double) Type.valueOf(x).sqrt();
	}

	public T getReal() {
		return real;
	}

	public void setReal(T real) {
		this.real = real;
	}

	public T getImag() {
		return imag;
	}

	public void setImag(T imag) {
		this.imag = imag;
	}
	
	public String toString()
	{
		String sep = (signum(imag)<0?" ":" +");
		return ""+toString((T)real)+sep+toString((T)imag)+"i";
	}

	public T negate(T x) 
	{	
		return (T) Type.valueOf(x).negate();
	}
		
	public int signum(T x)
	{
		return Type.valueOf(x).signum();		
	}

	public T add(T x, T y) 
	{	
		return (T) Type.valueOf(x).add(y);
	}
	
	public T subtract(T x, T y) 
	{	
		return (T) Type.valueOf(x).subtract(y);
	}
	
	public T multiply(T x, T y) 
	{	
		return (T) Type.valueOf(x).multiply(y);
	}
	
	public T divide(T x, T y) 
	{	
		return (T) Type.valueOf(x).divide(y);
	}
	
	public T divide(T x, int y) 
	{	
		return (T) Type.valueOf(x).divide(Type.valueOf(x).unit(y));
	}
	
	public T mod(T x, int y) 
	{	
		return (T) Type.valueOf(x).mod(Type.valueOf(x).unit(y));
	}
	
	public T ZERO(T x)
	{
		return (T) Type.valueOf(x).ZERO();
	}
	
	public T ONE(T x)
	{
		return (T) Type.valueOf(x).ONE();
	}
	
	public String toString(T x)
	{
		return ""+(T) Type.valueOf(x);
	}
		
	enum Type{
		
		INTEGER
		{
			@Override
			public Object ZERO() 		{ 	return 0; 	}
			@Override
			public Object ONE() 		{ 	return 1; 	}
			@Override
			public Object negate() 		{ 	return -((Integer)value); 	}
			@Override
			public int signum() 		{ 	return Integer.signum((Integer)value);	}	
			@Override
			public Object add(Object x) { 	return ((Integer)x)+((Integer)value); 	}
			@Override
			public Object subtract(Object x) { 	return -((Integer)x)+((Integer)value); 	}
			@Override
			public Object multiply(Object x) { 	return ((Integer)x)*((Integer)value); 	}
			@Override
			public Object divide(Object x) { 	return ((Integer)value)/((Integer)x); 	}
			@Override
			public Object sqrt() 		{ 	return java.lang.Math.sqrt((Double)value); 	}
			@Override
			public Object unit(int i) 		{ 	return i; 	}
			@Override
			public Object mod(Object x) 		{ 	return ((Integer)value)%((Integer)x);  	}

		},
		DOUBLE
		{
			@Override
			public Object ZERO() 		{ 	return 0.0; 	}
			@Override
			public Object ONE() 		{ 	return 1.0; 	}
			@Override
			public Object negate() 		{	return -((Double)value);	}
			@Override
			public int signum() 		{ 	
											double  d = (Double)value;
											return (d>0?1:(d<0)?-1:0);
										}	
			@Override
			public Object add(Object x) { 	return ((Double)x)+((Double)value); 	}
			@Override
			public Object subtract(Object x) { 	return -((Double)x)+((Double)value); 	}
			@Override
			public Object multiply(Object x) { 	return ((Double)x)*((Double)value); 	}
			@Override
			public Object divide(Object x) { 	return ((Double)value)/((Double)x); 	}
			@Override
			public Object sqrt() 		{ 	return java.lang.Math.sqrt((Double)value); 	}
			@Override
			public Object unit(int i) 		{ 	return (double)i; 	}
			@Override
			public Object mod(Object x) 		{ 	return ((Double)value)%((Double)x);  	}

		},
		BIGINTEGER
		{
			@Override
			public Object ZERO() 		{ 	return BigInteger.ZERO; 	}
			@Override
			public Object ONE() 		{ 	return BigInteger.ONE; 		}
			@Override
			public Object negate() 		{ 	return ((BigInteger)value).negate(); 	}
			@Override
			public int signum() 		{	return ((BigInteger)value).signum();	}
			@Override
			public Object add(Object x) { 	return ((BigInteger)x).add((BigInteger)value); 	}
			@Override
			public Object subtract(Object x) { 	return ((BigInteger)value).subtract((BigInteger)x); 	}
			@Override
			public Object multiply(Object x) { 	return ((BigInteger)x).multiply((BigInteger)value); 	}
			@Override
			public Object divide(Object x) { 	return ((BigInteger)value).divide((BigInteger)x); }
			@Override
			public Object sqrt() 		{ 	return java.lang.Math.sqrt(((BigInteger)value).intValue()); 	}
			@Override
			public Object unit(int i) 		{ 	return new BigInteger(""+i); 	}
			@Override
			public Object mod(Object x) 		{ 	return ((BigInteger)value).mod((BigInteger)x);  	}						
		},
			
		BIGDECIMAL
		{
			@Override
			public Object ZERO() 		{ 	return BigDecimal.ZERO; 	}
			@Override
			public Object ONE() 		{ 	return BigDecimal.ONE; 	}
			@Override
			public Object negate() 		{	return ((BigDecimal)value).negate();	}
			@Override
			public int signum() 		{	return ((BigDecimal)value).signum();	}
			@Override
			public Object add(Object x) { 	return ((BigDecimal)x).add((BigDecimal)value); 	}
			@Override
			public Object subtract(Object x) { 	return ((BigDecimal)value).subtract((BigDecimal)x); 	}
			@Override
			public Object multiply(Object x) { 	return ((BigDecimal)x).multiply((BigDecimal)value); 	}
			@Override
			public Object divide(Object x) { 	return ((BigDecimal)value).divide((BigDecimal)x,  ctx); 	}
			@Override
			public Object sqrt() 		{ 	return java.lang.Math.sqrt(((BigDecimal)value).doubleValue()); 	}
			@Override
			public Object unit(int i) 		{ 	return new BigDecimal(""+i); 	}
			@Override
			public Object mod(Object x) 		{ 	return ((BigDecimal)value).subtract((
															(BigDecimal)value).divide((BigDecimal)x,ctx)
															.setScale(0, RoundingMode.FLOOR)
															.multiply((BigDecimal)x)
															);
												}
			
		};
		
		private static Object value;
		public abstract Object ZERO();
		public abstract Object ONE();
		public abstract int signum();
		public abstract Object negate();
		public abstract Object add(Object x);
		public abstract Object subtract(Object x);
		public abstract Object multiply(Object x);
		public abstract Object divide(Object x);
		public abstract Object mod(Object x);
		public abstract Object unit(int i);
		public abstract Object sqrt();

		public String toString()
		{
						
			Type t = valueOf(value.getClass().getSimpleName().toUpperCase());
			switch (t)
			{
				case BIGDECIMAL:
				{
					DecimalFormat df = new DecimalFormat();

					df.setMaximumFractionDigits(16);
					df.setMinimumFractionDigits(0);
					df.setGroupingUsed(false);

					return df.format((BigDecimal)value);
				}
				
				default:
				{
					return ""+value;
				}
			}
		}

		static Type valueOf(Object x)
		{	
			Type t = valueOf(x.getClass().getSimpleName().toUpperCase());
			t.setValue(x);
			return t;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value_) {
			Type.value = value_;
		}
	}
}
