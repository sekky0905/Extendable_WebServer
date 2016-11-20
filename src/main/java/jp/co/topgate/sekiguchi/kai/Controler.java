package jp.co.topgate.sekiguchi.kai;

import java.io.File;

public class Controler {

	public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		String requestURI = httpRequest.getRequestURI();
		Files files = new Files();
		File file = new File("src/main/resources" + requestURI);

		// レスポンスヘッダ（今回は、Content-Typeのみ）の設定
		httpResponse.setResponseHeader(requestURI, file);

		// レスポンスボディの設定
		byte[] responseBody = files.readFile(requestURI);
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

	public void handlePost(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		//

	}

}
