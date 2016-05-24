package threads;
/**
 * @author Laimonas Juras
 */
public class PiCalc implements Runnable, Interruptable
{
    private boolean interrupted = false;
    double precision;
        
    PiCalc(double precision)
    {
        this.precision = precision;
    }
    
    @Override
    public synchronized void run()
    {
        long startTime = System.currentTimeMillis();
        System.out.println("Pi calculation started");
        double sum = 0;
        for(double i = 1; i < precision && !interrupted; i++)
        {
            if(i%2 == 0) // if the remainder of `i/2` is 0
                sum += -1 / ( 2 * i - 1);
            else
                sum += 1 / (2 * i - 1);
        }
        System.out.println("Pi = " + (sum * 4));
        System.out.println("Thread time: " + (System.currentTimeMillis() - startTime));
        
    }
    
    @Override
    public void interrupt()
    {
        this.interrupted = true;
    }
}
