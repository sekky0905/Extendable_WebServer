package jp.co.topgate.sekiguchi.kai.web;

import jp.co.topgate.sekiguchi.kai.web.util.Session;
import org.junit.Test;

/**
 * Created by sekiguchikai on 2016/12/01.
 */
public class SessionTest {

    @Test
    public void generateToken() {
        Session.generateToken();
    }

}