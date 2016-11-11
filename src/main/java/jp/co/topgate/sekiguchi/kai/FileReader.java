package jp.co.topgate.sekiguchi.kai;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * ファイルに関する責務を持つクラス
 * 
 * @author sekiguchikai
 * 
 */
public class FileReader {

	/**
	 * ファイルのバイナリデータ
	 */
	private byte[] byteContents;

	/**
	 * 指定されたファイルが存在するか確認するメソッド
	 * 
	 * @param file
	 *            ファイルの相対パス
	 * @return fileが存在する場合は、true
	 */

	public boolean ConfirmExistence(File file) {
		System.out.println("ファイルの存在を確かめます");

		boolean fileExistence;

		if (file.exists()) {
			fileExistence = true;
			System.out.println("ファイルが存在しました");

		} else {
			fileExistence = false;
			System.out.println("ファイルが存在しませんでした");
		}

		return fileExistence;

	}

	/**
	 * 指定されたファイルを読み込むメソッド
	 * 
	 * @param file
	 *            クライアントからのリクエストURI
	 * 
	 * @param fileExistence
	 *            ファイルが存在するかどうかの真偽値
	 *
	 * @return 読み込んだファイルのバイナリデータ
	 * 
	 * @exception IOException
	 *                指定されたファイルの読み込みに失敗しました
	 * 
	 */
	public byte[] getFileData(File file, boolean fileExistence) throws IOException {
		System.out.println("ファイルの読み込みを始めます");
		try {
			if (fileExistence == true) {

				System.out.println(file + "ファイルを探します");

				InputStream inputStream = new FileInputStream(file);
				// InputStream InputStream = new InputStream(fileInputStream);

				DataInputStream dataInputStream = new DataInputStream(inputStream);

				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				OutputStream outputStream = byteArrayOutputStream;

				// 入力ストリームからの読み込み（ファイルの読み込み）
				int len;

				while ((len = dataInputStream.read()) != -1) {
					outputStream.write(len);
				}

				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}

				// 戻り値は、byte[]
				this.byteContents = byteArrayOutputStream.toByteArray();

				inputStream.close();
			} else {
				String notFound;
				notFound = "404 NotFound";
				this.byteContents = notFound.getBytes();

			}

		} catch (IOException e) {
			System.out.println("指定されたファイルの読み込みに失敗しました");
			e.printStackTrace();
		}

		return this.byteContents;

	}

}