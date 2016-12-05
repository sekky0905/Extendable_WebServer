package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.handler.Handler;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.util.ResponseHeaderMaker;
import jp.co.topgate.sekiguchi.kai.web.webApp.WebApp;
import jp.co.topgate.sekiguchi.kai.web.template.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.template.Template;
import jp.co.topgate.sekiguchi.kai.web.util.Session;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 1つのスレッドを表すクラス
 * Created by sekiguchikai on 2016/12/02.
 */
public class ServerThread extends Thread {
    /**
     * socket
     */
    private Socket socket;

    /**
     * コンストラクタ
     */
    public ServerThread(Socket socket) {
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
            String queryString = httpRequest.getQueryString(httpRequest.getRequestMethod());
            httpRequest.setRequestParameter(queryString);

            // Webサーバ
            if (!(WebApp.checkHandlerNameExistence(requestURI))) {
                StaticFileHandler staticFileHandler = new StaticFileHandler();
                staticFileHandler.ProcessWebServer(httpRequest, httpResponse);
            } else {

                // ハンドラの決定
                String handlerName = WebApp.getHandlerName(requestURI);
                WebApp.setHandlerMap();
                Handler handler = WebApp.getHandlerMap(handlerName);
                if (httpRequest.getRequestMethod().equals("GET")) {
                    handler.handleGET(httpRequest, httpResponse);
                } else if ((httpRequest.getRequestMethod().equals("POST")) && (Session.confirmToken(httpRequest.getRequestParameter("token")))) {
                    handler.handlePOST(httpRequest, httpResponse);
                    // レスポンスの処理
                    Template template = new IndexTemplate();

                    httpResponse.sendResponse("HTTP/1.1 200 OK", ResponseHeaderMaker.makeContentType("html"), template.writeHTML());
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