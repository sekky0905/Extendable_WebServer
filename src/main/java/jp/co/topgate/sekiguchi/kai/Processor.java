package jp.co.topgate.sekiguchi.kai;

/**
 *
 * Created by sekiguchikai on 2016/11/22.
 */
 interface Processor {
    void process(HTTPRequest httpRequest, HTTPResponse httpResponse);
}