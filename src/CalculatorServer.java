import java.io.IOException;


public class CalculatorServer {
	public static void main(String[] args) throws IOException, Throwable {
		// obtain instance of Naming Service
		NamingProxy namingService = new NamingProxy("localhost", 1313);
		
		CalculatorProxy calculadoraImpl = new CalculatorProxy("localhost", 9085);

		// register Calculator in Naming service
		namingService.bind("Calculadora", calculadoraImpl);
		
		// invoker Invoker
	CalculatorInvoker invoker = new CalculatorInvoker();
		invoker.invoke(calculadoraImpl);
	}
}
