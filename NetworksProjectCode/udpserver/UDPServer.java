/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpserver;

/**
 *
 * @author PC-A
 */
import java.io.*;
import java.net.*;

public class UDPServer {

    public static void main(String[] args) throws Exception {

        // create a datagram socket at port 9876
        DatagramSocket serverSocket = new DatagramSocket(9876);

        // we put these here but better to put them in while loop
        // (this was the cause of the error we got earlier in class when we ran a new client with a
        // message shorter than the previous client's message
        byte [] receiveData = new byte[1024];
        byte [] sendData = new byte[1024];

        while(true){

            // create space for received datagram
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // receive datagram
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            // get IP address, port number of sender
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();

            // create datagram to send to client
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

            // write out datagram to socket
            serverSocket.send(sendPacket);

        } // loop back and wait for another datagram

    }
}