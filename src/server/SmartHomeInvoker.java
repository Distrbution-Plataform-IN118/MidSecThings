package server;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.ClientProxy;
import common.Marshaller;
import common.MethodNameRemoteObject;
import common.Termination;
import model.Item;
import model.Items;
import object.SmartHomeImpl;
import performance.Metrics;
import protocol.Message;
import protocol.MessageBody;
import protocol.MessageHeader;
import protocol.ReplyBody;
import protocol.ReplyHeader;
import security.audit.AuditManager;
import security.audit.AuditRecord;
import security.auth.AuthenticatorManager;
import security.custom.AccessPolicyManager;
import util.Crypter;

public class SmartHomeInvoker implements Runnable {
	private ServerRequestHandler srh;
	private static Logger logger = LoggerFactory.getLogger(SmartHomeInvoker.class);
	private static  int counter_invoker_performance = 0;
	private SmartHomeImpl rObj;
	private ClientProxy clientProxy;
	private boolean isFirstTime=true;

	public SmartHomeInvoker() {
	}

	public SmartHomeInvoker(ServerRequestHandler srh) {
		this.srh = srh;

		/*
		 * idStaticInvoker++; this.idInstanceInvoker = idStaticInvoker;
		 */
	}

	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {

		while (true) {

			/*
			 * CalculatorInvoker cv = checkInvokerAOR(clientProxy); new Thread(cv).start();
			 */
			this.clientProxy = new ClientProxy(clientProxy.getHost(), clientProxy.getPort());
			this.clientProxy.setObjectId(clientProxy.getObjectId());
			new Thread(new SmartHomeInvoker(new ServerRequestHandler(clientProxy.getPort()))).start();

		}
		// (new Thread(new CalculatorInvoker(new
		// ServerRequestHandler(clientProxy.getPort())))).start();

	}

