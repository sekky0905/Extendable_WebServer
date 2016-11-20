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
	 * クライアントからのsocket通信の中身を格納するための変数
	 */
	private InputStream inputStream;
	private String requestLine;
	private String requestMethod;
	private String requestURI;
	private String queryString;

	/**
	 * コンストラクタ
	 * 
	 * @param socketからのstreamを受け取る
	 */
	public HTTPRequest(InputStream inputStream) {
		this.inputStream = inputStream;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		try {
			this.requestLine = bufferedReader.readLine();
			if (bufferedReader != null) {
			}
		} catch (IOException e) {
			System.err.println("エラー:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * クライアントからのリクエストから、リクエストメソッドを抽出して返すメソッド
	 * 
	 * @return リクエストメソッドを抽出して返す
	 */
	public String getRequestMethod() {
		this.requestMethod = this.requestLine.substring(0, this.requestLine.indexOf(" "));
		System.out.println("リクエストメソッドは" + this.requestMethod);
		return this.requestMethod;

	}

	/**
	 * クライアントからのリクエストから、リクエストURIを抽出して返すメソッド S
	 * 
	 * @return リクエストURIを抽出して返す
	 */
	public String getRequestURI() {
		int firstEmpty = this.requestLine.indexOf(" ");
		String secondSentence = this.requestLine.substring(firstEmpty + 1,
				this.requestLine.indexOf(" ", firstEmpty + 1));

		String afterLastSlash = secondSentence.substring(secondSentence.lastIndexOf("/") + 1, secondSentence.length());

		if ((secondSentence.indexOf("?") == -1) && (afterLastSlash.indexOf(".") != -1)) {
			this.requestURI = secondSentence;
		} else if ((secondSentence.indexOf("?") == -1) && (afterLastSlash.indexOf(".") == -1)) {
			this.requestURI = secondSentence.substring(0, secondSentence.lastIndexOf("/") + 1);
		} else if ((secondSentence.indexOf("?") != -1) && (afterLastSlash.indexOf(".") != -1)) {
			this.requestURI = secondSentence.substring(0, secondSentence.indexOf("?"));
		} else if ((secondSentence.indexOf("?") != -1) && (afterLastSlash.indexOf(".") == -1)) {
			this.requestURI = secondSentence.substring(0, secondSentence.lastIndexOf("/") + 1);
		}

		return this.requestURI;

	}

	// /**
	// * クライアントからのリクエストから、クエリストリングを抽出して返すメソッド
	// *
	// * @return クエリストリングを抽出して返す
	// */
	// public void getQueryString() {
	// if (this.requestURI.indexOf("?") != -1) {
	// this.queryString =
	// this.requestLine.substring(this.requestURI.lastIndexOf("?"),
	// this.requestURI.length() - 1);
	// } else {
	// this.queryString =
	// }
	// }

}
