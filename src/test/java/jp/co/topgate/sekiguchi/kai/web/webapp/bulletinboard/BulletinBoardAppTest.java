package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard;


import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.handler.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * BulletinBoardAppクラスをテストするためのクラス
 * Created by sekiguchikai on 2016/12/13.
 */
public class BulletinBoardAppTest {


    /**
     * initializeHandlerメソッドとgetHandlerメソッドをテストするためのメソッド
     */
    @Test
    public void getHandler() {
        WebApp bulletinBoardApp = new BulletinBoardApp();

        bulletinBoardApp.initializeHandler();

        assertThat(bulletinBoardApp.getHandler("/program/board/") instanceof IndexHandler, is(true));
        assertThat(bulletinBoardApp.getHandler("/program/board/register/") instanceof RegisterMessageHandler, is(true));
        assertThat(bulletinBoardApp.getHandler("/program/board/search/") instanceof SearchMessageHandler, is(true));
        assertThat(bulletinBoardApp.getHandler("/program/board/delete/") instanceof DeleteMessageHandler, is(true));

    }
}