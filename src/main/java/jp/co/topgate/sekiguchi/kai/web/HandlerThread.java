package jp.co.topgate.sekiguchi.kai.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by sekiguchikai on 2016/12/02.
 */
public class HandlerThread extends Thread {
    /**
     * socket
     */
    private Socket socket;

    /**
     * コンストラクタ
     */
    public HandlerThread(Socket socket) {
        this.socket = socket;
        System.out.println("クライアントに接続されました " + socket.getRemoteSocketAddress());
    }

    /**
     * Thread実行のためのrunメソッド
     */
    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();

            HTTPRequest httpRequest = new HTTPRequest(inputStream);
            HTTPResponse httpResponse = new HTTPResponse(outputStream);

            String requestLine = httpRequest.getRequestLine();
            String secondSentence = httpRequest.getSecondSentence(requestLine);
            String requestURI = httpRequest.getRequestURI(secondSentence);

            String requestMethod = httpRequest.getRequestMethod(requestLine);


            Handler handler;

            if (requestURI.equals("/program/board/")) {
                handler = new MessageHandler();
                handler.handleGET(httpRequest, httpResponse);
            } else if (requestURI.equals("/program/board/registered") || requestURI.equals("/program/board/registered/afterDelete") || requestURI.equals("/program/board/registered/search") || requestURI.equals("/program/board/registered/showAll")) {
                handler = new MessageHandler();
                handler.handlePOST(httpRequest, httpResponse);
            } else {
                handler = new StaticFileHandler();
                if (requestMethod.equals("GET")) {
                    handler.handleGET(httpRequest, httpResponse);
                } else if (requestMethod.equals("POST")) {
                    handler.handlePOST(httpRequest, httpResponse);
                } else {
                    System.out.println("リクエストメソッドが不正です");
                }
            }

        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } finally {
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
            }
            System.out.println("クライアントとの通信を切断しました "
                    + socket.getRemoteSocketAddress());
        }
    }
}


