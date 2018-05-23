import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Font;

public class Sudoku {

    private JFrame frame;
    boolean isStarted = false;
    int prevBoard[][] = new int[9][9];

    final JButton startButton = new JButton("Start");
    final JButton submitButton = new JButton("Submit");
    final JTextField grid[][] = new JTextField[9][9];
    int timeCount = -1;


    //To convert counter into time.
    public String countToTime(int count){
        String min = Integer.toString(count/60);
        String sec = Integer.toString(count%60);
        if(Integer.parseInt(min) == 0) min = "0"+min;
        if(Integer.parseInt(sec)/10 == 0) sec = "0"+sec;
        return min+":"+sec;
    }

    //Event handler when game is over.
    public void gameOver(){
//        prevBoard = SudokuSolver.solve(prevBoard);
        boolean isFine = true;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(grid[i][j].getText().equals("")){
                    isFine = false;
                    break;
                }
                else if(Integer.parseInt(grid[i][j].getText()) != prevBoard[i][j]){
                    isFine = false;
                    break;
                }
            }
        }
        if(isFine && isStarted) JOptionPane.showMessageDialog(null, "You Won.");
        else JOptionPane.showMessageDialog(null, "You Lose.");
        isStarted = false;
        startButton.setText("Start");
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].setText("");
                grid[i][j].setEditable(false);
            }
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Sudoku window = new Sudoku();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Sudoku() {
        initialize();
    }


    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 668, 438);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //////////////////////////////////GRID START /////////////////////////////////////////////////////////////


        int h = 12, w = 13, hi = 39, wi = 37;

        for(int i = 0; i < 9 ; i++){
            if(i%3 == 0 && i!=0) w += 13;

            for(int j = 0; j < 9; j++){
                if(j%3 == 0 && j!=0) h += 11;

                grid[i][j] = new JTextField();
                grid[i][j].setColumns(10);
                grid[i][j].setBounds(h, w, 38, 37);
                frame.getContentPane().add(grid[i][j]);
                h += hi;
            }
            h = 12;
            w += wi;
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                grid[i][j].setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 22));
                grid[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                grid[i][j].setEditable(false);
            }
        }
        //////////////////////////////////  GRID END  /////////////////////////////////////////////////////////////

        submitButton.setVisible(false);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                gameOver();
            }
        });
        submitButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
        submitButton.setBounds(435, 270, 155, 37);
        frame.getContentPane().add(submitButton);

        JLabel difficultyLabel = new JLabel("Select difficulty:");
        difficultyLabel.setFont(new Font("Calibri Light", Font.BOLD, 16));
        difficultyLabel.setBounds(435, 70, 155, 24);
        frame.getContentPane().add(difficultyLabel);

        final JRadioButton easyButton = new JRadioButton("Easy");
        easyButton.setFont(new Font("Calibri Light", Font.BOLD, 13));
        easyButton.setBounds(435, 103, 127, 25);
        frame.getContentPane().add(easyButton);

        final JRadioButton mediumButton = new JRadioButton("Medium");
        mediumButton.setFont(new Font("Calibri Light", Font.BOLD, 13));
        mediumButton.setBounds(435, 133, 127, 25);
        frame.getContentPane().add(mediumButton);

        final JRadioButton hardButton = new JRadioButton("Hard");
        hardButton.setFont(new Font("Calibri Light", Font.BOLD, 13));
        hardButton.setBounds(435, 163, 127, 25);
        frame.getContentPane().add(hardButton);

        ButtonGroup bg = new ButtonGroup();
        bg.add(easyButton);
        bg.add(mediumButton);
        bg.add(hardButton);
        bg.setSelected(mediumButton.getModel(), true);



        startButton.setBounds(435, 206, 155, 37);
        frame.getContentPane().add(startButton);
        startButton.setFont(new Font("Calibri Light", Font.BOLD, 18));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(isStarted){
                    isStarted = false;
//                    prevBoard = SudokuSolver.solve(prevBoard);
                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            grid[i][j].setEditable(false);
                            grid[i][j].setText(Integer.toString(prevBoard[i][j]));
                        }
                    }
                    startButton.setText("Start");
                    submitButton.setVisible(false);
                }
                else{
                    int difficulty = 1; // Default is medium.
                    if(easyButton.isSelected()) difficulty = 0;
                    else if (hardButton.isSelected()) difficulty = 2;
                    else difficulty = 1;

                    if(difficulty == 0) timeCount = 600;
                    else if(difficulty == 1) timeCount = 360;
                    else timeCount = 180;

                    int board[][] = new int[9][9];
                    do{
//                        board = SudokuGenerator.generate(difficulty);
                    }while(board[0][0] == -1);
                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            prevBoard[i][j] = board[i][j];
                        }
                    }
                    for(int i = 0; i < 9; i++){
                        for(int j = 0; j < 9; j++){
                            if(board[i][j] != 0){
                                grid[i][j].setText(Integer.toString(board[i][j]));
                            }
                            else {
                                grid[i][j].setText("");
                                grid[i][j].setEditable(true);
                            }
                        }
                    }
                    submitButton.setVisible(true);
                    startButton.setText("Give up!");
                    isStarted = true;
                }
            }
        });
    }
}
