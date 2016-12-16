package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboardapp.model;

import java.time.LocalDateTime;

/**
 * 1つのMessageを表すクラス
 * Created by sekiguchikai on 2016/11/22.
 */
public class Message {
    /**
     * Messageを区別するためのid
     */
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
    private LocalDateTime createdAt;

    /**
     * idを設定するためのメソッド
     *
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * idを取得するためのメソッド
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

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
     * @param createdAt 投稿日時
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    /**
     * 投稿日時を取得するためのメソッド
     *
     * @return 投稿日時
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

}