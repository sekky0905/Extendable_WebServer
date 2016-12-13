package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.handler;


import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webServer.Template;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.io.IOException;

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
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException{
        MessageStorage.chooseMessageList(false);
        String name = httpRequest.getRequestParameter("searchName");
        MessageStorage.searchMessage(name);


        Template template = new IndexTemplate();
        httpResponse.addStatusLine(HTTPResponse.SC_OK);
        template.writeHTML(httpRequest, httpResponse);

    }
}
