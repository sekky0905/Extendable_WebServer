package jp.co.topgate.sekiguchi.kai.web.util;

import java.util.UUID;

/**
 * Sessionに関する責任を持つクラス
 * Created by sekiguchikai on 2016/12/01.
 */
public class Session {

    /**
     * ワンタームトークン
     */
    private static String token;

    /**
     * tokenを発生させるためのメソッド
     *
     * @return ワンタイムトークン
     */
    public static String generateToken() {
        Session.token = UUID.randomUUID().toString();
        System.out.println(token);

        return Session.token;

    }

    /**
     * tokenを返すためのメソッド
     *
     * @return tokenの文字列
     */
    public static String getToken() {
        return Session.token;
    }

    /**
     * 引数で渡されたtokenが発行したtokenと同じものであるか確認するためのメソッド
     *
     * @param acceptToken formでHTMLから受け取ったtoken
     * @return 受け取ったtokenが、以前発行したtokenと同じものであるかの真偽値
     */
    public static boolean confirmToken(String acceptToken) {
        boolean same;
        if (acceptToken.equals(Session.token)) {
            same = true;
        } else {
            same = false;
        }
        return same;
    }
}
