package client;

import java.io.IOException;
import java.net.UnknownHostException;

import model.CeilingHub;
import model.Item;
import model.PlaceHouse;
import naming.NamingProxy;
import object.ISmartHome;
import security.audit.AuditRecord;
import util.Port;

public class AdminSmartHomeClient {
	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {

		// Cria uma instancia do Servidor de Nome
		NamingProxy namingService = new NamingProxy("localhost", Port.NAMING_PORT);

		// Cria uma instancia do proxy que referencia o objeto remoto
		ISmartHome smarthome = (ISmartHome) namingService.lookup("Thing");

		// ********** <<< LOGIN USER >>> **********
		String token_rick = smarthome.login("rick", "security");
		System.out.println("Token Rick User = " + token_rick);
		// System.out.println("Number Attempts = " +
		// smarthome.numberAttempts(token_rick, "nelson"));
/*		Item item = smarthome.recuperarItem(token_rick, CeilingHub.Light_GF_Corridor_Ceiling);
		if (item.getState().equals("ON"))
			System.out.println(smarthome.atualizaItem(token_rick, CeilingHub.Light_GF_Corridor_Ceiling, "OFF"));
		else
			System.out.println(smarthome.atualizaItem(token_rick, CeilingHub.Light_GF_Corridor_Ceiling, "ON"));
		
		for (AuditRecord recordAudit :smarthome.listRecordsAudit(token_rick)) {
			System.out.printf("| %s | %s | %s | %s | %s |\n", recordAudit.getDate().toString(), recordAudit.getMethod(),recordAudit.getPermission(), recordAudit.getUser(),recordAudit.getRole());
		}*/
		System.out.println(smarthome.atualizarItemsArea(token_rick, PlaceHouse.GF_Corridor, "ON"));
		//System.out.println("SIZE ="+smarthome.listarTodasCoisas().size());
		smarthome.logout(token_rick);

	}

}
