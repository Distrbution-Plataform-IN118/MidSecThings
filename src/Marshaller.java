import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Marshaller {
	public static byte[] marshall(Message msgToMarshalled) throws IOException 
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		objectStream.writeObject(msgToMarshalled);
		return byteStream.toByteArray();
	}
	
	public static Message unmarshall(byte [] msgToBeUnmarshalled) throws IOException, ClassNotFoundException 
	{
		ByteArrayInputStream byteStream = new ByteArrayInputStream(msgToBeUnmarshalled);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		
		return (Message) objectStream.readObject();
	}
	
}
