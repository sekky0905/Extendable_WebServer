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

        String userName = httpRequest.getRequestParameter("userName");
        String comment = httpRequest.getRequestParameter("comment");


        Message message = new Message();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime atTime = LocalDateTime.parse(now.getYear() + "/" + now.getMonth() + "/" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.
                getMinute() + ":" + now.getSecond(), dateTimeFormatter);
        message.setAtTime(atTime);
        message.setUserName(userName);
        message.setComment(comment);

        // ここで、cookieがあれば過去
        if (httpRequest.getRequestHeader().contains("Cookie:")) {
            // cookieでvalueを取得
            // 取得したものをmodelのフィールドに設定
            // httprequestのmodelListに追加

            for (int i = 0; i <  )
            Cookie cookie = httpRequest.getCookie();
            cookie.getValue("userName" + String(i));


            PastModel pastModel = new PastModel();
            pastModel.getMessageList();
        }





        System.out.println("modelのインスタンス数は" + modelNumber2);

        Template template = new ResultTemplate();

        httpResponse.setResponseHeader("html");
        httpResponse.setStatusLine("HTTP/1.1 200 OK");
        httpResponse.setResponseBody(template.writeHTML(httpRequest, httpResponse));

        httpResponse.sendResponse();

    }
}