import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
public class GridPanel extends JPanel implements KeyListener, ActionListener {
	private GridTile[][] tiles;
	private int score;
	private boolean gameLost;
	private boolean gameWon;
	private boolean postWin;
	private boolean hasChanged;
	private int highScore = 0;
	public GridPanel() {
		restart();
	}
	public void restart() {
		tiles = new GridTile[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++)
				tiles[i][j] = new GridTile(i,j,0);
		}
		score = 0;
		gameLost = false;
		gameWon = false;
		postWin = false;
		hasChanged = false;
		addKeyListener(this);
		setFocusable(true);
		addTile();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(219, 191, 92)); // menu box/background
		g.fillRect(0, 0, Grid.WIDTH, Grid.HEIGHT+Grid.MENU_HEIGHT);
		for(GridTile[] row : tiles)
			for(GridTile tile : row)
				tile.paint(g);
		g.setColor(new Color(255, 233, 156)); // score/restart box
		g.fillRect(Grid.WIDTH/400*15, Grid.WIDTH/400*415, // score box
				Grid.WIDTH/400*200, Grid.WIDTH/400*45);
		g.fillRect(Grid.WIDTH/400*225, Grid.WIDTH/400*415, // restart box
				Grid.WIDTH/400*160, Grid.WIDTH/400*45);
		g.fillRect(Grid.WIDTH/400*15, Grid.WIDTH/400*470, // high score box
				Grid.WIDTH/400*370, Grid.WIDTH/400*45);
		g.setColor(new Color(82, 56, 0)); // score
		g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*22));
		g.drawString("SCORE: " + score, Grid.WIDTH/400*27, Grid.WIDTH/400*445);
		g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*18)); // restart
		g.drawString("Restart (enter)", Grid.WIDTH/400*238, Grid.WIDTH/400*443);
		g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*20)); // high score
		g.drawString("High Score: " + highScore, Grid.WIDTH/400*27, Grid.WIDTH/400*499);
		if(gameLost) { // game over message
			g.setColor(new Color(255, 245, 209));
			g.fillRect(Grid.WIDTH/10, Grid.HEIGHT/3, Grid.WIDTH/5*4, Grid.HEIGHT/10*3);
			g.setColor(new Color(82, 56, 0));
			g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*28));
			g.drawString("GAME OVER", Grid.WIDTH/7*2, Grid.HEIGHT/15*7);
			g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*20));
			g.drawString("Press ENTER to restart", Grid.WIDTH/400*84, Grid.HEIGHT/7*4);
		}
		if(gameWon) { // game won message
			g.setColor(new Color(255, 245, 209));
			g.fillRect(Grid.WIDTH/10, Grid.HEIGHT/400*128, Grid.WIDTH/5*4, Grid.HEIGHT/400*140);
			g.setColor(new Color(82, 56, 0));
			g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*28));
			g.drawString("YOU WON :D", Grid.WIDTH/400*110, Grid.HEIGHT/400*177);
			g.setFont(new Font("default", Font.BOLD, Grid.WIDTH/400*20));
			g.drawString("Press ENTER to restart", Grid.WIDTH/400*84, Grid.HEIGHT/400*218);
			g.drawString("or SPACE to continue", Grid.WIDTH/400*92, Grid.HEIGHT/400*249);
		}
	}
	private void addTile() {
		ArrayList<GridTile> open = new ArrayList<GridTile>();
		for(GridTile[] row : tiles)
			for(GridTile tile : row)
				if(tile.getValue() == 0)
					open.add(tile);
		int[] randomizer = {2,2,2,2,2,2,2,2,2,4};
		int newValue = randomizer[(int)(Math.random()*10)];
		open.get((int)(Math.random()*open.size())).setValue(16);
	}
	private boolean isGameOver() {
		for(int r = 0; r < 4; r++)
			for(int c = 0; c < 4; c++) {
				if(tiles[r][c].getValue() == 0)
					return false;
				if(c+1<4 && tiles[r][c].getValue() == tiles[r][c+1].getValue()) // right
					return false;
				if(r+1<4 && tiles[r][c].getValue() == tiles[r+1][c].getValue()) // down
					return false;
				if(c-1>=0 && tiles[r][c].getValue() == tiles[r][c-1].getValue()) // left
					return false;
				if(r-1>=0 && tiles[r][c].getValue() == tiles[r-1][c].getValue()) // up
					return false;	
			}
		if(score > highScore)
			highScore = score;
		return true;
	}
	private boolean isGameWon() {
		int maxValue = 0;
		for(GridTile[] row : tiles)
			for(GridTile tile : row) {
				if(tile.getValue() > maxValue)
					maxValue = tile.getValue();
			}
		if(!postWin && maxValue == 2048) {
			if(score > highScore)
				highScore = score;
			return true;
		}
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(!gameLost && !gameWon) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				for(int i = 0; i < 4; i++)
					for(int j = 0; j < 4; j++) {
						if(tiles[i][j].getValue() > 0)
							moveUp(tiles[i][j]);
					}
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				for(int i = 3; i >= 0; i--)
					for(int j = 0; j < 4; j++)
						if(tiles[i][j].getValue() > 0)
							moveDown(tiles[i][j]);
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				for(int i = 0; i < 4; i++)
					for(int j = 0; j < 4; j++) {
						if(tiles[i][j].getValue() > 0)
							moveLeft(tiles[i][j]);
					}
			}
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				for(int i = 0; i < 4; i++)
					for(int j = 3; j >= 0; j--) {
						if(tiles[i][j].getValue() > 0)
							moveRight(tiles[i][j]);
					}
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			restart();
		else if(gameWon && e.getKeyCode() == KeyEvent.VK_SPACE) {
			postWin = true;
			gameWon = false;
		}
		repaint();
	}
	private void moveUp(GridTile tile) {
		int row = tile.getRow();
		int col = tile.getCol();
		while(row != 0 &&
				(tiles[row-1][col].getValue() == 0 ||
				tiles[row-1][col].getValue() == tiles[row][col].getValue())) {
			if(!tiles[row][col].isDirty() && !tiles[row-1][col].isDirty() && tiles[row-1][col].getValue() == tiles[row][col].getValue()) {
				tiles[row-1][col].setValue(tiles[row][col].getValue()+tiles[row-1][col].getValue());
				score += tiles[row][col].getValue()*2;
				tiles[row-1][col].dirtify();
				tiles[row][col].setValue(0);
			}
			else if(tiles[row-1][col].getValue() == 0) {
				tiles[row-1][col].setValue(tiles[row][col].getValue());
				tiles[row-1][col].setDirty(tiles[row][col].isDirty());
				tiles[row][col].setValue(0);
				tiles[row][col].wash();
			}
			row--;
			hasChanged = true;
		}
	}
	private void moveDown(GridTile tile) {
		int row = tile.getRow();
		int col = tile.getCol();
		while(row != 3 &&
				(tiles[row+1][col].getValue() == 0 ||
				tiles[row+1][col].getValue() == tiles[row][col].getValue())) {
			if(!tiles[row][col].isDirty() && !tiles[row+1][col].isDirty() && tiles[row+1][col].getValue() == tiles[row][col].getValue()) {
				tiles[row+1][col].setValue(tiles[row][col].getValue()+tiles[row+1][col].getValue());
				score += tiles[row][col].getValue()*2;
				tiles[row+1][col].dirtify();
				tiles[row][col].setValue(0);
			}
			else if(tiles[row+1][col].getValue() == 0) {
				tiles[row+1][col].setValue(tiles[row][col].getValue());
				tiles[row+1][col].setDirty(tiles[row][col].isDirty());
				tiles[row][col].setValue(0);
				tiles[row][col].wash();
			}
			row++;
			hasChanged = true;
		}
	}
	private void moveLeft(GridTile tile) {
		int row = tile.getRow();
		int col = tile.getCol();
		while(col != 0 &&
				(tiles[row][col-1].getValue() == 0 ||
				tiles[row][col-1].getValue() == tiles[row][col].getValue())) {
			if(!tiles[row][col].isDirty() && !tiles[row][col-1].isDirty() && tiles[row][col-1].getValue() == tiles[row][col].getValue()) {
				tiles[row][col-1].setValue(tiles[row][col].getValue()+tiles[row][col-1].getValue());
				score += tiles[row][col].getValue()*2;
				tiles[row][col-1].dirtify();
				tiles[row][col].setValue(0);
			}
			else if(tiles[row][col-1].getValue() == 0) {
				tiles[row][col-1].setValue(tiles[row][col].getValue());
				tiles[row][col].setValue(0);
			}
			col--;
			hasChanged = true;
		}
	}
	private void moveRight(GridTile tile) {
		int row = tile.getRow();
		int col = tile.getCol();
		while(col != 3 &&
				(tiles[row][col+1].getValue() == 0 ||
				tiles[row][col+1].getValue() == tiles[row][col].getValue())) {
			if(!tiles[row][col].isDirty() && !tiles[row][col+1].isDirty() && tiles[row][col+1].getValue() == tiles[row][col].getValue()) {
				tiles[row][col+1].setValue(tiles[row][col].getValue()+tiles[row][col+1].getValue());
				score += tiles[row][col].getValue()*2;
				tiles[row][col+1].dirtify();
				tiles[row][col].setValue(0);
			}
			else if(tiles[row][col+1].getValue() == 0) {
				tiles[row][col+1].setValue(tiles[row][col].getValue());
				tiles[row][col].setValue(0);
			}
			col++;
			hasChanged = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_DOWN ||
				e.getKeyCode() == KeyEvent.VK_LEFT ||
				e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(hasChanged)
				addTile();
			if(isGameOver())
				gameLost = true;
			if(isGameWon())
				gameWon = true;
			hasChanged = false;
			washAll();
			repaint();
		}
	}
	private void washAll(){
		for(GridTile[] row : tiles)
			for(GridTile tile : row)
				tile.wash();
	}
}