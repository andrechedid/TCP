/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpservernoconnectionissues;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPServerNoConnectionIssues{

    private static Socket connectionSocket;
    
	public static void main(String[] args) throws IOException {
		System.out.println("Server up and running, waiting for requests...");
		String clientSentence, capitalizedSentence;

		ServerSocket welcomeSocket = new ServerSocket(6789);
                
		int clientId = 0;
		int timeout=10000;
		while (true) {
                    try{
			// wait on welcoming socket for contact by client, creates a new socket
			connectionSocket = welcomeSocket.accept();
                        connectionSocket.setSoTimeout(timeout); // After 10 secs the server will drop the connection due to a timeout exception
			// create input stream attached to socket
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			// create output stream attached to socket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                        
                        clientId++;
			System.out.println("Processing client " + clientId);

			clientSentence = inFromClient.readLine(); // read in line from socket
			capitalizedSentence = clientSentence.toUpperCase() + '\n'; // make sure to add carriage return here!
			
			outToClient.writeBytes(capitalizedSentence + '\n'); // write out line to socket
			System.out.println("Done with client " + clientId);

		}catch(SocketTimeoutException e){
                    System.out.println("Client wasted too much time"); //Client didnt send any messages for the whole timeout period of 10secs where he just stayed connected not doing anything depriving other clients of the servers services.
                    connectionSocket.close();
                }catch(NullPointerException e){
                    System.out.println("No message found!"); //Client connected and disconnected leading to a NullPointerException due to the fact that the server was expecting a message but got a null instead
                    connectionSocket.close();
                }
                }
	}

}
