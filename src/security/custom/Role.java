package security.custom;

public class Role {
	String name_role;
	String method_name;
	String permission ;
	
	public Role(String method_name, RoleType role ) {
		this.name_role=role.name();
		factoryPermission(role);
	}
	
	void factoryPermission(RoleType role) {
		if(role == RoleType.OWN || role == RoleType.ADMIN) {
			permission = "GRANT";
		}else {
			permission = "REVOKE";
		}
		
	}

	public String getPermission() {
		return permission;
	}

	public String getRole() {
		return name_role;
	}


	public String getMethod_name() {
		return method_name;
	}
	
	
}
