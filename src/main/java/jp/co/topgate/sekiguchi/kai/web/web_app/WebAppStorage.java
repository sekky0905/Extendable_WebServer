package jp.co.topgate.sekiguchi.kai.web.web_app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 複数のアプリケーションでwebサーバを使用可能にするためにwebApp(アプリケーション)を保持しておくためのクラス
 * Created by sekiguchikai on 2016/12/03.
 */
public class WebAppStorage {

    /**
     * WebAppを格納するためのMap
     * ここに格納するWebAppは、アプリケーションのルートディレクトリのみ
     */
    private static Map<String, WebApp> webAppMap = new HashMap<>();


    /**
     * アプリケーションをインスタンス化して、ルートURIに紐付けるメソッド
     */
    public static void initializeApp() {
        WebApp staticWebServerApp = new StaticWebServerApp();
        WebApp bulletinBoardApp = new BulletinBoardApp();

        webAppMap.put("/", staticWebServerApp);
        webAppMap.put("/program/board/", bulletinBoardApp);

    }


    /**
     * pathで指定したWebAppのインスタンスを取得するメソッド
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


    /**
     * 名前で指定されたwebAppのインスタンスをwebAppMapから取得するメソッド
     *
     * @param appName webAppMapに格納するwebApp(アプリケーションの名前)
     * @return webAppのインスタンス
     */
    public static WebApp getWebAppMap(String appName) {
        return webAppMap.get(appName);
    }


    /**
     * 名前で指定したアプリケーションがwebAppMapに格納されているかどうか確認するためのメソッド
     *
     * @param appName 確認するアプリケーションの名前
     * @return 名前で指定したアプリケーションがwebAppMapに格納されているかどうかどうかの真偽値
     */
    public static boolean AppIsExist(String appName) {
        return webAppMap.containsKey(appName);
    }
}
