package chet;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
	private JButton[][] jButtons;
	private Game game;
	private Chess moveChess;// ���U�h�����s
	private int gameStart = 0;// �O�_��}�l

	public ChessBoard(Game game) {

		this.game = game;
		game.getRule().addObserver(this);
		jFrame = new JFrame("Board");
		GridLayout gridLayout = new GridLayout(4, 8);
		JPanel jPanel = new JPanel(gridLayout);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setSize(800, 400);

		jFrame.add(jPanel);

		jButtons = new JButton[4][8];
		Random random = new Random();
		int a[] = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24,
				25, 26, 27, 28, 29, 30, 31 };
		for (int i = 0; i < a.length; i++) { // �~�P
			int index = random.nextInt(32);
			int tmp = a[index];
			a[index] = a[i];
			a[i] = tmp;
		}
		int b = 0;
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons[i].length; j++) {
				JButton button = new MyButton(new Coordinate(j, i));
				((MyButton) button).setChess(game.getAllChess()[a[b]]);// Button����Chess
				game.getAllChess()[a[b]].setCoordinate(((MyButton) button).getMyCoordinate());// ���Chess�y��
				b++;

				button.setMargin(new Insets(0, 0, 0, 0));

				button.setBorder(new LineBorder(Color.BLACK));// ���s��u
				button.setBackground(Color.WHITE);// ���s����
				button.setText("�л\��");

				try {
					Image icon = ImageIO.read(new File("bin/images/RedPawn.png"));// ?
					button.setIcon(new ImageIcon(icon));
				} catch (Exception e) {

				}
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (gameStart == 0) {// ��}�l�T�w���@�謰�u����
							game.isPlayer(((MyButton) button).getChess().getPlayer());
							System.out.println(game.whoPlay().getName() + "½�X�F" + button.getName());
							game.changePlayer();
							((MyButton) button).getChess().setState(1);
							System.out.println("���a�C��w�T�w");
							button.setText(button.getName());
							gameStart++;
						} else if (((MyButton) button).getChess() == null && moveChess == null) {
							System.out.println("�п�A�Ѥl");
						} else if (((MyButton) button).getChess() != null
								&& ((MyButton) button).getChess().getState() == 0 && moveChess == null) {
							button.setText(button.getName());
							((MyButton) button).getChess().setState(1);
							System.out.println(game.whoPlay().getName() + "½�X�F" + button.getName());
							game.changePlayer();
						} else if (moveChess == null && ((MyButton) button).getChess().getPlayer().toString()
								.equals(game.whoPlay().toString())) {
							System.out.println(game.whoPlay().getName() + "��ܤF" + button.getName());
							moveChess = ((MyButton) button).getChess();

						} else if (moveChess != null) {
							int a = ((MyButton) button).getMyCoordinate().getX() - moveChess.getCoordinate().getX();
							int b = ((MyButton) button).getMyCoordinate().getY() - moveChess.getCoordinate().getY();
							if (((MyButton) button).getChess() == null
									|| ((MyButton) button).getChess().getState() == 1) {
								game.change(moveChess, ((MyButton) button).getMyCoordinate());
								button.setText(button.getName());
								((MyButton) button).getChess().setState(1);
							} else {
								System.out.println("�Э��s���");
							}
							moveChess = null;
						} else {
							System.out.println("�п�ܦۤv���Ѥl");
						}

					}
				});
				jButtons[i][j] = button;
				jPanel.add(button);
			}
		}

		jFrame.setVisible(true);
	}

	// public getButtons(){}

	private class MyButton extends JButton {
		private Chess myChess;
		private Coordinate myCoordinate;

		public MyButton(Coordinate myCoordinate) {
			super();
			this.myCoordinate = myCoordinate;
		}

		public void setChess(Chess chess) {
			myChess = chess;
		}

		public Coordinate getMyCoordinate() {
			return this.myCoordinate;
		}

		public String getName() {
			return this.myChess.getName();
		}

		public Chess getChess() {
			return this.myChess;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		game.changePlayer();
		for (int i = 0; i < jButtons.length; i++) {
			for (int j = 0; j < jButtons[i].length; j++) {
				for (int k = 0; k < game.getAllChess().length; k++) {
					if (((MyButton) jButtons[i][j]).getMyCoordinate().equals(game.getAllChess()[k].getCoordinate())) {
						((MyButton) jButtons[i][j]).setChess(game.getAllChess()[k]);
						if (((MyButton) jButtons[i][j]).getChess().getState() == 1) {
							jButtons[i][j].setText(((MyButton) jButtons[i][j]).getName());
						} else if (((MyButton) jButtons[i][j]).getChess().getState() == 0) {
							jButtons[i][j].setText("�л\��");
						}
						break;
					} else {
						((MyButton) jButtons[i][j]).setChess(null);
						jButtons[i][j].setText(null);
					}
				}
			}
		}

	}

}
