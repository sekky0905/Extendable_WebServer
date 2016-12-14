package jp.co.topgate.sekiguchi.kai.web.webapp.staticwebserver;

import jp.co.topgate.sekiguchi.kai.web.webapp.WebApp;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * StaticWebServerAppクラスをテストするためのクラス
 * Created by sekiguchikai on 2016/12/13.
 */
public class StaticWebServerAppTest {

    /**
     * initializeHandlerメソッドとgetHandlerメソッドをテストするためのメソッド
     */
    @Test
    public void getHandler() {
        WebApp staticWebServerApp = new StaticWebServerApp();

        staticWebServerApp.initializeHandler();

        assertThat(staticWebServerApp.getHandler("/") instanceof StaticFileHandler, is(true));
    }

}