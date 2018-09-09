package client;

import java.io.IOException;
import java.net.UnknownHostException;

import model.Item;
import naming.NamingProxy;
import object.ISmartHome;
import util.Port;

public class SmartHomeClient {
	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {

		// Cria uma instancia do Servidor de Nome
		NamingProxy namingService = new NamingProxy("localhost", Port.NAMING_PORT);

		// Cria uma instancia do proxy que referencia o objeto remoto
		ISmartHome smarthome = (ISmartHome) namingService.lookup("Thing");

		// Imprime o resultado calculado
		// ********** <<< RECUPERAR ITEM >>> **********
		/*System.out.printf("ITEM  = [%s] | STATUS = [%s]\n", smarthome.recuperarItem("Light_GF_Corridor_Ceiling").getName(), smarthome.recuperarItem("Light_GF_Corridor_Ceiling").getState() );
		String nelson_token = smarthome.login("nelson", "middleware");
		System.out.println("Nelson User = " + nelson_token);
		*/
	/*	for (Item item : smarthome.listarTodasCoisas()) {

			System.out.printf("ITEM  = [%s] | STATUS = [%s]\n", smarthome.recuperarItem("Light_GF_Corridor_Ceiling").getName(), smarthome.recuperarItem("Light_GF_Corridor_Ceiling").getState() );;
		}
		*/
		/*for (int i = 0; i < 99; i++) {
			smarthome.login("nelson", "nautico");
		}
		System.out.printf("USUARIO  = [Nelson] | ATTEMPTS = [%s]\n" , smarthome.numberAttempts(nelson_token,"nelson"));
		
		
		if (smarthome.recuperarItem("Light_GF_Corridor_Ceiling").getState().equals("ON")) {
			System.out.println(smarthome.atualizaItem(nelson_token,"Light_GF_Corridor_Ceiling","OFF" ));
		}else {
			System.out.println(smarthome.atualizaItem(nelson_token,"Light_GF_Corridor_Ceiling","ON" ));
		}*/
		/*
		String nelson_token = smarthome.login("nelson", "middleware");
		System.out.println("Nelson User = " + nelson_token);
		String unknown_user_token = smarthome.login("Richardson", "middleware");
		System.out.println("Unknow User = " + unknown_user_token);
		smarthome.logout("Log out Nelson User = "+ smarthome.logout(nelson_token));
*/
	}

}
