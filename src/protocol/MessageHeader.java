package protocol;
import java.io.*;

public class MessageHeader implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String magic;//extra
	private int version;//versao
	private boolean byteOrder;//bitesignificativo
	private int messageType;//tipo da mensagem
	private long messageSize;//tamanho de mensagem
	public MessageHeader(String magic, int version, boolean byteOrder, int messageType, long messageSize)
	{
		this.magic = magic;
		this.version = version;
		this.byteOrder = byteOrder;
		this.messageType = messageType;
		this.messageSize = messageSize;
	}
	
	public String getMagic() {
		return magic;
	}

	public int getVersion() {
		return version;
	}

	public boolean getByteOrder() {
		return byteOrder;
	}

	public int getMessageType() {
		return messageType;
	}

	public long getMessageSize() {
		return messageSize;
	}
}
