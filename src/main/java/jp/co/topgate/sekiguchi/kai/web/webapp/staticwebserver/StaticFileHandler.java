package jp.co.topgate.sekiguchi.kai.web.webapp.staticwebserver;

import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webServer.ErrorTemplate;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;
import jp.co.topgate.sekiguchi.kai.web.webServer.Template;


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
            httpResponse.addStatusLine(HTTPResponse.SC_NOT_FOUND);
            errTemplate.writeHTML(httpRequest, httpResponse);
        } else if ((file.exists())) {
            try {
                httpResponse.addStatusLine(HTTPResponse.SC_OK);
                httpResponse.setStaticResponseBody(file);
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

}