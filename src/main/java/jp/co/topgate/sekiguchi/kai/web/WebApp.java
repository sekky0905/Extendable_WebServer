package jp.co.topgate.sekiguchi.kai.web;

import jp.co.topgate.sekiguchi.kai.web.handler.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sekiguchikai on 2016/12/03.
 */
public class WebApp {
    /**
     * Handlerのインスタンスが格納されているMap
     */
    private static Map<String, String> handlerNameMap = new HashMap<>();

    private static Map<String, Handler> handlerMap = new HashMap<>();

    /**
     * handlerMapに指定された名前で、Handlerクラスのインスタンスを格納するためのメソッド
     *
     * @param handlerName handlerMapに格納するHandlerクラスのインスタンスの名前
     * @param handler     Handlerクラスのインスタンス
     */
    public void setHandlerName(String path, String handlerName) {
        handlerNameMap.put(path, handlerName);
    }


    /**
     * パスで指定されたHandlerクラスのインスタンスを返す
     *
     * @param handlerName Handlerクラスの名前
     * @return Handlerのインスタンス
     */
    public static String getHandlerName(String path) {
        return handlerNameMap.get(path);
    }

    /**
     * 指定された名前のハンドラがHashMapうちに入っているかどうかの確認
     *
     * @param handkerName ハンドラの名前
     * @return ハンドラが存在しているか
     */
    public static boolean checkHandlerNameExistence(String handlerName) {
        return handlerNameMap.containsKey(handlerName);
    }

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

    public static Handler getHandlerMap(String handlerName) {
        return WebApp.handlerMap.get(handlerName);

    }
}

