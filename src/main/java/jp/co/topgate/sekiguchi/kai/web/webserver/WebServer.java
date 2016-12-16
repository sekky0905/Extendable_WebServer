package jp.co.topgate.sekiguchi.kai.web.webserver;

// フィルタをかけて流れてくるデータをバッファリングし、量がたまったら、一気に下流に流し込む


import java.io.IOException;
// TCP サーバーAPI、通常はクライアントソケットからの接続を 受け入れる
import java.net.ServerSocket;
// TCP クライアント API、通常はリモートホストに接続するために使用される
import java.net.Socket;

// socketはットワーク上のマシン間で通信リンクを確立するための手段
// =>TCPを用いているので、ポート番号を用いて、IPが運んできたデータがどの上位プロトコルのものか識別する
// =>socketは、コンピュータの特定のポート番号と関連付けれられる
// 例えば、8080ポートだったら、IPが運んできたデータはHTTPプロトコルのものと認識する
// sever socketは、acceptメソッドで、クライアントからの接続を待ち受け、クライアントからアクセスがあったら、
// socketを返す（これでクライアントは、接続する）

/**
 * socketの確立とControllerクラスを呼び出す責務を持ったクラス
 *
 * @author sekiguchikai
 */
public class WebServer {

    /**
     * PORT番号
     */
    private static final int PORT = 8080;

    /**
     * メインメソッド
     */
    public static void main(String[] args) {
        WebServer webServer = new WebServer();
        webServer.initialize();
    }

    /**
     * socketを確立し、HTTPRequestクラスとHTTPResponseクラスをインスタンス化するクラス
     */
    private void initialize() {
        System.out.println("Start the server at http://localhost:8080");
        ServerSocket serverSocket = null;

        HandlerStorage.initializeHandler();

        try {
            serverSocket = new ServerSocket(PORT);

            System.out.println("サーバが起動しました");
            // Handlerインスタンスを使い回せる
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                System.err.println("エラー:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}






