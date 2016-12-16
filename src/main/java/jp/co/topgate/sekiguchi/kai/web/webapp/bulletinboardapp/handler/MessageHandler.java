package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler;

import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;


/**
 * "/program/board/delete-result/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class MessageHandler extends Handler {

    /**
     * POSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {


        String name = httpRequest.getRequestParameter("searchName");

        Template template = new IndexTemplate(MessageStorage.searchMessage(name));
        template.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", "html");
    }

    /**
     * POSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {

        if (Token.confirmToken(httpRequest.getRequestParameter("token"))) {

            int MessageIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
            MessageStorage.removeMessage(MessageIndex);

            Token.generateToken();

        }

        Template template = new IndexTemplate(MessageStorage.getAllMessage());
        template.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", "html");
    }
}
