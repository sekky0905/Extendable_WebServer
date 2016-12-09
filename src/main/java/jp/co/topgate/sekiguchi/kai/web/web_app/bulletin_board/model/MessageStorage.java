package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Messageインスタンスを格納するためのクラス
 * Created by sekiguchikai on 2016/11/28.
 */
public class MessageStorage {
    /**
     * Messageを格納するためのリスト
     */
    private static List<Message> messageList = new ArrayList<>();

    /**
     * 一時的にmessageListの一部を退避するために格納するためのリスト
     */
    private static List<Message> tempList = new ArrayList<>();

    /**
     * messageListが選択された状態かどうかの真偽値
     */
    private static boolean messageListChose;


    /**
     * messageListか、tempListかを選択するためのメソッド
     *
     * @param messageListChose messageListを選択するかどうかの真偽値
     */
    public static void chooseMessageList(boolean messageListChose) {
        MessageStorage.messageListChose = messageListChose;
    }

    /**
     * messageListが選択されているかどうかの真偽値を確認するためのメソッド
     *
     * @return messageListが選択されているかどうかの真偽値
     */
    public static boolean checkMessageList() {
        return MessageStorage.messageListChose;
    }

    /**
     * Messageを受け取りmessageListに格納するためのメソッド
     *
     * @param message messageListに格納するモデル
     */
    public static void setMessageList(Message message) {
        MessageStorage.messageList.add(message);
    }

    /**
     * インデックスで指定されたmessageListに格納されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Message getMessageList(int index) {
        return MessageStorage.messageList.get(index);
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
     * messageListのサイズを返すメソッド
     *
     * @return messageListのサイズ
     */
    public static int countMessage() {
        return MessageStorage.messageList.size();
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
        MessageStorage.messageList.remove(index);
    }


    /**
     * 指定されたユーザーネームのモデルのみ格納するListを生成
     *
     * @param name ユーザーネーム
     */
    public static void searchMessage(String name) {
        MessageStorage.removeAllTemp();
        for (int i = 0; i <= MessageStorage.messageList.size(); i++) {
            if (i == MessageStorage.messageList.size()) {
                break;
            }
            if ((MessageStorage.messageList.get(i).getName().equals(name))) {
                MessageStorage.tempList.add(MessageStorage.messageList.get(i));
            }
        }
    }

    /**
     * messageListに保持している全てのインスタンスを削除するメソッド
     */
    static void removeAllMessage() {
        MessageStorage.messageList.clear();
    }

    /**
     * tempListに保持している全てのインスタンスを削除するメソッド
     */
    private static void removeAllTemp() {
        MessageStorage.tempList.clear();
    }

}


