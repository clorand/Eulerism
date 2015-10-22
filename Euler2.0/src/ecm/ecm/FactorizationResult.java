package ecm;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.math.BigInteger;

@SuppressWarnings("serial")
public class FactorizationResult extends ecm
{
	  	
	  private String factorString = null;
	  
	  
	  public FactorizationResult() 
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
	  }

	  public boolean isFactorizationComplete()
	  {
		  String lowerTextAreaInfo =  lowerTextArea.getText(); 
		  String textNumberString =  textNumber.getText();
		  
		  
		  return (   textNumberString.isEmpty()
				  ||(	!textNumberString.isEmpty()
					   &&lowerTextAreaInfo.startsWith("Factorization complete")
				    ) 
				 );
	  }
	  
	  public void getFactorizationResult(String factorString_)
	  {
		  factorString = factorString_;
		  
		  this.sleep();
		  
		  this.launchFactorization(factorString);	 
	  }
	  
	 public void sleep()
	 {
		  while (!isFactorizationComplete())
		  {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		 
	 }
	  
	public String toString()
	  {
		  String res = ""+getPD()[0]+"^"+getExp()[0];
		  
		  for (int i=1; i<getNbrFactors(); i++)
			  res += " * "+getPD()[i]+"^"+getExp()[i];
		  
		  return res;
		  
	  }
	
	public static void main(String[] args) 
	{
		
		FactorizationResult f= new FactorizationResult();

		f.getFactorizationResult("2");

		
		for (int i = 2; i<=1000; i++)
		{
			if (!new BigInteger(""+i).isProbablePrime(10))
			f.getFactorizationResult(""+i);
			//System.out.println(""+f);
		}
		
		System.out.println("finished");
	}
}