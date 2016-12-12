package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.web_app.WebApp;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.util.Session;

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
            httpRequest.setRequestParameter();

            // Webサーバ
            if (!(WebApp.handlerNameIsExist(requestURI))) {
                StaticFileHandler staticFileHandler = new StaticFileHandler();
                staticFileHandler.handleGET(httpRequest, httpResponse);
            } else {

                // ハンドラの決定
                String handlerName = WebApp.getHandlerName(requestURI);
                WebApp.setHandlerMap();
                Handler handler = WebApp.getHandlerMap(handlerName);


                if (requestURI.equals("/program/board/")) {
                    handler.handleGET(httpRequest, httpResponse);
                } else if (httpRequest.getRequestMethod().equals("GET") && (WebApp.handlerNameIsExist(requestURI))) {
                    httpResponse.setStatusLine(HTTPResponse.SC_NOT_FOUND);
                    Template template = new ErrorTemplate();
                    template.writeHTML(httpRequest, httpResponse);

                } else if ((httpRequest.getRequestMethod().equals("POST")) && (Session.confirmToken(httpRequest.getRequestParameter("token")))) {
                    handler.handlePOST(httpRequest, httpResponse);

                } else {
                    Template template = new IndexTemplate();
                    httpResponse.setStatusLine(HTTPResponse.SC_OK);
                    template.writeHTML(httpRequest, httpResponse);
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
                System.err.println("エラー:" + e.getMessage());
                e.printStackTrace();
            }

        }
    }
}