/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udpclient;

/**
 *
 * @author PC-A
 */
import java.io.*;
import java.net.*;

public class UDPClient{

    public static void main(String[]args)throws Exception {

        // create input stream, client reads line from standard input
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        // create client socket
        DatagramSocket clientSocket = new DatagramSocket();

        // translate hostname to IP address using DNS
        InetAddress IPAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        String sentence = inFromUser.readLine();
        sendData = sentence.getBytes();

        // create datagram with data to send length, IP address, and port number
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                IPAddress, 9876);

        // send datagram to server
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        // read datagram from server
        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData()); // extract data from received packet
        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();

    }
    }