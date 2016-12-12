package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.handler;


import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

/**
 * "/program/board/search/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class SearchMessageHandler extends Handler {

    /**
     * POSTの際のハンドラ
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        MessageStorage.chooseMessageList(false);
        String name = httpRequest.getRequestParameter("searchName");
        MessageStorage.searchMessage(name);

    }
}
