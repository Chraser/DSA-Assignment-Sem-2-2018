/************************************************************
 *Name: Kay Men Yap
 *File name: UserIO.java
 *Purpose: Handles all of user inputs and outputs
 *Date last modified: 28/10/2018
 ************************************************************/
//This file contains modified code obtained from OOPD Assignment
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.GregorianCalendar;
public class UserIO
{    
    /************************************************************
     *SUBMODULE: stringInput
     *IMPORTS: promptMessage(String)
     *EXPORT: string(String)
     *PURPOSE: prompt user to enter a string that is not empty 
               and inform any input errors
     ************************************************************/
    public static String stringInput(String promptMessage)
    {
        String string = "";
        String error = "";
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(promptMessage);
            string = sc.nextLine();
            error = "ERROR: Input can only be a non empty string";
        }while(string.equals(""));
        return string;
    }
    
    /************************************************************
     *SUBMODULE: integerInput
     *IMPORTS: promptMessage(String), min(Integer), max(Integer)
     *EXPORT: integer(Integer)
     *PURPOSE: prompt user to enter an integer and inform any input
               errors
     ************************************************************/
    public static int integerInput(String promptMessage, int min, int max)
    {
        int integer;
        String error = "";
        integer = Integer.MAX_VALUE;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(promptMessage);
            try
            {
                integer = sc.nextInt();
                error = "ERROR: Input can only be an integer between " + min
                         + " and " + max + " inclusive";
            }
            catch (InputMismatchException e)
            {
                String flush = sc.nextLine();
                error ="Invalid input. Integer input required."
                        + " Please enter again.";
            }
        }while((integer < min) || (integer > max));
        return integer;
    }
    
    /************************************************************
     *SUBMODULE: realInput
     *IMPORTS: promptMessage(String), min(Real), max(Real)
     *EXPORT: real(Real)
     *PURPOSE: prompt user to enter a real and inform any input
               errors
     ************************************************************/
    public static double realInput(String promptMessage)
    {
        double real;
        String error = "";
        real = 0;
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println(error);
            System.out.println(promptMessage);
            try
            {
                real = sc.nextDouble();
                error = "ERROR: Input can only be a real number";
            }
            catch (InputMismatchException e)
            {
                String flush = sc.nextLine();
                error = "Invalid input. Real input required."
                        + " Please enter again." ;
            }
        }while(Math.abs(real - 0) < 0.000001);
        return real;
    }
    
    //ASSERTION: the 2d array of candidates will be output to screen
    public static void displayCandidates(String[][] candidateArray)
    {
        System.out.println();
        for(int i = 0; i < candidateArray.length; i++)
        {
            for(int j = 0; j < candidateArray[0].length; j++)
            {
                if(j < candidateArray[0].length - 1)
                {
                    System.out.print(candidateArray[i][j] + ",");
                }
                else
                {
                    System.out.print(candidateArray[i][j] + "\n");
                }
            }
        }
    }
    
    //display the list by margin to the screen
    public static void displayListByMargin(DSALinkedList<String[]> listByMargin)
    {
        if(!listByMargin.isEmpty())
        {
            System.out.println();
            for(String[] margin : listByMargin)
            {
                for(int i = 0; i < margin.length; i++)
                {
                    if(i < margin.length - 1)
                    {
                        System.out.print(margin[i] + ", ");
                    }
                    else
                    {
                        System.out.print(margin[i] + "\n");
                    }
                }
            }
        }
        else
        {
            System.out.println("List by margin is empty");
        }
    }
    
    public static void displayItinerary(DSALinkedList<String[]> itinerary)
    {
        System.out.println();
        System.out.println("Itinerary");
        int totalTime = 0;
        for(String[] path : itinerary)
        {
            System.out.println(path[0]);
            totalTime += Integer.parseInt(path[1]);
        }
        int hours, minutes;
        hours = totalTime / 360;
        minutes = totalTime % 360 / 60;
        System.out.println("Total time: " + hours + " hours and " + minutes + " minutes");
    }
}