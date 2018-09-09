package security.level1;

public interface ICurrent {
	
	public String receiveCredentials=null; 
	public String ownCredentials=null; 
	public String auditDecision = null;
	public void getAttributes();
	public void setAttributes();
}
