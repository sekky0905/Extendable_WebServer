package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.handler;


import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;

/**
 * "/program/board/showAll/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class ShowAllMessageHandler extends Handler {
    /**
     * POSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        if (Token.confirmToken(httpRequest.getRequestParameter("token"))) {

            MessageStorage.chooseMessageList(true);
            Token.generateToken();

        }

        Template template = new IndexTemplate();
        httpResponse.addStatusLine(HTTPResponse.SC_OK);
        template.writeHTML(httpRequest, httpResponse);


    }
}
