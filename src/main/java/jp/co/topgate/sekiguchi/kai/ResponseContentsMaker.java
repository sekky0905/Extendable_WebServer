package jp.co.topgate.sekiguchi.kai;

/**
 * HTTPレスポンスに必要な各項目を作成する責務を持つクラス
 * 
 * @author sekiguchikai
 *
 */

public class ResponseContentsMaker {

	/**
	 * ステータスラインを設定するメソッド
	 * 
	 * @param fileExistence:リクエストで指定されたファイルが存在するかの真偽値
	 * 
	 * @return ステータスライン
	 */

	public String makeStatusLine(boolean fileExistence) {

		String statusLine;

		if (fileExistence == true) {
			statusLine = "HTTP/1.1 200 OK";
		} else {
			statusLine = "HTTP/1.1 400 Not Found";
		}

		return statusLine;
	}

	/**
	 * レスポンスヘッダを設定するメソッド
	 * 
	 * @param fileExtension
	 *            リクエストで指定されたファイルが存在するかどうかの真偽値
	 * 
	 * @return String レスポンスヘッダ
	 */

	public String makeResponseHeader(String fileExtension) {
		String contentType = null;

		// テキストファイル系

		if (fileExtension.equals("html") || fileExtension.equals("css") || fileExtension.equals("js")) {
			contentType = "Content-Type: text/" + fileExtension;

			// バイナリファイル系の処理
		} else if (fileExtension.equals("png") || fileExtension.equals("jpeg") || fileExtension.equals("gif")) {
			contentType = "Content-Type: image/" + fileExtension;
		}

		return contentType;

	}

}