package jp.co.topgate.sekiguchi.kai.web.util;

import jp.co.topgate.sekiguchi.kai.web.Model;

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
    private static List<Model> tempoList = new ArrayList<>();
    private static boolean modelListChose;


    public static void choiceModelList(boolean modelListChose) {
        ModelStorage.modelListChose = modelListChose;
    }

    /**
     * ModelListか、tempoListかを選択するメソッド
     *
     * @return ModelListを選択するかどうかの真偽値
     */
    public static boolean checkModelList() {
        return ModelStorage.modelListChose;
    }

    /**
     * モデルを受け取りmodelListに格納するためのメソッド
     *
     * @param model modelListに格納するモデル
     */
    public static void setModelList(Model model) {
        ModelStorage.modelList.add(model);
    }

    /**
     * インデックスで指定されたmodelListに格納されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Model getModelList(int index) {
        return ModelStorage.modelList.get(index);
    }


    /**
     * インデックスで指定されたtempoListに格納されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Model getTempoList(int index) {
        return ModelStorage.tempoList.get(index);
    }


    /**
     * modelListのサイズを返すメソッド
     *
     * @return modelListのサイズ
     */
    public static int countModel() {
        return ModelStorage.modelList.size();
    }


    /**
     * tempoListのサイズを返すメソッド
     *
     * @return tempoListのサイズ
     */
    public static int countTempo() {
        return ModelStorage.tempoList.size();
    }


    /**
     * インデックスで指定されたモデルを削除するメソッド
     *
     * @param index リストのインデックス
     */
    public static void removeModel(int index) {
        ModelStorage.modelList.remove(index);
    }


    /**
     * 指定されたユーザーネームのモデルのみ格納するListを生成
     *
     * @param name ユーザーネーム
     */
    public static void searchModel(String name) {
        ModelStorage.removeAllTempo();
        for (int i = 0; i <= ModelStorage.modelList.size(); i++) {
            if (i == ModelStorage.modelList.size()) {
                break;
            }
            if ((ModelStorage.modelList.get(i).getName().equals(name))) {
                ModelStorage.tempoList.add(ModelStorage.modelList.get(i));
            }
        }
    }

    /**
     * modelListに保持している全てのインスタンスを削除するメソッド
     */
    public static void removeAllModel() {
        ModelStorage.modelList.clear();
    }

    /**
     * tempoListに保持している全てのインスタンスを削除するメソッド
     */
    public static void removeAllTempo() {
        ModelStorage.tempoList.clear();
    }

}
