package jp.co.topgate.sekiguchi.kai.web.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * コンストラクタ
     *
     * @param outputStream アウトプットストリーム
     */
    public HTTPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    /**
     * クライアントにレスポンスを送信するためのメソッド
     */
    public void sendResponse(String statusLine, String requestHeader, byte[] responseBody) {
        try {
            System.out.println("クライアントに送信を開始します");
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // 引数で受け取ったステータスラインとレスポンスヘッダを結合
            byte[] responseHead = (statusLine + "\n" + requestHeader + "\n").getBytes();


            byte[] responseContents = new byte[responseHead.length + responseBody.length];
            // ResponseContentsにbyteResponseHeadを追加
            System.arraycopy(responseHead, 0, responseContents, 0, responseHead.length);
            // ResponseContentsにresponseBodyを追加
            System.arraycopy(responseBody, 0, responseContents, responseHead.length, responseBody.length);

            if (responseBody != null) {
                dataOutputStream.write(responseContents, 0, responseContents.length);
                dataOutputStream.flush();
                dataOutputStream.close();
            }

        } catch (IOException e) {
            System.err.println("エラー" + e.getMessage());
            e.printStackTrace();
        }
    }


}