package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler;

import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;

/**
 * "/program/board/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class IndexHandler extends Handler {
    /**
     * GETの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     * @throws java.io.IOException クライアントへのレスポンスの送信に失敗しました
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        Token.generateToken();
        IndexTemplate indexTemplate = new IndexTemplate(MessageStorage.getAllMessage());
        indexTemplate.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", "html");

    }
}
