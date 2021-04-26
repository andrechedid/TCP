/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserverside;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class ServerG implements Initializable {

    @FXML
    private TextField clientMessage;
    @FXML
    private TextField updatedMessage;
    @FXML
    private JFXButton clearHistory;
    @FXML
    private Text clientInfo;
    @FXML
    private TextArea clientSHistory;

    private static ServerSocket welcomeSocket;
    private static Socket connectionSocket;
    Thread t;
    boolean isRunning=true;
    private static String cs,CS,History="";
    
    /**
     * 
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            welcomeSocket=new ServerSocket(6789);
            t=new Thread(this::handleThread);
            t.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerG.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void handleThread(){
        while(isRunning){
            try{
                connectionSocket=welcomeSocket.accept();
                BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
                cs=inFromClient.readLine();
                CS=cs.toUpperCase() + "\n";
                outToClient.writeBytes(CS + "\n");
                History+=cs + "-->" + CS + "(" + connectionSocket.getLocalAddress().toString().substring(1) + "," + connectionSocket.getPort() + ") \n";
                Platform.runLater(() -> {
                    clientInfo.setText("Client:(" + connectionSocket.getLocalAddress().toString().substring(1) + "," + connectionSocket.getPort() + ")");
                    clientMessage.setText(cs);
                    updatedMessage.setText(CS);
                    clientSHistory.setText(History);
                });
            }catch(Exception e){
                
            }
        }
    }
    
    @FXML
    private void clearHistory(ActionEvent event) {
        History="";
        clientSHistory.setText(History);
    }
    
}
