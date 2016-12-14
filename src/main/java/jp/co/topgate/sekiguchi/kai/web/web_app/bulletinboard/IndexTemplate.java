package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard;

import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webServer.Template;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model.Message;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.util.XSSMeasure;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * 正常な時のTemplateを表すクラス
 * Created by sekiguchikai on 2016/11/22.
 */
public class IndexTemplate implements Template {
    Stream<Message> messageStream;

    public IndexTemplate(Stream<Message> messageStream) {
        this.messageStream = messageStream;
    }

    /**
     * HTMLのテンプレートを作成するメソッド
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
                .append("<title>掲示板</title>")
                .append("<style>")
                .append("table {")
                .append("border-collapse: collapse;")
                .append(" }")
                .append("td,")
                .append("th {")
                .append("border: solid 1px;")
                .append("padding: 0.5em;")
                .append("</style>")
                .append("</head>")
                .append("<body>");


        this.messageStream.forEach((Message message) -> {
            this.writeRepetition(stringBuilder, message);
        });


        stringBuilder.append("<form action=\"/program/board/register/\" method=\"post\" accept-charset=\"UTF-8\">")
                .append("<table>")
                .append("<tr>")
                .append("<th>ユーザーネーム(20文字まで):</th>")
                .append("<td><input type=\"text\" name=\"name\" maxlength=\"20\" size=\"40\"></td>")
                .append("</tr>")
                .append("<tr>")
                .append("<th>コメント(1000文字まで)</th>")
                .append("<td><textarea name=\"comment\" rows=\"4\" cols=\"40\" maxlength=\"1000\" placeholder=\"ここにコメントを記入してください\"></textarea></td>")
                .append("</tr>")
                .append("</table>")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<p><input type=\"submit\" value=\"送信する\"></p>")
                .append("</form>")
                .append("</body>")
                .append("</html>")


                .append("指定したユーザーの書き込みのみ表示させることができます。<br>")
                .append("下記で、検索したいユーザー名を指定してください<br>")

                .append("<form action=\"/program/board/search/\" method=\"post\" accept-charset=\"UTF-8\">")
                .append("<table>")
                .append("<tr>")
                .append("<th>ユーザーネーム:</th>")
                .append("<td><input type=\"text\" name=\"searchName\" maxlength=\"20\"></td>")
                .append("</tr>")
                .append("</table>")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<input type=\"submit\" value=\"検索する\">")
                .append("</form>")


                .append("<form action=\"/program/board/showAll/\" method=\"post\" accept-charset=\"UTF-8\">")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<p>")
                .append("<input type=\"submit\"  value=\" 全件表示 \"")
                .append("</p>")
                .append("</form>")

                .append("</body>")
                .append("</html>");

        httpResponse.setDynamicResponseBody(new String(stringBuilder).getBytes());
        httpResponse.sendResponse("html");
    }

    private void writeRepetition(StringBuilder stringBuilder, Message message) {
        if ("".equals(message.getName()) || "".equals(message.getComment())) {
            return;
        }

        stringBuilder.append("<table>")
                .append("<tr>")
                .append("<th>投稿日時:</th>")
                .append("<td>").append(message.getAtTime()).append("</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<th>ユーザーネーム:</th>")
                .append("<td>" + XSSMeasure.sanitize(message.getName()) + "</td>")
                .append("</tr>")

                .append("<tr>")
                .append("<th>コメント</th>")
                .append("<td>" + XSSMeasure.sanitize(message.getComment()) + "</td>")
                .append("</tr>")
                .append("</table>")
                .append("<form action=\"/program/board/delete/\" method=\"post\" accept-charset=\"UTF-8\">")
                .append("<input type=\"hidden\" name=\"token\" value=\"" + Token.getToken() + "\">")
                .append("<p>")
                .append("<input type=\"hidden\" name =\"delete\" value=" + message.getId() + ">")
                .append("<input type=\"submit\"  value=\" 削除 \"")
                .append("</p>")
                .append("</form>");
    }
}
