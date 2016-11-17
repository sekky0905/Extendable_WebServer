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
		String AfterSlash = requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length());

		if (AfterSlash.indexOf(".") != -1) {
			// ★拡張子=>リクエストURIの中で、最後の"."以降の文字列の部分
			// これを、実装すること

			// 1.文字列の中の最後尾の"."を取得する
			int lastDot = requestURI.lastIndexOf(".");

			// 2.最後尾の"."以降の文字列部分を取得する
			String afetrDotString = requestURI.substring(lastDot + 1, requestURI.length());
			// 1文字ごとに配列のインデックスに格納
			String[] afetrDotArray = afetrDotString.split("");

			StringBuilder stringBuilder = new StringBuilder();

			for (int i = 0; i < afetrDotArray.length; i++) {

				// アルファベット小文字ならば
				if (afetrDotArray[i].matches("^[a-z]$")) {
					stringBuilder.append(afetrDotArray[i]);
				} else {
					break;
				}
			}

			// 文字列化
			fileExtension = stringBuilder.toString();
			System.out.println("指定されたファイルの拡張子は" + fileExtension);
		} else if (AfterSlash.indexOf(".") == -1) {
			fileExtension = "html";
			System.out.println("指定されたファイルの拡張子は" + fileExtension);
		} else {
			fileExtension = "拡張子なし";
			System.out.println("拡張子の指定がありません");
		}
		return fileExtension;
	}
}
