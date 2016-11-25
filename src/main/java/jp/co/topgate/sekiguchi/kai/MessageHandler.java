package jp.co.topgate.sekiguchi.kai;

import java.time.ZonedDateTime;

/**
 * Messageに関するコントローラー Created by sekiguchikai on 2016/11/22.
 */
public class MessageHandler implements Handler {
	public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		// 3.HTMLTemplateにMessage渡す
		FormTemplate formTemplate = new FormTemplate();

		httpResponse.setResponseHeader("html");
		httpResponse.setStatusLine("HTTP/1.1 200 OK");
		httpResponse.setResponseBody(formTemplate.writeHTML());

		httpResponse.sendResponse();

	}

	public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {

		System.out.print("POSTダヨ");

		httpRequest.setRequestParameter(httpRequest.getRequstQuery(httpRequest.getRequestLine()));

		ZonedDateTime atTime = ZonedDateTime.now();
		String userName = httpRequest.getRequestParameter("userName");
		String comment = httpRequest.getRequestParameter("comment");

		Message message = new Message();
		message.setAtTime(atTime);
		message.setUserName(userName);
		message.setComment(comment);

		FormTemplate formTemplate = new FormTemplate();

		httpResponse.setResponseHeader("html");
		httpResponse.setStatusLine("HTTP/1.1 200 OK");
		httpResponse.setResponseBody(formTemplate.writeHTML());

		httpResponse.sendResponse();

	}
}
