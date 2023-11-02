import javax.swing.*;
import java.util.*;

/**
* @author grayb4
* */
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

	/**
	 * when a button is clicked it goes to this method which starts all below methods if nessasary.
	 * */

	public void clicked()
	{
		if(thisSquareHasBomb){
			setImage("Resources/images/bomb.png");
			gameOver();
		}else{
			checkAround();
		}
	}


	/**
	 * this checks the squares around the clicked square and sets the image to the amount of bobs around it.
	 * If there are no bombs in the 3x3 area around it it then recurses for the spaces around it.
	 * */
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

	/**
	 * This sets the image to the amount of bobs it has around it.
	 * @param bombs the amount of bombs in the immediate area.
	 * */

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


	/**
	 * when a game over is initated it reveals all bombs on the game map.
	 * */
	public void gameOver(){
		for (int i = 0; i < board.getWidth(); i++) {
			for (int j = 0; j < board.getHeight(); j++) {
				BombSquare s = (BombSquare) board.getSquareAt(i, j);
				if(s != null && s.isBomb()){
					s.setImage("Resources/images/bomb.png");
				}
			}
		}

	}

	public boolean isBomb(){
		return thisSquareHasBomb;
	}




}
