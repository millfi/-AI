package 五目並べAI;

public class GameBoard implements GameBoard_I{
	public enum Koma{
		first,
		late,
		nothing
	};
	public static final int 縦 = 7,横 = 9;
	//駒を新たに置く座標
	private int Addable[] =new int[横];
		
	Koma gameBoard[][] = new Koma[縦][横];
	public GameBoard(){
		for(int i = 0;i < 縦;i++) {
			for(int k = 0;k < 横;k++) {
				gameBoard[i][k] =  Koma.nothing;
			}
		}
		for(int k = 0;k < 横;k++) {Addable[k] = 0;}
	}
	//引数は左からn番目のnを表す
			/*○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *row = 7
			 *col = 9
			 */
	@Override
	public void firstHand(int a) {
		if(gameBoard[縦 - 1][a-1] == Koma.nothing) {
			gameBoard[Addable[a-1]][a-1] = Koma.first;
			Addable[a-1]++;
		}
		if(Addable[a] >= 縦) {}
	}
	@Override
	public void lateHand(int a) {
		if(gameBoard[縦 - 1][a-1] == Koma.nothing) {
			gameBoard[Addable[a-1]][a-1] = Koma.late;
			Addable[a-1]++;
		}
	}
	@Override
	public Koma[][] getGameBoard(){
		Koma[][] Board = new Koma[縦][横];
		for(int i = 0;i < 縦;i++) {
			for(int k = 0;k < 横;k++) {
				Board[i][k] = this.gameBoard[i][k];
			}
		}
		return Board;
	}
	private boolean first_is_win_row(int row,int col,int times){
		if(times == 5) {return true;}
		if(row == 縦) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_row(row + 1,col,times + 1);
		}
		else {
			return first_is_win_row(row + 1,col,0);
		}
	}
	private boolean first_is_win_col(int row,int col,int times) {
		if(times == 5) {return true;}
		if(col == 横) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_col(row,col + 1,times + 1);
		}
		else {
			return first_is_win_col(row,col + 1,0);
		}
	}
	private boolean late_is_win_row(int row,int col,int times){
		if(times == 5) {return true;}
		if(row == 縦) {return false;}
		if(gameBoard[row][col] == Koma.late) {
			return late_is_win_row(row + 1,col,times + 1);
		}
		else {
			return late_is_win_row(row + 1,col,0);
		}
	}
	private boolean late_is_win_col(int row,int col,int times) {
		if(times == 5) {return true;}
		if(col == 横) {return false;}
		if(gameBoard[row][col] == Koma.late) {
			return late_is_win_col(row,col + 1,times + 1);
		}
		else {
			return late_is_win_col(row,col + 1,0);
		}
	}
	//左下から右斜め上方向に舐めて判定
	private boolean first_is_win_Diagonal_row(int row,int col,int times) {
		if(times == 5) {return true;}
		if(row == 縦||row == -1) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_Diagonal_row(row - 1,col + 1,times + 1);
		}
		else {
			return first_is_win_Diagonal_row(row - 1,col + 1,0);
		}
	}
	private boolean first_is_win_Diagonal_col(int row,int col,int times) {
		if(times == 5) {return true;}
	}
	public boolean isEndGame(){
		for(int col = 0;col < 横;col++) {
			if(first_is_win_row(0,col,0)||late_is_win_row(0,col,0)) {return true;}
		}
		for(int row = 0;row < 縦;row++) {
			if(first_is_win_col(row,0,0)||late_is_win_col(row,0,0)) {return true;}
		}
		
		return false;
	}

	public static void main(String[]args) {
		GameBoard a =new GameBoard();

		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);

		for(int i = 0;i < 縦;i++) {
			for(int j = 0; j < 横;j++) {
				if(a.gameBoard[i][j] == Koma.nothing) {System.out.print('□');}
				else if(a.gameBoard[i][j] == Koma.first) {System.out.print('○');}
				else if(a.gameBoard[i][j] == Koma.late) {System.out.print('●');}
			}
			System.out.println("");
		}
		System.out.println(a.isEndGame());
	}
}