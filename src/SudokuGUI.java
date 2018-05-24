/*
 * CS 112
 * Final Project Sudoku
 * File Name: SudokuGUI.java
 * Programmer: Jesse Wolf
 * Date Last Modified: 23 May 2018
 *
 * Problem Statement: Create a GUI that will allow a user to play through an entire game of sudoku.
 *
 * Algorithm:
 * 1. Main Method within my file SudokuGUI.java:
 *   - Create an object of the SudokuGUI. The no-arg constructor will handle the rest.
 *
 *  2. TaxRateGUI.java
 *     - Create instance variables for:
 *       1. My Taxes Class object "myTaxes".
 *       2. Two JTextFields incomeTaxTextField and taxableIncomeTextField as instance variables in order for ease
 *          of use within my private classes.
 *       3. Four JCheckBoxes selfCheckBox, dependentChildCheckBox, threeLeggedDogCheckBox, and grandmotherCheckBox
 *          as instance variables in order for ease of use within my private classes.
 *          - In the default constructor definition:
 *           1. Create JFrame object using super's constructor and passing in "Income Tax Calculator" for a title.
 *              - Set default close operations to exit on close.
 *              - Set layout to a borderLayout.
 *           2. Create 3 separate JPanels to hold all of our individual elements.
 *              - All 3 JPanels will also house a grid layout to hold its elements.
 *              - Within these 3 JPanels we will create as follows:
 *              1. 1st JPanel will be located to the west most part of the frame and consist of:
 *                 - 3 RadioButtons Labeled "Single", "Married", "Single,Head of Household" respectively.
 *                 - These RadioButtons will be placed into a ButtonGroup in order to keep them linked together,
 *                   if they were not linked all three could be selected at the same time. This way that will not
 *                   happen.
 *                 - Each RadioButton will be given an ActionListener "RadioButtonListener"
 *                 - Will set the background of panel 1 to white and add all of my radioButtons to it.
 *              2. 2nd JPanel will be located in the center of the frame and consist of:
 *                 - 2 JTextFields labeled "taxableIncomeTextField" and "incomeTaxTextField". The taxableIncome field
 *                   will be used for user input. The incomeTax field is where we will output the end result of our
 *                   calculation to the user and is in such not editable by the user.
 *                   - Each JTextField will have a JLabel attached to it in order to label them properly.
 *                 - 1 Button labeled Compute. This button will be used to call upon our Taxes class calcIncomeTax
 *                   method.
 *                   - The button will be given an ActionListener "ComputeButtonListener".
 *                 - Will set the background of panel 2 to white and add the elements to the panel.
 *              3. 3rd Panel will be located in the east most part of the frame and consist of:
 *                 - 4 JCheckBoxes labeled "Self", "Dependent Child", "Three-legged Dog", and "Blind Grandmother"
 *                   respectively.
 *                   - Each CheckBox will have its background set to white and given an ActionListener
 *                     "CheckBoxListener".
 *                   - Will set the background of panel 3 to white and add the elements to the panel.
 *           3. Add all three panels to the frame settings each to their respective direction discussed above.
 *              - pack() and set my frame as visible.
 *       4. Create an ActionListener class "ComputeButtonListener" that will handle the action after ComputeButton has
 *          been clicked.
 *          - The actionPerformed method will consist of:
 *            - Capturing the string from my taxableIncomeTextField and stripping away the "$" and "," from this string.
 *              Then parsing it into a double for use in my calculations.
 *            - Will use Taxes method setTaxableIncome passing in the above value in order to set that objects taxable
 *              income double for use in the calculations.
 *            - Will use calcIncomeTax method from within my Taxes class in order to use all of the variables I have
 *              passed into it from my various ActionListeners to calculate the Income Tax this user should expect to
 *              pay. Will capture the double that is returned from this calculation and format it with a dollar sign and
 *              proper decimal point before sending it to my incomeTaxTextField for view by the user.
 *       5. Create an ActionListener class "CheckBoxListener" that will handle the actions from my check boxes.
 *          - The actionPerformed method will consist of:
 *            - A series of if statements that will call my Taxes class' setClaimSelf, setClaimChild, setClaimDog, and
 *              setClaimGrandmother respectively dependent upon which check boxes are selected or unselected within
 *              each action.
 *       6. Create an ActionListener class "RadioButtonListener" that will handle the actions from my radio buttons.
 *          - I will retrieve the label of the radio button through use of the getActionCommand method.
 *          - I will then use this to call my Taxes class' setStatus method passing into it the label of the respective
 *            radio button that had been clicked/selected.
 */




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
        SudokuGUI game1 = new SudokuGUI();
    }

    private JButton[] buttons = new JButton[81];
    private JTextField[][] grid = new JTextField[9][9];

    public SudokuGUI() {
        super("Sudoku");
        setLayout(new BorderLayout());
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
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

    /**
     * completeGrid method that will check to see if the grid is complete. If it is this will create a JOptionPane to
     * let the user know that they have completed the sudoku game and show their final score.
     */
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
                if(userInput == null) {
                    JOptionPane.showMessageDialog((JFrame) null, "You have not entered an int please try again.");
                }
                else {
                    JOptionPane.showMessageDialog((JFrame) null, "You have not entered an int instead you" +
                            " entered: \n" + (userInput));
                }
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
