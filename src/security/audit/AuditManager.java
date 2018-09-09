package security.audit;

import java.util.Objects;

public class AuditManager {

	private static  AuditManager  auditManager = null;
	
	private static AuditRepository auditRepository = new AuditRepository();
	
	public static AuditManager getInstance(){
		if(Objects.isNull(auditManager)) {
			return new AuditManager();
		}
		return auditManager;
	}
	
	public void addAuditRepository(AuditRecord record) {
		
		auditRepository.addRecord(record);
	}
	
	public AuditRepository listAuditRepository(){
		return auditRepository;
	}
	
	
}
