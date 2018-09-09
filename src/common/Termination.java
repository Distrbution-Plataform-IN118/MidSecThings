package common;

import java.util.Iterator;

import model.Items;

public class Termination 
{
	private Object result;
	private Object[] resultArray;
	public Termination(){}
	public Object getResult()
	{
		return result;
	}
	
	public void setResult(Object result)
	{
		this.result = result;
	}
	public void setResultArray(Items items) {
		// TODO Auto-generated method stub
		resultArray = new Object[items.size()];
		for (int i = 0; i < items.size(); i++) {
			resultArray[i] = items.get(i);
		}
		//result = resultArray;
	}
	public Object[] getResultArray() {
		return resultArray;
	}
	
}
