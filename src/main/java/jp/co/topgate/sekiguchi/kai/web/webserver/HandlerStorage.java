package jp.co.topgate.sekiguchi.kai.web.webserver;


import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.DeleteMessageHandler;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.IndexHandler;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.RegisterMessageHandler;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.SearchMessageHandler;
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
        Handler registerMessageHandler = new RegisterMessageHandler();
        Handler searchMessageHandler = new SearchMessageHandler();
        Handler deleteMessageHandler = new DeleteMessageHandler();


        // StaticFileApp
        HandlerStorage.handlerMap.put("/", staticFileHandler);

        // BulletinBoardApp
        HandlerStorage.handlerMap.put("/program/board/", indexHandler);
        HandlerStorage.handlerMap.put("/program/board/register/", registerMessageHandler);
        HandlerStorage.handlerMap.put("/program/board/search/", searchMessageHandler);
        HandlerStorage.handlerMap.put("/program/board/delete/", deleteMessageHandler);

    }


    /**
     * pathで指定したHandlerのインスタンスを取得するためのメソッド
     *
     * @param path 取得したい handlerのパス
     * @return handlerのインスタンス
     */
    static Handler getHandler(String path) {

        int secondSlash = path.indexOf(("/"), 1);
        int thirdSlash = path.indexOf(("/"), secondSlash + 1);
        String appPath = path.substring(0, thirdSlash + 1);

        // webサーバの時
        if (!HandlerStorage.handlerMap.containsKey(appPath) || path.equals("/")) {
            return HandlerStorage.handlerMap.get("/");
        } else {
            return HandlerStorage.handlerMap.get(appPath);
        }
    }

}
