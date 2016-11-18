package jp.co.topgate.sekiguchi.kai;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

	HTTPResponse(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	/**
	 * リクエストURIで指定されたファイルを読み込み、クライアントにレスポンスするクラス
	 * 
	 * @param requestURI
	 *            リクエストURI
	 */
	public void controlResponse(String requestURI) {

		// ここは冗長になってしまうが、テスト用
		String AfterSlash = requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length());
		if ((requestURI == ("/")) || (AfterSlash.indexOf(".") == -1)) {
			requestURI = "/index.html";
		}

		System.out.println("送信されてきたリクエストURIは" + requestURI);

		// 拡張子の取得
		String fileExtension = FileExtension.getFileExtension(requestURI);

		// 最後の.より前の文字列を抽出
		String preString = requestURI.substring(requestURI.lastIndexOf("/"), requestURI.lastIndexOf("."));

		// 以下ファイル名の取得

		// 文字列を配列に格納
		String preStringArray[] = preString.split("");
		// 格納した文字列を逆から読み込む
		List<String> list = new ArrayList<String>();

		for (int i = preStringArray.length - 1; i >= 0; i--) {
			if (preStringArray[i].matches("[a-zA-Z]")) {
				list.add(preStringArray[i]);
			} else {
				break;
			}
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = list.size() - 1; i >= 0; i--) {
			stringBuilder.append(list.get(i));
		}

		String requestFile = stringBuilder.toString();

		System.out.println("指定されたファイルは" + requestFile + "." + fileExtension);

		File file = new File("src/main/resources/" + requestFile + "." + fileExtension);

		// データの読み込み
		byte[] byteContents = readFile(requestURI, file);

		// ステータスラインの取得
		String statusLine = makeStatusLine(file);

		// レスポンスヘッダの取得
		String responseHeader = makeResponseHeader(fileExtension, file);

		// データの送信
		sendResponse(statusLine, responseHeader, byteContents);

	}

	/**
	 * リクエストURIで指定されたファイルを読み込んで、そのファイルのデータをバイナリデータで返すクラス
	 * 
	 * @param requestURI
	 * @param file
	 *            ファイル
	 * @return ファイルのバイナリーデータ
	 */
	public byte[] readFile(String requestURI, File file) {
		byte[] byteContents = null;

		System.out.println("ファイルの読み込みを始めます");

		if ((file.exists() == true)) {
			try {
				System.out.println(file + "ファイルを探します");
				InputStream inputStream = new FileInputStream(file);
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				// 入力ストリームからの読み込み（ファイルの読み込み）
				int len;
				while ((len = inputStream.read()) != -1) {
					byteArrayOutputStream.write(len);
				}
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.flush();
					byteArrayOutputStream.close();
				}
				byteContents = byteArrayOutputStream.toByteArray();
				inputStream.close();

			} catch (IOException e) {
				System.err.println("エラー" + e.getMessage());
				e.printStackTrace();
			}
		} else {
			byteContents = "404 Not Found".getBytes();
		}
		System.out.println("レスポンスボディは「404 Not Found」" + byteContents);
		return byteContents;
	}

	/**
	 * 指定されたファイルを元に、ステータスラインを生成して返す
	 * 
	 * @param file
	 * @return ステータスラインを返す
	 */
	private String makeStatusLine(File file) {
		// ステータスラインの設定
		String statusLine;
		if (file.exists() == true) {
			statusLine = "HTTP/1.1 200 OK";
		} else {
			statusLine = "HTTP/1.1 404 Not Found";
		}

		System.out.println("ステータスラインは" + statusLine);
		return statusLine;
	}

	/**
	 * 指定されたファイルの拡張子を元にレスポンスヘッダを生成して返す
	 * 
	 * @param fileExtension
	 *            指定されたファイルの拡張子
	 * @return レスポンスヘッダ
	 */
	private String makeResponseHeader(String fileExtension, File file) {

		String responseHeader = null;
		if ((file.exists() == true)) {

			if (fileExtension.equals("html") || fileExtension.equals("css") || fileExtension.equals("js")) {
				responseHeader = "Content-Type: text/" + fileExtension;

			} else if (fileExtension.equals("png") || fileExtension.equals("jpeg") || fileExtension.equals("gif")) {
				responseHeader = "Content-Type: image/" + fileExtension;
			} else {
				System.out.println("申し訳ございません、ご指定の拡張子には対応しておりません");
				responseHeader = "Content-Type: text/html";
			}

		} else {
			responseHeader = "Content-Type: text/html";
		}

		System.out.println("レスポンスヘッダは" + responseHeader);
		return responseHeader;
	}

	/**
	 * レスポンスの中身を引数で受け取り、クライアントへレスポンスを返すメソッド
	 * 
	 * @param statusLine
	 *            ステータスライン
	 * @param responseHeader
	 *            レスポンスヘッダ
	 * @param byteResponseBody
	 *            読み込んだファイルのバイナリデータであり、レスポンスボディ
	 */
	private void sendResponse(String statusLine, String responseHeader, byte[] byteResponseBody) {
		try {
			System.out.println("クライアントに送信を開始します");
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

			// 引数で受け取ったステータスラインとレスポンスヘッダを結合

			String responseHead = statusLine + "\n" + responseHeader + "\n" + "\n";
			byte[] byteResponseHead = responseHead.getBytes();
			byte[] ResponseContents = new byte[byteResponseHead.length + byteResponseBody.length];
			// ResponseContentsにbyteResponseHeadを追加
			System.arraycopy(byteResponseHead, 0, ResponseContents, 0, byteResponseHead.length);
			// ResponseContentsにbyteContentsを追加
			System.arraycopy(byteResponseBody, 0, ResponseContents, byteResponseHead.length, byteResponseBody.length);

			if (byteResponseBody != null) {
				dataOutputStream.write(ResponseContents, 0, ResponseContents.length);
				dataOutputStream.flush();
				dataOutputStream.close();
			}

		} catch (IOException e) {
			System.err.println("エラー" + e.getMessage());
			e.printStackTrace();
		}
	}

}