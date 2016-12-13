package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webServer.Template;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model.Message;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.util.XSSMeasure;

import java.io.IOException;

/**
 * 正常な時のTemplateを表すクラス
 * Created by sekiguchikai on 2016/11/22.
 */
public class IndexTemplate implements Template {
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


        int listSize;
        // 繰り返し部分
        if (MessageStorage.checkMessageList()) {
            listSize = MessageStorage.countMessage();
            stringBuilder.append(this.writeRepetition(listSize));
        } else {
            listSize = MessageStorage.countTemp();
            stringBuilder.append(this.writeRepetition(listSize));
        }


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

        httpResponse.setResponseBody(new String(stringBuilder).getBytes());
        httpResponse.sendResponse("html");

    }

    /**
     * 指定された回数、繰り返し部分のHTMLを作成し、返すメソッド
     * @param listSize modelListのsize
     * @return 繰り返し部分のHTML
     */

    private String writeRepetition(int listSize) {
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i <= listSize; i++) {
            if (i == listSize) {
                break;
            }

            Message message;
            if (MessageStorage.checkMessageList()) {
                message = MessageStorage.getMessageList(i);
            } else {
                message = MessageStorage.getTempList(i);
            }


            if (!(message.getName().equals("") || message.getComment().equals(""))) {

                stringBuilder.append("<table>")
                        .append("<tr>")
                        .append("<th>投稿日時:</th>")
                        .append("<td>" + message.getAtTime() + "</td>")
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
                        .append("<input type=\"hidden\" name =\"delete\" value=" + i + ">")
                        .append("<input type=\"submit\"  value=\" 削除 \"")
                        .append("</p>")
                        .append("</form>");
            }

        }
        return new String(stringBuilder);
    }
}
