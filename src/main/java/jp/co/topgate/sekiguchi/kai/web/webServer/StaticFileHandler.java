package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;


import java.io.*;

/**
 * 静的なファイルの処理の責任を持つクラス
 *
 * @author sekiguchikai
 */
public class StaticFileHandler extends Handler {
    /**
     * 静的なファイルの処理を行うメソッド
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        String requestURI = null;
        requestURI = httpRequest.getRequestURI();

        String requestResource = httpRequest.getRequestResource();
        String extension = httpRequest.getRequestResourceExtension(requestResource);
        File file = new File(requestResource);

        Template errTemplate = new ErrorTemplate();

        if (file.isDirectory()) {
            httpResponse.setStatusLine(HTTPResponse.SC_NOT_FOUND);
            httpResponse.sendResponse("html", errTemplate.writeHTML());
        } else if ((file.exists())) {
            try {
                httpResponse.setStatusLine(HTTPResponse.SC_OK);
                httpResponse.sendResponse(extension, this.readFile(file));
            } catch (IOException e) {
                System.err.println("エラー" + e.getMessage());
                e.printStackTrace();
                httpResponse.setStatusLine(HTTPResponse.SC_INTERNAL_SERVER_ERROR);
                httpResponse.sendResponse(extension, errTemplate.writeHTML());
            }

        } else {
            httpResponse.setStatusLine(HTTPResponse.SC_NOT_FOUND);
            httpResponse.sendResponse("html", errTemplate.writeHTML());
        }


    }

    /**
     * 指定されたファイルを読み込んで、そのバイナリデータを返す
     *
     * @return 読み込んだファイルのバイナリデータ
     */
    public byte[] readFile(File file) throws IOException {

        System.out.println("ファイルの読み込みを始めます");

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
        byte[] byteContents = byteArrayOutputStream.toByteArray();
        inputStream.close();

        return byteContents;

    }


}