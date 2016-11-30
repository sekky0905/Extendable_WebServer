package jp.co.topgate.sekiguchi.kai;

import java.util.ArrayList;
import java.util.List;

/**
 * Messageインスタンスを格納するためのクラス
 * Created by sekiguchikai on 2016/11/28.
 */
public class ModelStorage {

    /**
     * モデルを格納するためのリスト
     */
    private static List<Model> modelList = new ArrayList<>();


    /**
     * モデルを受け取りリストに格納するためのメソッド
     *
     * @param model リストに格納するモデル
     */
    public static void setModelList(Model model) {
        modelList.add(model);
    }

    /**
     * インデックスで指定されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Model getModelList(int index) {
        return modelList.get(index);
    }

    /**
     * modelListのサイズを返すメソッド
     *
     * @return modelListのサイズ
     */
    public static int countModel() {
        return modelList.size();
    }


    /**
     * インデックスで指定されたモデルを削除するメソッド
     *
     * @param index リストのインデックス
     */
    public static void removeModel(int index) {
        modelList.remove(index);
    }

    /**
     * 指定されたユーザーネームのモデルのみ格納するListを生成
     *
     * @param name ユーザーネーム
     */
    public static void searchModel(String name) {
        // 遠回りだが、必要処理
        List<Model> tempoList = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++) {

            if ((modelList.get(i).getName().equals(name))) {
                tempoList.add(modelList.get(i));
            }
        }

        modelList = tempoList;
    }

    /**
     * 保持している全てのインスタンスを削除するメソッド
     */
    public static void removeAllModel() {
        for (int i = 0; i < modelList.size(); i++) {
            modelList.remove(i);
        }
    }


}
