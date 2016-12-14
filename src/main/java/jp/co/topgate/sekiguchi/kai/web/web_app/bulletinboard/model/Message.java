package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model;

/**
 * 1つのMessageを表すクラス
 * Created by sekiguchikai on 2016/11/22.
 */
public class Message {
    private int id;

    /**
     * ユーザーネーム
     */
    private String name;

    /**
     * コメント
     */
    private String comment;
    /**
     * 投稿日時
     */
    private String atTime;


    /**
     * ユーザーネームを設定するためのメソッド
     *
     * @param name ユーザーネーム
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ユーザーネームを取得するためのメソッド
     *
     * @return ユーザーネーム
     */
    public String getName() {
        return this.name;
    }


    /**
     * コメントを設定するためのメソッド
     *
     * @param comment コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * コメントを取得するためのメソッド
     *
     * @return コメント
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * 投稿日時を設定するためのメソッド
     *
     * @param atTime 投稿日時
     */
    public void setAtTime(String atTime) {
        this.atTime = atTime;
    }


    /**
     * 投稿日時を取得するためのメソッド
     *
     * @return 投稿日時
     */
    public String getAtTime() {
        return this.atTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}