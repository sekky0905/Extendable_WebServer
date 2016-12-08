package jp.co.topgate.sekiguchi.kai.web.http;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

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
    private String requestLine[];

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
        try {
            this.setRequestContents();
        } catch (IOException e) {
            System.err.println("エラー:" + e.getMessage());
            e.getStackTrace();
        }
    }


    /**
     * リクエストの全文を読み込むメソッド
     */
    public void setRequestContents() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));

        String line = bufferedReader.readLine();
        this.requestLine = line.split(" ");

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

    }


    /**
     * リクエストメソッドを返すメソッド
     *
     * @return リクエストメソッドを返す
     */
    public String getRequestMethod() {
        String requestMethod = this.requestLine[0];
        System.out.println("リクエストメソッドは" + requestMethod);
        return requestMethod;
    }

    /**
     * リクエストURIを返すメソッド
     *
     * @return リクエストURIを返す
     */
    public String getRequestURI() {
        String requestURI;
        if (this.requestLine[1].contains("?")) {
            requestURI = this.requestLine[1].substring(0, this.requestLine[1].indexOf("?"));
        } else {
            requestURI = this.requestLine[1];
        }
        try {
            requestURI = URLDecoder.decode(requestURI, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
        }


        System.out.print("リクエストURIは" + requestURI);

        return requestURI;
    }

    /**
     * クエリストリングを返すメソッド
     *
     * @return クエリストリング
     */
    public String getQueryString() {
        String queryString = null;

        if (this.getRequestMethod().equals("GET")) {
            queryString = this.requestLine[1].substring(this.requestLine[1].indexOf("?") + 1, this.requestLine[1].length());
        } else if (this.getRequestMethod().equals("POST")) {
            queryString = this.requestBody.substring(0, this.requestBody.indexOf("\n"));
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
     */
    public void setRequestParameter() throws IOException {

        List<String> paramList = new ArrayList<>();
        if (this.getQueryString().contains("&")) {
            paramList = Arrays.asList(this.getQueryString().split("&"));
        } else {
            paramList.add(this.getQueryString());
        }
        for (String param : paramList) {
            String piece[] = param.split("=");
            if (piece.length == 2) {
                this.requestParameter.put(piece[0], URLDecoder.decode(piece[1], "UTF-8"));
            } else if (piece.length == 1 || piece.length == 0) {
                this.requestParameter.put(piece[0], "");
            }

        }
    }

    /**
     * リクエストURIから要求されているファイルを返すメソッド
     *
     * @return 要求されているファイル
     */
    public String getRequestResource() {
        String requestResource;
        if ((this.getRequestURI().endsWith("/")) || !(this.getRequestURI().substring(this.getRequestURI().lastIndexOf("/"), this.getRequestURI().length()).contains("."))) {
            requestResource = "src/main/resources" + this.getRequestURI() + "index.html";
        } else {
            requestResource = "src/main/resources" + this.getRequestURI();
        }
        System.out.println("要求されているファイルは" + requestResource);
        return requestResource;

    }

    /**
     * 指定されたファイルの拡張子を返すメソッド
     *
     * @return 指定されたファイルの拡張子
     */
    public String getRequestResourceExtension(String requestResource) {
        String extension = requestResource.substring(requestResource.lastIndexOf(".") + 1,
                requestResource.lastIndexOf(""));
        System.out.println("ファイルの拡張子は" + extension);
        return extension;
    }

}
