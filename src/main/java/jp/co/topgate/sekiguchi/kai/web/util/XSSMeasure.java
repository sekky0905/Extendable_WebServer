package jp.co.topgate.sekiguchi.kai.web.util;

/**
 * XSS対策を行うためのユーティリティクラス
 * Created by sekiguchikai on 2016/12/06.
 */
public class XSSMeasure {


    /**
     * 特殊文字列をサニタイジングするためのメソッド
     *
     * @param specialChar サニタイジング対象の特殊文字列
     * @return 特殊文字列をサニタイジングした後の一般文字列
     */
    public static String sanitize(String specialChar) {

        specialChar = specialChar.replaceAll("&", "&amp;");
        specialChar = specialChar.replaceAll("<", "&lt;");
        specialChar = specialChar.replaceAll(">", "&gt;");
        specialChar = specialChar.replaceAll("\"", "&quot;");
        specialChar = specialChar.replaceAll("'", "&#x27;");
        specialChar = specialChar.replaceAll("/", "&#x2F;");

        return specialChar;

    }

}
