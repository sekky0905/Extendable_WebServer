package jp.co.topgate.sekiguchi.kai.web;

/**
 * Handlerクラスのメソッドを定義したインターフェース
 * Created by sekiguchikai on 2016/11/22.
 */
interface Handler {
    void handleGET(HTTPRequest httpRequest, HTTPResponse httpResponse);

    void handlePOST(HTTPRequest httpRequest, HTTPResponse httpResponse);
}