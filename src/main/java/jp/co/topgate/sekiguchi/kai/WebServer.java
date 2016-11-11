package jp.co.topgate.sekiguchi.kai;

import java.io.File;

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
 * socketの確立と他のクラスを呼び出す責務を持ったクラス
 * 
 * @author sekiguchikai
 *
 */
public class WebServer {

	/**
	 * PORT番号
	 */
	private static final int PORT = 8080;

	/**
	 * サーバソケット
	 */
	private ServerSocket serverSocket = null;

	/**
	 * ソケット
	 */
	private Socket socket = null;

	/**
	 * ファイルのバイナリデータ
	 */
	private byte[] fileContents;

	/**
	 * socketを確立し、他のクラスを呼び出すためのメソッド
	 * 
	 * @throws IOException
	 *             Socketの確立に失敗しました
	 */
	public void socketGenerator() throws IOException {
		// サーバが起動したことを知らせる
		System.out.println("Start the server at http://localhost:8080");

		try {

			this.serverSocket = new ServerSocket(PORT);

			while (true) {
				this.socket = this.serverSocket.accept();
				System.out.println("request incoming...");

				InputStream inputStream = this.socket.getInputStream();

				HTTPRequest httpRequest = new HTTPRequest();

				// リクエストからリクエストURIを取得
				System.out.println("リクエストの中身を取得します");
				String requestLine = httpRequest.getRequestLine(inputStream);

				System.out.println("リクエストの中身を表示します");
				httpRequest.showRequest();

				String requestURI = httpRequest.getRequestURI(requestLine);
				File file = httpRequest.getRequestResource(requestURI);

				// ファイルが存在するかの確認とファイルの読み込み
				FileReader fileReader = new FileReader();
				boolean fileExistence = fileReader.ConfirmExistence(file);

				this.fileContents = fileReader.getFileData(file, fileExistence);

				// レスポンスの中身を作る
				ResponseContentsMaker responseContentsMaker = new ResponseContentsMaker();
				String statesline = responseContentsMaker.makeStatusLine(fileExistence);

				String extension = httpRequest.getResourceExt(requestURI);
				String responsetHeader = responseContentsMaker.makeResponseHeader(extension);

				// レソポンス
				OutputStream outputStream = this.socket.getOutputStream();
				HTTPResponse httpResponse = new HTTPResponse();
				httpResponse.ResponseWriter(outputStream, statesline, responsetHeader, this.fileContents);

			}
		} catch (IOException e) {
			System.out.println("Socketの確立に失敗しました" + e);
			System.exit(1);

		} finally {
			// 各プロセスを閉じる
			serverSocket.close();

			socket.close();
		}

	}
}