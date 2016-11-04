import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

	public static void main(String argv[]) throws Exception {

		String myMessage;
		String messageFromServer;

		BufferedReader userBufferedReader = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket = new Socket("localhost", 6780);
		DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader serverBufferedReader = new BufferedReader(
				new InputStreamReader(clientSocket.getInputStream()));
		myMessage = userBufferedReader.readLine();

		dataOutputStream.writeBytes(myMessage + '\n');
		messageFromServer = serverBufferedReader.readLine();
		System.out.println("From Server: " + messageFromServer);
		clientSocket.close();

	}

}