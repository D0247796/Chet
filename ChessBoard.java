package chet;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ChessBoard implements java.util.Observer {
	private JFrame jFrame;
	private MyButton[][] MyButton;
	private Game game;
	private Chess moveChess;// 按下去的按鈕
	private int gameStart = 0;// 是否剛開始
	private static ChessBoard singleChessBoard = null;// singleton

	public static ChessBoard instance(Game game) {
		if (singleChessBoard == null) {
			singleChessBoard = new ChessBoard(game);
		}
		return singleChessBoard;
	}

	private ChessBoard(Game game) {
		// 介面創造
		File source = null;
		Image image = null;
		source = new File("D:/編輯程式/eclipse/workspace/chet/src/chet/picture/象棋(未翻).jpg");
		try {
			image = ImageIO.read(source);
		} catch (IOException e1) {
			System.out.println("圖檔獨入錯誤");
		}
		this.game = game;
		game.addObserver(this);
		jFrame = new JFrame("Board");
		GridLayout gridLayout = new GridLayout(4, 8);
		JPanel jPanel = new JPanel(gridLayout);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(1600, 800);

		jFrame.add(jPanel);

		MyButton = new MyButton[4][8];
		Random random = new Random();
		int a[] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27, 28, 29, 30, 31 };
		for (int i = 0; i < a.length; i++) { // Chess洗牌
			int index = random.nextInt(32);
			int tmp = a[index];
			a[index] = a[i];
			a[i] = tmp;
		}
		int b = 0;
		for (int i = 0; i < MyButton.length; i++) {
			for (int j = 0; j < MyButton[i].length; j++) {
				MyButton[i][j] = new MyButton(new Coordinate(j, i));
				MyButton[i][j].setChess(game.getAllChess()[a[b]]);// Button中的Chess
				b++;

				MyButton[i][j].setMargin(new Insets(0, 0, 0, 0));

				MyButton[i][j].setBorder(new LineBorder(Color.BLACK));// 按鈕邊線

				try {
					// Image icon = ImageIO.read(new
					// File("bin/images/RedPawn.png"));// ?我不懂，別人打的
					MyButton[i][j].setIcon(new ImageIcon(image));
				} catch (Exception e) {
					System.out.println("棋子顏色失敗");
				}
				int p = i, q = j;// for迴圈數字會跑掉
				MyButton[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						game.chooseButton(MyButton[p][q]);
					}
				});
				jPanel.add(MyButton[i][j]);
			}
		}

		jFrame.setVisible(true);
	}

	// public getButtons(){}

	public class MyButton extends JButton {
		private Chess myChess;
		private Coordinate myCoordinate;

		public MyButton(Coordinate myCoordinate) {
			super();
			this.myCoordinate = myCoordinate;
		}

		public void setChess(Chess chess) {
			myChess = chess;
			chess.setCoordinate(myCoordinate);
		}

		public Coordinate getMyCoordinate() {
			return this.myCoordinate;
		}

		public Chess getChess() {
			return this.myChess;
		}
	}

	@Override
	public void update(Observable o, Object arg) {// 刷新介面
		for (int i = 0; i < MyButton.length; i++) {
			for (int j = 0; j < MyButton[i].length; j++) {
				if (MyButton[i][j].getChess().getState() == 1 || MyButton[i][j].getChess().getWeight() == -1) {
					MyButton[i][j].setIcon(MyButton[i][j].getChess().getPicture());
				}
			}
		}

	}

}
