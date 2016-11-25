package jp.co.topgate.sekiguchi.kai;

/**
 * Created by sekiguchikai on 2016/11/25.
 */
public class Choice {
    public static Handler ChoiceHandler(String requestURI) {

        Handler handler;

        if (requestURI.equals("/program/board/")) {
            handler = new MessageHandler();
        } else {
            handler = new StaticFileHandler();
        }


        return handler;
    }
}
