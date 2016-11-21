package jp.co.topgate.sekiguchi.kai;

import java.io.File;

/**
 * 全体のプログラムを制御するクラス
 * 
 * @author sekiguchikai
 *
 */
public class controlle {

	/**
	 * リクエストメソッドがGETメソッドの場合の処理
	 * 
	 * @param httpRequest
	 *            HTTPRequestクラスのインスタンス
	 * @param httpResponse
	 *            HTTPResponseクラスのインスタンス
	 */
	public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		String requestURI = httpRequest.getRequestURI();
		Files files = new Files();

		File file;
		String requestResource;

		if (requestURI.substring(requestURI.length() - 1).equals("/")
				|| requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length()).indexOf(".") == -1) {
			requestResource = "src/main/resources" + requestURI + "index.html";
			file = new File(requestResource);
		} else {
			requestResource = "src/main/resources" + requestURI;
			file = new File(requestResource);
		}

		// レスポンスヘッダ（今回は、Content-Typeのみ）の設定
		httpResponse.setResponseHeader(requestResource, file);

		// レスポンスボディの設定
		byte[] responseBody = files.readFile(file);
		httpResponse.setResponseBody(responseBody);

		// ステータスラインの設定
		if (file.exists()) {
			httpResponse.setStatusLine("HTTP/1.1 200 OK");
		} else {
			httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
		}

		// 送信
		httpResponse.sendResponse();

	}

	/**
	 * リクエストメソッドがPOSTメソッドの場合の処理
	 * 
	 * @param httpRequest
	 *            HTTPRequestクラスのインスタンス
	 * @param httpResponse
	 *            HTTPResponseクラスのインスタンス
	 */
	public void handlePost(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		// 今後作成予定

	}

}
