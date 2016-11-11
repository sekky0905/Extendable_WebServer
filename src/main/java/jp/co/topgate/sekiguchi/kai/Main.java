package jp.co.topgate.sekiguchi.kai;

import java.io.IOException;

/**
 * メインクラス
 * 
 * @author sekiguchikai
 * @version 1.0
 *
 */

public class Main {

	public static void main(String[] args) throws IOException {

		WebServer webServer = new WebServer();
		webServer.socketGenerator();

	}

}