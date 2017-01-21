package jp.co.topgate.sekiguchi.kai.web.webapp.staticserverapp;

import jp.co.topgate.sekiguchi.kai.web.webapp.WebApp;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;


/**
 * StaticServerAppを表すクラス
 * Created by sekiguchikai on 2016/12/16.
 */
public class StaticServerApp extends WebApp {

    /**
     * コンストラクタ
     * initHandlerメソッドを実施
     */
    public StaticServerApp() {
        this.initHandler();
    }

    /**
     * Handlerを初期化し、パスとバンドルするためのメソッド
     */
    public void initHandler() {
        Handler staticFileHandler = new StaticFileHandler();

        super.handlerMap.put("/", staticFileHandler);
    }

}
