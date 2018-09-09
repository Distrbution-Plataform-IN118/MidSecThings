package security;


import security.audit.AccessDecision;
import security.audit.AuditDecision;
import security.corba.Policy;
import security.level2.Credentials;
import security.level2.CredentialsList;
import security.level2.PrincipalAuthenticator;
import security.level2.RequiredRights;
import security.level2.TargetCredentials;

public class  SecurityManager {
	CredentialsList own_credentials;
	RequiredRights required_rights_object;
	PrincipalAuthenticator  principal_authenticator;
	AccessDecision access_decision;
	AuditDecision audit_decision;
	public Policy getSecurityPolicy() {
		return null;
	}
	
	TargetCredentials get_target_credentials(Object obj_ref) {
		return null;
	}
	void remove_own_credentials(Credentials creds) {
		
	}
	
}
