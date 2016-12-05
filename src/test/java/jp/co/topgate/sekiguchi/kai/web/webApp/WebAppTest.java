package jp.co.topgate.sekiguchi.kai.web.webApp;

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

        assertThat(WebApp.getHandlerMap("/program/board/"), is("IndexHandler"));

    }

    /**
     * setHandlerMapメソッドとcheckHandlerNameExistenceメソッドをテストするためのメソッド
     */
    @Test
    public void setHandlerMap() {
        WebApp bulletinBoard = new WebApp();
        bulletinBoard.setHandlerName("/program/board/", "IndexHandler");

        WebApp.setHandlerMap();
        assertThat(WebApp.checkHandlerNameExistence("IndexHandler"), is(true));
    }


//    public void getHandlerMap(String handlerName) {
//
//    }


}