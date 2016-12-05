package jp.co.topgate.sekiguchi.kai.web.model;


import jp.co.topgate.sekiguchi.kai.web.model.Message;
import jp.co.topgate.sekiguchi.kai.web.model.ModelStorage;
import org.junit.After;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by sekiguchikai on 2016/11/29.
 */
public class ModelStorageTest {

    @After
    public void after() {
        ModelStorage.removeAllModel();
    }


    /**
     * setModelListメソッドとgetModelListメソッドをテストするためのテスト
     */
    @Test
    public void getModelList() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 0);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);

        Message message = new Message();
        message.setAtTime(atTime);
        message.setName("sekky");
        message.setComment("テスト");

        ModelStorage.setModelList(message);

        Message messageTest = (Message) ModelStorage.getModelList(0);
        String atTimeTest = messageTest.getAtTime();
        String nameTest = messageTest.getName();
        String commentTest = messageTest.getComment();

        assertThat(atTimeTest, is("2016/11/29 15:00:00"));
        assertThat(nameTest, is("sekky"));
        assertThat(commentTest, is("テスト"));

    }

    /**
     * countModelメソッドをテストするためのテスト
     */
    @Test
    public void countModel() {
        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);
        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);

        Message message = new Message();
        Message message1 = new Message();
        message1.setAtTime(atTime);
        message1.setName("sekky");
        message1.setComment("テスト");
        ModelStorage.setModelList(message1);

        Message message2 = new Message();
        message2.setAtTime(atTime);
        message2.setName("sekky2");
        message2.setComment("テスト2");
        ModelStorage.setModelList(message2);


        assertThat(ModelStorage.countModel(), is(2));


    }

    /**
     * removeModelメソッドをテストするためのテスト
     */
//    @Test
//    public void removeModel() {
//        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);
//        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);
//
//        Message message1 = new Message();
//        message1.setAtTime(atTime);
//        message1.setName("sekky");
//        message1.setComment("テスト");
//        ModelStorage.setModelList(message1);
//
//        Message message2 = new Message();
//        message2.setAtTime(atTime);
//        message2.setName("sekky2");
//        message2.setComment("テスト2");
//        ModelStorage.setModelList(message2);
//
//        ModelStorage.removeModel(0);
//
//
//        assertThat(ModelStorage.countModel(), is(1));
//
//
//    }


//    /**
//     * searchModelメソッドをテストするためのテスト
//     */
//    @Test
//    public void searchModel() {
//
//        LocalDateTime localTimeTest = LocalDateTime.of(2016, 11, 29, 15, 0, 00);
//        String atTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(localTimeTest);
//
//        ModelStorage.removeAllModel();
//
//        Message message1 = new Message();
//        message1.setAtTime(atTime);
//        message1.setName("sekky");
//        message1.setComment("テスト");
//        ModelStorage.setModelList(message1);
//
//        Message message2 = new Message();
//        message2.setAtTime(atTime);
//        message2.setName("sekky2");
//        message2.setComment("テスト2");
//        ModelStorage.setModelList(message2);
//
//        Message message3 = new Message();
//        message3.setAtTime(atTime);
//        message3.setName("sekky");
//        message3.setComment("テスト3");
//        ModelStorage.setModelList(message3);
//
//        ModelStorage.searchModel("sekky");
//        int instanceNumber = ModelStorage.countModel();
//        assertThat(instanceNumber, is(2));
//
//
//        for (int i = 0; i < instanceNumber; i++) {
//            Message message = (Message) ModelStorage.getModelList(i);
//            assertThat(message.getName(), is("sekky"));
//
//        }
//
//        ModelStorage.removeAllModel();
//
//    }

}