package chet;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ChessBoard {
	private JFrame jFrame;
	private JButton[][] jButtons;
	private Game game;

	public ChessBoard(Game game){
		this.game=game;
		
		jFrame = new JFrame("Board");
		GridLayout gridLayout =  new GridLayout(4, 8);
		JPanel jPanel = new JPanel(gridLayout);
		
		jFrame.setSize(800, 400);
		
		jFrame.add(jPanel);
		
		jButtons = new JButton[4][8];
		 Random random = new Random();
		    int a[] = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		    for(int i=0; i < a.length; i ++){ // 這個迴圈也可以只跑到result.length/2
		    	int index = random.nextInt(32);
		    	//交換 i 跟index的原素
		    	int tmp = a[index];
		    	a[index] = a[i];
		    	a[i] = tmp;
		    }
		    int b=0;
		for(int ii=0;ii<jButtons.length;ii++){
			for(int jj=0;jj<jButtons[ii].length;jj++){
				JButton button = new MyButton(game.getAllChess()[a[b]]);
				
				b++;
				
				
				
				button.setMargin(new Insets(0, 0, 0, 0)); 
				
				button.setBorder(new LineBorder(Color.BLACK));
				button.setBackground(Color.WHITE);
				button.setText(button.getName());

				try {
					Image icon = ImageIO.read(new File("bin/images/RedPawn.png"));
					button.setIcon(new ImageIcon(icon));
				} catch (Exception e) {
					// TODO: handle exception
				}
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				jButtons[ii][jj] = button;
				jPanel.add(button);
			}
		}
			
		jFrame.setVisible(true);
	}

	// public getButtons(){}

	private class MyButton extends JButton {
		private Chess myChess;

		public MyButton(Chess myChess) {
			super();
			this.myChess = myChess;
		}

		public void setChess(Chess chess) {
			myChess = chess;
		}

		public Chess getChess() {
			return this.myChess;
		}
		public String getName() {
			return this.myChess.getName();
		}
	}

}
