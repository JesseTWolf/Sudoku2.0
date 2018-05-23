//file:FileIO.java
//Author:Jesse Wolf
//Purpose:To import and export data from my file.
//Pre: The data in my file is UserName, TotalScore, and TotalGames in that order with a space between each.
//Post: The data will be imported for use with my Sudoku game and rewritten to file when game is complete.
//Date: 10 Dec 2017

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *File import and export. Default constructor imports,
 *method saveToFile exports data back to file.
 */
public class FileIO{

    private int lineCount;
    private String [] userName;
    private int [] totalScore;
    private int [] totalGames;
    private Scanner keyboard = new Scanner(System.in);
    private String fileName;
    String currentUser;
    int currentTotal;
    int currentNumGames;
    private int userIndex;
    String currentUserName;
    int currentUserTotal;
    int currentUserGames;

    /**
     *Full constructor for FileIO class. Checks file line count,
     *set user data arrays (name, score, games) with input from file,
     *asks user for username and checks if within user array,
     *if found set current user data as respective data or
     *if not found set current as 0's.
     *
     *@param fileName is fed from my Object.
     *
     */
    public FileIO(String fileName){

        Scanner file = null;
        //Get lineCount
        try{
            file = new Scanner(new FileInputStream(fileName));
            lineCount = 0;
            while (file.hasNextLine()) {
                lineCount++;
                file.nextLine();
            }
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
        lineCount = lineCount + 1;
        //setUserArrays
        userName = new String [lineCount];
        totalScore = new int [lineCount];
        totalGames = new int [lineCount];
        //Fill my Arrays
        Scanner inputStream = null;
        int index = 0;
        try{
            inputStream = new Scanner(new FileInputStream(fileName));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
        String tempUser;
        int tempScore;
        int tempTotal;
        //System.out.println("My lineCount is: " + lineCount);
        while(inputStream.hasNext()){
            tempUser = inputStream.next();
            if(tempUser.equals(null)){
                String trash = tempUser;
            }
            else{
                //System.out.println(tempUser);
                userName[index] = tempUser;
            }
            //System.out.print(index + " ");
            //System.out.print(userName[index] + " ");
            tempScore = inputStream.nextInt();
            totalScore[index] = tempScore;
            //System.out.print(tempScore + " ");
            //System.out.print(totalScore[index] + " ");
            tempTotal = inputStream.nextInt();
            totalGames[index] = tempTotal;
            //System.out.print(tempTotal);
            //System.out.println(totalGames[index]);
            index++;
        }
        //}
        inputStream.close();
        //Login the user
        System.out.println("Please enter your username: _ ");
        String checkUser = keyboard.next();
        //System.out.println(checkUser);
        //Scan my userArray for input username
        for (int count=0;count<userName.length;count++){
            //System.out.println(userName[count]);
            if(checkUser.equals(userName[count])) {
                System.out.println("Found " + count);
                userIndex = count;
                break;
            }
            else{
                userIndex = -1;
            }
        }
        //System.out.println(userIndex);
        //Set current user's data
        if(userIndex != -1){
            currentUserName = userName[userIndex];
            currentUserTotal = totalScore[userIndex];
            currentUserGames = totalGames[userIndex];
        }
        else{
            int a = (lineCount - 1);
            userName[a]=checkUser;
            totalScore[a]=0;
            totalGames[a]=0;
            currentUserName=userName[a];
            currentUserTotal=totalScore[a];
            currentUserGames=totalGames[a];
            System.out.println(userName[a]);
            System.out.println("UserIndex: " + userIndex);
        }
        //System.out.println(currentUserName + " " + currentUserTotal + " " + currentUserGames);
    }

    /**
     *To update my current user data
     *
     *@param String user, int score, and int games. The data being used to update User data.
     */
    public void updateUserData(String user, int score, int games){
        if(userIndex != -1){
            userName[userIndex] = user;
            totalScore[userIndex] = score;
            totalGames[userIndex] = games;
        }
        else{
            userName[lineCount -1] = user;
            totalScore[lineCount -1] = score;
            totalGames[lineCount -1] = games;
        }
    }

    /**
     *Set my fileName
     *
     */
    public void setFileName(){
        System.out.println("What is the file containing user data? _ ");
        fileName = keyboard.next();
    }

    /**
     *getGileName for use in Constructor
     *
     *@return fileName (name of file)
     */
    public String getFileName(){
        return fileName;
    }

    /**
     *getCurrentUser for use in Player class
     *
     *@return currentUser (current user name)
     */
    public String getCurrentUserName(){
        return currentUserName;
    }

    /**
     *getCurrentUserTotal will return current user total
     *
     *@return currentUserTotal (total score for current user)
     */
    public int getCurrentUserTotal(){
        return currentUserTotal;
    }

    /**
     *getCurrentUserGames will return current user games.
     *
     *@return currentUserGames (current users number of games)
     */
    public int getCurrentUserGames(){
        return currentUserGames;
    }

    /**
     *saveToFile method to print all my arrays back into my file.
     */
    public void saveToFile(){
        PrintWriter outputStream = null;
        try{
            outputStream = new PrintWriter(new FileOutputStream("info.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Error opening file.");
            System.exit(0);
        }
        for(int a=0;a<userName.length;a++){
            outputStream.print(userName[a] + " ");
            outputStream.print(totalScore[a] + " ");
            outputStream.print(totalGames[a]);
            outputStream.println();
        }
        outputStream.close();
    }

    /**
     *getUserIndex
     *
     *@return userIndex (index where user was found in my array, if found previously)
     */
    public int getUserIndex(){
        return userIndex;
    }
}
