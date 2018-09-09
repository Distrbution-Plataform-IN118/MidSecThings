package common;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.crypto.CipherInputStream;

import protocol.Message;


public class Marshaller {
	//metodo que serializa a messagem -> array byte
	public static byte[] marshall(Message msgToMarshalled) throws IOException 
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		objectStream.writeObject(msgToMarshalled);
		return byteStream.toByteArray();
	}
	//metodo que deserializa a  array byte -> messagem
	public static Message unmarshall(byte [] msgToBeUnmarshalled) throws IOException, ClassNotFoundException 
	{
		ByteArrayInputStream byteStream = new ByteArrayInputStream(msgToBeUnmarshalled);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		
		return (Message) objectStream.readObject();
	}
	
}
