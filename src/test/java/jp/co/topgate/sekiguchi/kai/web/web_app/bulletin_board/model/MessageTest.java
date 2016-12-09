package jp.co.topgate.sekiguchi.kai.web.web_app.bulletin_board.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Messageクラスをテストするためのメソッド
 * Created by sekiguchikai on 2016/11/24.
 */
public class MessageTest {
    private Message message = new Message();

    /**
     * setNameメソッドをテストするメソッド
     * ついでにgetNameメソッドもテストする
     */
    @Test
    public void setName() {
        message.setName("sekky");
        assertThat(message.getName(), is("sekky"));
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
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);

        message.setAtTime(atTime);
        assertThat(message.getAtTime(), is(atTime));
    }


}