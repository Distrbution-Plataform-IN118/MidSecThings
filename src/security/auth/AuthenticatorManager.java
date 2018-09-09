package security.auth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class AuthenticatorManager {
	 private final Map<String, String> users = new HashMap();
	  
	 /* Simula uma tabela do banco com as chaves de acesso */
	 private final Map<String, String> keys = new HashMap();
	  
	 /* Guarda os tokens gerados em tempo de execução */
	 private final Map<String, String> tokens = new HashMap<>();
	 
	 private final Map<String, Integer> attempts = new HashMap<>();
	 
	 private static AuthenticatorManager autenticator = null;
	 
	 private AuthenticatorManager() {
	   /**
	   * Guarda o usuário e senha dos clientes da API
	   */
	   users.put("nelson", "middleware");
	   users.put("henrique", "redes");
	   users.put("rick", "security");
	   
	   attempts.put("nelson", 0);
	   attempts.put("henrique", 0);
	   attempts.put("rick", 0);
	 
	 }
	 
	 public static AuthenticatorManager getInstance() {
	   if (Objects.isNull(autenticator)) {
	     autenticator = new AuthenticatorManager();
	   }
	   return autenticator;
	 }
	 
	 public String login(String login, String senha) {
		 
		 if(users.get(login) != null) {
			 String senha_value = users.get(login);
			 if (senha_value.equals(senha)) {
				 String auth = UUID.randomUUID().toString();
                 tokens.put(auth, login);
				 return auth;
			 }else {
				 int value =  attempts.get(login);
				 value++;
				 attempts.put(login, value);
			 }
			
			 
		 }
		 
		 return "ACCESS DENIED";
	
	 }
	 
	 public String logout(String token ) {
		 if (isValidToken(token)) {
                 tokens.remove(token);
                 return "SESSION LOST";
		 }else {
			  return "SESSION NOT INITIALIZED";
			 
		 }
             
	 }

	 public String getUserSession(String token_current_user) {
		 if(tokens.containsKey(token_current_user)){
			 return tokens.get(token_current_user);
		 }
		 return "CLOSED SESSION";
	 }
	 
	 public boolean isValidToken(String token_current_user) {
		 return tokens.containsKey(token_current_user);
	 }
	 
	 public void refresh() {
		 
	 }
	 public Integer getNumberAttempts(String usuario) {
		 if (attempts.containsKey(usuario)) {
			 return attempts.get(usuario);
		 }else {
			 return -1;
		 }
		  
	 }
}
