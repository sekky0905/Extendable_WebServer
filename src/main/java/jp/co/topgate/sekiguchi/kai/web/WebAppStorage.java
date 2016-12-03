package jp.co.topgate.sekiguchi.kai.web;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sekiguchikai on 2016/12/03.
 */
public class WebAppStorage {

    /**
     *
     */
    private static Map<String, WebApp> webAppMap = new HashMap<>();
    // ここで、WebAppと、パスをputする

    public static void setWebAppMap(String appName, WebApp webApp){
        webAppMap.put(appName, webApp);
    }
}
