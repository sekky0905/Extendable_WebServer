package jp.co.topgate.sekiguchi.kai;

/**
 * Created by sekiguchikai on 2016/11/25.
 */
public class Choice {
    public static Handler ChoicdHandler(String requestURI) {

        Handler handler;

        if (requestURI.equals("message")) {
            handler = new MessageHandler();
        } else {
            handler = new StaticFileHandler();
        }


        return handler;
    }
}
