package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.HTTPResponse;

import java.io.*;

/**
 * 全体のプログラムを制御するクラス
 *
 * @author sekiguchikai
 */
public class StaticFileHandler {


    /**
     * リクエストメソッドがGETメソッドの場合の処理
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */
    public void ProcessWebServer(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String requestURI = httpRequest.getRequestURI();

        File file;
        String requestResource;

        if ((requestURI.endsWith("/")) || !(requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length()).contains("."))) {
            requestResource = "src/main/resources" + requestURI + "index.html";
            file = new File(requestResource);
        } else {
            requestResource = "src/main/resources" + requestURI;
            file = new File(requestResource);
        }

        String extension = requestResource.substring(requestResource.lastIndexOf(".") + 1,
                requestResource.lastIndexOf(""));

        if ((file.exists()) && (extension.equals("html") || extension.equals("css") || extension.equals("js") || (extension.equals("png") || extension.equals("jpeg") || extension.equals("gif")))) {
            httpResponse.setStatusLine("HTTP/1.1 200 OK");
            httpResponse.setResponseHeader(extension);
            httpResponse.setResponseBody(this.readFile(file));
        } else {
            httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
            httpResponse.setResponseHeader("html");
            httpResponse.setResponseBody("404 Not Found".getBytes());
        }

        httpResponse.sendResponse();

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

