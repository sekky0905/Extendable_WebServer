package jp.co.topgate.sekiguchi.kai.web.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * クライアントへ送信するHTTPレスポンスに関する責務を持つクラス
 *
 * @author sekiguchikai
 */
public class HTTPResponse {

    /**
     * クライアントとのsocketを格納したOutputStream
     */
    private OutputStream outputStream;
    /**
     * クライアントへのレスポンスライン
     */
    private String statusLine;
    /**
     * クライアントへのレスポンスヘッダ
     */
    private String responseHeader;
    /**
     * クライアントへのレスポンスボディ
     */
    private byte[] responseBody;

    /**
     * コンストラクタ
     *
     * @param outputStream アウトプットストリーム
     */
    public HTTPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * ステータスラインを設定するメソッド
     *
     * @param statusLine ステータスライン
     */
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    /**
     * レスポンスヘッダを設定するメソッド
     *
     * @param fileExtension ファイルの拡張子
     */
    public void setResponseHeader(String fileExtension) {

        Map<String, String> contentTypeMap = new HashMap<>();
        contentTypeMap.put("html", "Content-Type: text/html");
        contentTypeMap.put("css", "Content-Type: text/css");
        contentTypeMap.put("js", "Content-Type: text/js");
        contentTypeMap.put("jpeg", "Content-Type: image/jpeg");
        contentTypeMap.put("html", "Content-Type: image/png");
        contentTypeMap.put("html", "Content-Type: image/gif");

        String contentType = contentTypeMap.get(fileExtension);

        if (contentType == null) {
            this.responseHeader = "Content-Type: text/html";
        } else {
            this.responseHeader = contentType;
        }


        if (fileExtension.equals("html") || fileExtension.equals("css") || fileExtension.equals("js")) {
            this.responseHeader = "Content-Type: text/" + fileExtension;
        } else if (fileExtension.equals("png") || fileExtension.equals("jpeg") || fileExtension.equals("gif")) {
            this.responseHeader = "Content-Type: image/" + fileExtension;
        } else {
            this.responseHeader = "Content-Type: text/html";
        }
        System.out.println("レスポンスヘッダは" + this.responseHeader);
    }


    /**
     * クライアントへ送信するレスポンスのうち、レスポンスボディを設定するメソッド
     *
     * @param responseBody リクエストボディ
     */
    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    /**
     * クライアントにレスポンスを送信するためのメソッド
     */
    public void sendResponse() {
        try {
            System.out.println("クライアントに送信を開始します");
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // 引数で受け取ったステータスラインとレスポンスヘッダを結合

            byte[] responseHead = (this.statusLine + "\n" + this.responseHeader + "\n" + "\n").getBytes();
            byte[] responseContents = new byte[responseHead.length + this.responseBody.length];
            // ResponseContentsにbyteResponseHeadを追加
            System.arraycopy(responseHead, 0, responseContents, 0, responseHead.length);
            // ResponseContentsにresponseBodyを追加
            System.arraycopy(this.responseBody, 0, responseContents, responseHead.length, this.responseBody.length);

            if (this.responseBody != null) {
                dataOutputStream.write(responseContents, 0, responseContents.length);
                dataOutputStream.flush();
                dataOutputStream.close();
            }

        } catch (IOException e) {
            System.err.println("エラー" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 以下、テスト用の仕掛け

    /**
     * statusLineを取得するためのメソッド
     *
     * @return responseHeader
     */

    public String getStatusLine() {
        return this.statusLine;
    }

    /**
     * responseHeaderを取得するためのメソッド
     *
     * @return responseHeader
     */
    public String getResponseHeader() {
        return this.responseHeader;
    }

}