package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler;


import jp.co.topgate.sekiguchi.kai.web.util.Token;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model.Message;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model.MessageStorage;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.webserver.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;
import jp.co.topgate.sekiguchi.kai.web.webserver.Template;

import java.time.LocalDateTime;

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

    /**
     * POSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        if (Token.confirmToken(httpRequest.getRequestParameter("token"))) {
            System.out.println("RegisterMessageHandlerのhandlePOSTが呼び出されました");
            LocalDateTime createdAt = LocalDateTime.now();
            String name = httpRequest.getRequestParameter("name");
            String comment = httpRequest.getRequestParameter("comment");

            Message message = new Message();
            message.setCreatedAt(createdAt);
            message.setName(name);
            message.setComment(comment);

            Token.generateToken();
            MessageStorage.addMessage(message);
        }


        Template template = new IndexTemplate(MessageStorage.getAllMessage());
        template.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_OK, "OK", "html");

    }
}
