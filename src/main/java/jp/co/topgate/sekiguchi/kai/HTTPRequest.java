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
     * クライアントからのリクエストライン
     */
    private String requestLine;
    /**
     * クライアントからのリクエスト本文
     */
    private List<String> requestString = new ArrayList<>();

    /**
     * クライアントからのリクエストメソッド
     */
    private String requestMethod;
    /**
     * クライアントからのリクエストURI
     */
    private String requestURI;

    /**
     * クエリパラメーター
     */
    private Map<String, String> requestParameter = new HashMap<>();


    /**
     * コンストラクタ、set~で各フィールドを初期設定する
     *
     * @param inputStream
     */
    public HTTPRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        this.setRequestString();
        this.setHTTPRequestLine();
        this.setRequestURI();
        this.setRequestMethod();
    }


    private void setRequestString() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        String tempoRequestString;
        try {
            tempoRequestString = bufferedReader.readLine();
            this.requestString.add(tempoRequestString + "\n");

            while (tempoRequestString != null) {
                System.out.println(tempoRequestString);
                tempoRequestString = bufferedReader.readLine();
                this.requestString.add(tempoRequestString + "\n");
            }
        } catch (IOException e) {
            e.getCause();
            System.out.println("ファイル名の解析に失敗しました");
            e.printStackTrace();
        }
    }

    /**
     * クライアントからのリクエストから、リクエストラインを抽出してフィールドに設定するメソッド
     */
    private void setHTTPRequestLine() {
        this.requestLine = this.requestString.get(0);
        System.out.println("リクエストラインは、" + this.requestLine);
    }


    /**
     * クライアントからのリクエストから、リクエストURIを抽出してフィールドに設定するメソッド
     */
    private void setRequestURI() {
        int firstEmpty = this.requestLine.indexOf(" ");
        String secondSentence = this.requestLine.substring(firstEmpty + 1,
                this.requestLine.indexOf(" ", firstEmpty + 1));

        if (secondSentence.contains("?")) {
            this.requestURI = secondSentence.substring(0, secondSentence.indexOf("?"));
        } else {
            this.requestURI = secondSentence;
        }
        System.out.print("リクエストURIは" + this.requestURI);
    }

    /**
     * クライアントからのリクエストから、リクエストメソッドを抽出してフィールドに設定するメソッド
     */
    private void setRequestMethod() {
        this.requestMethod = this.requestLine.substring(0, this.requestLine.indexOf(" "));
        System.out.println("リクエストメソッドは" + this.requestMethod);
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
    public List<String> getRequestString() {
        return this.requestString;
    }

    /**
     * リクエストラインを返すメソッド
     *
     * @return リクエストラインを返す
     */
    public String getRequestLine() {
        return this.requestLine;
    }

    /**
     * リクエストメソッドを返すメソッド
     *
     * @return リクエストメソッドを返す
     */
    public String getRequestMethod() {
        return this.requestMethod;
    }

    /**
     * リクエストURIを返すメソッド
     *
     * @return リクエストURIを返す
     */
    public String getRequestURI() {
        return this.requestURI;
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
