
import java.util.concurrent.atomic.AtomicReference;

public class CLHLock implements Lock
{
    private static class Node
    {
        volatile boolean locked;
    }

    private AtomicReference<Node> tail =
        new AtomicReference<Node>(new Node());

    private ThreadLocal<Node> myNode =
        new ThreadLocal<Node>();

    private ThreadLocal<Node> myPred =
        new ThreadLocal<Node>();

    public void lock()
    {
        Node node = new Node();
        node.locked = true;

        myNode.set(node);

        Node pred = tail.getAndSet(node);
        myPred.set(pred);

        while(pred.locked)
        {
        }
    }

    public void unlock()
    {
        Node node = myNode.get();
        node.locked = false;
        myNode.set(myPred.get());
    }
}
