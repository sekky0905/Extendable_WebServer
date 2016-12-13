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
 * "/program/board/delete/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class DeleteMessageHandler extends Handler {
    /**
     * POSTの際のハンドラ
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException{
        MessageStorage.chooseMessageList(true);
        int MessageIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
        MessageStorage.removeMessage(MessageIndex);

        Token.generateToken();

        Template template = new IndexTemplate();
        httpResponse.addStatusLine(HTTPResponse.SC_OK);
        template.writeHTML(httpRequest, httpResponse);


    }
}
