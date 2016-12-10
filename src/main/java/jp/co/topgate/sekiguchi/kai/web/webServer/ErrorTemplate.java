package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;

import java.io.IOException;

/**
 * エラーの際のTemplateを表すクラス
 * Created by sekiguchikai on 2016/12/07.
 */
class ErrorTemplate implements Template {
    /**
     * エラーの際のHTMLのテンプレートを作成し、それをbyte[]にして返すメソッド
     *
     * @return HTMLテンプレートをbyte[]にしたもの
     */

    public void writeHTML(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html>")
        .append("<html lang=\"ja\">")
        .append("<head>")
        .append("<meta charset=\"UTF-8\">")
        .append("<title>エラー</title>")
        .append("</head>")
        .append("<body>")
        .append("<p>" + HTTPResponse.getStatusLine() + "</p>")
        .append("</body>");


        httpResponse.setDynamicBody(new String(stringBuilder).getBytes());

        httpResponse.sendResponse("html");
    }
}