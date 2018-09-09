package object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import model.Item;
import model.Items;
import security.audit.AuditManager;
import security.audit.AuditRecord;
import security.auth.AuthenticatorManager;
import security.custom.AccessPolicyManager;
import security.custom.Permission;

public class SmartHomeImpl implements ISmartHome, Serializable {

	private ClientConfig clientConfig;
	private Client client;

	private WebTarget webTarget;

	private WebTarget resourceWebTarget;

	private Invocation.Builder invocationBuilder;
	private static final HashMap<String, List<Item>> classification_item_by_place = new HashMap<>();
	Response response;

	static {
		discovery();
	}

	public SmartHomeImpl() {
		super();
		// TODO Auto-generated constructor stub
		clientConfig = new ClientConfig();
		client = ClientBuilder.newClient(clientConfig);
		webTarget = client.target("http://demo.openhab.org:8080/rest");

	}

	@Override
	public Items listarTodasCoisas() throws Throwable {
		// TODO Auto-generated method stub

		resourceWebTarget = webTarget.path("items");
		invocationBuilder = resourceWebTarget.request("application/json;charset=UTF-8");
		response = invocationBuilder.get();
		Items items = response.readEntity(Items.class);
		return items;
	}

	@Override
	public Item recuperarItem(String token_current_user, String uuid) throws Throwable {
		// TODO Auto-generated method stub

		if (AuthenticatorManager.getInstance().isValidToken(token_current_user)) {
			String current_user = AuthenticatorManager.getInstance().getUserSession(token_current_user);
			class Local {
			}
			String method_name = null;
			method_name = Local.class.getEnclosingMethod().getName();
			String permision = AccessPolicyManager.getInstance().getPriviligies(current_user, method_name);
			if (permision.equals(Permission.GRANT)) {
				resourceWebTarget = webTarget.path("items").path(uuid);
				invocationBuilder = resourceWebTarget.request("application/json;charset=UTF-8");
				response = invocationBuilder.get();
				Item item = response.readEntity(Item.class);
				return item;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	@Override
	public String atualizaItem(String token_current_user, String uuid, String status) throws Throwable {
		if (AuthenticatorManager.getInstance().isValidToken(token_current_user)) {

			class Local {
			}
			String current_user = AuthenticatorManager.getInstance().getUserSession(token_current_user);
			String method_name = null;
			method_name = Local.class.getEnclosingMethod().getName();
			String permision = AccessPolicyManager.getInstance().getPriviligies(current_user, method_name);
			if (permision.equals(Permission.GRANT)) {
				resourceWebTarget = webTarget.path("items").path(uuid).path("state");
				Response response = resourceWebTarget.request("text/plain").put(Entity.entity(status, "text/plain"));
				return response.getStatusInfo().toString();
			} else {
				return "DENIED AUTHORIZATION";
			}
		} else {
			return "LOST AUTHETICATION";
		}

	}

	@Override
	public String login(String user, String senha) throws Throwable {
		// TODO Auto-generated method stub

		return AuthenticatorManager.getInstance().login(user, senha);
	}

	@Override
	public String logout(String token) throws Throwable {
		// TODO Auto-generated method stub
		return AuthenticatorManager.getInstance().logout(token);
	}

	@Override
	public Integer numberAttempts(String token_current_user, String name_user) throws Throwable {
		// TODO Auto-generated method stub
		if (AuthenticatorManager.getInstance().isValidToken(token_current_user)) {

			String current_user = AuthenticatorManager.getInstance().getUserSession(token_current_user);
			class Local {
			}
			String method_name = null;
			method_name = Local.class.getEnclosingMethod().getName();
			System.out.printf("NAME METHOD = [%s]\n", method_name);
			System.out.printf("NAME METHOD = [%s]\n", method_name);
			String permision = AccessPolicyManager.getInstance().getPriviligies(current_user, method_name);
			System.out.println(permision);
			if (permision.equals(Permission.GRANT.toString()))
				return AuthenticatorManager.getInstance().getNumberAttempts(name_user);

		}

		return -1;
	}

	@Override
	public List<AuditRecord> listRecordsAudit(String token_current_user) throws Throwable {
		// TODO Auto-generated method stub
		if (AuthenticatorManager.getInstance().isValidToken(token_current_user)) {

			String current_user = AuthenticatorManager.getInstance().getUserSession(token_current_user);
			class Local {
			}
			String method_name = null;
			method_name = Local.class.getEnclosingMethod().getName();
			String permision = AccessPolicyManager.getInstance().getPriviligies(current_user, method_name);
			// System.out.println(permision);
			if (permision.equals(Permission.GRANT.toString()))
				return AuditManager.getInstance().listAuditRepository().listRecordsAudit();

		}
		return null;
	}

	@Override
	public String atualizarItemsArea(String token_current_user, String lugar_casa, String status) throws Throwable {
		// TODO Auto-generated method stub
		if (AuthenticatorManager.getInstance().isValidToken(token_current_user)) {

			class Local {
			}
			String current_user = AuthenticatorManager.getInstance().getUserSession(token_current_user);
			String method_name = null;
			method_name = Local.class.getEnclosingMethod().getName();
			String permision = AccessPolicyManager.getInstance().getPriviligies(current_user, method_name);
			if (permision.equals(Permission.GRANT)) {
				// discovery();
				if (classification_item_by_place.containsKey(lugar_casa)) {
					List<Item> item_list_value = classification_item_by_place.get(lugar_casa);

					for (Item item : item_list_value) {
						/*
						 * System.out.println("ITEM NAME=" + item.getName());
						 * System.out.println("ITEM STATUS=" + item.getCategory());
						 */
						this.atualizaItem(token_current_user, item.getName(), status);
					}
					return "ITEMS " + lugar_casa + " ATUALIZADOS";
				} else {
					return "NOT FOUND PLACE HOME";
				}

			} else {
				return "DENIED AUTHORIZATION";
			}
		} else {
			return "LOST AUTHETICATION";
		}

	}

	private static void discovery() {

		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		WebTarget webTarget = client.target("http://demo.openhab.org:8080/rest");
		WebTarget resourceWebTarget = webTarget.path("items");
		Invocation.Builder invocationBuilder = resourceWebTarget.request("application/json;charset=UTF-8");

		Response response = invocationBuilder.get();

		// System.out.println(response.getStatus());

		Items items = response.readEntity(Items.class);

		for (Item item : items) {

			if (item.getGroupNames().size() == 2) {
				String light_group_name = (String) item.getGroupNames().get(1);
				if (light_group_name != null && light_group_name.contains("ight")) {
					String group_name_key = (String) item.getGroupNames().get(0);
					if (classification_item_by_place.containsKey(group_name_key)) {

						classification_item_by_place.get(group_name_key).add(item);

					} else {
						List<Item> item_temp_list = new ArrayList<>();
						item_temp_list.add(item);
						classification_item_by_place.put(group_name_key, item_temp_list);
					}
				}

			}
		}
	}
}
