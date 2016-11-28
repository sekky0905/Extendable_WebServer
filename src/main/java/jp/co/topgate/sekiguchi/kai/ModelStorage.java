package jp.co.topgate.sekiguchi.kai;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sekiguchikai on 2016/11/28.
 */
public class ModelStorage {

    /**
     * モデルを格納するためのリスト
     */
    private static List<Object> modelList = new ArrayList<>();

    /**
     * モデルを受け取りリストに格納するためのメソッド
     *
     * @param obj リストに格納するモデル
     */
    public static void setModelList(Object obj) {
        modelList.add(obj);
    }

    /**
     * インデックスでリストの中のモデルを指定し、指定されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Object getModelList(int index) {
        return modelList.get(index);
    }

    public static int ConutModel() {
        return modelList.size();
    }

    public  static  void removeModel(int index) {
        modelList.remove(index);
    }

}
