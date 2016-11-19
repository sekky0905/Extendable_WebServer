package jp.co.topgate.sekiguchi.kai;

public class Controler {

	public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		String requestURI = httpRequest.getRequestURI();
		Files files = new Files();

		// レスポンスヘッダ（今回は、Content-Typeのみ）の設定
		httpResponse.setResponseHeader(requestURI);

		// レスポンスボディの設定
		byte[] responseBody = files.readFile(requestURI);
		httpResponse.setResponseBody(responseBody);

		// ステータスラインの設定
		if (responseBody == "404 Not Found".getBytes()) {
			httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
		} else {
			httpResponse.setStatusLine("HTTP/1.1 200 OK");
		}

	}

	public void handlePost(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		//

	}

}
