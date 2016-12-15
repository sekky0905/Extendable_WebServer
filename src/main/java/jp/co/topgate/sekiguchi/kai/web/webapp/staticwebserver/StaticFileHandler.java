package jp.co.topgate.sekiguchi.kai.web.webapp.staticwebserver;

import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.ErrorTemplate;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;


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

        ErrorTemplate errTemplate = new ErrorTemplate();

        if (file.isDirectory()) {
            errTemplate.setErrMessage("400 Not Found");
            errTemplate.writeHTML(httpRequest, httpResponse);
            httpResponse.sendResponse(HTTPResponse.SC_NOT_FOUND, "Not Found", "html");
        } else if ((file.exists())) {
            try {
                httpResponse.setStaticResponseBody(file);
                httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", extension);
            } catch (IOException e) {
                System.err.println("エラー" + e.getMessage());
                e.printStackTrace();
                errTemplate.setErrMessage("500 Internal Server Error");
                errTemplate.writeHTML(httpRequest, httpResponse);
                httpResponse.sendResponse(HTTPResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error", "html");
            }

        } else {
            errTemplate.setErrMessage("400 Not Found");
            errTemplate.writeHTML(httpRequest, httpResponse);
            httpResponse.sendResponse(HTTPResponse.SC_NOT_FOUND, "Not Found", "html");
        }


    }

}