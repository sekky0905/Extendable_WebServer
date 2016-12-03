package jp.co.topgate.sekiguchi.kai.web.handler;

import jp.co.topgate.sekiguchi.kai.web.*;

/**
 * Created by sekiguchikai on 2016/12/03.
 */
public class IndexHandler extends Handler {
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
}
