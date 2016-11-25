package jp.co.topgate.sekiguchi.kai;

//フィルタをかけて流れてくるデータをバッファリングし、量がたまったら、一気に下流に流し込む

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//TCP サーバーAPI、通常はクライアントソケットからの接続を 受け入れる
import java.net.ServerSocket;
//TCP クライアント API、通常はリモートホストに接続するために使用される
import java.net.Socket;

//socketはットワーク上のマシン間で通信リンクを確立するための手段
//=>TCPを用いているので、ポート番号を用いて、IPが運んできたデータがどの上位プロトコルのものか識別する
//=>socketは、コンピュータの特定のポート番号と関連付けれられる
//例えば、8080ポートだったら、IPが運んできたデータはHTTPプロトコルのものと認識する
//sever socketは、acceptメソッドで、クライアントからの接続を待ち受け、クライアントからアクセスがあったら、
//socketを返す（これでクライアントは、接続する）

/**
 * socketの確立とControlerクラスを呼び出す責務を持ったクラス
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
	public static void main(String[] args) throws IOException {
		WebServer webServer = new WebServer();
		webServer.initialize();
	}

	/**
	 * socketを確立し、HTTPRequestクラスとHTTPResponseクラスをインスタンス化するクラス
	 */
	public void initialize() throws IOException {
		System.out.println("Start the server at http://localhost:8080");
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(PORT);
			while (true) {
				socket = serverSocket.accept();
				System.out.println("request incoming...");

				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();

				HTTPRequest httpRequest = new HTTPRequest(inputStream);
				HTTPResponse httpResponse = new HTTPResponse(outputStream);

				String requestLine = httpRequest.getRequestLine();

				String requestURI = httpRequest.getRequestURI(requestLine);

				String requestMethod = httpRequest.getRequestMethod(requestLine);
				Handler handler;

				if (requestURI.equals("/program/board/")) {
					handler = new MessageHandler();
					handler.handleGET(httpRequest, httpResponse);
				} else if (requestURI.equals("/program/board/registered")) {
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

				if (socket != null) {
					socket.close();
				}

			}

		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			System.exit(1);
		} finally {
			if (serverSocket != null) {
				serverSocket.close();
			}

			if (socket != null) {
				socket.close();
			}
		}

	}
}