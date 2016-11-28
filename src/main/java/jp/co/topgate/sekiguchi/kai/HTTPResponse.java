package jp.co.topgate.sekiguchi.kai;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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
     * @param outputStream
     */
    HTTPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * クライアントへ送信するレスポンスのうち、ステータスラインを設定するメソッド
     *
     * @param statusLine ステータス来ん
     */
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }


    /**
     * ファイルの拡張子を受け取りレスポンスヘッダのContent-Typeを設定するメソッド
     * @param fileExtension ファイルの拡張子
     * @return Content-Type
     */
    public String addContentType(String fileExtension) {
        String contentType = null;
        if (fileExtension.equals("html") || fileExtension.equals("css") || fileExtension.equals("js")) {
            contentType = "Content-Type: text/" + fileExtension;
        } else if (fileExtension.equals("png") || fileExtension.equals("jpeg") || fileExtension.equals("gif")) {
            contentType = "Content-Type: image/" + fileExtension;
        }

        System.out.println("Content-Typeは" + contentType);
        return contentType;
    }

    public String addCookie(Cookie cookie) {

    }

    /**
     * クライアントへ送信するレスポンスのうち、レスポンスヘッダを設定するメソッド
     *
     * @param requestResource リクエストされたリソース
     * @param file            ファイル
     */
    public void setResponseHeader() {


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

    /**
     * responseBodyを取得するためのメソッド
     *
     * @return responseBody
     */
    public byte[] getResponseBody() {
        return this.responseBody;
    }

}