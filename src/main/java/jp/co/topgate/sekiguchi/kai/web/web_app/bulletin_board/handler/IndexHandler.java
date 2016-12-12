package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.util.Session;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.io.IOException;

/**
 * "/program/board/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class IndexHandler extends Handler {
    /**
     * GETの際のハンドラ
     *
     * @param httpRequest  httpRequestクラスのインスタンス
     * @param httpResponse httpResponseクラスのインスタンス
     * @throws java.io.IOException クライアントへのレスポンスの送信に失敗しました
     */
    public void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException {

        IndexTemplate indexTemplate = new IndexTemplate();

        Session.generateToken();

        httpResponse.setStatusLine(HTTPResponse.SC_OK);
        indexTemplate.writeHTML(httpRequest, httpResponse);

    }
}
