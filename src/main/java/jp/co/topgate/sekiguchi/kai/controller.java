package jp.co.topgate.sekiguchi.kai;

import java.io.File;

/**
 * 全体のプログラムを制御するクラス
 * 
 * @author sekiguchikai
 *
 */
public class controller {

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

		httpResponse.setResponseHeader(requestResource, file);

		byte[] responseBody = files.readFile(file);
		httpResponse.setResponseBody(responseBody);

		if (file.exists()) {
			httpResponse.setStatusLine("HTTP/1.1 200 OK");
		} else {
			httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
		}

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