	@Override
	public void run() {
		long  startTime = System.nanoTime();

		byte[] msgMarshalled = null;
		byte[] msgToBeUnmarshalled = null;
		Message msgUnmarshalled = null;
		Termination ter = new Termination();
		// variaveis de criptografia & descriptografia
		byte[] msgToBeDecripted = null;
		byte[] msgEncripted = null;
		// inversion loop
		try {
			Crypter crypter = new Crypter();
			crypter.init(srh);

			while (true) {
				// @ Receive Message
				try {
					msgToBeDecripted = srh.receive();
				} catch (IOException e) {
					break;
				}

				// @ Unmarshall received message
				msgToBeUnmarshalled = crypter.decrypt(msgToBeDecripted);
				msgUnmarshalled = Marshaller.unmarshall(msgToBeUnmarshalled);
				Float operando1 = null;
				Float operando2 = null;
				Float resultado = null;
				Message messagem_retorno = null;
				String identifiedMagic = null;
				Items items = null;
				AuditRecord record = null;
				String operation = msgUnmarshalled.getBody().getRequestHeader().getOperation();
				switch (operation) {

				case "listarTodasCoisas":
					logger.info("Operacao =  listar todas coisas");
					// @ Invokes the remote object

					rObj = new SmartHomeImpl();
					items = rObj.listarTodasCoisas();

					ter.setResult(items);

					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				case "login":
					logger.info("Operacao =  login");
					// @ Invokes the remote object
					String user = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					String senha = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
					rObj = new SmartHomeImpl();
					String token_login = rObj.login(user, senha);
					ter.setResult(token_login);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				case "logout":
					logger.info("Operacao =  logout");
					String token_logout = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
					rObj = new SmartHomeImpl();
					String token_output = rObj.logout(token_logout);
					ter.setResult(token_output);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				case "recuperarItem":
					//logger.info("Operacao =  recuperarItem");
					String token_recuperar_item = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(0);
					String uuid_recuperar_item = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(1);
					rObj = new SmartHomeImpl();
					String user_session_recuperar_item = AuthenticatorManager.getInstance()
							.getUserSession(token_recuperar_item);
					String permission_recuperar_item = AccessPolicyManager.getInstance()
							.getPriviligies(user_session_recuperar_item, operation);
					String role_recuperar_item = AccessPolicyManager.getInstance()
							.getRoleTypeUser(user_session_recuperar_item);
					record = new AuditRecord(operation, user_session_recuperar_item, permission_recuperar_item,
							role_recuperar_item, new Date());
					AuditManager.getInstance().addAuditRepository(record);
					Item recuperar_item = rObj.recuperarItem(token_recuperar_item, uuid_recuperar_item);
					ter.setResult(recuperar_item);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;
				case "atualizaItem":

					//logger.info("Operacao =  atualizaItem");
					String token_atualizar_item = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(0);
					String uuid_atualizar_item = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(1);
					String state_atualizar_item = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(2);

					rObj = new SmartHomeImpl();
					String user_session_atualizar_item = AuthenticatorManager.getInstance()
							.getUserSession(token_atualizar_item);
					String permission_atualizar_item = AccessPolicyManager.getInstance()
							.getPriviligies(user_session_atualizar_item, operation);
					String role_atualizar_item = AccessPolicyManager.getInstance()
							.getRoleTypeUser(user_session_atualizar_item);
					record = new AuditRecord(operation, user_session_atualizar_item, permission_atualizar_item,
							role_atualizar_item, new Date());
					AuditManager.getInstance().addAuditRepository(record);

					String response_atualizar_item = rObj.atualizaItem(token_atualizar_item, uuid_atualizar_item,
							state_atualizar_item);
					ter.setResult(response_atualizar_item);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				case "numberAttempts":
					logger.info("Operacao =  numberAttempts");
					String token_usuario_attempt = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(0);
					String name_user_attempt = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(1);
					rObj = new SmartHomeImpl();
					Integer attempt_int = rObj.numberAttempts(token_usuario_attempt, name_user_attempt);
					ter.setResult(attempt_int);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				case MethodNameRemoteObject.LIST_RECORDS_AUDIT:
					logger.info("Operacao =  listRecordAudit");
					rObj = new SmartHomeImpl();
					String token_list_records_audit = (String) msgUnmarshalled.getBody().getRequestBody()
							.getParameters().get(0);
					List<AuditRecord> list_audit = rObj.listRecordsAudit(token_list_records_audit);
					ter.setResult(list_audit);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				case MethodNameRemoteObject.UPDATE_ITEMS_AREA:

					//logger.info("Operacao =  Autualizar Item Area");
					String token_atualizar_item_area = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(0);
					String lugar_casa_atualizar_item_area = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(1);
					String state_atualizar_item_area = (String) msgUnmarshalled.getBody().getRequestBody().getParameters()
							.get(2);
					//logger.info(lugar_casa_atualizar_item_area);
					rObj = new SmartHomeImpl();
					String user_session_atualizar_item_area = AuthenticatorManager.getInstance()
							.getUserSession(token_atualizar_item_area);
					String permission_atualizar_item_area = AccessPolicyManager.getInstance()
							.getPriviligies(user_session_atualizar_item_area, operation);
					String role_atualizar_item_area = AccessPolicyManager.getInstance()
							.getRoleTypeUser(user_session_atualizar_item_area);
					record = new AuditRecord(operation, user_session_atualizar_item_area, permission_atualizar_item_area,
							role_atualizar_item_area, new Date());
					AuditManager.getInstance().addAuditRepository(record);

					String response_atualizar_item_area = rObj.atualizarItemsArea(token_atualizar_item_area, lugar_casa_atualizar_item_area,
							state_atualizar_item_area);
					ter.setResult(response_atualizar_item_area);
					messagem_retorno = new Message(new MessageHeader("protocol", 0, false, 0, 0),
							new MessageBody(null, null, new ReplyHeader("", 0, 0), new ReplyBody(ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);

					msgEncripted = crypter.encrypt(msgMarshalled);
					// @ Send response
					srh.send(msgEncripted);
					break;

				default:
					break;
				}
				if(isFirstTime) {
				Metrics.UNMARSHALL_CLIENT_DURATION_LIST[counter_invoker_performance] = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
				counter_invoker_performance++;
				isFirstTime =false;
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
