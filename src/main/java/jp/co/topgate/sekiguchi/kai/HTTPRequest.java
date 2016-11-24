package jp.co.topgate.sekiguchi.kai;

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
     * クライアントからのリクエストメソッド
     */
    private String requestMethod;
    /**
     * クライアントからのリクエストURI
     */
    private String requestURI;
    // private String queryString;

    /**
     * コンストラクタ、set~で各フィールドを初期設定する
     *
     * @param socketからのstreamを受け取る
     */
    public HTTPRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        this.setHTTPRequestLine();
        this.setRequestURI();
        this.setRequestMethod();
    }

    /**
     * クライアントからのリクエストから、リクエストラインを抽出してフィールドに設定するメソッド
     */
    private void setHTTPRequestLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        try {
            this.requestLine = bufferedReader.readLine();
            if (bufferedReader != null) {
            }
        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * クライアントからのリクエストから、リクエストURIを抽出してフィールドに設定するメソッド
     */
    public void setRequestURI() {
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


    // /**
    // * クライアントからのリクエストから、クエリストリングを抽出して返すメソッド
    // *
    // * @return クエリストリングを抽出して返す
    // */
    // public void getQueryString() {
    // if (this.requestURI.indexOf("?") != -1) {
    // this.queryString =
    // this.requestLine.substring(this.requestURI.lastIndexOf("?"),
    // this.requestURI.length() - 1);
    // } else {
    // this.queryString =
    // }
    // }

}
