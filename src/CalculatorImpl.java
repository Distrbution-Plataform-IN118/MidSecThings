import java.io.Serializable;


public class CalculatorImpl implements ICalculator, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public float add(float a, float b) throws Throwable {
		// TODO Auto-generated method stub
		
		return a+b;
	}

	@Override
	public float sub(float a, float b) throws Throwable {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public float mul(float a, float b) throws Throwable {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public float div(float a, float b) throws Throwable {
		// TODO Auto-generated method stub
		float result = -1;
		try{
			result = a/b;
		}catch(ArithmeticException e){
			e.printStackTrace();
		}
		
		return result;
	}

}
