public class Main 
{

    public static void main(String[] args) throws InterruptedException 
    {
        int numberOfThreads = 0; /*Change*/
        int iterations = 200;

        System.out.println("=== TTAS Lock ===");

        new Runner(numberOfThreads, iterations, new Auction(AuctionUtils.generateItemName()), new TTASLock()).run();

        System.out.println();

        System.out.println("=== CLH Lock ===");

        new Runner(numberOfThreads, iterations, new Auction(AuctionUtils.generateItemName()), new CLHLock()).run();

        System.out.println();

        System.out.println("=== MCS Lock ===");

        new Runner(numberOfThreads, iterations, new Auction(AuctionUtils.generateItemName()), new MCSLock()).run();
    }
}