package jp.co.topgate.sekiguchi.kai;

import java.util.ArrayList;

/**
 * Created by sekiguchikai on 2016/11/22.
 */
public class ResultTemplate implements Template {
    /**
     * HTMLのテンプレートを作成し、それをbyte[]にして返すメソッド
     *
     * @return HTMLテンプレートをbyte[]にしたもの
     */
    public byte[] writeHTML(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        StringBuilder stringBuilder = new StringBuilder();

        String modelNumber = String.valueOf(httpRequest.countModel());
        System.out.println("ResultTemplateの時のmodelのカウントは" + modelNumber);
        Message message = (Message) httpRequest.getModel("message" + String.valueOf(httpRequest.countModel() - 1));


        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"en\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Document</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");


        for (int i = 0; i < httpRequest.countModel(); i++) {

            stringBuilder.append("<p>投稿日時:" + message.getAtTime() + "</p>");
            stringBuilder.append("<p>名前:" + message.getUserName() + "</p>");
            stringBuilder.append("<p>コメント</p>");
            stringBuilder.append("<p>" + message.getComment() + "</p>");
            stringBuilder.append("<br>");

        }

        stringBuilder.append("<form action=\"/program/board/registered\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<p>");
        stringBuilder.append("名前：<input type=\"text\" name=\"userName\" size=\"40\">");
        stringBuilder.append("</p>");
        stringBuilder.append("<p>");
        stringBuilder.append("<textarea name=\"comment\" rows=\"4\" cols=\"40\">ここにコメントを記入してください</textarea><br>");
        stringBuilder.append("</p>");
        stringBuilder.append("<p>");
        stringBuilder.append("<input type=\"submit\" value=\"送信する\">");
        stringBuilder.append("</p>");
        stringBuilder.append("</form>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return new String(stringBuilder).getBytes();

    }
}























