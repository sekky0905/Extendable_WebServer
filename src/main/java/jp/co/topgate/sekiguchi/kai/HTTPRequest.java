package jp.co.topgate.sekiguchi.kai;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * クライアントからのTTPリクエストに関する責務を持つクラス
 *
 * @author sekiguchikai
 */
public class HTTPRequest {

    /**
     * クライアントからのsocket通信の中身を格納するための変数
     */
    private InputStream inputStream;

    /**
     * クエリパラメーター
     */
    private Map<String, String> requestParameter = new HashMap<>();

    private List<String> requestString = new ArrayList<>();


    /**
     * コンストラクタ、set~で各フィールドを初期設定する
     *
     * @param inputStream
     */
    public HTTPRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        this.setRequestString();
    }


    /**
     * リクエストパラメータを取得するメソッド
     *
     * @return リクエストパラメータの名前と値をセットで格納したMapを返す
     */
    public void setRequestParameter(String targetString) {
        String[] parameter;
        if (targetString.contains("&")) {
            parameter = targetString.split("&");
            for (String param : parameter) {
                String[] piece = param.split("=");
                this.requestParameter.put(piece[0], piece[1]);
            }
        } else {
            String[] piece = targetString.split("=");
            this.requestParameter.put(piece[0], piece[1]);
        }
    }

    /**
     * リクエスト本文を返すメソッド
     *
     * @return リクエスト本文を返す
     */
    public void setRequestString() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        String tempoRequestString;

        try {
            while (!(tempoRequestString = bufferedReader.readLine()).equals("")) {
                System.out.println(tempoRequestString);
                this.requestString.add(tempoRequestString + "\n");

            }
        } catch (IOException e) {
            e.getCause();
            System.out.println("ファイル名の解析に失敗しました");
            e.printStackTrace();
        }
    }

    /**
     * リクエスト本文を返すメソッド
     *
     * @return リクエスト本文を返す
     */
    public List<String> getRequestString() {
        return this.requestString;
    }

    /**
     * リクエストラインを返すメソッド
     *
     * @return リクエストラインを返す
     */
    public String getRequestLine(List<String> requestString) {
        String requestLine = requestString.get(0);
        System.out.println("リクエストラインは、" + requestLine);
        return requestLine;
    }

    /**
     * リクエストメソッドを返すメソッド
     *
     * @return リクエストメソッドを返す
     */
    public String getRequestMethod(String requestLine) {

        String requestMethod = requestLine.substring(0, requestLine.indexOf(" "));
        System.out.println("リクエストメソッドは" + requestMethod);
        return requestMethod;
    }

    /**
     * リクエストURIを返すメソッド
     *
     * @return リクエストURIを返す
     */
    public String getRequestURI(String requestLine) {
        String requestURI;
        int firstEmpty = requestLine.indexOf(" ");
        String secondSentence = requestLine.substring(firstEmpty + 1,
                requestLine.indexOf(" ", firstEmpty + 1));

        if (secondSentence.contains("?")) {
            requestURI = secondSentence.substring(0, secondSentence.indexOf("?"));
        } else {
            requestURI = secondSentence;
        }
        System.out.print("リクエストURIは" + requestURI);

        return requestURI;
    }


    /**
     * 「?」以降の文字列を返す
     *
     * @param requestLine リクエストライン
     * @return ?」以降の文字列
     */
    public String getRequstQuery(String requestLine) {
        int firstEmpty = requestLine.indexOf(" ");
        String secondSentence = requestLine.substring(firstEmpty + 1,
                requestLine.indexOf(" ", firstEmpty + 1));
        return secondSentence.substring(secondSentence.indexOf("?"), secondSentence.length());
    }


    /**
     * クライアントからのリクエストパラメータを抽出して返すメソッド
     *
     * @return リクエストパラメータを抽出して返す
     */
    public String getRequestParameter(String name) {

        return this.requestParameter.get(name);
    }

}
