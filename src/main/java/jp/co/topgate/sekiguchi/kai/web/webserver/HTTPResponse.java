package jp.co.topgate.sekiguchi.kai.web.webserver;


import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import java.io.DataOutputStream;
import java.io.File;
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
    public static final int SC_OK = 200;

    /**
     * リクエストされたリソースが利用可能でないことを示すステータスコード(400)
     */
    public static final int SC_NOT_FOUND = 400;

    /**
     * HTTP サーバの内部エラーを示すステータスコード (500)
     */
    public static final int SC_INTERNAL_SERVER_ERROR = 500;

    /**
     * 動的なレスポンスボディ
     */
    private byte[] dynamicResponseBody;

    /**
     * 静的なレスポンスボディ
     */
    private File staticResponseBody;


    /**
     * コンストラクタ
     *
     * @param outputStream アウトプットストリーム
     */
    public HTTPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }


    /**
     * レスポンスボディを設定するメソッド
     *
     * @param responseBody レスポンスボディ
     */
    public void setDynamicResponseBody(byte[] responseBody) {
        this.dynamicResponseBody = responseBody;
    }

    /**
     * レスポンスボディを設定するメソッド
     *
     * @param file レスポンスボディに設定するためのFileインスタンス
     */
    public void setStaticResponseBody(File file) {
        this.staticResponseBody = file;
    }


    /**
     * レスポンスヘッダのContent-Typeを設定するメソッド
     *
     * @param fileExt ファイルの拡張子
     * @return Content-Type
     */
    private String makeContentType(String fileExt) {

        Map<String, String> contentTypeMap = new HashMap<>();
        contentTypeMap.put("html", "Content-Type: text/html" + "\n");
        contentTypeMap.put("css", "Content-Type: text/css" + "\n");
        contentTypeMap.put("js", "Content-Type: text/js" + "\n");
        contentTypeMap.put("jpeg", "Content-Type: image/jpeg" + "\n");
        contentTypeMap.put("png", "Content-Type: image/png" + "\n");
        contentTypeMap.put("gif", "Content-Type: image/gif" + "\n");

        String contentType = contentTypeMap.get(fileExt);

        if (contentType == null) {
            contentType = "Content-Type: application/octet-stream" + "\n";
        }

        System.out.println("レスポンスヘッダは" + contentType);
        return contentType;

    }

    /**
     * クライアントにレスポンスを送信するためのメソッド
     *
     * @param fileExt ファイルの拡張子
     * @throws java.io.IOException クライアントへのHTTPレスポンスの送信に失敗しました
     */
    public void sendResponse(int statusCode, String reasonPhrase, String fileExt) throws IOException {

        System.out.println("クライアントに送信を開始します");
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        if (this.dynamicResponseBody != null) {
            // 引数で受け取ったステータスラインとレスポンスヘッダを結合
            byte[] responseHead = ("HTTP/1.1 " + statusCode + " " + reasonPhrase + "\n" + this.makeContentType(fileExt) + "\n").getBytes();
            dataOutputStream.write(responseHead);
            dataOutputStream.write(this.dynamicResponseBody);
            dataOutputStream.flush();
            dataOutputStream.close();
        } else {
            FileDataSource fileDataSource = new FileDataSource(this.staticResponseBody);
            DataHandler dataHandler = new javax.activation.DataHandler(fileDataSource);
            dataHandler.writeTo(dataOutputStream);

        }

    }

}