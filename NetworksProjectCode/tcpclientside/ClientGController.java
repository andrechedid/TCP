package tcpclientside;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author PC-A
 */
public class ClientGController implements Initializable {

    @FXML
    private TextArea msgSent;
    @FXML
    private TextArea msgRec;
    @FXML
    private TextField portNumber;
    @FXML
    private TextField ipAdd;
    @FXML
    private TextField msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void send(ActionEvent event) throws IOException {
        String host=ipAdd.getText();
        int port=Integer.parseInt(portNumber.getText());
        Socket clientSocket=new Socket(host,port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(msg.getText() + '\n');
        msgRec.setText(inFromServer.readLine());
        msgSent.setText(msg.getText());
        clientSocket.close();
    }
    
}
