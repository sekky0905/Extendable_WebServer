package jp.co.topgate.sekiguchi.kai;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sekiguchikai on 2016/11/28.
 */
public class Cookie {
    /**
     * Cookieの名前と値を格納したMap
     */
    private String cookieArray[];

    public Cookie(String cookieArray[]) {
        this.cookieArray = cookieArray;
    }


    /**
     * Cookieのnameを指定するとvalueを返すメソッド
     *
     * @return Cookieでのnameに紐付いたvalue
     */
    public String getValue(String name) {
        Map<String, String> cookieMap = new HashMap<>();
        for (int i = 0; i < cookieArray.length; i++) {
            String[] piece = cookieArray[i].split("=");
            cookieMap.put(piece[0], piece[1]);
        }

        return cookieMap.get(name);
    }

}
