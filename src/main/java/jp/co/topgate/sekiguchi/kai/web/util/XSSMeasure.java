package jp.co.topgate.sekiguchi.kai.web.util;

/**
 * XSS対策を行うためのユーティリティクラス
 * Created by sekiguchikai on 2016/12/06.
 */
public class XSSMeasure {


    /**
     * 特殊文字列をサニタイジングするためのメソッド
     *
     * @param target サニタイジング対象の特殊文字列
     * @return 特殊文字列をサニタイジングした後の一般文字列
     */
    public static String sanitize(String target) {

        target = target.replaceAll("&", "&amp;");
        target = target.replaceAll("<", "&lt;");
        target = target.replaceAll(">", "&gt;");
        target = target.replaceAll("\"", "&quot;");
        target = target.replaceAll("'", "&#x27;");
        target = target.replaceAll("/", "&#x2F;");

        return target;

    }

}
