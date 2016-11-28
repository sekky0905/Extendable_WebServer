package jp.co.topgate.sekiguchi.kai;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
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
     * リクエストパラメーター
     */
    private Map<String, String> requestParameter = new HashMap<>();

    /**
     * JavaBeansのオブジェクト保存用
     */
    private List<Object> modelList = new ArrayList<>();

    private String requestLine;
    private String requestHeader;
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
     *
     * @return リクエストの全文
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

            this.requestHeader = new String(stringBuilder);
            System.out.print("リクエストヘッダは" + this.requestHeader);

            if (0 < contentLength) {
                char[] c = new char[contentLength];
                bufferedReader.read(c);
                this.requestBody = new String(c);
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
     * Mapに格納されているJavaBeansのモデルを返すメソッド
     *
     * @param index モデルを格納したリストのインデックス
     * @return モデルのリクストの中から、indexで指定されたモデルを返す
     */
    public Object getModel(int index) {
        return this.modelList.get(index);
    }

    /**
     * JavaBeansのモデルをMapに格納するメソッド
     */
    public void setModel(Object obj) {
        this.modelList.add(obj);
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
            queryString = this.requestBody;
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
                    String[] piece = param.split("=");
                    this.requestParameter.put(piece[0], URLDecoder.decode(piece[1], "UTF-8"));
                }
            } else {
                String[] piece = queryString.split("=");
                this.requestParameter.put(piece[0], URLDecoder.decode(piece[1], "UTF-8"));
            }

        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.getCause();
            e.printStackTrace();
        }

    }

    public String getRequestHeader() {
        return this.requestHeader;
    }


    /**
     * @return リクエストに付加されているCookieを返す
     */
    public Cookie getCookie() {
        String cookieString = null;

        cookieString = this.requestHeader.substring(requestHeader.indexOf("Cookie:"), requestHeader.indexOf("\n", requestHeader.indexOf("Cookie:")));

        String[] parameter = null;

        if (cookieString.contains(";")) {
            parameter = cookieString.split(";");
        } else {
            parameter[0] =cookieString;
        }

            Cookie cookie = new Cookie(parameter);
            return cookie;
        }
    }


