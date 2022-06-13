package front;
import back.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
public class MainFront {
    private JPanel[][] jpanels;
    private JLabel scoreLabel;
    public static void main(String[] args) {
        MainFront printer = new MainFront(16, 16);
        MyPoint.enterBoardDimensions(16, 16);
        MyMainThread thread = new MyMainThread(new Snake() ,printer);
        thread.start();
    }
    public MainFront(int maxX, int maxY) {//game panel, add main menu later
        JFrame frame = new JFrame("Snake Game");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(maxX, maxY));
        jpanels = new JPanel[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                jpanels[i][j] = new JPanel();
                mainPanel.add(jpanels[i][j]);
                jpanels[i][j].setBackground(java.awt.Color.BLACK);
                jpanels[i][j].addKeyListener(new MyKeyListener());
            }
        }
        //add label at the edge to show the score
        scoreLabel = new JLabel("0");
        jpanels[0][maxY-1].add(scoreLabel);
        mainPanel.addKeyListener(new MyKeyListener());
        frame.addKeyListener(new MyKeyListener());
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    public void print(MyPoint p, int r, int g, int b) {
        jpanels[p.getY()][p.getX()].setBackground(ColorsFactory.getColor(r, g, b));
        //System.out.println("Point drawn at "+p.getX()+" , "+p.getY());
    }
    public void resetScreen(){
        for (int i = 0; i < jpanels.length; i++) {
            for (int j = 0; j < jpanels[i].length; j++) {
                jpanels[i][j].setBackground(java.awt.Color.BLACK);
            }
        }
        //System.out.println("Screen reset");
    }
    public void updateScore(int i){
        scoreLabel.setText(""+i);
    }
}