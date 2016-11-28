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
    public byte[] writeHTML() {
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"en\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Document</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");

        for (int i = 0; i < ModelStorage.ConutModel(); i++) {
            Message message = (Message) ModelStorage.getModelList(i);

            stringBuilder.append("<p>投稿日時:" + message.getAtTime() + "</p>");
            stringBuilder.append("<p>名前:" + message.getUserName() + "</p>");
            stringBuilder.append("<p>コメント</p>");
            stringBuilder.append("<p>" + message.getComment() + "</p>");
            stringBuilder.append("<form action=\"/program/board/registered/afterDelete\" method=\"post\" accept-charset=\"UTF-8\">");
            stringBuilder.append("<p>");
            stringBuilder.append("<input type=\"hidden\" name =\"delete\" value=" + i + ">");
            stringBuilder.append("<input type=\"submit\"  value=\" 削除 \"");
            stringBuilder.append("</p>");
            stringBuilder.append("</form>");


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
        stringBuilder.append("<p>");
        stringBuilder.append("指定したユーザーの書き込みのみ表示させることができます。<br>");
        stringBuilder.append("下記で、検索したいユーザー名を指定してください<br>");
        stringBuilder.append("ユーザー名:<input type=\"text\" name=\"search\">");
        stringBuilder.append("</p>");
        stringBuilder.append("<p>");
        stringBuilder.append("<input type=\"submit\" value=\"検索する\">");
        stringBuilder.append("</p>");
        stringBuilder.append("</form>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return new String(stringBuilder).getBytes();

    }
}
