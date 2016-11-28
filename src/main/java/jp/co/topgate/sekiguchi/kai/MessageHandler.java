package jp.co.topgate.sekiguchi.kai;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Messageに関するコントローラー
 * Created by sekiguchikai on 2016/11/22.
 */
public class MessageHandler implements Handler {
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        // 3.HTMLTemplateにMessage渡す
        FormTemplate formTemplate = new FormTemplate();


        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(formTemplate.writeHTML(httpRequest, httpResponse));

        httpResponse.sendResponse();

    }

    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String requestLine = httpRequest.getRequestLine();
        httpRequest.setRequestParameter(httpRequest.getQueryString(httpRequest.getRequestMethod(requestLine), httpRequest.getRequestURI(requestLine)));



        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        String userName = httpRequest.getRequestParameter("userName");
        String comment = httpRequest.getRequestParameter("comment");


        Message message = new Message();
        message.setAtTime(atTime);
        message.setUserName(userName);
        message.setComment(comment);

        String modelNumber = String.valueOf(httpRequest.countModel() + 1);
        System.out.println("MessageHandlerの時のmodelのカウントは" + modelNumber);
        httpRequest.setModel("message" + modelNumber, message);
        System.out.println("modelのインスタンス数は" + httpRequest.countModel());

        Template template = new ResultTemplate();

        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(template.writeHTML(httpRequest, httpResponse));

        httpResponse.sendResponse();

    }
}