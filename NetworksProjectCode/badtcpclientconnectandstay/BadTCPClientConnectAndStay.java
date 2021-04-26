/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package badtcpclientconnectandstay;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author PC-A
 */
public class BadTCPClientConnectAndStay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        System.out.println("Client is running, enter some text:");


        // create client socket and connect to server; this initiates TCP cnx between client and server
        Socket clientSocket = new Socket("localhost", 6789);
        while(true){
            //this will lead to the server just wasting time waiting for a message to be sent which will lead to bad service on behalf of the server to other clients waiting for this client to be done which will not happen
        }
    }
}
