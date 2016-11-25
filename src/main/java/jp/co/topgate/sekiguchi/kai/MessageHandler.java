package jp.co.topgate.sekiguchi.kai;

import java.time.ZonedDateTime;

/**
 * Messageに関するコントローラー
 * Created by sekiguchikai on 2016/11/22.
 */
public class MessageHandler implements Handler {
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        // 3.HTMLTemplateにMessage渡す
        HTMLTemplate htmlTemplate = new HTMLTemplate();


        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(htmlTemplate.writeHTML());

        httpResponse.sendResponse();

    }

    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        // 1.リクエストのMessageに関する情報を取得する
        ZonedDateTime atTime = ZonedDateTime.now();
        String userName = httpRequest.getRequestParameter("userName");
        String comment = httpRequest.getRequestParameter("comment");

        // 2.リクエストMessageに関する情報を設定する
        Message message = new Message();
        message.setAtTime(atTime);
        message.setUserName(userName);
        message.setComment(comment);

        // 3.HTMLTemplateにMessage渡す
        HTMLTemplate htmlTemplate = new HTMLTemplate();


        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(htmlTemplate.writeHTML());

        httpResponse.sendResponse();

    }
}
