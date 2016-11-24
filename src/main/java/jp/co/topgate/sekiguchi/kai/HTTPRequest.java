package jp.co.topgate.sekiguchi.kai;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    private String requestString;

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
    private String requestParameter;


    /**
     * コンストラクタ、set~で各フィールドを初期設定する
     *
     * @param socketからのstreamを受け取る
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
        StringBuilder stringBuilder = new StringBuilder();
        String tempoRequestString;
        try {
            while ((tempoRequestString = bufferedReader.readLine()) != null) {
                System.out.println(tempoRequestString);
                stringBuilder.append(tempoRequestString + "\n");
            }
        } catch (IOException e) {
            e.getCause();
            System.out.println("ファイル名の解析に失敗しました");
            e.printStackTrace();
        }

        this.requestString = new String(stringBuilder);

    }

    /**
     * クライアントからのリクエストから、リクエストラインを抽出してフィールドに設定するメソッド
     */
    private void setHTTPRequestLine() {
        this.requestLine = this.requestString.substring(0, this.requestString.indexOf("\n"));
    System.out.println("リクエストラインは、" + this.requestLine);
    }


    /**
     * クライアントからのリクエストから、リクエストURIを抽出してフィールドに設定するメソッド
     */
    private void setRequestURI() {
        int firstEmpty = this.requestLine.indexOf(" ");
        String secondSentence = this.requestLine.substring(firstEmpty + 1,
                this.requestLine.indexOf(" ", firstEmpty + 1));

        if ((secondSentence.indexOf("?") == -1)) {
            this.requestURI = secondSentence;
        } else {
            this.requestURI = secondSentence.substring(0, secondSentence.indexOf("?"));
        }
    }

    /**
     * クライアントからのリクエストから、リクエストメソッドを抽出してフィールドに設定するメソッド
     */
    private void setRequestMethod() {
        this.requestMethod = this.requestLine.substring(0, this.requestLine.indexOf(" "));
        System.out.println("リクエストメソッドは" + this.requestMethod);
    }

    private void setRequestParameter() {

    }


    /**
     * リクエスト本文を返すメソッド
     *
     * @return リクエスト本文を返す
     */
    public String getRequestString() {
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
    public void getParameter() {


    }

}
