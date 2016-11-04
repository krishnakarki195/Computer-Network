import java.io.*;
import java.net.*;

public class TCPServer {
	public static void main(String argv[]) throws Exception {

		String messageFromClient;
		ServerSocket serverSockect = new ServerSocket(6780);

		while (true) {
			Socket socketConnection = serverSockect.accept();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(socketConnection.getInputStream()));
			DataOutputStream dataOutputStream = new DataOutputStream(
					socketConnection.getOutputStream());
			messageFromClient = bufferedReader.readLine();
			
			System.out.println("From Client: " + messageFromClient);
			dataOutputStream.writeBytes(messageFromClient.toUpperCase());
		}

	}
}