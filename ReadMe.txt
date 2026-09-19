Running and compiling the files:
javac *.java
java Main

Members:
CC (Cameron) Ambraal
VLV (Vimbai) Chivunga
T (Taya) Govender

The results show that at low contention (2 threads) all three locks perform similarly, but as thread count increases to 16, TTAS degrades noticeably while CLH and MCS scale well. TTAS suffers from the thundering herd problem, on release, all spinning threads hit the same memory location at once, causing cache-coherence traffic, whereas CLH and MCS have each thread spin on its own node so only the next thread is woken. Every configuration produced the expected number of bids and a consistent final highest bid, confirming mutual exclusion held correctly. Fairness differed clearly: TTAS showed a larger spread in bids won per bidder because a releasing thread can immediately re-acquire the lock, while CLH and MCS are FIFO-fair, producing an almost even distribution. The additional measurement, maximum wait time, reinforced this, with TTAS showing tail latencies far above its average wait, while CLH and MCS kept max waits close to their averages. In conclusion, TTAS is adequate under light contention but scales poorly, while CLH and MCS are superior under heavy contention due to their queue-based design, which eliminates global cache-line contention and guarantees fairness.