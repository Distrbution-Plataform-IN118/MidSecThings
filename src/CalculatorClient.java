import java.io.IOException;
import java.net.UnknownHostException;


public class CalculatorClient {
	public static void main(String[] args) throws UnknownHostException, IOException, Throwable{
		
		//Cria uma instancia do Servidor de Nome
		NamingProxy namingService = new NamingProxy("localhost", 1313);
		
		//Cria uma instancia do proxy que referencia o objeto remoto
		ICalculator calculadora = (ICalculator) namingService.lookup("Calculadora");
		 
		//Imprime o resultado calculado
		System.out.println(calculadora.add(2, 2));
		System.out.println(calculadora.sub(2, 2));
		System.out.println(calculadora.mul(2, 2));
		System.out.println(calculadora.div(2, 2));
		
	}
}
