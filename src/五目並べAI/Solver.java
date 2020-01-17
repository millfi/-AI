package 五目並べAI;

import 五目並べAI.GameBoard.Koma;

public class Solver {
	GameBoard GB;
	public static final int height = 7,width = 9;
	public int valuation() {
		Koma gb[][] = new Koma[height][width];
		for(int i = 0;i < height;i++) {
			for(int k = 0;k < width;k++) {
				gb[i][k] = GB.getGameBoard(i,k);
			}
		}
		return 0;
	}
}
