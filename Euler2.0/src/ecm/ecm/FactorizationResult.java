package ecm;

import java.math.BigInteger;

public class FactorizationResult
{
	  
	  public BigInteger[] factor = null;
	  public int[] exp = null;
	  public int nbFactors = 0;
	  
	  public FactorizationResult(
			    BigInteger[] factor_
			  , int[] exp2
			  , int nbFactors_)
	  {
		    nbFactors = nbFactors_;
		    factor = new BigInteger[nbFactors];
		    exp = new int[nbFactors];
		    System.arraycopy(factor_, 0, factor, 0, nbFactors);
		    System.arraycopy(exp2, 0, exp, 0, nbFactors);
	  }
	  
	  public String toString()
	  {
		  String res = ""+factor[0]+"^"+exp[0];
		  
		  for (int i=1; i<nbFactors; i++)
			  res += "+"+factor[i]+"^"+exp[i];
		  
		  return res;
		  
	  }
}