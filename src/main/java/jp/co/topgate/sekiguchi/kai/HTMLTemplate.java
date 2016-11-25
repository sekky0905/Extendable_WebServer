package jp.co.topgate.sekiguchi.kai;

import java.util.ArrayList;

/**
 * Created by sekiguchikai on 2016/11/22.
 */
public class HTMLTemplate {
    /**
     * HTMLのテンプレートを作成し、それをbyte[]にして返すメソッド
     *
     * @param messageList Messageインスタンスを格納したList
     * @return HTMLテンプレートをbyte[]にしたもの
     */
    public byte[] writeHTML() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"en\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Document</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<form action=\"src/main/java/message\" method=\"post\">");
        stringBuilder.append("<p>");
        stringBuilder.append("名前：<input type=\"text\" name=\"userName\" size=\"40\" maxlength=\"20\">");
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























