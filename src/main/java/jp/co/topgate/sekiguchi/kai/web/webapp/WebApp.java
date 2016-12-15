package jp.co.topgate.sekiguchi.kai.web.webapp;

import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;

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
   protected Map<String, Handler> handlerMap = new HashMap<>();

    /**
     * コンストラクタ
     * エラーの際のHandlerを設定する
     */
    protected WebApp() {
        Handler errHandler = new Handler();
        handlerMap.put("err", errHandler);
    }

    /**
     * WebAppクラスを継承した各クラスに必要なHandlerクラスをインスタンス化し、パスを紐付ける抽象メソッド
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

