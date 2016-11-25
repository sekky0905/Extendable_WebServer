package jp.co.topgate.sekiguchi.kai;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by sekiguchikai on 2016/11/24.
 */
public class MessageTest {
    Message message = new Message();

    /**
     * setUserNameメソッドをテストするメソッド
     * ついでにgetUserNameメソッドもテストする
     */
    @Test
    public void setUserName() {
        message.setUserName("sekky");
        assertThat(message.getUserName(), is("sekky"));
    }

    /**
     * setTextメソッドをテストするメソッド
     * ついでにgetTextメソッドも実装する
     */
    @Test
    public void setComment() {
        message.setComment("こんにちわ");
        assertThat(message.getComment(), is("こんにちわ"));
    }

    /**
     * setAttimeメソッドをテストするメソッド
     * ついでにgetAtTimeメソッドを実装する
     */
    @Test
    public void setAtTime() {
        ZonedDateTime zT = ZonedDateTime.of(2016, 11, 24, 15, 15, 15, 15, ZoneId.of("Asia/Tokyo"));
        message.setAtTime(zT);
        assertThat(message.getAtTime(), is(zT));
    }


}