package jp.co.topgate.sekiguchi.kai.web.webapp.staticserverapp;

<<<<<<< HEAD:src/main/java/jp/co/topgate/sekiguchi/kai/web/webapp/staticserverapp/StaticFileHandler.java
import jp.co.topgate.sekiguchi.kai.web.webserver.ErrorTemplate;
=======
<<<<<<< HEAD
=======
>>>>>>> 7b3faeeb4ef5986f5972a5c00571297dcf6eb960:src/main/java/jp/co/topgate/sekiguchi/kai/web/webapp/staticwebserver/StaticFileHandler.java
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
<<<<<<< HEAD:src/main/java/jp/co/topgate/sekiguchi/kai/web/webapp/staticserverapp/StaticFileHandler.java

import java.io.File;
import java.io.IOException;

=======
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;
>>>>>>> develop

import jp.co.topgate.sekiguchi.kai.web.webserver.*;

import java.io.File;
import java.io.IOException;
>>>>>>> 7b3faeeb4ef5986f5972a5c00571297dcf6eb960:src/main/java/jp/co/topgate/sekiguchi/kai/web/webapp/staticwebserver/StaticFileHandler.java

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

