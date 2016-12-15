package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.handler;

import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;

<<<<<<< HEAD
=======
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;
>>>>>>> develop
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
<<<<<<< HEAD
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;
=======
>>>>>>> develop

/**
 * "/program/board/delete/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class DeleteMessageHandler extends Handler {
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
