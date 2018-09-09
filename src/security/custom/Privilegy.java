package security.custom;

import java.util.HashMap;
import java.util.Map;

import common.MethodNameRemoteObject;

public class Privilegy {
	private final Map<String, String> method_privilegy = new HashMap<>();

	// private final Map<String, String> attribute_privilegy = new HashMap<>();
	private String roleUser;

	public Map<String, String> getMethod_privilegy() {
		return method_privilegy;
	}

	public Privilegy(RoleType role) {

		if (role == RoleType.GUEST) {
			roleUser = RoleType.GUEST.name();
			method_privilegy.put("login", "GRANT");
			method_privilegy.put("logout", "GRANT");
			method_privilegy.put("recuperarItem", "GRANT");
			method_privilegy.put("atualizaItem", "REVOKE");
			method_privilegy.put("numberAttempts", "REVOKE");
			method_privilegy.put(MethodNameRemoteObject.LIST_RECORDS_AUDIT, "REVOKE");
			method_privilegy.put(MethodNameRemoteObject.UPDATE_ITEMS_AREA, "REVOKE");
		} else if (role == RoleType.OWN) {
			roleUser = RoleType.OWN.name();
			method_privilegy.put("login", "GRANT");
			method_privilegy.put("logout", "GRANT");
			method_privilegy.put("recuperarItem", "GRANT");
			method_privilegy.put("atualizaItem", "GRANT");
			method_privilegy.put("numberAttempts", "REVOKE");
			method_privilegy.put(MethodNameRemoteObject.LIST_RECORDS_AUDIT, "REVOKE");
			method_privilegy.put(MethodNameRemoteObject.UPDATE_ITEMS_AREA, "REVOKE");
		} else if (role == RoleType.ADMIN) {
			roleUser = RoleType.ADMIN.name();
			method_privilegy.put("login", "GRANT");
			method_privilegy.put("logout", "GRANT");
			method_privilegy.put("recuperarItem", "GRANT");
			method_privilegy.put("atualizaItem", "GRANT");
			method_privilegy.put("numberAttempts", "GRANT");
			method_privilegy.put(MethodNameRemoteObject.LIST_RECORDS_AUDIT, "GRANT");
			method_privilegy.put(MethodNameRemoteObject.UPDATE_ITEMS_AREA, "GRANT");

		}

	}

	public String getRoleUser() {
		return roleUser;
	}

}
