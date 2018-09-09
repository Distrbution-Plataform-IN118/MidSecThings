package security.audit;

import java.util.ArrayList;
import java.util.List;

public class AuditRepository {

	private static List<AuditRecord> recordsAudit = new ArrayList<AuditRecord>();
	
	public  void addRecord(AuditRecord record) {
		recordsAudit.add(record);
	}
	
	public  List<AuditRecord> listRecordsAudit() {
		return recordsAudit;
	}
	

}
