package jp.co.topgate.sekiguchi.kai.web.webapp;


import jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.BulletinBoardApp;
import jp.co.topgate.sekiguchi.kai.web.webapp.staticwebserver.StaticWebServerApp;

import java.util.HashMap;
import java.util.Map;

/**
 * 複数のアプリケーションでwebサーバを使用可能にするためにwebApp(アプリケーション)を保持しておくためのクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class WebAppStorage {

    /**
     * WebAppを格納するためのMap
     * key: webAppのルートパス value: WebAppのインスタンスという形で保存される
     */
    private static Map<String, WebApp> webAppMap = new HashMap<>();


    /**
     * アプリケーションをインスタンス化して、ルートパスに紐付けるためのメソッド
     */
    public static void initializeApp() {
        WebApp staticWebServerApp = new StaticWebServerApp();
        WebApp bulletinBoardApp = new BulletinBoardApp();

        webAppMap.put("/", staticWebServerApp);
        webAppMap.put("/program/board/", bulletinBoardApp);

    }


    /**
     * pathで指定したWebAppのインスタンスを取得するためのメソッド
     *
     * @param path 取得したい WebAppのパス
     * @return WebAppのインスタンス
     */
    public static WebApp getWebApp(String path) {

        int secondSlash = path.indexOf(("/"), 1);
        int thirdSlash = path.indexOf(("/"), secondSlash + 1);
        String appPath = path.substring(0, thirdSlash + 1);

        // webサーバの時
        if (!webAppMap.containsKey(appPath) || path.equals("/")) {
            return WebAppStorage.webAppMap.get("/");
        } else {
            return WebAppStorage.webAppMap.get(appPath);
        }
    }

}
