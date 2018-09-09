import java.io.Serializable;
import java.util.ArrayList;


public class CalculatorProxy extends ClientProxy implements ICalculator, Serializable {
	private static final long serialVersionUID = 1L;
	private Requestor requestor;
	public CalculatorProxy(String host,int port) {
		super(host,port);
		System.out.println("Realizando Requestor pelo ClientProxy");
		requestor = new Requestor(host, port);
	}


	@Override
	public float add(float a, float b) throws Throwable{
		// TODO Auto-generated method stub
		System.out.println("Chamando metodo add pelo ClientProxy");

		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		//Requestor invoke
		ter = requestor.invoke(inv);
		// Resultado enviada de volta para Client
		return (Float) ter.getResult();
	}

	@Override
	public float sub(float a, float b) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Chamando metodo (sub) pelo ClientProxy");
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		//Requestor invoke
		ter = requestor.invoke(inv);
		// Resultado enviada de volta para Client
		return (Float) ter.getResult();
	}

	@Override
	public float mul(float a, float b) throws Throwable{
		// TODO Auto-generated method stub
		System.out.println("Chamando metodo (mul) pelo ClientProxy");
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		//Requestor invoke
		ter = requestor.invoke(inv);
		// Resultado enviada de volta para Client
		return (Float) ter.getResult();
	}

	@Override
	public float div(float a, float b)throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Chamando metodo (div) pelo ClientProxy");
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		
		//Requestor invoke
		ter = requestor.invoke(inv);
		// Resultado enviada de volta para Client
		return (Float) ter.getResult();
	}

}
