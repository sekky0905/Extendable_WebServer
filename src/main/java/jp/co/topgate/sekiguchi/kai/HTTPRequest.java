package jp.co.topgate.sekiguchi.kai;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
     * リクエストライン
     */
    private String requestLine;

    /**
     * リクエストパラメーター
     */
    private Map<String, String> requestParameter = new HashMap<>();

    /**
     * JavaBeansのオブジェクト保存用
     */
    private Map<String, Object> modelMap = new HashMap<>();


    /**
     * コンストラクタ、set~で各フィールドを初期設定する
     *
     * @param inputStream
     */
    public HTTPRequest(InputStream inputStream) {
        this.inputStream = inputStream;
        this.setRequestLine();
    }

    /**
     * リクエストラインを返すメソッド
     *
     * @return リクエストラインを返す
     */

    public String getRequestLine() {
        System.out.println("リクエストラインは" + this.requestLine);
        return this.requestLine;
    }


    /**
     * リクエストラインを設定するメソッド
     */
    private void setRequestLine() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
        String requestLine = null;

        try {
            this.requestLine = bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.getCause();
            e.printStackTrace();
        }
    }


    /**
     * Mapに格納されているJavaBeansのモデルを返すメソッド
     *
     * @param name モデルの名前
     * @return モデル
     */
    public Object getModel(String name) {
        return this.modelMap.get(name);
    }

    /**
     * JavaBeansのモデルをMapに格納するメソッド
     */
    public void setModel(String name, Object obj) {
        this.modelMap.put(name, obj);
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
     * クエリストリングを返すメソッド
     *
     * @param requestMethod リクエストメソッド
     * @param requestURI    リクエストURI
     * @return クエリストリング
     */
    public String getQueryString(String requestMethod, String requestURI) {
        String queryString = null;

        if (requestMethod.equals("GET")) {
            queryString = requestURI.substring(requestURI.indexOf("?"), requestURI.length());
        } else if (requestMethod.equals("POST")) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            try {
                String requestContents = bufferedReader.readLine();
                StringBuilder stringBuilder = new StringBuilder();
                int contentLength = 0;

                while (requestContents != null && !requestContents.isEmpty()) {
                    if (requestContents.startsWith("Content-Length")) {
                        contentLength = Integer.parseInt(requestContents.split(":")[1].trim());
                    }
                    stringBuilder.append(requestContents + "\n");
                    requestContents = bufferedReader.readLine();
                }

                if (0 < contentLength) { // ★Content-Length 分取得
                    char[] c = new char[contentLength];
                    bufferedReader.read(c);
                    queryString = new String(c);
                }

            } catch (IOException e) {
                System.err.println("エラー:" + e.getMessage());
                e.getCause();
                e.printStackTrace();

            }
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
        String[] parameter;
        if (queryString.contains("&")) {
            parameter = queryString.split("&");
            for (String param : parameter) {
                String[] piece = param.split("=");
                this.requestParameter.put(piece[0], piece[1]);
            }
        } else {
            String[] piece = queryString.split("=");
            this.requestParameter.put(piece[0], piece[1]);
        }
    }


}