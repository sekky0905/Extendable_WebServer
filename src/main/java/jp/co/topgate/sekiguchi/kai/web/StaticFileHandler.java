package jp.co.topgate.sekiguchi.kai.web;

import java.io.File;

/**
 * 全体のプログラムを制御するクラス
 *
 * @author sekiguchikai
 */
public class StaticFileHandler implements Handler {


    /**
     * リクエストメソッドがGETメソッドの場合の処理
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String requestURI = httpRequest.getRequestURI(httpRequest.getSecondSentence(httpRequest.getRequestLine()));

        Files files = new Files();

        File file;
        String requestResource;
// かつ「/」連続じゃない&&「.」で終わらない
        if ((requestURI.substring(requestURI.length() - 1).equals("/")) || !(requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length()).contains(".")) && requestURI.endsWith(".")) {
            requestResource = "src/main/resources" + requestURI + "index.html";
            file = new File(requestResource);
        } else {
            requestResource = "src/main/resources" + requestURI;
            file = new File(requestResource);
        }

        String fileExtension = requestResource.substring(requestResource.lastIndexOf(".") + 1,
                requestResource.lastIndexOf(""));

        boolean extensionExistence = httpResponse.setResponseHeader(fileExtension);


        if ((extensionExistence) && (file.exists())) {
            httpResponse.setStatusLine("HTTP/1.1 200 OK");
            httpResponse.setResponseBody(files.readFile(file));
        } else if (!(file.exists())) {
            httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
            httpResponse.setResponseBody("404 Not Found".getBytes());
        } else if (!(extensionExistence)) {
            httpResponse.setStatusLine("HTTP/1.1 404 Not Found");
            httpResponse.setResponseBody("404 Not Found".getBytes());

        }

        httpResponse.sendResponse();

    }

    /**
     * リクエストメソッドがPOSTメソッドの場合の処理
     *
     * @param httpRequest  HTTPRequestクラスのインスタンス
     * @param httpResponse HTTPResponseクラスのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        // 今後作成予定

    }

}