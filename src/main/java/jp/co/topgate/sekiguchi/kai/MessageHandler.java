package jp.co.topgate.sekiguchi.kai;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Messageに関するコントローラー
 * Created by sekiguchikai on 2016/11/22.
 */
public class MessageHandler implements Handler {
    /**
     * リクエストGETの際のハンドラ
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        FormTemplate formTemplate = new FormTemplate();

        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(formTemplate.writeHTML());

        httpResponse.sendResponse();
    }

    /**
     * リクエストPOSTの際のハンドラ
     * @param httpRequest リクエスト
     * @param httpResponse レスポンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        String requestLine = httpRequest.getRequestLine();
        String queryString = httpRequest.getQueryString(httpRequest.getRequestMethod(requestLine), httpRequest.getSecondSentence(requestLine));
        httpRequest.setRequestParameter(queryString);

        if (httpRequest.getRequestURI(httpRequest.getSecondSentence(requestLine)).equals("/program/board/registered")) {
            String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
            String userName = httpRequest.getRequestParameter("userName");
            String comment = httpRequest.getRequestParameter("comment");

            Message message = new Message();
            message.setAtTime(atTime);
            message.setUserName(userName);
            message.setComment(comment);

            MessageStorage.setModelList(message);

        } else if (httpRequest.getRequestURI(httpRequest.getSecondSentence(requestLine)).equals("/program/board/registered/afterDelete")) {
            int objIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
            MessageStorage.removeModel(objIndex);
        } else if (httpRequest.getRequestURI(httpRequest.getSecondSentence(requestLine)).equals("/program/board/registered/search")) {
            String userName = httpRequest.getRequestParameter("searchName");
            MessageStorage.searchModel(userName);
        }

        Template template = new ResultTemplate();

        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(template.writeHTML());

        httpResponse.sendResponse();

    }
}