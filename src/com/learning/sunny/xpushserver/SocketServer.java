package com.learning.sunny.xpushserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class SocketServer {
    private Timer mHeartBeatsTimer = null;

    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();
        socketServer.start();
    }

    private void manageConnection(Socket socket) {
        new Thread(() -> {
            BufferedReader inputReader = null;
            //发送消息
            BufferedWriter outPutWriter = null;
            try {
                System.out.println("Client-" + socket.hashCode() + " Connected...");
                inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                outPutWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String receiveMsg;
                while ((receiveMsg = inputReader.readLine()) != null) {
                    System.out.println(receiveMsg);
                    outPutWriter.write("Server have received Client-" + socket.hashCode() + ":" + receiveMsg + "\n");
                    outPutWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputReader != null) {
                        inputReader.close();
                    }
                    if (outPutWriter != null) {
                        outPutWriter.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    private void heartBeatsTask(BufferedWriter writer) {
        this.mHeartBeatsTimer = new Timer();
        this.mHeartBeatsTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Heart Beats once...");
                    writer.write("Heart Beats once...\n");
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);
    }

    private void start() {
        //接收消息
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9898);
            System.out.println("Server Started...");
            //可用于返回消息
            while (true) {
                Socket socket = serverSocket.accept();
                manageConnection(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
