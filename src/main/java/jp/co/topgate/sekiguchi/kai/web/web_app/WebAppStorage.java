package jp.co.topgate.sekiguchi.kai.web.web_app;


import java.util.HashMap;
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
     * webAppの名前とインスタンスをwebAppMapに追加するメソッド
     *
     * @param appName webAppMapに格納するwebApp(アプリケーションの名前)
     * @param webApp  webAppMapに格納するwebAppのインスタンス
     */
    public static void setWebAppMap(String appName, WebApp webApp) {
        webAppMap.put(appName, webApp);
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
