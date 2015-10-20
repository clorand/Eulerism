package ecm;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.math.BigInteger;

@SuppressWarnings("serial")
public class FactorizationResult extends ecm
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
	  
	  public FactorizationResult(String factorString) 
	  {
		  super();
	      Frame frame = new Frame("Integer factorization using ECM/SIQS");
	      frame.addWindowListener(new PanelWindowListener());
	      frame.setLayout(new BorderLayout());
	      frame.add("Center", this);
	      frame.setSize(620, 420);
	      this.init();
	      this.start();
	      frame.setVisible(false);   
	      this.getFactorizationResult(factorString);	 
		    

	  }

	public String toString()
	  {
		  String res = ""+PD[0]+"^"+Exp[0];
		  
		  for (int i=1; i<NbrFactors; i++)
			  res += " * "+PD[i]+"^"+Exp[i];
		  
		  return res;
		  
	  }
	
	public static void main(String[] args)
	{
		
		new FactorizationResult("28");
	}
}