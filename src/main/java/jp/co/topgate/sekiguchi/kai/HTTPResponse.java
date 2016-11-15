package jp.co.topgate.sekiguchi.kai;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * クライアントへ送信するHTTPレスポンスに関する責務を持つクラス
 * 
 * @author sekiguchikai
 */
public class HTTPResponse {
	/**
	 * クライアントとのsocketを格納したOutputStream
	 */
	private OutputStream outputStream;

	HTTPResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	/**
	 * リクエストURIで指定されたファイルを読み込み、クライアントにレスポンスするクラス
	 * 
	 * @param requestURI
	 *            リクエストURI
	 */
	public void controlResponse(String requestURI) {
		// 読み込むファイルの取得
		File file = new File("src/main/resources" + requestURI);

		// データの読み込み
		byte[] byteContents = readFile(requestURI, file);

		// 拡張子の取得
		String fileExtension = FileExtension.getFileExtension(requestURI);

		// ステータスラインの取得
		String statusLine = makeStatusLine(file);

		// レスポンスヘッダの取得
		String responseHeader = makeResponseHeader(fileExtension);

		// データの送信
		sendResponse(statusLine, responseHeader, byteContents);

	}

	/**
	 * リクエストURIで指定されたファイルを読み込んで、そのファイルのデータをバイナリデータで返すクラス
	 * 
	 * @param requestURI
	 * @param file
	 *            ファイル
	 * @return ファイルのバイナリーデータ
	 */
	public byte[] readFile(String requestURI, File file) {
		byte[] byteContents = null;

		System.out.println("ファイルの読み込みを始めます");

		if (file.exists() == true) {
			try {
				System.out.println(file + "ファイルを探します");
				InputStream inputStream = new FileInputStream(file);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// 入力ストリームからの読み込み（ファイルの読み込み）
				int len;
				while ((len = inputStream.read()) != -1) {
					byteArrayOutputStream.write(len);
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.flush();
					byteArrayOutputStream.close();
				}
				byteContents = byteArrayOutputStream.toByteArray();
				inputStream.close();

			} catch (IOException e) {
				System.err.println("エラー" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			byteContents = "404 Not Found".getBytes();
		}
		System.out.println("レスポンスボディは" + byteContents);
		return byteContents;
	}

	/**
	 * 指定されたファイルを元に、ステータスラインを生成して返す
	 * 
	 * @param file
	 * @return ステータスラインを返す
	 */
	private String makeStatusLine(File file) {
		// ステータスラインの設定
		String statusLine;
		if (file.exists() == true) {
			statusLine = "HTTP/1.1 200 OK";
		} else {
			statusLine = "HTTP/1.1 404 Not Found";
		}

		System.out.println("ステータスラインは" + statusLine);
		return statusLine;
	}

	/**
	 * 指定されたファイルの拡張子を元にレスポンスヘッダを生成して返す
	 * 
	 * @param fileExtension
	 *            指定されたファイルの拡張子
	 * @return レスポンスヘッダ
	 */
	private String makeResponseHeader(String fileExtension) {

		String responseHeader;
		if (fileExtension.equals("html") || fileExtension.equals("css") || fileExtension.equals("js")) {
			responseHeader = "Content-Type: text/" + fileExtension;

		} else if (fileExtension.equals("png") || fileExtension.equals("jpeg") || fileExtension.equals("gif")) {
			responseHeader = "Content-Type: image/" + fileExtension;
		} else {
			System.out.println("申し訳ございません、ご指定の拡張子には対応しておりません");
			responseHeader = "Content-Type: text/html";
		}
		System.out.println("レスポンスヘッダは" + responseHeader);
		return responseHeader;

	}

	/**
	 * レスポンスの中身を引数で受け取り、クライアントへレスポンスを返すメソッド
	 * 
	 * @param statusLine
	 *            ステータスライン
	 * @param responseHeader
	 *            レスポンスヘッダ
	 * @param byteContents
	 *            読み込んだファイルのバイナリデータであり、レスポンスボディ
	 */
	private void sendResponse(String statusLine, String responseHeader, byte[] byteContents) {
		try {
			System.out.println("クライアントに送信を開始します");
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			StringBuilder builder = new StringBuilder();
			builder.append(statusLine).append("\n");
			builder.append(responseHeader).append("\n");
			builder.append("\n");

			// PrintWriter:テキスト出力ストリームに出力する
			PrintWriter writer = new PrintWriter(outputStream, true);
			// Stringに変えて、出力ストリームに送信
			writer.print(builder.toString());
			if (byteContents != null) {
				dataOutputStream.write(byteContents, 0, byteContents.length);
				dataOutputStream.flush();
				dataOutputStream.close();
			} else {
				System.out.println("データの中身がありません");
			}

		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}
	}

}