package performance;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import model.CeilingHub;
import naming.NamingProxy;
import object.ISmartHome;
import util.Port;

public class RetrieveItemClient {

	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {

		// Cria uma instancia do Servidor de Nome
		NamingProxy namingService = new NamingProxy("localhost", Port.NAMING_PORT);

		// Cria uma instancia do proxy que referencia o objeto remoto
		ISmartHome smarthome = (ISmartHome) namingService.lookup("Thing");
		int sampleSize = 1000;

		Random generator = new Random();
		long totalTime = 0;
		long duration;
		long startTime;
		String token_rick = smarthome.login("rick", "security");
		System.out.println("Token Rick User = " + token_rick);

		for (int i = 0; i < Metrics.SAMPLE; i++) {
			// invoke calculator
			startTime = System.nanoTime();

			// System.out.println(s);
			smarthome.recuperarItem(token_rick,CeilingHub.Light_GF_Corridor_Ceiling);
			Metrics.RTT_CLIENT_DURATION_LIST[i] = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
			// totalTime = totalTime + duration;

			Thread.sleep((long) (generator.nextGaussian() * 0.1 + 10));
		}

		// System.out.println("Total Time Duration: " + totalTime + " ms");
		// System.out.println("Mean Time Duration: " + totalTime / ((double) sampleSize)
		// + " ms");
		System.out.println("<< INVOKER >>");
		WriteExcel excel = new WriteExcel("C:/Users/Richardson/Documents/Security_Recuperar_Item_Invoker.xls");
		excel.init();

		for (int i = 0; i < Metrics.INVOKER_DURATION_LIST.length; i++) {
			// System.out.println(Metrics.INVOKER_DURATION_LIST[i]); ;
			//System.out.println(Metrics.INVOKER_DURATION_LIST[i]);
			excel.createContent(Metrics.INVOKER_DURATION_LIST[i]);

		}
		excel.close();

		excel = new WriteExcel("C:/Users/Richardson/Documents/Security_Recuperar_Item_Unmarshall.xls");
		excel.init();
		System.out.println("<< UNMARSHALL_CLIENT >>");
		for (int i = 0; i < Metrics.UNMARSHALL_CLIENT_DURATION_LIST.length; i++) {
			// System.out.println(Metrics.UNMARSHALL_CLIENT_DURATION_LIST[i]); ;
			excel.createContent(Metrics.UNMARSHALL_CLIENT_DURATION_LIST[i]);
		}

		excel.close();

		excel = new WriteExcel("C:/Users/Richardson/Documents/Security_Recuperar_Item_Rtt.xls");
		excel.init();

		System.out.println("<< RTT_CLIENT >>");
		for (int i = 0; i < Metrics.RTT_CLIENT_DURATION_LIST.length; i++) {
			// System.out.println(Metrics.RTT_CLIENT_DURATION_LIST[i]); ;
			excel.createContent(Metrics.RTT_CLIENT_DURATION_LIST[i]);

		}
		excel.close();
		
	}

}
