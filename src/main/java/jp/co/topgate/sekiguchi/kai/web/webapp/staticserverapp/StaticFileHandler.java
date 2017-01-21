package jp.co.topgate.sekiguchi.kai.web.webapp.staticserverapp;

import jp.co.topgate.sekiguchi.kai.web.webserver.ErrTemplate;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;

import java.io.File;
import java.io.IOException;

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

        ErrTemplate errTemplate = new ErrTemplate();

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

