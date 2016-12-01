package jp.co.topgate.sekiguchi.kai.web;

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

        for (int i = 0; i <= ModelStorage.countModel(); i++) {
            if (i == ModelStorage.countModel()) {
                break;
            }
            Message message = (Message) ModelStorage.getModelList(i);

            stringBuilder.append(this.writeRepetition(message, i));

        }


        stringBuilder.append("<form action=\"/program/board/registered\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<table>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>ユーザーネーム:</th>");
        stringBuilder.append("<td><input type=\"text\" name=\"name\" size=\"40\"></td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>コメント</th>");
        stringBuilder.append("<td><textarea name=\"comment\" rows=\"4\" cols=\"40\">ここにコメントを記入してください</textarea></td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("<p><input type=\"submit\" value=\"送信する\"></p>");
        stringBuilder.append("</form>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");


        stringBuilder.append("指定したユーザーの書き込みのみ表示させることができます。<br>");
        stringBuilder.append("下記で、検索したいユーザー名を指定してください<br>");

        stringBuilder.append("<form action=\"/program/board/registered/search\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<table>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>ユーザーネーム:</th>");
        stringBuilder.append("<td><input type=\"text\" name=\"searchName\"></td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("<input type=\"submit\" value=\"検索する\">");
        stringBuilder.append("</form>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        return new String(stringBuilder).getBytes();

    }


    private String writeRepetition(Message message, int index) {
        StringBuilder stringBuilder = new StringBuilder();

        String name;
        String comment;

        if (message.getName().equals("エラー!文字を入力してください")) {
            stringBuilder.append("<table style=\"background-color:red;\">");
            name = " style=\"color:yellow;\"> ユーザーネームが入力されていません";
            comment = " style=\"color:yellow;\"> この書き込みを削除してください";
        } else {
            stringBuilder.append("<table>");
            name = ">" + message.getName();
            comment = ">" + message.getComment();
        }
        stringBuilder.append("<tr>");
        stringBuilder.append("<th>投稿日時:</th>");
        stringBuilder.append("<td>" + message.getAtTime() + "</td>");
        stringBuilder.append("</tr>");

        stringBuilder.append("<tr>");
        stringBuilder.append("<th>ユーザーネーム:</th>");
        stringBuilder.append("<td" + name + "</td>");
        stringBuilder.append("</tr>");

        stringBuilder.append("<tr>");
        stringBuilder.append("<th>コメント</th>");
        stringBuilder.append("<td" + comment + "</td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");


        stringBuilder.append("<form action=\"/program/board/registered/afterDelete\" method=\"post\" accept-charset=\"UTF-8\">");
        stringBuilder.append("<p>");
        stringBuilder.append("<input type=\"hidden\" name =\"delete\" value=" + index + ">");
        stringBuilder.append("<input type=\"submit\"  value=\" 削除 \"");
        stringBuilder.append("</p>");
        stringBuilder.append("</form>");


        return new String(stringBuilder);
    }
}
