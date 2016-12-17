package jp.co.topgate.sekiguchi.kai.web.webserver;


/**
 * Handlerの継承元クラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class Handler {
    /**
     * リクエストGETの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        // オーバーライドしない場合は、404を返す
        ErrTemplate template = new ErrTemplate();
        template.setErrMessage("400 Not Found");
        template.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_NOT_FOUND, "Not Found", "html");

    }

    /**
     * リクエストPOSTの際のHandler
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws Exception {
        // オーバーライドしない場合は、404を返す
        ErrTemplate template = new ErrTemplate();
        template.setErrMessage("400 Not Found");
        template.writeHTML(httpRequest, httpResponse);
        httpResponse.sendResponse(HTTPResponse.SC_NOT_FOUND, "Not Found", "html");
    }
}
