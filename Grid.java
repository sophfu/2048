import java.awt.*;
import javax.swing.*;
public class Grid {
	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH;
	public static final int MENU_HEIGHT = WIDTH/400*152;
	public static void main(String[] args) {
		JFrame window = new JFrame("2048");
		window.setSize(WIDTH, HEIGHT + MENU_HEIGHT);
		window.getContentPane().setBackground(new Color(219, 191, 92));
		window.add(new GridPanel());
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}