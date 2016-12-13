package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard;

import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;
import jp.co.topgate.sekiguchi.kai.web.web_app.WebApp;
import jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.handler.*;


/**
 * BulletinBoardアプリケーションを表すクラス
 * Created by sekiguchikai on 2016/12/13.
 */
public class BulletinBoardApp extends WebApp {
    public BulletinBoardApp() {
        super();
    }


    /**
     * WebAppクラスを継承した各クラスに必要なHandlerクラスをインスタンス化し、パスを紐付けるメソッド
     */
    public void initializeHandler() {
        Handler indexHandler = new IndexHandler();
        Handler registerMessageHandler = new RegisterMessageHandler();
        Handler searchMessageHandler = new SearchMessageHandler();
        Handler deleteMessageHandler = new DeleteMessageHandler();
        Handler showAllMessageHandler = new ShowAllMessageHandler();

        this.handlerMap.put("/program/board/", indexHandler);
        this.handlerMap.put("/program/board/register/", registerMessageHandler);
        this.handlerMap.put("/program/board/search/", searchMessageHandler);
        this.handlerMap.put("/program/board/delete/", deleteMessageHandler);
        this.handlerMap.put("/program/board/showAll/", showAllMessageHandler);
    }

}
