### Livelock или Активная (живая) блокировка

Livelock — это рекурсивная ситуация, когда два или более потока продолжают
бесконечно повторять определенную логику кода. Предполагаемая логика обычно
дает возможность другим потокам продолжить работу в пользу потока оппонента.

Жизненный пример 'живой блокировки' происходит, когда два человека встречаются в
узком коридоре (на очень узком мосту), и каждый пытается быть вежливым, отодвигаясь
в сторону (или возвращаясь в начало моста), чтобы пропустить другого, но в конечном
итоге они раскачиваются (мотаются) из стороны в сторону, не добиваясь никакого
прогресса, потому что они оба постоянно перемещаются туда-сюда, но не достигают
противоположного конца коридора (моста).

Т.е. потоки живы, что-то делают, но работа одного (нескольких) перечеркивается
работой другого (других), все повторяется сначала (продолжается не достигнув
должного результата), и программа не завершается.

---
**Доп. материал:**
- [Java Thread Deadlock and Livelock](https://www.baeldung.com/java-deadlock-livelock)
- [Good example of livelock?](https://stackoverflow.com/questions/1036364/good-example-of-livelock)
- [Thread Livelock](https://codingnomads.com/java-thread-livelock)
- [Deadlock and Livelock](https://javatrainingschool.com/deadlock-and-livelock/)
- [Starvation and Livelock](https://www.geeksforgeeks.org/operating-systems/deadlock-starvation-and-livelock/)
- [Java Concurrency: A Deep Dive into Multithreading and Best Practices](https://wslisam.medium.com/java-concurrency-a-deep-dive-into-multithreading-and-best-practices-6d7b54f0c89b)
- [CAS, Deadlock, Livelock & Starvation: The Concurrency Concepts That Actually Matter in Real Systems.](https://blog.stackademic.com/cas-deadlock-livelock-starvation-the-concurrency-concepts-that-actually-matter-in-real-systems-137fcb44b863)
- [Livelock in Java Multi-Threading With Example](https://www.netjstech.com/2017/12/livelock-in-java-multi-threading.html)
- [Multithreading & Concurrency in Java: One-Stop Solution for Interviews](https://dev.to/devcorner/multithreading-concurrency-in-java-one-stop-solution-for-interviews-1fkn)
- [Deadlock in Java: Examples, Detection, and Prevention](https://www.digitalocean.com/community/tutorials/deadlock-in-java-example)
- [Java - Thread Livelock](https://www.logicbig.com/tutorials/core-java-tutorial/java-multi-threading/thread-livelock.html)
- [Livelock](https://algomaster.io/learn/concurrency-interview/livelock)
