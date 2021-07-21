import java.awt.*;
import javax.swing.*;
public class GridTile {
	private int row, col,value;
	private Color color;
	private boolean dirty;
	public GridTile(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
		setColor();
		this.dirty = false;
	}
	private void setColor() {
		if(value == 0)
			color = new Color(255,227,128);
		else if(value == 2)
			color = new Color(200,255,128);
		else if(value == 4)
			color = new Color(128,255,210);
		else if(value == 8)
			color = new Color(128,183,255);
		else if(value == 16)
			color = new Color(139,128,255);
		else if(value == 32)
			color = new Color(57,8,255);
		else if(value == 64)
			color = new Color(50,0,150);
		else if(value == 128)
			color = new Color(236,143,255);
		else if(value == 256)
			color = new Color(255,92,255);
		else if(value == 512)
			color = new Color(255,18,192);
		else if(value == 1024)
			color = new Color(255,0,128);
		else if(value == 2048)
			color = Color.red;
		else if (value > 2048)
			color = new Color(105,0,0);
	}
	public void paint(Graphics g) {
		g.setColor(color); // tile
		g.fillRect(col * Grid.WIDTH/4, row * Grid.HEIGHT/4, 
				Grid.WIDTH/4, Grid.HEIGHT/4);
		g.setColor(new Color(219, 191, 92)); // border
		g.drawRect(col * Grid.WIDTH/4, row * Grid.HEIGHT/4, 
				Grid.WIDTH/4, Grid.HEIGHT/4);
		g.setColor(Color.WHITE); // number
		g.setFont(new Font("SansSerif", Font.BOLD, 36));
		if(value > 0) {
			if(value > 0 && value < 10) { // one digit
				g.drawString(""+value, col * Grid.WIDTH/4+Grid.WIDTH/11+Grid.WIDTH/150, 
						row * Grid.HEIGHT/4+Grid.HEIGHT/7+Grid.HEIGHT/55);
			}
			else if(value < 100) { // two digit
				g.drawString(""+value, col * Grid.WIDTH/4+Grid.WIDTH/400*25, 
						row * Grid.HEIGHT/4+Grid.HEIGHT/7+Grid.HEIGHT/55);
			}
			else if(value < 1000) { // three digit
				g.setFont(new Font("SansSerif", Font.BOLD, 32));
				g.drawString(""+value, col * Grid.WIDTH/4+Grid.WIDTH/23, 
						row * Grid.HEIGHT/4+Grid.HEIGHT/7+Grid.HEIGHT/55);
			}
			else if(value < 10000) { // four digit
				g.setFont(new Font("SansSerif", Font.BOLD, 28));
				g.drawString(""+value, col * Grid.WIDTH/4+Grid.WIDTH/400*13, 
						row * Grid.HEIGHT/4+Grid.HEIGHT/7+Grid.HEIGHT/400*4);
			}
			else if(value < 100000) { // five digit
				g.setFont(new Font("SansSerif", Font.BOLD, 24));
				g.drawString(""+value, col * Grid.WIDTH/4+Grid.WIDTH/39, 
						row * Grid.HEIGHT/4+Grid.HEIGHT/7+Grid.HEIGHT/130);
			}
		}
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
		setColor();
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public void wash(){
		dirty = false;
	}
	public void dirtify(){
		dirty = true;
	}
	public boolean isDirty(){
		return dirty;
	}
	public void setDirty(boolean dirty){
		this.dirty = dirty;
	}
}