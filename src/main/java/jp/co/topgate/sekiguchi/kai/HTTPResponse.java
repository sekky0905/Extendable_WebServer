package jp.co.topgate.sekiguchi.kai;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 
 * クライアントへ送信するHTTPレスポンスに関する責務を持つクラス
 * 
 * @author sekiguchikai
 *
 */
public class HTTPResponse {

	/**
	 * 
	 * @param outputStream
	 *            クライアントとの通信で使うsocketインスタンスを格納したストリーム
	 * @param statusLine
	 *            クライアントへのレスポンスで使用するステータスライン
	 * @param responseHeader
	 *            クライアントへのレスポンスで使用するレスポンスヘッダ
	 * @param byteContents
	 *            クライアントへのレスポンスで使用するレスポンスボディ（GETで指定されたリソースのバイナリデータ）
	 * 
	 * @exception IOException
	 *                送信エラーです
	 * 
	 */
	public void ResponseWriter(OutputStream outputStream, String statusLine, String responseHeader, byte[] byteContents)
			throws IOException {

		System.out.println("クライアントに送信します");
		System.out.println("ステータスラインは" + statusLine);
		System.out.println("レスポンスヘッダは" + responseHeader);
		System.out.println("レスポンスボディは" + byteContents);

		try {

			System.out.println("クライアントに送信を開始します");

			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

			StringBuilder builder = new StringBuilder();

			builder.append(statusLine).append("\n"); // "HTTP/1.1
														// 200
														// OK"など

			builder.append(responseHeader).append("\n"); // "Content-Type:
															// text/html"など
			builder.append("\n");

			// PrintWriter:テキスト出力ストリームに出力する
			PrintWriter writer = new PrintWriter(outputStream, true);

			// Stringに変えて、出力ストリームに送信
			writer.print(builder.toString());

			System.out.println("bsの中身は" + byteContents);

			dataOutputStream.write(byteContents, 0, byteContents.length);

			dataOutputStream.flush();

			dataOutputStream.close();

		} catch (IOException e) {
			System.out.println("送信エラーです");
			e.printStackTrace();
		}
	}

}