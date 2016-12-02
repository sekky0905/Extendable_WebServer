package jp.co.topgate.sekiguchi.kai.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
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
     * リクエストパラメーター
     */
    private Map<String, String> requestParameter = new HashMap<>();

    /**
     * リクエストライン
     */
    private String requestLine;

    /**
     * リクエストボディ
     */
    private String requestBody;


    /**
     * コンストラクタ、set~で各フィールドを初期設定する
     *
     * @param inputStream socketのストリーム
     */
    public HTTPRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        this.setRequestContents();
    }


    /**
     * リクエストの全文を読み込むメソッド
     */
    public void setRequestContents() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));

            String line = bufferedReader.readLine();
            this.requestLine = line;
            System.out.print("リクエストラインは" + this.requestLine);

            StringBuilder stringBuilder = new StringBuilder();
            int contentLength = 0;

            while (line != null && !line.isEmpty()) {
                if (line.startsWith("Content-Length")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }

                stringBuilder.append(line + "\n");
                line = bufferedReader.readLine();
            }

            String requestHeader = new String(stringBuilder);
            System.out.print("リクエストヘッダは" + requestHeader);

            if (0 < contentLength) {
                char[] c = new char[contentLength];
                bufferedReader.read(c);
                this.requestBody = new String(c) + "\n";
                System.out.print("リクエストボディは" + this.requestBody);
            }

        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.getCause();
            e.getStackTrace();
        }


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
    public String getRequestMethod(String requestLine) {
        String requestMethod = requestLine.substring(0, requestLine.indexOf(" "));
        System.out.println("リクエストメソッドは" + requestMethod);
        return requestMethod;
    }

    /**
     * @param requestLine リクエストライン
     * @return リクエストURI + クエリストリング
     */
    public String getSecondSentence(String requestLine) {int firstEmpty = requestLine.indexOf(" ");
        return requestLine.substring(firstEmpty + 1,
                requestLine.indexOf(" ", firstEmpty + 1));
    }

    /**
     * リクエストURIを返すメソッド
     *
     * @return リクエストURIを返す
     */
    public String getRequestURI(String secondSentence) {

        String requestURI;

        if (secondSentence.contains("?")) {
            requestURI = secondSentence.substring(0, secondSentence.indexOf("?"));
        } else {
            requestURI = secondSentence;
        }
        System.out.print("リクエストURIは" + requestURI);

        return requestURI;
    }

    /**
     * クエリストリングを返すメソッド
     *
     * @param requestMethod  リクエストメソッド
     * @param secondSentence リクエストURI + クエリストリング
     * @return クエリストリング
     */
    public String getQueryString(String requestMethod, String secondSentence) {
        String queryString = null;

        if (requestMethod.equals("GET")) {
            queryString = secondSentence.substring(secondSentence.indexOf("?") + 1, secondSentence.length());
        } else if (requestMethod.equals("POST")) {
            queryString = this.requestBody.substring(0, requestBody.indexOf("\n"));
        }
        return queryString;
    }


    /**
     * クライアントからのリクエストパラメータを抽出して返すメソッド
     *
     * @return リクエストパラメータを抽出して返す
     */
    public String getRequestParameter(String name) {
        String value = this.requestParameter.get(name);
        System.out.println("リクエストパラメータ" + name + "の値は" + value);
        return value;
    }

    /**
     * リクエストパラメータを設定するメソッド
     *
     * @param queryString クエリストリング
     */
    public void setRequestParameter(String queryString) {
        try {
            String[] parameter;
            if (queryString.contains("&")) {
                parameter = queryString.split("&");
                for (String param : parameter) {
                    String piece[] = param.split("=");
                    if (piece.length == 2) {
                        this.requestParameter.put(piece[0], URLDecoder.decode(piece[1], "UTF-8"));
                    } else if (piece.length == 1) {
                        this.requestParameter.put(piece[0], "エラー!文字を入力してください");
                    }

                }
            } else {
                String piece[] = queryString.split("=");
                if (piece.length == 2) {
                    this.requestParameter.put(piece[0], URLDecoder.decode(piece[1], "UTF-8"));
                } else if (piece.length == 1) {
                    this.requestParameter.put(piece[0], "エラー!文字を入力してください");
                }
            }

        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.getCause();
            e.printStackTrace();
        }

    }


}
