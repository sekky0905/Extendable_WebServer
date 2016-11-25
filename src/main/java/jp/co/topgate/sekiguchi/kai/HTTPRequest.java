package jp.co.topgate.sekiguchi.kai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

	/**
	 * クエリパラメーター
	 */
	private Map<String, String> requestParameter = new HashMap<>();

	private String requestString;

	/**
	 * コンストラクタ、set~で各フィールドを初期設定する
	 *
	 * @param inputStream
	 */
	public HTTPRequest(InputStream inputStream) {
		this.inputStream = inputStream;
		this.setRequestString();
	}

	/**
	 * リクエストパラメータを取得するメソッド
	 *
	 * @return リクエストパラメータの名前と値をセットで格納したMapを返す
	 */
	public void setRequestParameter(String targetString) {
		String[] parameter;
		if (targetString.contains("&")) {
			parameter = targetString.split("&");
			for (String param : parameter) {
				String[] piece = param.split("=");
				this.requestParameter.put(piece[0], piece[1]);
			}
		} else {
			String[] piece = targetString.split("=");
			this.requestParameter.put(piece[0], piece[1]);
		}
	}

	/**
	 * リクエスト本文を返すメソッド
	 *
	 * @return リクエスト本文を返す
	 */
	public void setRequestString() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
		String tempoRequestString;
		StringBuilder stringBuilder = new StringBuilder();

		try {
			int i = bufferedReader.read();
			while (i != -1) {
				char c = (char) i;
				// System.out.println(c);
				stringBuilder.append(c);
				i = bufferedReader.read();
			}
			System.out.println(stringBuilder);
		} catch (IOException e) {
			e.getCause();
			System.out.println("ファイル名の解析に失敗しました");
			e.printStackTrace();
		}

		System.out.println(this.requestString);
	}

	/**
	 * リクエスト本文を返すメソッド
	 *
	 * @return リクエスト本文を返す
	 */
	public String getRequestString() {
		return this.requestString;
	}

	/**
	 * リクエストラインを返すメソッド
	 *
	 * @return リクエストラインを返す
	 */
	public String getRequestLine() {
		String requestLine = this.requestString.substring(0, this.requestString.indexOf("\r"));
		System.out.println("リクエストラインは、" + requestLine);
		return requestLine;
	}

	/**
	 * リクエストメソッドを返すメソッド
	 *
	 * @return リクエストメソッドを返す
	 */
	public String getRequestMethod(String requestLine) {

		String requestMethod = requestLine.substring(0, requestLine.indexOf(" "));
		System.out.println("リクエストメソッドは" + requestMethod);
		return requestMethod;
	}

	/**
	 * リクエストURIを返すメソッド
	 *
	 * @return リクエストURIを返す
	 */
	public String getRequestURI(String requestLine) {
		String requestURI;
		int firstEmpty = requestLine.indexOf(" ");
		String secondSentence = requestLine.substring(firstEmpty + 1, requestLine.indexOf(" ", firstEmpty + 1));

		if (secondSentence.contains("?")) {
			requestURI = secondSentence.substring(0, secondSentence.indexOf("?"));
		} else {
			requestURI = secondSentence;
		}
		System.out.print("リクエストURIは" + requestURI);

		return requestURI;
	}

	/**
	 * 「?」以降の文字列を返す
	 *
	 * @param requestLine
	 *            リクエストライン
	 * @return ?」以降の文字列
	 */
	public String getRequstQuery(String requestLine) {
		int firstEmpty = requestLine.indexOf(" ");
		String secondSentence = requestLine.substring(firstEmpty + 1, requestLine.indexOf(" ", firstEmpty + 1));
		return secondSentence.substring(secondSentence.indexOf("?"), secondSentence.length());
	}

	// public String getRequestQuery(String requestMethod){
	// if(requestMethod.equals("POST")){
	// BufferedReader bufferedReader
	// }else if(requestMethod.equals("GET")){
	//
	// }
	// return ;
	// }
	//

	/**
	 * クライアントからのリクエストパラメータを抽出して返すメソッド
	 *
	 * @return リクエストパラメータを抽出して返す
	 */
	public String getRequestParameter(String name) {
		return this.requestParameter.get(name);
	}

}
