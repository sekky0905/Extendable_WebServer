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
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
        String requestResource = httpRequest.getRequestResource();
        String extension = httpRequest.getRequestResourceExtension(requestResource);
        File file = new File(requestResource);

        Template errTemplate = new ErrorTemplate();

        if (file.isDirectory()) {
            httpResponse.addStatusLine(HTTPResponse.SC_NOT_FOUND);
            errTemplate.writeHTML(httpRequest, httpResponse);
        } else if ((file.exists())) {
            try {
                httpResponse.addStatusLine(HTTPResponse.SC_OK);
                httpResponse.setResponseBody(this.readFile(file));
                httpResponse.sendResponse(extension);
            } catch (IOException e) {
                System.err.println("エラー" + e.getMessage());
                e.printStackTrace();
                httpResponse.addStatusLine(HTTPResponse.SC_INTERNAL_SERVER_ERROR);
                errTemplate.writeHTML(httpRequest, httpResponse);
            }

        } else {
            httpResponse.addStatusLine(HTTPResponse.SC_NOT_FOUND);
            errTemplate.writeHTML(httpRequest, httpResponse);
        }


    }

    /**
     * 指定されたファイルを読み込んで、そのバイナリデータを返す
     * @param file Fileクラスのインスタンス
     * @return 読み込んだファイルのバイナリデータ
     */
    byte[] readFile(File file) throws IOException {

        System.out.println("ファイルの読み込みを始めます");
        System.out.println(file + "ファイルを探します");

        byte[] byteContents;
        try (InputStream inputStream = new FileInputStream(file);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            int len;
            while ((len = inputStream.read()) != -1) {
                byteArrayOutputStream.write(len);
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
            byteContents = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IOException();
        }

        return byteContents;

    }


}