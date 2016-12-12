package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model.Message;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Session;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * "/program/board/resister/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class ResisterMessageHandler extends Handler {
    /**
     * POSTの際のハンドラ
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        MessageStorage.chooseMessageList(true);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        String name = httpRequest.getRequestParameter("name");
        String comment = httpRequest.getRequestParameter("comment");

        Message message = new Message();
        message.setAtTime(atTime);
        message.setName(name);
        message.setComment(comment);

        Session.generateToken();
        MessageStorage.setMessageList(message);



    }
}
