//package UDPLab;

import java.util.*;
import java.net.*;
import java.text.*;

public class PingClient {

	public static void main(String[] args) throws Exception {

		// Requires host and port number.
		if (args.length != 2) {
			System.out.println("Please enter host and port.");
			return;
		} else {
			System.out.println("\nHost: " + args[0] + "\nPort number: " + args[1] + "\n");
		}

		// Get host by his name
		InetAddress host = InetAddress.getByName(args[0]);

		// Get informed port
		int portNumber = Integer.parseInt(args[1]);

		// Create a datagram socket used for sending and recieving packets
		DatagramSocket socket = new DatagramSocket();
		socket.setSoTimeout(1000);

		// Start loop to send packets
		for (int i = 0; i < 10; i++) {
			// Create ping message
			String message = "Test" + i;
			long sentTime = System.currentTimeMillis();
			String pingMessage = pingMessage(message);

			// Create send packet
			DatagramPacket sendPacket = new DatagramPacket(pingMessage.getBytes(), pingMessage.length(), host,
					portNumber);

			// Send ping request
			socket.send(sendPacket);
			System.out.print(pingMessage);

			// Datagram packet for the server response
			DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);

			// Wait for ping response
			try {
				// Response received
				socket.receive(receivePacket);
				long receivedTime = System.currentTimeMillis();
				System.out.println("Response received from " + receivePacket.getAddress().getHostAddress() + " "
						+ "(time=" + (receivedTime - sentTime) + "ms)");
			}
			// Catch timeout exception
			catch (SocketTimeoutException e) {
				System.out.println("Timeout");
			}
			// Catch other exceptions
			catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}

		}
	}

	private static String pingMessage(String message) {
		return "ping message " + message + " ";
	}

}
