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
            errTemplate.writeHTML(httpRequest, httpResponse);
        } else if ((file.exists())) {
            try {
                httpResponse.setStaticResponseBody(file);
                httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", extension);
            } catch (IOException e) {
                System.err.println("エラー" + e.getMessage());
                e.printStackTrace();
                errTemplate.writeHTML(httpRequest, httpResponse);
                httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", "html");
            }

        } else {
            errTemplate.writeHTML(httpRequest, httpResponse);
            httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, "Not Found", "html");
        }


    }

}