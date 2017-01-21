package jp.co.topgate.sekiguchi.kai.web.webapp;

import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;

import java.util.HashMap;
import java.util.Map;

/**
 * WebAppの抽象クラス
 * 各App系クラスは本クラスを継承する
 * Created by sekiguchikai on 2016/12/16.
 */
public abstract class WebApp {

    /**
     * key: パス、value: HandlerのMap
     */
    protected Map<String, Handler> handlerMap = new HashMap<>();


    /**
     * WebApp所属のHandlerを初期化し、URLと結びつけるためのメソッド
     */
    public abstract void initHandler();

    /**
     * pathで指定したHandlerのインスタンスを取得するためのメソッド
     *
     * @param path 取得したい handlerのパス
     * @return handlerのインスタンス
     */
    public Handler getHandler(String path) {
        // webサーバの時
        if (!this.handlerMap.containsKey(path) || path.equals("/")) {
            return this.handlerMap.get("/");
        } else {
            return this.handlerMap.get(path);
        }
    }

}