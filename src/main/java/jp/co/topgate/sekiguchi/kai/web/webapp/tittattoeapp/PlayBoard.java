package jp.co.topgate.sekiguchi.kai.web.webapp.tittattoeapp;

/**
 * ゲームの盤を表すクラス
 * Created by sekiguchikai on 2016/12/17.
 */
public class PlayBoard {
    /**
     * 盤を表す二次元配列
     */
    private int boardArray[][] = new int[3][3];

    /**
     * ユーザーが決定した打ち手を盤の指定の位置に登録するメソッド
     * @param x 盤のx軸
     * @param y 盤のy軸
     */
    public void setUserMove(int x, int y) {

    }

    /**
     * CPUが決定した打ち手を盤の指定の位置に登録するメソッド
     * @param x 盤のx軸
     * @param y 盤のy軸
     */
    public void setCPUMove(int x, int y) {
    }

    /**
     * 盤を表すarray(boardArray)を返すメソッド
     * @return 盤を表すarray
     */
    public int[][] getBoardArray(){
        return this.boardArray;
    }
}
