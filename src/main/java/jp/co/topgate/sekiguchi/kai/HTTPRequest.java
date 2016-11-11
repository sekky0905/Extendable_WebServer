package jp.co.topgate.sekiguchi.kai;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * クライアントからのTTPリクエストに関する責務を持つクラス
 * 
 * @author sekiguchikai
 *
 */

public class HTTPRequest {

	private BufferedReader bufferedReader;

	/**
	 * リクエストの中身を取得するメソッド
	 * 
	 * @param inputStream
	 *            クライアントとの通信で使うsocketインスタンスを格納したストリーム
	 * 
	 * @return リクエストラインを返す
	 *
	 * @exception IOException
	 *                ファイル名の解析に失敗しました
	 */

	public String getRequestLine(InputStream inputStream) throws IOException {

		String requestLine = null;

		try {
			this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			requestLine = bufferedReader.readLine();

			System.out.println("リクエストラインは" + requestLine);

		} catch (IOException e) {
			e.getCause();
			System.out.println("ファイル名の解析に失敗しました");
			e.printStackTrace();
		}

		return requestLine;

	}

	/**
	 * 受け取ったリクエストの全文を表示するメソッド
	 * 
	 * @exception IOException
	 *                リクエストの全部読み込みに失敗しました
	 */

	public void showRequest() throws IOException {
		String requestContents;
		try {
			while (!(requestContents = this.bufferedReader.readLine()).equals("")) {
				System.out.println(requestContents);
			}
		} catch (IOException e) {
			System.out.println("リクエストの全部読み込みに失敗しました");
			e.printStackTrace();
		}
	}

	/**
	 * strLine(リクエストライン)の中身から、リクエストURIを取得するメソッド
	 * 
	 * @param requestLine
	 *            クライアントからのリクエストライン
	 * @return リクエストラインのうち、リクエストURIを返す
	 */
	public String getRequestURI(String requestLine) {

		// リクエストラインのうち、2番目の半角空白のインデックスを格納する
		int firstEmpty = requestLine.indexOf(" ");

		// リクエストラインのうち、2番目の半角空白のインデックスを格納する
		int secondEmty = requestLine.indexOf(" ", firstEmpty + 1);

		// リクエストラインのうち、リクエストURIの部分を抽出して格納する変数
		String requestURI = requestLine.substring(firstEmpty + 1, secondEmty);

		if (requestURI.equals("/")) {
			requestURI = requestURI + "index.html";
		}

		System.out.println("クライアントから送信されてきたリクエストURIは" + requestURI);

		return requestURI;

	}

	/**
	 * リクエストURIから、求めているリソースjava.io.Fileのインスタンスをインスタンス化して返すメソッド
	 * 
	 * @param requestURI
	 *            リクエストURI
	 * @return 指定されたリソースのjava.io.Fileのインスタンスを返す
	 */
	public File getRequestResource(String requestURI) {

		File file = new File("src/main/resources" + requestURI);

		System.out.println("クライアントが求めているリソースは" + file);

		return file;

	}

	/**
	 * リクエストラインから、指定されたファイルの拡張子を取得するメソッド
	 * 
	 * @param requestURI
	 *            リクエストURI
	 * @return 指定されたファイルの拡張子を返す
	 */
	public String getResourceExt(String requestURI) {

		String extension;

		if (requestURI.indexOf(".") != -1) {

			System.out.println("ここでのrequestURIは" + requestURI);

			// ドットの位置を格納
			int dotPosition = requestURI.indexOf(".");

			// 拡張子の位置を取得し格納
			extension = requestURI.substring(dotPosition + 1, requestURI.length());

		} else {
			extension = "拡張子はなし";
			System.out.println("このリクエストは拡張子でのファイルの指定はありません");
		}

		System.out.println("指定されたファイルの拡張子は" + extension);

		return extension;

	}

}