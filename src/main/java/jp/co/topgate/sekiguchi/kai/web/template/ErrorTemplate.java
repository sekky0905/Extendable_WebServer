package jp.co.topgate.sekiguchi.kai.web.template;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;

/**
 * Created by sekiguchikai on 2016/12/07.
 */
public class ErrorTemplate implements Template {
    /**
     * エラーの際のHTMLのテンプレートを作成し、それをbyte[]にして返すメソッド
     *
     * @return HTMLテンプレートをbyte[]にしたもの
     */
    public byte[] writeHTML() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"ja\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>掲示板</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<p>" + HTTPResponse.getStatusLine() + "</p>");
        stringBuilder.append("</body>");

        return new String(stringBuilder).getBytes();
    }
}