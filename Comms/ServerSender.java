/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comms;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Hynek
 */
public class ServerSender {
    public static Socket socket;
    public static void send(String ip, int port, String message){
        try {
            socket = new Socket(ip, port);
        } catch (IOException ex) {
            error();
        }
        
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(message + "\r\n");
            out.flush();
            socket.close();
        } catch (IOException ex) {
            error();
        }
    }

    public static String ask(String ip, int port, String message){
        try {
            socket = new Socket(ip, port);
        } catch (IOException ex) {
            return error();
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(message + "\r\n");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            String input = in.readLine();
            socket.close();

            return input;
        } catch (IOException ex) {
            return error();
        }
    }

    private static String error() {
        System.err.println("unable to connect");
        return "Error";
    }
}
