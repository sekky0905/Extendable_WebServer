package jp.co.topgate.sekiguchi.kai.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * レスポンスヘッダの各項目を作成するためのユーティリティクラス
 * Created by sekiguchikai on 2016/12/05.
 */
public class ResponseHeaderMaker {

    /**
     * レスポンスヘッダのContent-Typeを設定するメソッド
     *
     * @param fileExtension ファイルの拡張子
     */
    public static String makeContentType(String fileExtension) {

        Map<String, String> contentTypeMap = new HashMap<>();
        contentTypeMap.put("html", "Content-Type: text/html");
        contentTypeMap.put("css", "Content-Type: text/css");
        contentTypeMap.put("js", "Content-Type: text/js");
        contentTypeMap.put("jpeg", "Content-Type: image/jpeg");
        contentTypeMap.put("png", "Content-Type: image/png");
        contentTypeMap.put("gif", "Content-Type: image/gif");

        String contentType = contentTypeMap.get(fileExtension);

        if (contentType == null) {
            contentType = "Content-Type: text/html";
        }

        System.out.println("レスポンスヘッダは" + contentType);
        return contentType;

    }
}
