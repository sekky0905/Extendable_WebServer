package jp.co.topgate.sekiguchi.kai.web.webserver;

import jp.co.topgate.sekiguchi.kai.web.webapp.WebApp;
import jp.co.topgate.sekiguchi.kai.web.webapp.WebAppStorage;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.IndexTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 1つのスレッドを表すクラス
 * Created by sekiguchikai on 2016/12/15.
 */
public class ServerThread extends Thread {
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
                try {
                    handler.handleGET(httpRequest, httpResponse);
                } catch (Exception e) {
                    System.err.println("エラー:" + e.getMessage());
                    e.printStackTrace();

                    // アプリケーション側の例外をサーバでcatch
                    Template template = new IndexTemplate();
                    httpResponse.addStatusLine(HTTPResponse.SC_INTERNAL_SERVER_ERROR);
                    template.writeHTML(httpRequest, httpResponse);
                }


            } else if (httpRequest.getRequestMethod().equals("POST")) {
                try {
                    handler.handlePOST(httpRequest, httpResponse);
                } catch (Exception e) {
                    System.err.println("エラー:" + e.getMessage());
                    e.printStackTrace();

                    // アプリケーション側の例外をサーバでcatch
                    Template template = new IndexTemplate();
                    httpResponse.addStatusLine(HTTPResponse.SC_INTERNAL_SERVER_ERROR);
                    template.writeHTML(httpRequest, httpResponse);
                }
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



