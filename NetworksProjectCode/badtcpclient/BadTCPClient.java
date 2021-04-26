/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badtcpclient;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author PC-A
 */
public class BadTCPClient {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Client is running");
		Socket clientSocket = new Socket("localhost", 6789);
		clientSocket.close(); // close TCP cnx between client and server
                //This client will just connect and disconnect without sending any data in hopes of having the server crash due to a null pointer exception
	}
	
}
