package jp.co.topgate.sekiguchi.kai.web.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.util.ModelStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Session;

/**
 * Created by sekiguchikai on 2016/12/03.
 */
public class DeleteMessageHandler extends Handler {
    /**
     * リクエストPOSTの際のハンドラ
     *
     * @param httpRequest  リクエスト
     * @param httpResponse レスポンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {

        String queryString = httpRequest.getQueryString(httpRequest.getRequestMethod());
        httpRequest.setRequestParameter(queryString);

        ModelStorage.choiceModelList(true);
        int modelIndex = Integer.parseInt(httpRequest.getRequestParameter("delete"));
        ModelStorage.removeModel(modelIndex);

        Session.generateToken();


    }
}
