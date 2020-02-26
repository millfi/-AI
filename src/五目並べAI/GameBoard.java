package 五目並べAI;

public class GameBoard implements GameBoard_I{
	public enum Koma{
		first,
		late,
		nothing
	};
	public static final int height = 7,width = 9;
	//駒を新たに置く座標
	private int Addable[] =new int[width];
	private Koma gameBoard[][] = new Koma[height][width];
	public GameBoard(){
		for(int i = 0;i < height;i++) {
			for(int k = 0;k < width;k++) {
				gameBoard[i][k] =  Koma.nothing;
			}
		}
		for(int k = 0;k < width;k++) {Addable[k] = 0;}
	}
	//引数は左からn番目のnを表す
			/*○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *○○○○○○○○○
			 *height:7
			 *width:9
			 *原点は左上
			 */
	@Override
	public void firstHand(int a) {
		if(gameBoard[height - 1][a-1] == Koma.nothing) {
			gameBoard[Addable[a-1]][a-1] = Koma.first;
			Addable[a-1]++;
		}
		if(Addable[a] >= height) {}
	}
	@Override
	public void lateHand(int a) {
		if(gameBoard[height - 1][a-1] == Koma.nothing) {
			gameBoard[Addable[a-1]][a-1] = Koma.late;
			Addable[a-1]++;
		}
		if(Addable[a] >= height) {}
	}
	public Koma getGameBoard(int i,int j){	
		return  this.gameBoard[i][j];
	}
	private boolean first_is_win_row(int row,int col,int times){
		if(times == 5) {return true;}
		if(row == height) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_row(row + 1,col,times + 1);
		}
		else {
			return first_is_win_row(row + 1,col,0);
		}
	}
	private boolean first_is_win_col(int row,int col,int times) {
		if(times == 5) {return true;}
		if(col == width) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_col(row,col + 1,times + 1);
		}
		else {
			return first_is_win_col(row,col + 1,0);
		}
	}
	private boolean late_is_win_row(int row,int col,int times){
		if(times == 5) {return true;}
		if(row == height) {return false;}
		if(gameBoard[row][col] == Koma.late) {
			return late_is_win_row(row + 1,col,times + 1);
		}
		else {
			return late_is_win_row(row + 1,col,0);
		}
	}
	private boolean late_is_win_col(int row,int col,int times) {
		if(times == 5) {return true;}
		if(col == width) {return false;}
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
		if(row == -1) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_Diagonal_row(row - 1,col + 1,times + 1);
		}
		else {
			return first_is_win_Diagonal_row(row - 1,col + 1,0);
		}
	}
	private boolean first_is_win_Diagonal_col(int row,int col,int times) {
		if(times == 5) {return true;}
		if(col == -1) {return false;}
		if(gameBoard[row][col] == Koma.first) {
			return first_is_win_Diagonal_col(row - 1,col + 1,times + 1);
		}
		else {
			return first_is_win_Diagonal_col(row - 1,col + 1,0);
		}
	}
	private boolean late_is_win_Diagonal_row(int row,int col,int times) {
		if(times == 5) {return true;}
		if(row == -1) {return false;}
		if(gameBoard[row][col] == Koma.late) {
			return late_is_win_Diagonal_row(row - 1,col + 1,times + 1);
		}
		else {
			return late_is_win_Diagonal_row(row - 1,col + 1,times + 1);
		}
	}
	private boolean late_is_winDiagonal_col(int row,int col,int times) {
		if(times == 5) {return true;}
		if(col == -1) {return false;}
		
	}
	@Override
	public boolean isEndGame(){
		for(int col = 0;col < width;col++) {
			if(first_is_win_row(0,col,0)||late_is_win_row(0,col,0)) {return true;}
		}
		for(int row = 0;row < height;row++) {
			if(first_is_win_col(row,0,0)||late_is_win_col(row,0,0)) {return true;}
		}
		for(int col = 0;col < width;col++) {}
		for(int row = 0;row < height;row++) {}
		return false;
	}

	public static void main(String[]args) {
		GameBoard a =new GameBoard();

		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);
		a.firstHand(3);a.firstHand(4);a.firstHand(5);a.firstHand(6);

		for(int i = 0;i < height;i++) {
			for(int j = 0; j < width;j++) {
				if(a.gameBoard[i][j] == Koma.nothing) {System.out.print('□');}
				else if(a.gameBoard[i][j] == Koma.first) {System.out.print('○');}
				else if(a.gameBoard[i][j] == Koma.late) {System.out.print('●');}
			}
			System.out.println("");
		}
		System.out.println(a.isEndGame());
	}
}