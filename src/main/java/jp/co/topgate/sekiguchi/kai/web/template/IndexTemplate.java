package jp.co.topgate.sekiguchi.kai.web.template;

import jp.co.topgate.sekiguchi.kai.web.model.Message;
import jp.co.topgate.sekiguchi.kai.web.model.ModelStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Session;
import jp.co.topgate.sekiguchi.kai.web.util.XSSMeasure;

/**
 * Created by sekiguchikai on 2016/11/22.
 */
public class IndexTemplate implements Template {
    /**
     * HTMLのテンプレートを作成し、それをbyte[]にして返すメソッド
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
        stringBuilder.append("<style>");
        stringBuilder.append("table {");
        stringBuilder.append("border-collapse: collapse;");
        stringBuilder.append(" }");
        stringBuilder.append("td,");
        stringBuilder.append("th {");
        stringBuilder.append("border: solid 1px;");
        stringBuilder.append("padding: 0.5em;");
        stringBuilder.append("</style>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");


        int listSize;
        // 繰り返し部分
        if (ModelStorage.checkModelList()) {
            listSize = ModelStorage.countModel();
            stringBuilder.append(this.writeRepetition(listSize));
        } else {
            listSize = ModelStorage.countTempo();
            stringBuilder.append(this.writeRepetition(listSize));
        }


        stringBuilder.append("<form action=\"/program/board/resister/\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<table>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>ユーザーネーム:</th>");
        stringBuilder.append("<td><input type=\"text\" name=\"name\" maxlength=\"20\" size=\"40\"></td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>コメント</th>");
        stringBuilder.append("<td><textarea name=\"comment\" rows=\"4\" cols=\"40\" maxlength=\"1000\" placeholder=\"ここにコメントを記入してください\"></textarea></td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("<input type=\"hidden\" name=\"token\" value=\"" + Session.getToken() + "\">");
        stringBuilder.append("<p><input type=\"submit\" value=\"送信する\"></p>");
        stringBuilder.append("</form>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");


        stringBuilder.append("指定したユーザーの書き込みのみ表示させることができます。<br>");
        stringBuilder.append("下記で、検索したいユーザー名を指定してください<br>");

        stringBuilder.append("<form action=\"/program/board/search/\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<table>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>ユーザーネーム:</th>");
        stringBuilder.append("<td><input type=\"text\" name=\"searchName\"></td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("<input type=\"hidden\" name=\"token\" value=\"" + Session.getToken() + "\">");
        stringBuilder.append("<input type=\"submit\" value=\"検索する\">");
        stringBuilder.append("</form>");


        stringBuilder.append("<form action=\"/program/board/showAll/\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<input type=\"hidden\" name=\"token\" value=\"" + Session.getToken() + "\">");
        stringBuilder.append("<p>");
        stringBuilder.append("<input type=\"submit\"  value=\" 全件表示 \"");
        stringBuilder.append("</p>");
        stringBuilder.append("</form>");

        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return new String(stringBuilder).getBytes();

    }


    private String writeRepetition(int listSize) {
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i <= listSize; i++) {
            if (i == listSize) {
                break;
            }

            Message message;
            if (ModelStorage.checkModelList()) {
                message = (Message) ModelStorage.getModelList(i);
            } else {
                message = (Message) ModelStorage.getTempoList(i);
            }


            if (!(message.getName().equals("") || message.getComment().equals(""))) {

                stringBuilder.append("<table>");
                stringBuilder.append("<tr>");
                stringBuilder.append("<th>投稿日時:</th>");
                stringBuilder.append("<td>" + message.getAtTime() + "</td>");
                stringBuilder.append("</tr>");

                stringBuilder.append("<tr>");
                stringBuilder.append("<th>ユーザーネーム:</th>");
                stringBuilder.append("<td>" + XSSMeasure.sanitize(message.getName()) + "</td>");
                stringBuilder.append("</tr>");

                stringBuilder.append("<tr>");
                stringBuilder.append("<th>コメント</th>");
                stringBuilder.append("<td>" + XSSMeasure.sanitize(message.getComment()) + "</td>");
                stringBuilder.append("</tr>");
                stringBuilder.append("</table>");
                stringBuilder.append("<form action=\"/program/board/delete/\" method=\"post\" accept-charset=\"UTF-8\">");
                stringBuilder.append("<input type=\"hidden\" name=\"token\" value=\"" + Session.getToken() + "\">");
                stringBuilder.append("<p>");
                stringBuilder.append("<input type=\"hidden\" name =\"delete\" value=" + i + ">");
                stringBuilder.append("<input type=\"submit\"  value=\" 削除 \"");
                stringBuilder.append("</p>");
                stringBuilder.append("</form>");
            }


        }
        return new String(stringBuilder);
    }
}
