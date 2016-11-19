package jp.co.topgate.sekiguchi.kai;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
	private String statusLine;
	private String responseHeader;
	private byte[] responseBody;

	HTTPResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	/**
	 * クライアントへ送信するレスポンスのうち、ステータスラインを設定するメソッド
	 * 
	 * @return
	 */
	public void setStatusLine(String status) {
		this.statusLine = status;
	}

	/**
	 * クライアントへ送信するレスポンスのうち、レスポンスヘッダを設定するメソッド
	 * 
	 * @param
	 */
	public void setResponseHeader(String requestURI) {
		if (requestURI.indexOf("?") == -1) {
			String fileExtension = requestURI.substring(requestURI.lastIndexOf("."), requestURI.lastIndexOf(""));
		} else {
			String fileExtension = requestURI.substring(requestURI.lastIndexOf("."), requestURI.indexOf("?"));
		}

		String fileExtension = null;
		if (fileExtension.equals("html") || fileExtension.equals("css") || fileExtension.equals("js")) {
			this.responseHeader = "Content-Type: text/" + fileExtension;

		} else if (fileExtension.equals("png") || fileExtension.equals("jpeg") || fileExtension.equals("gif")) {
			this.responseHeader = "Content-Type: image/" + fileExtension;
		} else {
			System.out.println("申し訳ございません、ご指定の拡張子には対応しておりません");
			this.responseHeader = "Content-Type: text/html";
		}

		System.out.println("レスポンスヘッダは" + this.responseHeader);

	}

	/**
	 * クライアントへ送信するレスポンスのうち、レスポンスボディを設定するメソッド
	 * 
	 * @param responseBody
	 */
	public void setResponseBody(byte[] responseBody) {
		this.responseBody = responseBody;
	}

	public void sendResponse() {
		try {
			System.out.println("クライアントに送信を開始します");
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

			// 引数で受け取ったステータスラインとレスポンスヘッダを結合

			byte[] responseHead = (this.statusLine + "\n" + this.responseHeader + "\n" + "\n").getBytes();
			byte[] responseContents = new byte[responseHead.length + this.responseBody.length];
			// ResponseContentsにbyteResponseHeadを追加
			System.arraycopy(responseHead, 0, responseContents, 0, responseHead.length);
			// ResponseContentsにresponseBodyを追加
			System.arraycopy(this.responseBody, 0, responseContents, this.responseBody.length,
					this.responseBody.length);

			if (this.responseBody != null) {
				dataOutputStream.write(responseContents, 0, responseContents.length);
				dataOutputStream.flush();
				dataOutputStream.close();
			}

		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}
	}

	// テスト用の仕掛け
	public String getStatusLine() {
		return this.statusLine;
	}

	public String getResponseHeader() {
		return this.responseHeader;
	}

	public byte[] getResponseBody() {
		return this.responseBody;
	}

}