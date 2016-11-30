package jp.co.topgate.sekiguchi.kai;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by sekiguchikai on 2016/11/22.
 */
public class Message extends Model{

    /**
     * 本文
     */
    private String comment;
    /**
     * 投稿日時
     */
    private String atTime;


    /**
     * コメントを設定するためのメソッド
     *
     * @param comment 　コメント
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * コメントを取得するためのメソッド
     *
     * @return 本文
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * 投稿日時を取得するためのメソッド
     *
     * @return 投稿日時
     */
    public String getAtTime() {
        return this.atTime;
    }

}