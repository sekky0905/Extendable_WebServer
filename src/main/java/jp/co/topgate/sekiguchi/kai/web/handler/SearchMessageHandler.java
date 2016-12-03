package jp.co.topgate.sekiguchi.kai.web.handler;


import jp.co.topgate.sekiguchi.kai.web.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.Handler;
import jp.co.topgate.sekiguchi.kai.web.util.ModelStorage;

/**
 * Created by sekiguchikai on 2016/12/04.
 */
public class SearchMessageHandler extends Handler {

    /**
     * リクエストPOSTの際のハンドラ
     *
     * @param httpRequest  リクエスト
     * @param httpResponse レスポンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        String queryString = httpRequest.getQueryString(httpRequest.getRequestMethod());
        httpRequest.setRequestParameter(queryString);

        ModelStorage.choiceModelList(false);
        String name = httpRequest.getRequestParameter("searchName");
        ModelStorage.searchModel(name);

    }
}