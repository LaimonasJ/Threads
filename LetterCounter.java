package threads;
import java.io.*;
import java.util.*;
/**
 * @author Laimonas Juras
 */
public class LetterCounter implements Runnable, Interruptable
{
    private boolean interrupted = false;
    private final String inputFileName;
    LetterCounter(String input)
    {
        this.inputFileName = input;
    }

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        File file = new File(inputFileName);
        try (Scanner scanner = new Scanner(new FileReader (inputFileName)))
        {
            printStart();
            HashMap<Character, Integer> letterMap = new HashMap<>();
            String line = null;
            while (scanner.hasNext()&& !interrupted)
            {
                line = scanner.nextLine();
                for(char c: line.toCharArray())
                {
                    c = Character.toUpperCase(c);
                    if (Character.isLetter(c))
                    {
                        if(letterMap.containsKey(c))
                        {
                            letterMap.replace(c, letterMap.get(c) + 1);
                        }
                        else
                        {
                            letterMap.put(c, 0);
                        }
                    }
                      
                }
            }
            
            if (letterMap.isEmpty())
            {
                printEmpty(System.currentTimeMillis() - startTime);
                return;
            }
            
            long bigAmount = 0;
            char popularChar = ' ';
            for(char c: letterMap.keySet())
            {
                if (bigAmount < letterMap.get(c))
                {
                   bigAmount = letterMap.get(c);
                   popularChar = c;
                }
            }
            
            printPopular(popularChar, bigAmount, System.currentTimeMillis() - startTime);
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void interrupt()
    {
        this.interrupted = true;
    }
    
    private synchronized void printPopular(char c, long letterCount, long runTime)
            throws IOException
    {
        System.out.print("Most common letter '" + c + "' was found "
                        + letterCount + " time(s) in the document!\n"
                        + "Thread time: " + runTime + "\n");
    }
    
    private synchronized void printEmpty(long runTime)
            throws IOException
    {
        System.out.print("No letters in document\n"
                        + "Thread time: " + runTime + "\n");
    }
    
    private synchronized void printStart()
            throws IOException
    {
        System.out.print("Letter thread started\n");
    }
            
}
