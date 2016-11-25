package jp.co.topgate.sekiguchi.kai;

import java.io.File;
import java.time.ZonedDateTime;

/**
 * Messageに関するコントローラー
 * Created by sekiguchikai on 2016/11/22.
 */
class MessageProcessor implements Processor {
    public void process(HTTPRequest httpRequest, HTTPResponse httpResponse) {

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
        htmlTemplate.writeHTML(message);

    }
}
