import java.io.IOException;

public class CalculatorInvoker implements Runnable {
	private ServerRequestHandler srh;

	public CalculatorInvoker() {
	}

	public CalculatorInvoker(ServerRequestHandler srh) {
		this.srh = srh;
	}

	public void invoke(ClientProxy clientProxy) throws IOException, Throwable {

		while (true) {
			(new Thread(new CalculatorInvoker(new ServerRequestHandler(
					clientProxy.getPort())))).start();
		}
		// (new Thread(new CalculatorInvoker(new
		// ServerRequestHandler(clientProxy.getPort())))).start();

	}

	@Override
	public void run() {

		byte[] msgMarshalled = null;
		byte[] msgToBeUnmarshalled = null;
		Message msgUnmarshalled = null;
		Termination ter = new Termination();

		CalculatorImpl rObj;
		// inversion loop
		try {

			while (true) {
				// @ Receive Message
				try {
					msgToBeUnmarshalled = srh.receive();
				} catch (IOException e) {
					break;
				}

				// @ Unmarshall received message
				msgUnmarshalled = Marshaller.unmarshall(msgToBeUnmarshalled);
				Float operando1 = null;
				Float operando2 = null;
				Float resultado = null;
				Message messagem_retorno = null;

				switch (msgUnmarshalled.getBody().getRequestHeader()
						.getOperation()) {
				case "add":
					// @ Invokes the remote object
					operando1 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(0);
					operando2 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(1);

					rObj = new CalculatorImpl();
					resultado = rObj.add(operando1, operando2);
					ter.setResult(resultado);

					messagem_retorno = new Message(new MessageHeader(
							"protocol", 0, false, 0, 0), new MessageBody(null,
							null, new ReplyHeader("", 0, 0), new ReplyBody(
									ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);
					// @ Send response
					srh.send(msgMarshalled);
					break;

				case "sub":
					// @ Invokes the remote object
					operando1 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(0);
					operando2 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(1);

					rObj = new CalculatorImpl();
					resultado = rObj.sub(operando1, operando2);
					ter.setResult(resultado);

					messagem_retorno = new Message(new MessageHeader(
							"protocol", 0, false, 0, 0), new MessageBody(null,
							null, new ReplyHeader("", 0, 0), new ReplyBody(
									ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);
					// @ Send response
					srh.send(msgMarshalled);
					break;

				case "mul":
					// @ Invokes the remote object
					operando1 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(0);

					operando2 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(1);

					rObj = new CalculatorImpl();
					resultado = rObj.mul(operando1, operando2);
					ter.setResult(resultado);

					messagem_retorno = new Message(new MessageHeader(
							"protocol", 0, false, 0, 0), new MessageBody(null,
							null, new ReplyHeader("", 0, 0), new ReplyBody(
									ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);
					// @ Send response
					srh.send(msgMarshalled);
					break;
				case "div":
					// @ Invokes the remote object
					operando1 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(0);

					operando2 = (Float) msgUnmarshalled.getBody()
							.getRequestBody().getParameters().get(1);

					rObj = new CalculatorImpl();
					resultado = rObj.div(operando1, operando2);
					ter.setResult(resultado);

					messagem_retorno = new Message(new MessageHeader(
							"protocol", 0, false, 0, 0), new MessageBody(null,
							null, new ReplyHeader("", 0, 0), new ReplyBody(
									ter.getResult())));

					// @ Marshall the response
					msgMarshalled = Marshaller.marshall(messagem_retorno);
					// @ Send response
					srh.send(msgMarshalled);
					break;
				default:
					break;
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
