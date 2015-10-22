package UnitTests;

import java.math.BigDecimal;
import java.math.MathContext;
import org.junit.Test;

import complex.BigComplex;
import complex.Functions;
import static org.junit.Assert.*;

public class FunctionsUnit {
	
	private static final BigDecimal PI = new BigDecimal("3.14159265358979323846264338327950288419716939937510582");
	private static final MathContext ctx = new MathContext(128);

	
    @Test
    public void testExpBigDecimalPrecision() {
        BigComplex z = new BigComplex(new BigDecimal("0"), PI.multiply(new BigDecimal("-11")));
        String result = ""+Functions.exp(z).compareTo(new BigComplex(new BigDecimal("-1")), 1e-16);
        assertEquals("0",result);
    }
	
    @Test
    public void testExp() {
    	BigComplex z = new BigComplex(0.0);
        String result = ""+Functions.exp(z);
        assertEquals("1 +0i", result);
        
        z = new BigComplex(1.0);
        result = ""+Functions.exp(z);
        assertEquals("2.7182818284590452 +0i", result);
        
        z = new BigComplex(0.0, PI.doubleValue());
        result = ""+Functions.exp(z).compareTo(new BigComplex(-1.0), 1e-15);
        assertEquals("0", result);
    }
    
    @Test
    public void testLn() {
    	BigDecimal x = Functions.ONE;
    	BigDecimal result = Functions.ln(x);
        assertEquals(result, Functions.ZERO);
        
    	x = Functions.TWO;
    	result = Functions.ln(x);
        assertEquals(result, new BigDecimal("0.69314718055994530941723212145816"));
    }
    
    @Test
    public void testTrigo() {
    	BigDecimal x = PI;
    	BigDecimal result = Functions.cos(x);
    	BigDecimal expected = new BigDecimal("-1"); 
        assertEquals((result.subtract(expected).doubleValue()<1e-16),true);
        
    	result = Functions.sin(x);
    	expected = new BigDecimal("0");
        assertEquals((result.subtract(expected).doubleValue()<1e-16),true);
        
        x = PI.divide(Functions.FOUR, ctx);
    	result = Functions.tan(x);
    	expected = new BigDecimal("1");
        assertEquals((result.subtract(expected).doubleValue()<1e-16),true);
        
    	result = Functions.atan(Functions.ONE, Functions.ONE);
    	expected = x;
        assertEquals((result.subtract(expected).doubleValue()<1e-15),true);

    	result = Functions.atan(Functions.ONE, Functions.ONE.negate());
    	expected = x.negate();
        assertEquals((result.subtract(expected).doubleValue()<1e-15),true);
        
        BigComplex c = new BigComplex(Functions.ONE, Functions.ONE);
        BigComplex cresult   = Functions.ln(c);
        BigComplex cexpected = new BigComplex(Functions.ZERO,  x); 
        assertEquals((cresult.subtract(cexpected).abs().doubleValue()<1e-15),true);
    }
}






