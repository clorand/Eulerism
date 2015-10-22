package complex;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Functions {
	
	private static final Double epsilon 	= 1e-32;
	public static final BigDecimal PI 		= new BigDecimal("3.14159265358979323846264338327950288419716939937510582");
	public static final BigDecimal ZERO 	= new BigDecimal("0");
	public static final BigDecimal ONE 		= new BigDecimal("1");
	public static final BigDecimal TWO 		= new BigDecimal("2");
	public static final BigDecimal THREE 	= new BigDecimal("3");
	public static final BigDecimal FOUR 	= new BigDecimal("4");
	public static final BigDecimal TWO_PI 	= TWO.multiply(PI);
	private static final MathContext ctx     = new MathContext(128);
	private static final int 		scale    =  32;
	private static final RoundingMode rnd    =  RoundingMode.HALF_DOWN;
	public static final BigDecimal UNDEFINED = new BigDecimal("123456789.123456789123456789"); 
	
	public static BigDecimal exp(BigDecimal z)
	{		
		int i = 0;
		BigDecimal x = ONE;
		BigDecimal exp = x;
		while(x.abs().doubleValue() > epsilon)
		{
			i++;
			x = x.multiply(z).divide(new BigDecimal(""+i), ctx);
			exp = exp.add(x);
		};
		return exp.setScale(scale, rnd);
	}
	
	public static BigComplex ln(BigComplex c)
	{
		BigDecimal rho   = c.abs();
		BigDecimal theta = atan(c.getReal(), c.getImag());
		
		return new BigComplex(ln(rho), theta);
	}
	
	public static BigDecimal cos(BigDecimal z)
	{		
		int i = 0;
		BigDecimal x = ONE;
		BigDecimal cos = x;
		while(x.abs().doubleValue() > epsilon)
		{
			i++;
			x = x.multiply(z.pow(2)).divide(new BigDecimal(""+(-1*(2*i)*(2*i-1))), ctx);
			cos = cos.add(x);
			//System.out.println("cos("+z+")="+cos+"\t i:"+i+"\t x:"+x);
		};
		return cos.setScale(scale, rnd);
	}
	
	public static BigDecimal tan(BigDecimal z)
	{
		return sin(z).divide(cos(z), ctx);
	}
	
	public static BigDecimal atan(BigDecimal x, BigDecimal y)
	{
	
		if (x.multiply(y).compareTo(ZERO)!=0)
		{
		BigDecimal tan = y.divide(x, ctx).abs();
		BigDecimal high = tan;
		BigDecimal low  = tan.subtract(tan.pow(3).divide(new BigDecimal("3"), ctx));		
		while (tan(low).compareTo(tan)>=0)  low  = tan.subtract(ONE);	
		BigDecimal atan = (high.add(low)).divide(TWO,ctx);
		int i=0;
		while (high.subtract(low).doubleValue()> epsilon)
		{
			i++;
			if (tan(atan).compareTo(tan)<=0)
			{
				low = atan;
			}
			else
			{
				high = atan;
			}
			atan = (high.add(low)).divide(TWO, ctx);
		}		
		
		if (x.signum()==1)
		{
			if (y.signum()==1) 
			{
				// atan = atan;
			}
			else
			{
				atan = atan.negate();
			}
		}
		else
		{
			if (y.signum()==1) 
			{
				atan = PI.subtract(atan);
			}
			else
			{
				atan = atan.subtract(PI);
			}			
		}
			
		System.out.println("atan("+tan+")="+atan+", in :"+i+" iterations");		
		return atan.setScale(scale, rnd);
		}
		else
		{
			if (y.compareTo(ZERO)==0) 
			{
				return ZERO;
			}
			else
			{
				return UNDEFINED;
			}
		}
	}
	
	public static BigDecimal sin(BigDecimal z)
	{		
		int i = 0;
		BigDecimal x = z;
		BigDecimal sin = x;
		while(x.abs().doubleValue() > epsilon)
		{
			i++;
			x = x.multiply(z.pow(2)).divide(new BigDecimal(""+(-(2*i+1)*(2*i))), ctx);
			sin = sin.add(x);
		};
		return sin.setScale(16, rnd);
	}
	
	public static BigComplex exp(BigComplex z)
	{		
		BigDecimal im = z.getImag();
		if (im.compareTo(PI)!=0)
		{
			im = im.subtract(((im.divide(TWO_PI, ctx)).setScale(0, RoundingMode.FLOOR)).multiply(TWO_PI), ctx);
			z = new BigComplex(z.getReal(), im);
		};
		int i = 0;
		BigComplex x = z.ONE();
		BigComplex exp = x;
		while(x.abs().doubleValue() > epsilon)
		{
			i++;
			x = x.multiply(z).divide(i);
			exp = exp.add(x);
		};
		System.out.println("exp("+z+")="+exp+", in :"+i+" iterations");
		return exp;
	}
	
	public static BigDecimal ln(BigDecimal x)
	{
		switch (x.compareTo(ONE))
		{
		
		default :	{	return ZERO;	}
		case  1 :	{	return ln(ONE.divide(x,ctx)).negate();	}
		case  0 :	{	return ZERO;	}
		
		case -1 :	{	
			if (x.compareTo(ZERO)<=0)
			{
				return UNDEFINED;
			}
			else
			{
				BigDecimal high = x.subtract(ONE);
				BigDecimal low  = high;
				BigDecimal ln   = ZERO;
				int i=0;
				while (exp(low).compareTo(x)==1) low = low.subtract(ONE);
				while (high.subtract(low).doubleValue()> epsilon)
				{
					i++;
					ln = (high.add(low)).divide(TWO, ctx);
					if (exp(ln).compareTo(x)<=0)
					{
						low = ln;
					}
					else
					{
						high = ln;
					}
				}				
				System.out.println("ln("+x+")="+ln+", in :"+i+" iterations");
				return ln.setScale(scale, rnd);
			}
		  }
		}
		
	}

}
