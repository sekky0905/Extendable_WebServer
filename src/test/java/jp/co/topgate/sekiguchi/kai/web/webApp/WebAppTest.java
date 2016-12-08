package jp.co.topgate.sekiguchi.kai.web.webApp;

import jp.co.topgate.sekiguchi.kai.web.webServer.Handler;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Created by sekiguchikai on 2016/12/05.
 */
public class WebAppTest {

    /**
     * setHandlerNameメソッドとgetHandlerNameメソッドをテストするためのメソッド
     */
    @Test
    public void setHandlerName() {
        WebApp bulletinBoard = new WebApp();
        bulletinBoard.setHandlerName("/program/board/", "IndexHandler");

        assertThat(WebApp.getHandlerName("/program/board/"), is("IndexHandler"));

    }

    /**
     * setHandlerMapメソッドとcheckHandlerNameExistenceメソッドをテストするためのメソッド
     */
    @Test
    public void setHandlerMap() {
        WebApp bulletinBoard = new WebApp();
        bulletinBoard.setHandlerName("/program/board/", "IndexHandler");
        WebApp.setHandlerMap();
        assertThat(WebApp.checkHandlerNameExistence("/program/board/"), is(true));
    }


    /**
     * getHandlerMapメソッドをテストするためのメソッド
     */
    @Test
    public void getHandlerMap() {
        WebApp bulletinBoard = new WebApp();
        bulletinBoard.setHandlerName("/program/board/", "IndexHandler");

        WebApp.setHandlerMap();

        Handler handler = WebApp.getHandlerMap("IndexHandler");
        Class handlerClass = handler.getClass();

        assertThat(handlerClass.getName(), is("jp.co.topgate.sekiguchi.kai.web.webApp.bulletin_board.handler.IndexHandler"));


    }


}