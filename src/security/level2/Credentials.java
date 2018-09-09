package security.level2;

import java.util.List;

import security.AssociationOptions;
import security.AuthenticationStatus;
import security.InvocationCredentialsType;
import security.Security;

public class Credentials {
	AuthenticationStatus authentication_state;
	InvocationCredentialsType credentials_type;
	String mechanismeType = Security.mechanismtype;

	AssociationOptions accepting_options_supported;
	AssociationOptions accepting_options_required;
	AssociationOptions invocation_options_supported;
	List<Credentials> credentials_list;

	public static void main(String[] args) {
	}

}
