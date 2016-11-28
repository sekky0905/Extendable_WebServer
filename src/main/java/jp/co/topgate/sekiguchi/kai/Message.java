package jp.co.topgate.sekiguchi.kai;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
    private String comment;
    /**
     * 投稿日時
     */
    private LocalDateTime atTime;


    /**
     * ユーザーネームのセッター
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;

    }

    /**
     * 本文のセッター
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;

    }

    /**
     * 投稿日時のセッター
     *
     * @param atTime
     */
    public void setAtTime(LocalDateTime atTime) {
        this.atTime = atTime;
    }

    /**
     * ユーザーネームのゲッター
     *
     * @return ユーザーネーム
     */
    public String getUserName() {
        return this.userName;

    }

    /**
     * 本文のゲッター
     *
     * @return 本文
     */
    public String getComment() {
        return this.comment;

    }

    /**
     * 投稿日時のゲッター
     *
     * @return 投稿日時
     */
    public LocalDateTime getAtTime() {
        return this.atTime;

    }


}