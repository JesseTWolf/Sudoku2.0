import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//public class GUI extends JFrame{
//
//    public static void main(String [] args) {
//        SudokuGenerator.main(null);
//        array=readIn();
//        sudoku=generate();
//        for (int i = 0; i<9;i++){
//            for (int j=0;j<9;j++)
//                System.out.print(array[i][j] + " ");
//            System.out.println();
//        }
//        new GUI();
//    }
//
//    JTextField[][] textFields;
//    Sudoku sudoku;
//    boolean paint;

//    public GUI() {
//        super();
//        textFields = new JTextField[Sudoku.GRID_SIZE][Sudoku.GRID_SIZE];
//        sudoku = new Sudoku();
//        this.setLayout(new GridLayout(Sudoku.GRID_SIZE, Sudoku.GRID_SIZE));

//        paint = true;
//        for (int r = 0; r < Sudoku.GRID_SIZE; r++) {
//            for (int c = 0; c < Sudoku.GRID_SIZE; c++) {
//                textFields[r][c] = new JTextField(2);
//                paintSquare(r, c);
//                if ((c + 1) % 3 == 0) {
//                    paint = !paint;
//                }
//                Font f = new Font("Dialog", Font.PLAIN, 18);
//                textFields[r][c].setFont(f);
//                textFields[r][c].setHorizontalAlignment(JTextField.CENTER);
//                this.add(textFields[r][c]);
//            }
//            if ((r + 1) / 3 == 1) {
//                paint = false;
//            } else {
//                paint = true;
//            }
//        }
//    }
//
//    private void paintSquare(int r, int c) {
//        if (paint) {
//            textFields[r][c].setBackground(new Color(200, 200, 200));
//        }
//    }
//}