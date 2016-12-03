package jp.co.topgate.sekiguchi.kai.web.model;

/**
 * Modelを定義した抽象クラス
 * Created by sekiguchikai on 2016/11/30.
 */
public abstract class Model {
    /**
     * ユーザーネーム
     */
    private String name;

    /**
     * ユーザーネームを取得するためのメソッド
     *
     * @return ユーザーネーム
     */
    public String getName() {
        return this.name;
    }

    /**
     * ユーザーネームを設定するためのメソッド
     *
     * @param name 　ユーザーネーム
     */
    public void setName(String name) {
        this.name = name;
    }
}
