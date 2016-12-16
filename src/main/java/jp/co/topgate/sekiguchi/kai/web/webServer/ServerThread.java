package jp.co.topgate.sekiguchi.kai.web.webserver;


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

            Handler handler = HandlerStorage.getHandler(httpRequest.getRequestURI());

            if (httpRequest.getRequestMethod().equals("GET")) {
                try {
                    handler.handleGET(httpRequest, httpResponse);
                } catch (Exception e) {
                    System.err.println("エラー:" + e.getMessage());
                    e.printStackTrace();

                    // アプリケーション側の例外をサーバでcatch
                    ErrorTemplate template = new ErrorTemplate();
                    template.setErrMessage("500 Internal Server Error");
                    template.writeHTML(httpRequest, httpResponse);
                    httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", "html");
                }


            } else if (httpRequest.getRequestMethod().equals("POST")) {
                try {
                    handler.handlePOST(httpRequest, httpResponse);
                } catch (Exception e) {
                    System.err.println("エラー:" + e.getMessage());
                    e.printStackTrace();

                    // アプリケーション側の例外をサーバでcatch
                    ErrorTemplate template = new ErrorTemplate();
                    template.setErrMessage("500 Internal Server Error");
                    template.writeHTML(httpRequest, httpResponse);
                    httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", "html");
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



