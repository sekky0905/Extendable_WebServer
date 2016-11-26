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
    public byte[] writeHTML(Message message) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"en\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Document</title>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<p>投稿日時:" + message.getAtTime() + "</p>");
        stringBuilder.append("<p>名前:" + message.getUserName() + "</p>");
        stringBuilder.append("<p>コメント:" + message.getComment() + "</p>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");


        return new String(stringBuilder).getBytes();
    }
}























