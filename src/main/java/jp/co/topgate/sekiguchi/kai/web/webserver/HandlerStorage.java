package jp.co.topgate.sekiguchi.kai.web.webserver;


import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.MessageHandler;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.IndexHandler;
import jp.co.topgate.sekiguchi.kai.web.webapp.staticserverapp.StaticFileHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Handlerを格納するためのクラス
 * Created by sekiguchikai on 2016/12/15.
 */
public class HandlerStorage {

    /**
     * key: パス、value: HandlerのMap
     */
    private static Map<String, Handler> handlerMap = new HashMap<>();

    /**
     * Handlerを初期化し、パスとバンドルするためのメソッド
     */
    static void initializeHandler() {
        // StaticFileApp
        Handler staticFileHandler = new StaticFileHandler();

        // BulletinBoardApp
        Handler indexHandler = new IndexHandler();
        Handler messageHandler = new MessageHandler();


        // StaticFileApp
        HandlerStorage.handlerMap.put("/", staticFileHandler);

        // BulletinBoardApp
        HandlerStorage.handlerMap.put("/program/board/", indexHandler);
        HandlerStorage.handlerMap.put("/program/board/message/", messageHandler);


    }


    /**
     * pathで指定したHandlerのインスタンスを取得するためのメソッド
     *
     * @param path 取得したい handlerのパス
     * @return handlerのインスタンス
     */
    static Handler getHandler(String path) {
        // webサーバの時
        if (!HandlerStorage.handlerMap.containsKey(path) || path.equals("/")) {
            return HandlerStorage.handlerMap.get("/");
        } else {
            return HandlerStorage.handlerMap.get(path);
        }
    }

}


