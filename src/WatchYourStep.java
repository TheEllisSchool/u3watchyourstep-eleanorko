import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class WatchYourStep extends JFrame{
	//WatchYourStep inherits from JFrame (lets the class know that there will be a window)
	
	private static final int GRIDSIZE = 10;
	//private + final means that it cannot be changed
	private TerrainButton [] [] terrain = new TerrainButton [GRIDSIZE][GRIDSIZE];
	//the two square brackets create a two dimensional array
	private static final int NUMBEROFBOMBS = 10;
	//static means that it is the same fore every instance of the class
	
	//default constructor
	public WatchYourStep(){	
		
		initGUI();
		setBombs();
		//sets up graphic user interface (sets up the actual window)
		
		setTitle("Mine Sweeper");
		setSize(200, 100); //pixels
		setResizable(true);
		pack();
		//pack(): fits everything IN the window
		setLocationRelativeTo(null); //centers on screen, do this after packing but before visible, null = center

			
		setVisible(true);
		//if default is false, the window will not show up
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	public void initGUI(){
		Font titleFont = new Font("Arial", Font.BOLD, 32);
		JLabel titleLabel = new JLabel("Watch Your Step");
		
		titleLabel.setFont(titleFont);
		titleLabel.setHorizontalAlignment(JLabel.CENTER); //left or right
		titleLabel.setBackground(Color.pink);
		titleLabel.setForeground(Color.yellow);
		titleLabel.setOpaque(true);
		add(titleLabel, BorderLayout.PAGE_START);
		
		
		//mini window within a window
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
		add(centerPanel, BorderLayout.CENTER);
		
		//TerrainButton testButton = new TerrainButton(0, 0);
		//centerPanel.add(testButton);
		for(int r = 0; r < GRIDSIZE; r++) {
			for (int c = 0; c < GRIDSIZE; c++) {
				terrain [r][c] = new TerrainButton(r, c); //makes all the buttons
				
				terrain[r][c].addActionListener (new ActionListener(){
					public void actionPerformed (ActionEvent e) { //ActionEvent = something happened
						TerrainButton button = (TerrainButton) e.getSource(); //this methods figures out which row and col were clicked
						int row = button.getRow();
						int col = button.getCol();
						terrain[row][col].click(); //use row and col b/c this function doesn't have access to r and c
					}
				});
				centerPanel.add(terrain[r][c]);
			}
		}	
	}

	public void setBombs() {
		Random rand = new Random();
		int pickRow;
		int pickCol;
		for (int i = 0; i < NUMBEROFBOMBS; i++) {	
			do {
				pickRow = rand.nextInt(GRIDSIZE);
				pickCol = rand.nextInt(GRIDSIZE);
			}while(terrain[pickRow][pickCol].hasBomb());
			terrain[pickRow][pickCol].setBomb();

	}}
	
	
	public static void main(String[] args) {
		try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            //platforms (i.e Mac vs. Windows)
            //the looks of the window corresponds to the platform
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
        
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new WatchYourStep();
                //making a new instance of the class
            }   
        });

	}

}
