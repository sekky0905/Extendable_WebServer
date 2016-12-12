package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;

import jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Session;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

/**
 * "/program/board/delete/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class DeleteMessageHandler extends Handler {
    /**
     * POSTの際のハンドラ
     *
     * @param httpRequest  httpRequestクラスのインスタンス
     * @param httpResponse httpResponseクラスのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        MessageStorage.chooseMessageList(true);
        int MessageIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
        MessageStorage.removeMessage(MessageIndex);

        Session.generateToken();


    }
}
