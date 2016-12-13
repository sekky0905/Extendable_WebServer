package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.web_app.WebApp;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.web_app.WebAppStorage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 1つのスレッドを表すクラス
 * Created by sekiguchikai on 2016/12/02.
 */
class ServerThread extends Thread {
    /**
     * socket socket
     */
    private final Socket socket;

    /**
     * コンストラクタ
     * socketインスタンスを受け取り、本クラスのsocketフィールドに設定する
     */
    ServerThread(Socket socket) {
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

            String requestURI = httpRequest.getRequestURI();

            WebApp webApp = WebAppStorage.getWebApp(requestURI);
            Handler handler = webApp.getHandler(requestURI);

            if (httpRequest.getRequestMethod().equals("GET")) {
                handler.handleGET(httpRequest, httpResponse);

            } else if ((httpRequest.getRequestMethod().equals("POST")) && (Token.confirmToken(httpRequest.getRequestParameter("token")))) {
                handler.handlePOST(httpRequest, httpResponse);
            }


        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (this.socket != null) {
                    this.socket.close();
                }
            } catch (IOException e) {
                System.err.println("エラー:" + e.getMessage());
                e.printStackTrace();
            }

        }
    }
}