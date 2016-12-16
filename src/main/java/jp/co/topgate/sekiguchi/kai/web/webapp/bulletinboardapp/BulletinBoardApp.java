package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp;

import jp.co.topgate.sekiguchi.kai.web.webapp.WebApp;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.IndexHandler;
import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.handler.MessageHandler;
import jp.co.topgate.sekiguchi.kai.web.webserver.Handler;


/**
 * BulletinBoardAppを表すクラス
 * Created by sekiguchikai on 2016/12/16.
 */
public class BulletinBoardApp extends WebApp {

    /**
     * コンストラクタ
     * initHandlerメソッドを実施
     */
    public BulletinBoardApp() {
        this.initHandler();
    }

    /**
     * BulletinBoardApp所属のHandlerを初期化し、URLと結びつけるためのメソッド
     */
    public void initHandler() {
        Handler indexHandler = new IndexHandler();
        Handler messageHandler = new MessageHandler();

        super.handlerMap.put("/program/board/", indexHandler);
        super.handlerMap.put("/program/board/message/", messageHandler);
    }

}
