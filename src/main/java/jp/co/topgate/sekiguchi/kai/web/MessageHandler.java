package jp.co.topgate.sekiguchi.kai.web;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Messageに関するコントローラー
 * Created by sekiguchikai on 2016/11/22.
 */
public class MessageHandler implements Handler {
    /**
     * リクエストGETの際のハンドラ
     *
     * @param httpRequest  リクエスト
     * @param httpResponse レスポンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        IndexTemplate indexTemplate = new IndexTemplate();

        Session.generateToken();

        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(indexTemplate.writeHTML());


        httpResponse.sendResponse();

    }

    /**
     * リクエストPOSTの際のハンドラ
     *
     * @param httpRequest  リクエスト
     * @param httpResponse レスポンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String queryString = httpRequest.getQueryString(httpRequest.getRequestMethod());
        httpRequest.setRequestParameter(queryString);


        if (httpRequest.getRequestURI().equals("/program/board/registered") && Session.confirmToken(httpRequest.getRequestParameter("token"))) {

            ModelStorage.choiceModelList(true);
            String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
            String name = httpRequest.getRequestParameter("name");
            String comment = httpRequest.getRequestParameter("comment");

            Message message = new Message();
            message.setAtTime(atTime);
            message.setName(name);
            message.setComment(comment);

            Session.generateToken();
            ModelStorage.setModelList(message);

        } else if (httpRequest.getRequestURI().equals("/program/board/registered/afterDelete") && Session.confirmToken(httpRequest.getRequestParameter("token"))) {
            ModelStorage.choiceModelList(true);
            int modelIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
            ModelStorage.removeModel(modelIndex);
            //
            Session.generateToken();
        } else if (httpRequest.getRequestURI().equals("/program/board/registered/search") && Session.confirmToken(httpRequest.getRequestParameter("token"))) {
            ModelStorage.choiceModelList(false);
            String name = httpRequest.getRequestParameter("searchName");
            ModelStorage.searchModel(name);

            Session.generateToken();
        } else if (httpRequest.getRequestURI().equals("/program/board/registered/showAll") && Session.confirmToken(httpRequest.getRequestParameter("token"))) {
            ModelStorage.choiceModelList(true);
            Session.generateToken();
        } else {
            System.out.println("不正なリクエスト");
        }

        Template template = new IndexTemplate();

        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(template.writeHTML());

        httpResponse.sendResponse();

    }
}