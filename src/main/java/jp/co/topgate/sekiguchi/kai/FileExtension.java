package jp.co.topgate.sekiguchi.kai;

/**
 * 指定されたリクエストURIに対してその中のファイルの拡張子を取得して返すユーテリティクラス
 * 
 * @author sekiguchikai
 */
public class FileExtension {

	/**
	 * 指定されたファイルの拡張子を取得するメソッド
	 * 
	 * @param requestURI
	 *            リクエストURI
	 * @return 指定されたファイルの拡張子を返す
	 */
	public static String getFileExtension(String requestURI) {
		String fileExtension;
		int dotPosition = requestURI.indexOf(".");
		if (requestURI.indexOf(".") != -1) {
			fileExtension = requestURI.substring(dotPosition + 1, requestURI.length());
		} else {
			fileExtension = "拡張子はなし";
			System.out.println("このリクエストは拡張子でのファイルの指定はありません");
		}

		if (fileExtension.indexOf("?") != -1) {
			int hatenaPosition = requestURI.indexOf("?");
			fileExtension = requestURI.substring(dotPosition + 1, hatenaPosition);
		}

		System.out.println("指定されたファイルの拡張子は" + fileExtension);
		System.out.println("クライアントから送信されてきたリクエストURIは" + requestURI);
		return fileExtension;
	}
}
