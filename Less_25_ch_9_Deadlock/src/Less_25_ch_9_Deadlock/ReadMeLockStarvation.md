### Starvation или голодание

Помимо блокировок (deadlock и livelock) есть ещё одна проблема при работе с
многопоточностью — Starvation, или "голодание". От блокировок это явление
отличается тем, что потоки не заблокированы, а им просто не хватает ресурсов
на всех.

Например, из 5-ти созданных потоков всю работу на себя берут, только 3-и,
а остальные бездействуют (ожидают своей очереди), а тут, в ходе работы
программы еще создаются потоки с высоким приоритетом.

---
**Доп. материалы:**
- [Multithreading Concepts Part 1: Atomicity and Immutability](https://dev.to/anwaar/multithreading-key-concepts-for-engineers-part-1-4g73)
- [Multithreading Concepts Part 2: Starvation](https://dev.to/anwaar/multithreading-concepts-part-2-starvation-1abb)
- [Debug and Monitor Java App with VisualVM and jstack](https://dev.to/anwaar/debug-and-monitor-java-app-with-visualvm-and-jstack-aoa)
- [Multithreading Concepts Part 3: Deadlock](https://dev.to/anwaar/multithreading-concepts-part-3-deadlock-4ip6)

--- 
- [Starvation and Fairness (from jenkov.com)](https://jenkov.com/tutorials/java-concurrency/starvation-and-fairness.html)
- [Starvation and Livelock](https://www.geeksforgeeks.org/operating-systems/deadlock-starvation-and-livelock/)
- [Deadlock and Starvation in Java](https://www.geeksforgeeks.org/java/deadlock-starvation-java/)
- [Starvation and Livelock (The Java™ Tutorials by ORACLE)](https://docs.oracle.com/javase/tutorial/essential/concurrency/starvelive.html)
- [What is starvation?](https://stackoverflow.com/questions/1162587/what-is-starvation)
- [Understanding Deadlock, Livelock and Starvation with Code Examples in Java](https://www.codejava.net/java-core/concurrency/understanding-deadlock-livelock-and-starvation-with-code-examples-in-java)
- [Race conditions vs Deadlocks vs Resource starvation](https://medium.com/@qingedaig/race-conditions-vs-deadlocks-vs-resource-starvation-32e26b039cc2)
- [Java Thread Starvation and Livelock with Examples](https://avaldes.com/java-thread-starvation-livelock-with-examples/)
- [Java - Thread Starvation and Fairness](https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/thread-starvation.html)
