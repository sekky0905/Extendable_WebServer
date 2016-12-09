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

        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"ja\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>エラー</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<p>" + HTTPResponse.getStatusLine() + "</p>");
        stringBuilder.append("</body>");


        httpResponse.setDynamicBody(new String(stringBuilder).getBytes());

        httpResponse.sendResponse("html");
    }
}