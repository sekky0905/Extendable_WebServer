package jp.co.topgate.sekiguchi.kai.web.webServer;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;

import java.io.IOException;

/**
 * Handlerの継承元クラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class Handler {
    /**
     * リクエストGETの際のハンドラ
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {

    }

    /**
     * リクエストPOSTの際のハンドラ
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {
    }
}
