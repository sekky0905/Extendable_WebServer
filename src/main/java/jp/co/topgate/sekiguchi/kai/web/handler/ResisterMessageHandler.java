package jp.co.topgate.sekiguchi.kai.web.handler;

import jp.co.topgate.sekiguchi.kai.web.http.HTTPRequest;
import jp.co.topgate.sekiguchi.kai.web.http.HTTPResponse;
import jp.co.topgate.sekiguchi.kai.web.model.Message;
import jp.co.topgate.sekiguchi.kai.web.model.ModelStorage;
import jp.co.topgate.sekiguchi.kai.web.util.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * "/program/board/resister/"に紐づくHandlerを表すクラス
 * Created by sekiguchikai on 2016/12/04.
 */
public class ResisterMessageHandler extends Handler {
    /**
     * リクエストPOSTの際のハンドラ
     *
     * @param httpRequest  リクエスト
     * @param httpResponse レスポンス
     */
    public void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse) {
        ModelStorage.chooseModelList(true);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
        String name = httpRequest.getRequestParameter("name");
        String comment = httpRequest.getRequestParameter("comment");

        Message message = new Message();
        message.setAtTime(atTime);
        message.setName(name);
        message.setComment(comment);

        Session.generateToken();
        ModelStorage.setModelList(message);



    }
}
