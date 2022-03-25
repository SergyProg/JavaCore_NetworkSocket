package ru.netology;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{
    private static Socket clientSocket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static String ipAddr = "localhost";
    public static int port = 8080;

   @Override
   public void run(){
        try {
            try {
                clientSocket = new Socket(ipAddr, port);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        clientSocket.getOutputStream())), true);

                out.println("Иван\n");
                String resp = in.readLine();
                System.out.println(resp);
            } finally {
                System.out.println("Соединение разорвано");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}

