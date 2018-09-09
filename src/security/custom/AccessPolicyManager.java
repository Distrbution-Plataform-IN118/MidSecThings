package security.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AccessPolicyManager {
	private final Map<String, Privilegy> priviligies_users = new HashMap();

	/* Simula uma tabela do banco com as chaves de acesso */
	private final Map<String, String> keys = new HashMap();

	/* Guarda os tokens gerados em tempo de execução */
	private final Map<String, String> tokens = new HashMap<>();

	private final Map<String, Integer> attempts = new HashMap<>();

	private static AccessPolicyManager autenticator = null;

	private AccessPolicyManager() {
		/**
		 * Guarda o usuário e senha dos clientes da API
		 */
		priviligies_users.put("henrique", new Privilegy(RoleType.GUEST));
		priviligies_users.put("nelson", new Privilegy(RoleType.OWN));
		priviligies_users.put("rick", new Privilegy(RoleType.ADMIN));

	}

	public static AccessPolicyManager getInstance() {
		if (Objects.isNull(autenticator)) {
			autenticator = new AccessPolicyManager();
		}
	
		return autenticator;
	}

	/*public setPrivilegy(String user, Privilegy privilegies) {
		
	}*/

	public String getPriviligies(String user, String method_name) {
		if(priviligies_users.containsKey(user)) {
			if(priviligies_users.get(user).getMethod_privilegy().containsKey(method_name)) {
				return priviligies_users.get(user).getMethod_privilegy().get(method_name);
			}
		}
		return null;
	}
	
	public String getRoleTypeUser(String user) {
		if(priviligies_users.containsKey(user)) {
		
				return priviligies_users.get(user).getRoleUser();
		
		}
		return null;
	}
}
