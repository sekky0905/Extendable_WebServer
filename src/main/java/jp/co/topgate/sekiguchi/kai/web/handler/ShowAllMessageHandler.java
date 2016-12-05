package jp.co.topgate.sekiguchi.kai.web.handler;


import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.model.ModelStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Session;

/**
 * "/program/board/showAll/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class ShowAllMessageHandler extends Handler {
    /**
     * リクエストPOSTの際のハンドラ
     *
     * @param httpRequest  リクエスト
     * @param httpResponse レスポンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        String queryString = httpRequest.getQueryString(httpRequest.getRequestMethod());
        httpRequest.setRequestParameter(queryString);

        ModelStorage.chooseModelList(true);
        Session.generateToken();

    }
}
