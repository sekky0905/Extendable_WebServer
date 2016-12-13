package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.handler;


import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webServer.Template;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.io.IOException;

/**
 * "/program/board/showAll/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class ShowAllMessageHandler extends Handler {
    /**
     * POSTの際のハンドラ
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException{
        MessageStorage.chooseMessageList(true);
        Token.generateToken();


        Template template = new IndexTemplate();
        httpResponse.addStatusLine(HTTPResponse.SC_OK);
        template.writeHTML(httpRequest, httpResponse);

    }
}
