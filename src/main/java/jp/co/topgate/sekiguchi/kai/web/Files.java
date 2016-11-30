package jp.co.topgate.sekiguchi.kai.web;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Files {

    /**
     * 指定されたファイルを読み込んで、そのバイナリデータを返す
     *
     * @return 読み込んだファイルのバイナリデータ
     */
    public byte[] readFile(File file) {
        byte[] byteContents = null;

        System.out.println("ファイルの読み込みを始めます");


            try {
                System.out.println(file + "ファイルを探します");
                InputStream inputStream = new FileInputStream(file);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

                int len;
                while ((len = inputStream.read()) != -1) {
                    byteArrayOutputStream.write(len);
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                }
                byteContents = byteArrayOutputStream.toByteArray();
                inputStream.close();

            } catch (IOException e) {
                System.err.println("エラー" + e.getMessage());
                e.printStackTrace();
            }

        System.out.println("レスポンスボディは" + byteContents);

        return byteContents;

    }

}
