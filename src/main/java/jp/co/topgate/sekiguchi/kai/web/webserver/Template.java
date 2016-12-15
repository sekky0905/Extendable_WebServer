package jp.co.topgate.sekiguchi.kai.web.webserver;



import java.io.IOException;

/**
 * Templateクラスの継承元のインターフェース
 * Created by sekiguchikai on 2016/11/26.
 */
public interface Template {

    /**
     * HTMLのテンプレートを作成するメソッド
     *
     * @param httpRequest  httpRequestのインスタンス
     * @param httpResponse httpResponseのインスタンス
     */
    void writeHTML(HTTPRequest httpRequest, HTTPResponse httpResponse) throws IOException;

}


