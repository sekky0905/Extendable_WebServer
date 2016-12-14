package jp.co.topgate.sekiguchi.kai.web.webapp.staticwebserver;

import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;
import jp.co.topgate.sekiguchi.kai.web.webapp.WebApp;


/**
 * StaticWebServerアプリケーションを表すクラス
 * Created by sekiguchikai on 2016/12/13.
 */
public class StaticWebServerApp extends WebApp {

    /**
     * WebAppクラスを継承した各クラスに必要なHandlerクラスをインスタンス化し、パスを紐付けるメソッド
     */
    public void initializeHandler() {
        Handler staticFileHandler = new StaticFileHandler();

        handlerMap.put("/", staticFileHandler);
    }

    /**
     * WebAppクラスのgetHandlerメソッドをオーバライドしたメソッド
     * 必ず、StaticFileHandlerクラスのインスタンスを返す
     *
     * @param requestURI リクエストURI
     * @return StaticFileHandlerクラスのインスタンス
     */
    public Handler getHandler(String requestURI) {
        return handlerMap.get("/");
    }

}
