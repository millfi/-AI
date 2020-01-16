package 五目並べAI;

public interface GameBoard_I {
	public enum Koma{};
	void firstHand(int a);
	void lateHand(int a);
	Koma[][] getGameBoard();
}
