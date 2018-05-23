import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SudokuGUI extends JFrame {
    private final int WIDTH = 400;
    private final int HEIGHT = 800;
    private int totalPoints = 100;

    Generation gridGen = new Generation();
    int[] answerGrid = gridGen.getFinalGrid1D();
    int[] playerGrid = gridGen.getPlayerGrid();

    public static void main(String[] args) {
        SudokuGUI test = new SudokuGUI();
    }

    private JButton[] buttons = new JButton[81];
    private JTextField[][] grid = new JTextField[9][9];

    public SudokuGUI() {
        super("Sudoku");
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create three panels
        JPanel mainGrid = new JPanel();
        JPanel subGrid1 = new JPanel();
        JPanel subGrid2 = new JPanel();
        JPanel subGrid3 = new JPanel();
        JPanel subGrid4 = new JPanel();
        JPanel subGrid5 = new JPanel();
        JPanel subGrid6 = new JPanel();
        JPanel subGrid7 = new JPanel();
        JPanel subGrid8 = new JPanel();
        JPanel subGrid9 = new JPanel();
        JPanel left3 = new JPanel();
        JPanel middle3 = new JPanel();
        JPanel right3 = new JPanel();

        mainGrid.setLayout(new GridLayout(9, 9, 5, 5));
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int id = (r * 9) + c;
                String stringID = Integer.toString(id);
                buttons[id] = new JButton(stringID);
                buttons[id].addActionListener(new GridListener());
                buttons[id].setActionCommand(stringID);
                if (playerGrid[id] == 0) {
                    buttons[id].setText(" ");
                } else {
                    buttons[id].setText(Integer.toString(playerGrid[id]));
                }

                if (c < 3) {
                    if (r < 3) {
                        subGrid1.setLayout(new GridLayout(3, 3));
                        subGrid1.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid1.add(buttons[id]);
                    } else if (r < 6) {
                        subGrid4.setLayout(new GridLayout(3, 3));
                        subGrid4.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid4.add(buttons[id]);
                    } else if (r < 9) {
                        subGrid7.setLayout(new GridLayout(3, 3));
                        subGrid7.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid7.add(buttons[id]);
                    }
                } else if (c < 6) {
                    if (r < 3) {
                        subGrid2.setLayout(new GridLayout(3, 3));
                        subGrid2.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid2.add(buttons[id]);
                    } else if (r < 6) {
                        subGrid5.setLayout(new GridLayout(3, 3));
                        subGrid5.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid5.add(buttons[id]);
                    } else if (r < 9) {
                        subGrid8.setLayout(new GridLayout(3, 3));
                        subGrid8.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid8.add(buttons[id]);
                    }
                } else if (c < 9) {
                    if (r < 3) {
                        subGrid3.setLayout(new GridLayout(3, 3));
                        subGrid3.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid3.add(buttons[id]);
                    } else if (r < 6) {
                        subGrid6.setLayout(new GridLayout(3, 3));
                        subGrid6.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid6.add(buttons[id]);
                    } else if (r < 9) {
                        subGrid9.setLayout(new GridLayout(3, 3));
                        subGrid9.setBorder(new LineBorder(Color.BLACK, 2));
                        subGrid9.add(buttons[id]);
                    }
                }
            }
            left3.setLayout(new BorderLayout());
            left3.add(subGrid1, BorderLayout.NORTH);
            left3.add(subGrid4, BorderLayout.CENTER);
            left3.add(subGrid7, BorderLayout.SOUTH);

            middle3.setLayout(new BorderLayout());
            middle3.add(subGrid2, BorderLayout.NORTH);
            middle3.add(subGrid5, BorderLayout.CENTER);
            middle3.add(subGrid8, BorderLayout.SOUTH);

            right3.setLayout(new BorderLayout());
            right3.add(subGrid3, BorderLayout.NORTH);
            right3.add(subGrid6, BorderLayout.CENTER);
            right3.add(subGrid9, BorderLayout.SOUTH);

            add(left3, BorderLayout.WEST);
            add(middle3, BorderLayout.CENTER);
            add(right3, BorderLayout.EAST);

            pack();
            setVisible(true);
            setLocation(380, 380);
        }
    }

    /**
     * checkAnswer will take the userValue, index, and grid passed into it and use this to check if
     * the user input value matches the grid at the designated index. Will return true or false
     * dependent upon if it matches or not.
     *
     * @param userValue int
     * @param index     int
     * @param grid      int[]
     * @return boolean
     */
    public boolean checkAnswer(int userValue, int index, int[] grid) {
        int tempFullGridValue = grid[index];
        if (userValue == tempFullGridValue) {
            return true;
        } else {
            totalPoints -= 1;
            return false;
        }
    }

    public void completeGrid() {
        boolean complete = false;
        for (int i = 0; i < playerGrid.length; i++) {
            if (playerGrid[i] == answerGrid[i])
                complete = true;
            if (playerGrid[i] != answerGrid[i]) {
                complete = false;
                return;
            }
        }
        System.out.println("In complete grid method" + complete);
        if(complete) {
            System.out.println("Inside if statement");
            JOptionPane.showMessageDialog(null, "Congratulations you have won. " +
                    "\nEnd Score is: " + totalPoints);
        }
    }

        /**
         * CalculateButtonListener Class. This class handles the ActionListener for my calculate button.
         * Will calculate my total charge from all of my inner panels and output the end result to the user.
         */
        private class GridListener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                String buttonString = e.getActionCommand();
                int buttonID = Integer.parseInt(buttonString);
                int integerInput = -1;

                String userInput = ""; //= JOptionPane.showInputDialog("Please enter an integer:");
                try {
                    userInput = JOptionPane.showInputDialog("Please enter an integer:");
//                userInput = Integer.parseInt(JOptionPane.showInputDialog("Please enter an integer:"));
                    integerInput = Integer.parseInt(userInput);
                } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog((JFrame) null, "You have not entered an int instead you" +
                    " entered: \n" + (userInput));
                    userInput = null;
                }
                boolean checkAnswer = false;

                if(userInput != null) {

                    checkAnswer = checkAnswer(integerInput, buttonID, answerGrid);
                    if (checkAnswer) {
                        buttons[buttonID].setText(userInput);
                        playerGrid[buttonID] = integerInput;
                    }
                    //If incorrect then the button will still be updated however it will be turned red
                    //to show it was incorrect.
                    if (!checkAnswer) {
                        JOptionPane.showMessageDialog(null, "You have entered incorrectly " +
                                "please try again. \nYour current points are: " + totalPoints);
                    }
                }
                System.out.println(checkAnswer);
                System.out.println(answerGrid[buttonID]);
                System.out.println(userInput);
                System.out.println(totalPoints);
                completeGrid();
            }
        }
    }
