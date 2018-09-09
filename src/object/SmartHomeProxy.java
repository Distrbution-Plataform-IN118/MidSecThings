package object;

import java.lang.reflect.Parameter;
import java.security.Policy.Parameters;
import java.util.ArrayList;
import java.util.List;

import common.ClientProxy;
import common.Invocation;
import common.Requestor;
import common.Termination;
import model.Item;
import model.Items;
import security.audit.AuditRecord;

public class SmartHomeProxy extends ClientProxy implements ISmartHome{
	
	private static final long serialVersionUID = 1L;
	private Requestor requestor;
	
	public SmartHomeProxy(String host, int port) {
		super(host, port);
		requestor = new Requestor(host, port);
		// TODO Auto-generated constructor stub
	}
	@Override
	public Items listarTodasCoisas() throws Throwable {
		// TODO Auto-generated method stub
		
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (Items) ter.getResult();
	}

	@Override
	public Item recuperarItem(String token_current_user, String uuid) throws Throwable {
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(token_current_user);
		parameters.add(uuid);
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (Item) ter.getResult();
		
	}
	@Override
	public String atualizaItem(String token_current_user, String uuid, String status) throws Throwable {
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(token_current_user);
		parameters.add(uuid);
		parameters.add(status);
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (String) ter.getResult();
	}
	@Override
	public String login(String user, String senha) throws Throwable {
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(user);
		parameters.add(senha);
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		requestor.getCrh().setClient(true);
		return (String) ter.getResult();
	}
	@Override
	public String logout(String token) throws Throwable {
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(token);
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (String) ter.getResult();
	}
	@Override
	public Integer numberAttempts(String token_current_user, String name_user) throws Throwable {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(token_current_user); //token_current_user
		parameters.add(name_user);//name_user
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (Integer) ter.getResult();
	}
	@Override
	public List<AuditRecord> listRecordsAudit(String token) throws Throwable {
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(token);
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (List<AuditRecord>) ter.getResult();
	
	}
	@Override
	public String atualizarItemsArea(String token_current_user, String lugar_casa, String status) throws Throwable {
		// TODO Auto-generated method stub
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(token_current_user);
		parameters.add(lugar_casa);
		parameters.add(status);
		class Local{}
		String methodName = null;
	
		
		methodName = Local.class.getEnclosingMethod().getName();
		//Informacao enviada para Requestor
		inv.setObjectId(this.getObjectId());
		inv.setHost(this.getHost());
		inv.setPort(this.getPort());
		inv.setMethodName(methodName);
		inv.setParameters(parameters);
		ter = requestor.invoke(inv);
		
		return (String) ter.getResult();
	}

	
}
