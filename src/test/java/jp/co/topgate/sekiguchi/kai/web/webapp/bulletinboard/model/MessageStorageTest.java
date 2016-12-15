package jp.co.topgate.sekiguchi.kai.web.webapp.bulletinboard.model;


import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Messageを保存しておくためのクラス
 * Created by sekiguchikai on 2016/11/29.
 */
public class MessageStorageTest {

    /**
     * 各メソッド実行後に自動で行う処理
     */
    @After
    public void after() {
        MessageStorage.removeAllMessage();
    }


    /**
     * addMessageメソッドとgetMessageメソッドをテストするためのテスト
     */
    @Test
    public void getMessage() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 0);

        Message message = new Message();
        message.setCreatedAt(localTimeTest);
        message.setName("sekky");
        message.setComment("テスト");

        MessageStorage.addMessage(message);

        Message messageTest = MessageStorage.getMessage(0);
        LocalDateTime createdAtTest = messageTest.getCreatedAt();
        String nameTest = messageTest.getName();
        String commentTest = messageTest.getComment();

        assertThat(createdAtTest, is(localTimeTest));
        assertThat(nameTest, is("sekky"));
        assertThat(commentTest, is("テスト"));

    }


    /**
     * removeMessageメソッドをテストするためのテスト
     */
    @Test
    public void removeMessage() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);

        Message message1 = new Message();
        message1.setCreatedAt(localTimeTest);
        message1.setName("sekky");
        message1.setComment("テスト");
        MessageStorage.addMessage(message1);

        Message message2 = new Message();
        message2.setCreatedAt(localTimeTest);
        message2.setName("sekky2");
        message2.setComment("テスト2");
        MessageStorage.addMessage(message2);

        MessageStorage.removeMessage(0);


        assertThat(MessageStorage.getAllMessage().count(), is(1L));


    }


    /**
     * searchMessageメソッドをテストするためのテスト
     */
    @Test
    public void searchMessage() {

        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);

        MessageStorage.removeAllMessage();

        Message message1 = new Message();
        message1.setCreatedAt(localTimeTest);
        message1.setName("sekky");
        message1.setComment("テスト");
        MessageStorage.addMessage(message1);

        Message message2 = new Message();
        message2.setCreatedAt(localTimeTest);
        message2.setName("sekky2");
        message2.setComment("テスト2");
        MessageStorage.addMessage(message2);

        Message message3 = new Message();
        message3.setCreatedAt(localTimeTest);
        message3.setName("sekky");
        message3.setComment("テスト3");
        MessageStorage.addMessage(message3);

        assertThat(MessageStorage.searchMessage("sekky").count(), is(2L));

    }

    /**
     * removeAllMessageメソッドをテストするためのメソッド
     */
    @Test
    public void removeAllMessage() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);

        MessageStorage.removeAllMessage();

        Message message1 = new Message();
        message1.setCreatedAt(localTimeTest);
        message1.setName("sekky");
        message1.setComment("テスト");
        MessageStorage.addMessage(message1);


        Message message2 = new Message();
        message2.setCreatedAt(localTimeTest);
        message2.setName("sekky");
        message2.setComment("テスト3");
        MessageStorage.addMessage(message2);

        MessageStorage.searchMessage("sekky");

        assertThat(MessageStorage.getAllMessage().count(), is(2L));

        MessageStorage.removeAllMessage();
        assertThat(MessageStorage.getAllMessage().count(), is(0L));


    }

}