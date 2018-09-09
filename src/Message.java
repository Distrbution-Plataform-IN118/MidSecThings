import java.io.Serializable;


public class Message implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private MessageHeader header;
	private MessageBody body;
	
	public Message(MessageHeader messageHeader, MessageBody messageBody) {
		// TODO Auto-generated constructor stub
		this.header = messageHeader;
		this.body = messageBody;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public MessageHeader getHeader(){
		return this.header;
	}
	public MessageBody getBody() {
		// TODO Auto-generated method stub
		return this.body;
	}

}
