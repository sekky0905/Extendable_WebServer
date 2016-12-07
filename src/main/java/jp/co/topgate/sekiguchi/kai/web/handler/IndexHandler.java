package jp.co.topgate.sekiguchi.kai.web.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.template.IndexTemplate;
import jp.co.topgate.sekiguchi.kai.web.util.ResponseHeaderMaker;
import jp.co.topgate.sekiguchi.kai.web.util.Session;

/**
 * "/program/board/"に紐づくHandlerを表すクラス
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

        httpResponse.setStatusLine(HTTPResponse.SC_OK);
        httpResponse.sendResponse(ResponseHeaderMaker.makeContentType("html"), indexTemplate.writeHTML());

    }
}