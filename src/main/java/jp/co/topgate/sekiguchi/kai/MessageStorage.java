package jp.co.topgate.sekiguchi.kai;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sekiguchikai on 2016/11/28.
 */
public class MessageStorage {

    /**
     * モデルを格納するためのリスト
     */
    private static List<Message> modelList = new ArrayList<>();


    /**
     * モデルを受け取りリストに格納するためのメソッド
     *
     * @param message リストに格納するモデル
     */
    public static void setModelList(Message message) {
        modelList.add(message);
    }

    /**
     * インデックスで指定されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Object getModelList(int index) {
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
     * @param userName ユーザーネーム
     */
    public static void searchModel(String userName) {
        // 遠回りだが、必要処理
        List<Message> tempoList = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++) {

            if ((modelList.get(i).getUserName().equals(userName))) {
                tempoList.add(modelList.get(i));
            }
        }

        modelList = tempoList;
    }


}
