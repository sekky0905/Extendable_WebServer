package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.util.ResponseHeaderMaker;


import java.io.*;

/**
 * 静的なファイルの処理の責任を持つクラス
 *
 * @author sekiguchikai
 */
public class StaticFileHandler {
    /**
     * 静的なファイルの処理を行うメソッド
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */
    public void ProcessWebServer(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String requestURI = httpRequest.getRequestURI();

        String requestResource = httpRequest.getRequestResource(requestURI);

        String extension = httpRequest.getRequestResourceExtension(requestResource);

        File file = new File(requestResource);

        if ((file.exists()) && (extension.equals("html") || extension.equals("css") || extension.equals("js") || (extension.equals("png") || extension.equals("jpeg") || extension.equals("gif")))) {
            httpResponse.sendResponse("HTTP/1.1 200 OK", ResponseHeaderMaker.makeContentType(extension), this.readFile(file));

        } else {
            httpResponse.sendResponse("HTTP/1.1 404 Not Found", ResponseHeaderMaker.makeContentType("html"), "404 Not Found".getBytes());
        }


    }

    /**
     * 指定されたファイルを読み込んで、そのバイナリデータを返す
     *
     * @return 読み込んだファイルのバイナリデータ
     */
    public byte[] readFile(File file) {
        byte[] byteContents = null;

        System.out.println("ファイルの読み込みを始めます");


        try {
            System.out.println(file + "ファイルを探します");
            InputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

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

        System.out.println("レスポンスボディは" + byteContents);

        return byteContents;

    }


}