package jp.co.topgate.sekiguchi.kai;

import java.util.Date;

/**
 * Created by sekiguchikai on 2016/11/22.
 */
public class Message {
    /**
     * ユーザーネーム
     */
    private String userName;
    /**
     * 本文
     */
    private String text;
    /**
     * 投稿日時
     */
    private Date atTime;


    /**
     * ユーザーネームのセッター
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;

    }

    /**
     * 本文のセッター
     * @param text
     */
    public void setText(String text) {
        this.text = text;

    }

    /**
     * 投稿日時のセッター
     * @param atTime
     */
    public void setAtTime(Date atTime) {
        this.atTime = atTime;

    }

    /**
     * ユーザーネームのゲッター
     * @return ユーザーネーム
     */
    public String userName() {
        return this.userName;

   }

    /**
     * 本文のゲッター
     * @return 本文
     */
    public String getText() {
        return this.text;

    }

    /**
     * 投稿日時のゲッター
     * @return 投稿日時
     */
    public Date getAtTime() {
        return this.atTime;

    }



}