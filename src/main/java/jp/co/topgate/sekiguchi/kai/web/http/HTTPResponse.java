package jp.co.topgate.sekiguchi.kai.web.http;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * クライアントへ送信するHTTPレスポンスに関する責務を持つクラス
 *
 * @author sekiguchikai
 */
public class HTTPResponse<E> {

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
     * 動的なレスポンスボディ
     */
    private byte[] dynamicBody;

    /**
     * 静的なレスポンスボディ
     */
    private File staticBody;


    /**
     * 動的なレスポンスボディを設定するメソッド
     *
     * @param dynamicBody 　動的なレスポンスボディ
     */
    public void setDynamicBody(byte[] dynamicBody) {
        this.dynamicBody = dynamicBody;
    }

    /**
     * 静的なレスポンスボディを設定するメソッド
     *
     * @param staticBody 静的なレスポンスボディ
     */
    public void setStaticBody(File staticBody) {
        this.staticBody = staticBody;
    }


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
            contentType = "Content-Type: multipart/form-data" + "\n";
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
    public void sendResponse(String fileExt) throws IOException {
        System.out.println("クライアントに送信を開始します");
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {

            byte[] responseHead = (statusLine + "\n" + this.makeContentType(fileExt) + "\n").getBytes();
            dataOutputStream.write(responseHead);

            if (this.dynamicBody != null) {
                dataOutputStream.write(this.dynamicBody);
            } else {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(staticBody));

                List<Integer> fileContentsList = new ArrayList<>();

                int len;
                while ((len = bufferedInputStream.read()) != -1) {
                    fileContentsList.add(len);
                }
                for (int i = 0; i < fileContentsList.size(); i++) {
                    dataOutputStream.write(fileContentsList.get(i));
                }
            }
        } catch (
                IOException e)

        {
            System.err.println("エラー:" + e.getMessage());
            e.printStackTrace();
            // closeは必ず実行したいので、try()内に記述し、意図的にIOExceptionを生成
            throw new IOException();
        }


    }


}