package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.handler;

import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webServer.HTTPResponse;

import jp.co.topgate.sekiguchi.kai.web.webServer.Template;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.io.IOException;

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

            MessageStorage.chooseMessageList(true);
            int MessageIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
            MessageStorage.removeMessage(MessageIndex);

            Token.generateToken();

            Template template = new IndexTemplate();
            httpResponse.addStatusLine(HTTPResponse.SC_OK);
            template.writeHTML(httpRequest, httpResponse);

        } else {
            System.out.println("tokenの番号が適切ではありません");
        }


    }
}
