package jp.co.topgate.sekiguchi.kai.web.web_app;

import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 1つのアプリケーションを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class WebApp {

    /**
     * Handlerの名前とそれに紐づくHandlerのインスタンスを格納するためのMap
     */
    private static Map<String, Handler> handlerMap = new HashMap<>();

    /**
     * handlerMapに指定された名前で、Handlerクラスのインスタンスを格納するためのメソッド
     *
     * @param path    Handlerに紐づけるパス
     * @param handler handlerMapに格納するHandlerクラスのインスタンス
     */
    public void setHandler(String path, Handler handler) {
        handlerMap.put(path, handler);
    }

    /**
     * パスで指定されたHandlerクラスのインスタンスを返ためのメソッド
     *
     * @param path Handlerに紐づけられたパス
     * @return Handlerクラスのインスタンスの名前
     */
    public static Handler getHandlerMap(String path) {
        return handlerMap.get(path);
    }

    /**
     * 指定された名前のHandlerがHashMapうちに入っているかどうかの確認するためのメソッド
     *
     * @param handlerName Handlerの名前
     * @return Handlerが存在しているかどうかの真偽値
     */
    public static boolean handlerIsExist(String handlerName) {
        return handlerMap.containsKey(handlerName);
    }


}

