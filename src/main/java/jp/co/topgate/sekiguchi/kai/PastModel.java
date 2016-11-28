package jp.co.topgate.sekiguchi.kai;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sekiguchikai on 2016/11/28.
 */
public class PastModel {
    List<Message> messageList = new ArrayList<>();

    /**
     * Cookieで送られてきた過去のMessageインスタンスをmessageListに格納
     *
     * @param cookiePairList Cookieを格納したMap
     *
     */
    public void setMessageList(List<Cookie> cookiePairList) {

        for (int i = 0; i < cookiePairList.size(); i++) {
            Cookie cookie = cookiePairList.get(i);
            String now = cookie.getValue("atTime");
            String name = cookie.getValue("name");
            String text = cookie.getValue("text");

            String year = now.substring(0, now.indexOf("/") - 1);
            String month = now.substring(now.indexOf("/" + 1), now.indexOf("/", 5));
            String day = now.substring(now.indexOf("/" + 1), now.indexOf("/", 8));
            String hour = now.substring(now.indexOf(" " + 1), now.indexOf(":"));
            String minute = now.substring(now.indexOf(":" + 1), now.lastIndexOf(":"));
            String second = now.substring(now.lastIndexOf(":"), now.length());

            // ここで、StringからLocalに変更すること
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime atTime = LocalDateTime.parse(year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second, dateTimeFormatter);

            Message message = new Message();
            message.setAtTime(atTime);
            message.setUserName(name);
            message.setComment(text);

            messageList.add(message);
        }

    }


    /**
     * messageListを返すメソッド
     *
     * @return 過去のmessageインスタンスが格納されているmessageList
     */
    public List<Message> getMessageList() {
        return this.messageList;
    }

}
