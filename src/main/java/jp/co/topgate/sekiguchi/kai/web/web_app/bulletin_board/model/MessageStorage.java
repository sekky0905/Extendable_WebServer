package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Messageインスタンスを格納するためのクラス
 * Created by sekiguchikai on 2016/11/28.
 */
public class MessageStorage {
    /**
     * モデルを格納するためのリスト
     */
    private static List<Message> modelList = new ArrayList<>();
    private static List<Message> tempList = new ArrayList<>();
    private static boolean modelListChose;


    public static void chooseMessageList(boolean modelListChose) {
        MessageStorage.modelListChose = modelListChose;
    }

    /**
     * ModelListか、tempListかを選択するメソッド
     *
     * @return ModelListを選択するかどうかの真偽値
     */
    public static boolean checkMessageList() {
        return MessageStorage.modelListChose;
    }

    /**
     * モデルを受け取りmodelListに格納するためのメソッド
     *
     * @param model modelListに格納するモデル
     */
    public static void setMessageList(Message model) {
        MessageStorage.modelList.add(model);
    }

    /**
     * インデックスで指定されたmodelListに格納されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Message getMessageList(int index) {
        return MessageStorage.modelList.get(index);
    }


    /**
     * インデックスで指定されたtempListに格納されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Message getTempList(int index) {
        return MessageStorage.tempList.get(index);
    }


    /**
     * modelListのサイズを返すメソッド
     *
     * @return modelListのサイズ
     */
    public static int countMessage() {
        return MessageStorage.modelList.size();
    }


    /**
     * tempListのサイズを返すメソッド
     *
     * @return tempListのサイズ
     */
    public static int countTemp() {
        return MessageStorage.tempList.size();
    }


    /**
     * インデックスで指定されたモデルを削除するメソッド
     *
     * @param index リストのインデックス
     */
    public static void removeMessage(int index) {
        MessageStorage.modelList.remove(index);
    }


    /**
     * 指定されたユーザーネームのモデルのみ格納するListを生成
     *
     * @param name ユーザーネーム
     */
    public static void searchMessage(String name) {
        MessageStorage.removeAllTemp();
        for (int i = 0; i <= MessageStorage.modelList.size(); i++) {
            if (i == MessageStorage.modelList.size()) {
                break;
            }
            if ((MessageStorage.modelList.get(i).getName().equals(name))) {
                MessageStorage.tempList.add(MessageStorage.modelList.get(i));
            }
        }
    }

    /**
     * modelListに保持している全てのインスタンスを削除するメソッド
     */
    public static void removeAllMessage() {
        MessageStorage.modelList.clear();
    }

    /**
     * tempListに保持している全てのインスタンスを削除するメソッド
     */
    public static void removeAllTemp() {
        MessageStorage.tempList.clear();
    }

}


