import javax.swing.*;
import java.util.*;

public class BombSquare extends GameSquare
{
	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;

	private boolean clicked = false;

	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	public void clicked()
	{
		if(thisSquareHasBomb){
			setImage("Resources/images/bomb.png");
			gameOver();
		}else{
			checkAround();
		}
	}

	public void checkAround(){
		this.clicked = true;
		int bombs = 0;
		for (int i = this.xLocation-1; i <= this.xLocation+1; i++) {
			for (int j = this.yLocation-1; j <= this.yLocation+1; j++) {
				BombSquare s = (BombSquare) board.getSquareAt(i, j);
				if(s != null && s.isBomb()){
					bombs++;
				}
			}
		}
		if(bombs == 0){
			for (int i = this.xLocation-1; i <= this.xLocation+1; i++) {
				for (int j = this.yLocation-1; j <= this.yLocation+1; j++) {
					BombSquare s = (BombSquare) board.getSquareAt(i, j);
					if(s != null && s.clicked == false){ s.checkAround(); }
				}
			}
		}
		setImage(updateImage(bombs));


	}

	public String updateImage(int bombs){
		String image = "Resources/images/";
		switch (bombs){
			case 1:
				image = image.concat("1.png");
				break;
			case 2:
				image = image.concat("2.png");
				break;
			case 3:
				image = image.concat("3.png");
				break;
			case 4:
				image = image.concat("4.png");
				break;
			case 5:
				image = image.concat("5.png");
				break;
			case 6:
				image = image.concat("6.png");
				break;
			case 7:
				image = image.concat("7.png");
				break;
			case 8:
				image = image.concat("8.png");
				break;
			default:
				image = image.concat("0.png");
				break;
		}
		return image;
	}

	public void gameOver(){

	}

	public boolean isBomb(){
		return thisSquareHasBomb;
	}

	public boolean isClicked(){
		return clicked;
	}



}
