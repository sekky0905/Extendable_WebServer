package jp.co.topgate.sekiguchi.kai.web.web_app;

import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 1つのアプリケーションを表すクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public abstract class WebApp {

    /**
     * Handlerの名前とそれに紐づくHandlerのインスタンスを格納するためのMap
     */
    Map<String, Handler> handlerMap = new HashMap<>();

    WebApp() {
        Handler errHandler = new Handler();
        handlerMap.put("err", errHandler);
    }

    /**
     * WebAppクラスを継承した各クラスに必要なHandlerクラスをインスタンス化し、パスを紐付けるメソッド
     */
    public abstract void initializeHandler();


    /**
     * パスで指定されたHandlerクラスのインスタンスを返ためのメソッド
     *
     * @param path Handlerに紐づけられたパス
     * @return Handlerクラスのインスタンスの名前
     */
    public Handler getHandler(String path) {
        if (handlerMap.containsKey(path)) {
            return handlerMap.get(path);
        } else {
            return handlerMap.get("err");
        }

    }

}

