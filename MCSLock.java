import java.util.concurrent.atomic.AtomicReference;

public class MCSLock implements Lock
{
    private static class Node
    {
        volatile boolean locked;
        volatile Node next;
    }

    private final AtomicReference<Node> tail =
        new AtomicReference<Node>(null);

    private final ThreadLocal<Node> myNode = new ThreadLocal<Node>()
    {
        protected Node initialValue()
        {
            return new Node();
        }
    };

    public void lock()
    {
        Node node = myNode.get();
        node.locked = true;
        node.next = null;

        Node pred = tail.getAndSet(node);

        if(pred != null)
        {
            pred.next = node;

            while(node.locked)
            {
            }
        }
    }

    public void unlock()
    {
        Node node = myNode.get();

        if(node.next == null)
        {
            if(tail.compareAndSet(node, null))
            {
                return;
            }

            while(node.next == null)
            {
            }
        }

        node.next.locked = false;
        node.next = null;
    }
}