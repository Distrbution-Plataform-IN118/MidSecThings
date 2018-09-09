package security.level2;

import java.util.HashMap;

import security.RightsCombinator;
import security.RightsList;
import security.corba.Identifier;
import security.corba.RepositoryId;

public class RequiredRights {
	RightsList rights;
	RightsCombinator rights_combinator;
	
	public HashMap<String , Object> get_required_rights(Object obj, Identifier operation_name, RepositoryId interface_name ) {
		
		return null;                          
	}
	
	
	public void set_required_rights(Identifier operation_name, RepositoryId interface_name, RightsList rights, RightsCombinator rights_combinator ) {
		
	}

	
	
	
	
}
