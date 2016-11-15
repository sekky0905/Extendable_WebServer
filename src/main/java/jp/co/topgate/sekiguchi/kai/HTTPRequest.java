package jp.co.topgate.sekiguchi.kai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * クライアントからのTTPリクエストに関する責務を持つクラス
 * 
 * @author sekiguchikai
 */
public class HTTPRequest {

	/**
	 * リクエストURIを取得するメソッド
	 * 
	 * @return リクエストURIを返す
	 */
	public String getRequestURI(InputStream inputStream) {
		BufferedReader bufferedReader;
		String requestLine = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			requestLine = bufferedReader.readLine();

			String requestContents = null;

			while (requestContents != null) {
				System.out.println(requestContents);
				requestContents = bufferedReader.readLine();
			}

		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}

		System.out.println("リクエストラインは" + requestLine);

		// リクエストラインのうち、2番目の半角空白のインデックスを格納する
		int firstEmpty = requestLine.indexOf(" ");
		// リクエストラインのうち、2番目の半角空白のインデックスを格納する
		int secondEmty = requestLine.indexOf(" ", firstEmpty + 1);
		// リクエストラインのうち、リクエストURIの部分を抽出して格納する変数
		String requestURI = requestLine.substring(firstEmpty + 1, secondEmty);

		if (requestURI.equals("/")) {
			requestURI = requestURI + "index.html";
		}
		System.out.println("リクエストURIは" + requestURI);
		return requestURI;
	}
}