/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hynek
 */
public class ServerRecieve implements Runnable{
    Thread v;
    public static ServerSocket server;
    Socket cekac;
    ArrayList<BufferedReader> readers;
    
    public ServerRecieve(){
        v = new Thread(this, "Server");
        v.start();
    }
    
    @Override
    public void run() {
        Activate();
    }
    
    public void Activate(){
        try {
            server = new ServerSocket(666);
            readers = new ArrayList<>();            
            listen();
        } catch (IOException ex) {
            Logger.getLogger(ServerRecieve.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void listen(){
        while(true){
            try {
                cekac = server.accept();
            } catch (IOException ex) {
                Logger.getLogger(ServerRecieve.class.getName()).log(Level.SEVERE, null, ex);
            }
            Thread accept = new Thread(new Runnable(){
                public void run(){
                    Socket client = cekac;
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
                        while (true){
                            String input = in.readLine();
                            if(!"".equals(input) && input != null){
                                String data = input.substring(5);
                                String command = input.substring(0, 5);
                            }else{
                                break;
                            }
                        }
                        client.close();
                        in.close();
                    } catch (IOException ex) {
                        try {
                            client.close();
                        } catch (IOException ex1) {
                            System.err.println("[ServerRecieve] Failed to close the client.");
                        }
                    }
                }
            });
            accept.start();
        }
    }
    
}
