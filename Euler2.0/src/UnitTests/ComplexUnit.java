package UnitTests;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;
import complex.Complex;

import static org.junit.Assert.*;

public class ComplexUnit {
	
	private static final double dr = 1;
	private static final double di = 1;
	
	private static final int ir = 1;
	private static final int ii = 1;
	
	private static final BigInteger br = BigInteger.ONE;
	private static final BigInteger bi = BigInteger.ONE;
	
	private static final BigDecimal bdr = BigDecimal.ONE;
	private static final BigDecimal bdi = BigDecimal.ONE;

    @Test
    public void testGenericTypes() {
    	// Double
        Complex c = new Complex(dr,di);
        String result = ""+c;
        assertEquals(dr+" +"+di+"i", result);
        
    	// Integer
        c = new Complex(ir,ii);
        result = ""+c;
        assertEquals(ir+" +"+ii+"i", result);
        
    	// BigInteger
        c = new Complex(br,bi);
        result = ""+c;
        assertEquals(br+" +"+bi+"i", result);
       
       // BigDecimal
        c = new Complex(bdr,bdi);
        result = ""+c;
        assertEquals(bdr+" +"+bdi+"i", result);

    }
    
    @Test
    public void testConjugate() {
    	// Double
        Complex c = new Complex(dr,di);
        String result = ""+c.conjugate();
        assertEquals(dr+" -"+di+"i", result);
        
    	// Integer
        c = new Complex(ir,ii);
        result = ""+c.conjugate();
        assertEquals(ir+" -"+ii+"i", result);
        
    	// BigInteger
        c = new Complex(br,bi);
        result = ""+c.conjugate();
        assertEquals(br+" -"+bi+"i", result);
       
       // BigDecimal
        c = new Complex(bdr,bdi);
        result = ""+c.conjugate();
        assertEquals(bdr+" -"+bdi+"i", result);    	
    }
    
    @Test
    public void testNegate() {
    	// Double
        Complex c = new Complex(dr,di);
        String result = ""+c.negate();
        assertEquals("-"+dr+" -"+di+"i", result);
        
    	// Integer
        c = new Complex(ir,ii);
        result = ""+c.negate();
        assertEquals("-"+ir+" -"+ii+"i", result);
        
    	// BigInteger
        c = new Complex(br,bi);
        result = ""+c.negate();
        assertEquals("-"+br+" -"+bi+"i", result);
       
       // BigDecimal
        c = new Complex(bdr,bdi);
        result = ""+c.negate();
        assertEquals("-"+bdr+" -"+bdi+"i", result);    	
    }
    
    @Test
    public void testAdd() {
    	// Double
        Complex c = new Complex(dr,di);
        String result = ""+c.add(c);
        assertEquals(""+dr*2+" +"+di*2+"i", result);
        
    	// Integer
        c = new Complex(ir,ii);
        result = ""+c.add(c);
        assertEquals(""+ir*2+" +"+ii*2+"i", result);
        
    	// BigInteger
        c = new Complex(br,bi);
        result = ""+c.add(c);
        assertEquals(""+br.multiply(new BigInteger("2"))+" +"+bi.multiply(new BigInteger("2"))+"i", result);
       
       // BigDecimal
        c = new Complex(bdr,bdi);
        result = ""+c.add(c);
        assertEquals(""+bdr.multiply(new BigDecimal("2"))+" +"+bdi.multiply(new BigDecimal("2"))+"i", result);    	
    }
    
    @Test
    public void testSubtract() {
    	// Double
        Complex c = new Complex(dr,di);
        String result = ""+c.subtract(c);
        assertEquals("0.0 +0.0i", result);
        
    	// Integer
        c = new Complex(ir,ii);
        result = ""+c.subtract(c);
        assertEquals("0 +0i", result);
        
    	// BigInteger
        c = new Complex(br,bi);
        result = ""+c.subtract(c);
        assertEquals("0 +0i", result);
       
       // BigDecimal
        c = new Complex(bdr,bdi);
        result = ""+c.subtract(c);
        assertEquals("0 +0i", result);    	
    }
    
    @Test
    public void testMultiply() {
    	// Double
        Complex c = new Complex(dr,di);
        String result = ""+c.multiply(c);
        assertEquals("0.0 +2.0i", result);
        
    	// Integer
        c = new Complex(ir,ii);
        result = ""+c.multiply(c);
        assertEquals("0 +2i", result);
        
    	// BigInteger
        c = new Complex(br,bi);
        result = ""+c.multiply(c);
        assertEquals("0 +2i", result);
       
       // BigDecimal
        c = new Complex(bdr,bdi);
        result = ""+c.multiply(c);
        assertEquals("0 +2i", result);    	
    }
    
    @Test
    public void testDivide() {
    	// Double
    	Complex a = new Complex(0.0, 2.0);
        Complex c = new Complex(dr,di);
        String result = ""+a.divide(c);
        assertEquals("1.0 +1.0i", result);
        
    	// Integer
        a = new Complex(0, 2);
        c = new Complex(ir,ii);
        result = ""+a.divide(c);
        assertEquals("0 +0i", result);
        
    	// BigInteger
        a = new Complex(BigInteger.ZERO, BigInteger.ONE.shiftRight(1));
        c = new Complex(br,bi);
        result = ""+a.divide(c);
        assertEquals("0 +0i", result);
       
       // BigDecimal
        a = new Complex(BigDecimal.ZERO, new BigDecimal("2"));
        c = new Complex(bdr,bdi);
        result = ""+a.divide(c);
        assertEquals("1.0 +1.0i", result);    	
    }
    
    
    @Test
    public void testMod() {
    	// Double
    	Complex a = new Complex(0.0, 2.0);
        Complex c = new Complex(dr,di);
        String result = ""+a.divide(c);
        assertEquals("1.0 +1.0i", result);
        
    	// Integer
        a = new Complex(0, 2);
        c = new Complex(ir,ii);
        result = ""+a.divide(c);
        assertEquals("0 +0i", result);
        
    	// BigInteger
        a = new Complex(BigInteger.ZERO, BigInteger.ONE.shiftRight(1));
        c = new Complex(br,bi);
        result = ""+a.divide(c);
        assertEquals("0 +0i", result);
       
       // BigDecimal
        a = new Complex(BigDecimal.ZERO, new BigDecimal("2"));
        c = new Complex(bdr,bdi);
        result = ""+a.divide(c);
        assertEquals("1.0 +1.0i", result);    	
    }
 }
