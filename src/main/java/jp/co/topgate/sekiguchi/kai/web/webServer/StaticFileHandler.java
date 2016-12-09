package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;


import java.io.*;

/**
 * 静的なファイルの処理の責任を持つクラス
 *
 * @author sekiguchikai
 */
class StaticFileHandler extends Handler {
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
            httpResponse.setStatusLine(HTTPResponse.SC_NOT_FOUND);
            errTemplate.writeHTML(httpRequest, httpResponse);
        } else if ((file.exists())) {
            try {
                httpResponse.setStatusLine(HTTPResponse.SC_OK);
                httpResponse.setStaticBody(file);
                httpResponse.sendResponse(extension);


            } catch (IOException e) {
                System.err.println("エラー" + e.getMessage());
                e.printStackTrace();
                httpResponse.setStatusLine(HTTPResponse.SC_INTERNAL_SERVER_ERROR);
                errTemplate.writeHTML(httpRequest, httpResponse);
            }

        } else {
            httpResponse.setStatusLine(HTTPResponse.SC_NOT_FOUND);
            errTemplate.writeHTML(httpRequest, httpResponse);
        }


    }



}