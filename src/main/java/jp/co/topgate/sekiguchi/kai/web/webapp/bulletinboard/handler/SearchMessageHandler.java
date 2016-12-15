package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.handler;


import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;

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
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        if (Token.confirmToken(httpRequest.getRequestParameter("token"))) {

            MessageStorage.chooseMessageList(false);
            String name = httpRequest.getRequestParameter("searchName");
            MessageStorage.searchMessage(name);

        }
        Template template = new IndexTemplate();
        httpResponse.addStatusLine(HTTPResponse.SC_OK);
        template.writeHTML(httpRequest, httpResponse);


    }
}
