package jp.co.topgate.sekiguchi.kai.web.webserver;

import java.io.IOException;

/**
 * エラーの際のTemplateを表すクラス
 * Created by sekiguchikai on 2016/12/07.
 */
public class ErrorTemplate implements Template {
    /**
     * エラーメッセージ
     */
    private String errMessage;

    /**
     * errMessageを設定するためのメソッド
     *
     * @param errMessage エラーメッセージ
     */
    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    /**
     * エラーの際のHTMLのテンプレートをするメソッド
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
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
                .append("<p>" + this.errMessage + "</p>")
                .append("</body>");


        httpResponse.setDynamicResponseBody(new String(stringBuilder).getBytes());
    }
}