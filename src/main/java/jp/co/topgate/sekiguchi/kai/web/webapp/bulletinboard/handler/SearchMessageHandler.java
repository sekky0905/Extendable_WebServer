package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.handler;


import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;

/**
 * "/program/board/search/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class SearchMessageHandler extends Handler {

    /**
     * POSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        String name = null;
        if (Token.confirmToken(httpRequest.getRequestParameter("token"))) {

            name = httpRequest.getRequestParameter("searchName");

        }
        Template template = new IndexTemplate(MessageStorage.searchMessage(name));
        template.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", "html");


    }
}