/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basictcpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author PC-A
 */
public class BasicTCPClient {
	
    public static void main(String[] args) throws UnknownHostException, IOException {
        try{
        String sentence; // data to send to server
        String modifiedSentence; // received from server

        System.out.println("Client is running, enter some text:");

        // create input stream, client reads line from standard input
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // create client socket and connect to server; this initiates TCP cnx between client and server
        Socket clientSocket = new Socket("localhost", 6789);
        
        // create output stream attached to socket
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // create input stream attached to socket
        BufferedReader inFromServer = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));

        sentence = inFromUser.readLine(); // read line from user
        
        outToServer.writeBytes(sentence + '\n'); //send line to server
        
        /*
        characters continue to accumulate in modifiedSentence until the line ends with a carriage return
        */
        modifiedSentence = inFromServer.readLine(); // read line from server
        
        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close(); // close TCP cnx between client and server

        System.out.println("Client closed the socket and is done execution");
    }catch(SocketException e){
        System.out.println("Server closed the connection you timed out!");
    }
    }
}
