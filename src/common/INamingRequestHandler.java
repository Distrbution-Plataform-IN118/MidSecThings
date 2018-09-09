package common;
import java.io.IOException;


public interface INamingRequestHandler {
	public byte [] receive() throws IOException, InterruptedException;
	public void send(byte [] msg) throws IOException, InterruptedException;
}
