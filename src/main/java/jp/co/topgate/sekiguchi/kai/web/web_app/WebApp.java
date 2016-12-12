package jp.co.topgate.sekiguchi.kai.web.web_app;

import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.handler.*;
import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 1つのアプリケーションを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class WebApp {
    /**
     * Handlerのパスとそれに紐づくHandlerの名前を格納するためのMap
     */
    private static Map<String, String> handlerNameMap = new HashMap<>();
    /**
     * Handlerの名前とそれに紐づくHandlerのインスタンスを格納するためのMap
     */
    private static Map<String, Handler> handlerMap = new HashMap<>();

    /**
     * handlerMapに指定された名前で、Handlerクラスのインスタンスを格納するためのメソッド
     *
     * @param path Handlerに紐づけるパス
     * @param handlerName handlerMapに格納するHandlerクラスのインスタンスの名前
     */
    public void setHandlerName(String path, String handlerName) {
        handlerNameMap.put(path, handlerName);
    }


    /**
     * パスで指定されたHandlerクラスのインスタンスを返ためのメソッド
     *
     * @param path Handlerに紐づけられたパス
     * @return Handlerクラスのインスタンスの名前
     */
    public static String getHandlerName(String path) {
        return handlerNameMap.get(path);
    }

    /**
     * 指定された名前のHandlerがHashMapうちに入っているかどうかの確認するためのメソッド
     * @param handlerName Handlerの名前
     * @return Handlerが存在しているかどうかの真偽値
     */
    public static boolean handlerNameIsExist(String handlerName) {
        return handlerNameMap.containsKey(handlerName);
    }

    /**
     * Handlerの初期設定を行うメソッド
     */
    public static void setHandlerMap() {
        IndexHandler indexHandler = new IndexHandler();
        ResisterMessageHandler resisterMessageHandler = new ResisterMessageHandler();
        SearchMessageHandler searchMessageHandler = new SearchMessageHandler();
        DeleteMessageHandler deleteMessageHandler = new DeleteMessageHandler();
        ShowAllMessageHandler showAllMessageHandler = new ShowAllMessageHandler();

        WebApp.handlerMap.put("IndexHandler", indexHandler);
        WebApp.handlerMap.put("ResisterMessageHandler", resisterMessageHandler);
        WebApp.handlerMap.put("SearchMessageHandler", searchMessageHandler);
        WebApp.handlerMap.put("DeleteMessageHandler", deleteMessageHandler);
        WebApp.handlerMap.put("ShowAllMessageHandler", showAllMessageHandler);

    }

    /**
     * ハンドラの名前を指定するとその名前に紐づけられたHandlerインスタンスを返すメソッド
     * @param handlerName Handlerの名前
     * @return Handlerインスタンス
     */
    public static Handler getHandlerMap(String handlerName) {
        return WebApp.handlerMap.get(handlerName);
    }
}

