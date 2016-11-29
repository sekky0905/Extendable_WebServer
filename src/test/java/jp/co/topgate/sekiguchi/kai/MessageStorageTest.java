package jp.co.topgate.sekiguchi.kai;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by sekiguchikai on 2016/11/29.
 */
public class MessageStorageTest {

    /**
     * setModelListメソッドとgetModelListメソッドをテストするためのテスト
     */
    @Test
    public void getModelList() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 0);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);

        Message message = new Message();
        message.setAtTime(atTime);
        message.setUserName("sekky");
        message.setComment("テスト");

        MessageStorage.setModelList(message);

        Message messageTest = (Message) MessageStorage.getModelList(0);
        String atTimeTest = messageTest.getAtTime();
        String userNameTest = messageTest.getUserName();
        String commentTest = messageTest.getComment();

        assertThat(atTimeTest, is("2016/11/29 15: 0:0"));
        assertThat(userNameTest, is("sekky"));
        assertThat(commentTest, is("テスト"));

    }

    /**
     * countModelメソッドをテストするためのテスト
     */
    @Test
    public void countModel() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 0);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);

        Message message = new Message();
        Message message1 = new Message();
        message1.setAtTime(atTime);
        message1.setUserName("sekky");
        message1.setComment("テスト");
        MessageStorage.setModelList(message1);

        Message message2 = new Message();
        message2.setAtTime(atTime);
        message2.setUserName("sekky2");
        message2.setComment("テスト2");
        MessageStorage.setModelList(message2);


        assertThat(MessageStorage.countModel(), is(2));


    }

    @Test
    public void removeModel() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 0);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);

        Message message1 = new Message();
        message1.setAtTime(atTime);
        message1.setUserName("sekky");
        message1.setComment("テスト");
        MessageStorage.setModelList(message1);

        Message message2 = new Message();
        message2.setAtTime(atTime);
        message2.setUserName("sekky2");
        message2.setComment("テスト2");
        MessageStorage.setModelList(message2);

        MessageStorage.removeModel(0);


        assertThat(MessageStorage.countModel(), is(1));


    }

    @Test
    public void searchModel() {

    }

}