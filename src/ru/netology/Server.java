package ru.netology;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static PrintWriter out;
    public static final int PORT = 8080;

    @Override
    public void run(){
        try {
            try  {
                server = new ServerSocket(PORT);
                System.out.println("Сервер запущен");
                clientSocket = server.accept();
                System.out.println("Соединение установлено");
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                            clientSocket.getOutputStream())), true);
                    String name = in.readLine();
                    out.println(String.format("Привет %s, твой порт %d", name, clientSocket.getPort()));
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
