import java.util.concurrent.atomic.AtomicBoolean;

public class TTASLock implements Lock
{
    private final AtomicBoolean locked = new AtomicBoolean(false);

    public void lock()
    {
        while(true)
        {
            while(locked.get())
            {
            }

            if(!locked.getAndSet(true))
            {
                return;
            }
        }
    }

    public void unlock()
    {
        locked.set(false);
    }
}