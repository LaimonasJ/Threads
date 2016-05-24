package threads;

import java.util.*;
import java.io.*;

/**
 * @author Laimonas Juras
 */
public class Threads
{

    static double piPrecision = 300000000; 
    static String sampleTextName = "input.txt";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        System.out.println("Welcome to hotel manager!");
        int option = 1;
        while (option != 0)
        {
            System.out.println("Available options:");
            System.out.println("0. Exit");
            System.out.println("1: Get the most repeated letter in text");
            System.out.println("2: Calculate Pi");
            try
            {
                option = Integer.parseInt(new Scanner(System.in).nextLine());
                switch(option)
                {
                    case 0:
                        System.out.println("Goodbye!");
                        break;
                    case 1:
                        createLetterThread(sampleTextName);
                        break;
                    case 2:
                        createCalcThread();
                        break;
                    default:
                        printOptionError();
                        break;
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            catch(Exception e)
            {
                printOptionError();
            }
        }
    }
    public static synchronized void createCalcThread()
            throws IOException
    {
        PiCalc calcRunnable = new PiCalc(piPrecision);
        Thread calcThread = new Thread(calcRunnable);
        calcThread.start();
    }
    public static synchronized void createLetterThread(String inputFile)
            throws IOException
    {
        LetterCounter letterRunnable = new LetterCounter(inputFile);
        Thread letterThread = new Thread(letterRunnable);
        letterThread.start();
    }
    public static void printOptionError()
    {
        System.out.println("Not an available option!");
    }
    
}
