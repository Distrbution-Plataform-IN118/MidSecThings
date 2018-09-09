package object;

import java.util.List;

import model.Item;
import model.Items;
import security.audit.AuditRecord;

public interface ISmartHome  {
	//Items
	Items listarTodasCoisas()throws Throwable;

	Item recuperarItem(String token_current_user, String uuid)throws Throwable;
	String atualizaItem(String token_current_user, String uuid, String status)throws Throwable;
//	void visualizarStatusFirmaware(String uuid)throws Throwable;
//	void visualizarStatusCoisa(String uuid)throws Throwable;

	String login(String user, String senha)throws Throwable;
	
	String logout(String token)throws Throwable;
	
	String atualizarItemsArea(String token_current_user, String lugar_casa, String status)throws Throwable;	
	List<AuditRecord> listRecordsAudit(String token) throws Throwable;
	Integer numberAttempts(String  token_current_user, String name_user)throws Throwable;
	
	
	
	
}
