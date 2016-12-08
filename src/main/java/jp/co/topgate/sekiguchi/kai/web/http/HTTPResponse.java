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
     * リクエストが正常に成功したことを示すステータスコード (200)
     */
    public static final String SC_OK = "HTTP/1.1 200 OK";

    /**
     * リクエストされたリソースが利用可能でないことを示すステータスコード
     */
    public static final String SC_NOT_FOUND = "HTTP/1.1 404 Not Found";

    /**
     * ステータスライン
     */
    private static String statusLine;

    /**
     * HTTP サーバの内部エラーを示すステータスコード (500)
     */
    public static final String SC_INTERNAL_SERVER_ERROR = "HTTP/1.1 500 Internal Server Error";

    /**
     * コンストラクタ
     *
     * @param outputStream アウトプットストリーム
     */
    public HTTPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * ステータスコードを引数にステータスラインを設定するメソッド
     *
     * @param statusCode ステータスコード
     */
    public void setStatusLine(String statusCode) {
        HTTPResponse.statusLine = statusCode;
    }

    /**
     * ステータスコードを引数にステータスラインを設定するメソッド
     *
     * @return ステータスライン
     */
    public static String getStatusLine() {
        return HTTPResponse.statusLine;
    }

    /**
     * レスポンスヘッダのContent-Typeを設定するメソッド
     *
     * @param fileExt ファイルの拡張子
     */
    public static String makeContentType(String fileExt) {

        Map<String, String> contentTypeMap = new HashMap<>();
        contentTypeMap.put("html", "Content-Type: text/html" + "\n");
        contentTypeMap.put("css", "Content-Type: text/css" + "\n");
        contentTypeMap.put("js", "Content-Type: text/js" + "\n");
        contentTypeMap.put("jpeg", "Content-Type: image/jpeg" + "\n");
        contentTypeMap.put("png", "Content-Type: image/png" + "\n");
        contentTypeMap.put("gif", "Content-Type: image/gif" + "\n");

        String contentType = contentTypeMap.get(fileExt);

        if (contentType == null) {
            contentType = "Content-Type: multipart/form-data" + "\n";
        }

        System.out.println("レスポンスヘッダは" + contentType);
        return contentType;

    }


    /**
     * クライアントにレスポンスを送信するためのメソッド
     */
    public void sendResponse(String fileExt, byte[] responseBody) {
        try {
            System.out.println("クライアントに送信を開始します");
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


            // 引数で受け取ったステータスラインとレスポンスヘッダを結合
            byte[] responseHead = (statusLine + "\n" + this.makeContentType(fileExt) + "\n").getBytes();


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