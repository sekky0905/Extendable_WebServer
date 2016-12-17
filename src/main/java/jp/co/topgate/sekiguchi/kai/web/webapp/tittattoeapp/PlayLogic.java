package jp.co.topgate.sekiguchi.kai.web.webapp.tittattoeapp;

/**
 * TitTatToe（まるばつゲーム）のロジックを表すクラス
 * Created by sekiguchikai on 2016/12/17.
 */
public class PlayLogic {
    /**
     * 盤の状況を引数として受けて、CPUの打ち手を決定し、返すメソッド
     *
     * @param playBoard 盤
     * @return CPUの打ち手
     */
    public int generateCPUMove(PlayBoard playBoard) {
        // CPUの打ち手ロジックを記入
        return 1;
    }

    /**
     * 盤の状況を引数として受けて、勝敗を決定して返すメソッド
     *
     * @param boardArray 盤の二次元配列
     * @return 勝敗(君の勝ちだ/君の負けだ/引き分けだ)
     */
    public String judgeResult(int[][] boardArray) {
        // 勝敗を表すロジックを記入
        return "";
    }
}
