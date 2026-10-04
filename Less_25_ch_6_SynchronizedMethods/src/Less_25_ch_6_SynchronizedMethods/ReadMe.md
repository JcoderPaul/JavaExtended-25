'Race condition' и 'data race' — две разные проблемы многопоточности, которые часто путают.

---

### Race condition

Существует много формулировок определения:
- **Race condition** представляет собой класс проблем, в которых корректное поведение системы
  зависит от двух независимых событий, происходящих в правильном порядке, однако отсутствует
  механизм, для того чтобы гарантировать фактическое возникновение этих событий.

- **Race condition** — ошибка проектирования многопоточной системы или приложения, при которой
  работа системы или приложения зависит от того, в каком порядке выполняются части кода.

- **Race condition** — это нежелательная ситуация, которая возникает, когда устройство или система
  пытается выполнить две или более операций одновременно, но из-за природы устройства или системы,
  операции должны выполняться в правильной последовательности, чтобы быть выполненными правильно.

- **Race condition** — это недостаток, связанный с синхронизацией или упорядочением событий, что приводит
  к ошибочному поведению программы.

- **Race condition** - 'состояние гонки' возникает, когда один и тот же ресурс используется несколькими
  потоками одновременно, и в зависимости от порядка действий каждого потока может быть несколько
  возможных результатов.

И наконец наиболее короткое и простое:

- **Race condition** — это недостаток, возникающий, когда время или порядок событий влияют на
  правильность выполнения программы.

Проблемы с доступом к общим ресурсам проще обнаружить автоматически и решаются они обычно с
помощью синхронизации.

---
**Доп. материал:**
- [Небезопасная многопоточность или Race Condition](https://habr.com/ru/articles/764234/)
- [Состояние гонки (from Wiki)](https://ru.wikipedia.org/wiki/%D0%A1%D0%BE%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5_%D0%B3%D0%BE%D0%BD%D0%BA%D0%B8)
- [Что такое состояние гонки (гонка данных, race condition)](https://apptractor.ru/info/articles/chto-takoe-sostoyanie-gonki-race-condition.html)
- [Состоянии гонки (Race condition) на примере счетчика](https://www.kobzarev.com/programming/race-condition/)
- [Многопоточность. Race condition](https://rwsite.ru/race-condition/)
- [Как избежать состояния гонки (race condition)?](https://easyadvice.ru/questions/frontend/how_to_avoid_race_condition/)
- [Быстрее улитки или Race Conditions в Websocket-ах](https://habr.com/ru/articles/763488/)

---
### Data Race

**Data race** - это состояние когда разные потоки обращаются к одной ячейке памяти без какой-либо
синхронизации и как минимум один из потоков осуществляет запись.

**Data race** - 'Гонка данных' возникает, когда два или более потока пытаются получить доступ к одной
и той же не финальной переменной без синхронизации. Отсутствие синхронизации может привести к внесению
изменений, которые не будут видны другим потокам, из-за этого возможно чтение устаревших данных, что,
в свою очередь, приводит к бесконечным циклам, поврежденным структурам данных или неточным вычислениям.

**Решив Data Race** через синхронизацию доступа к памяти (блокировки) **не всегда решается race condition
и logical correctness**.

---
**Доп. материал:**
- [Разница между Data Race и Race Condition](https://habr.com/ru/articles/760434/)
- [Race condition и Data Race](https://medium.com/german-gorelkin/race-8936927dba20)
- [Гонка данных (from GitHub)](https://github.com/comerc/yandex/blob/main/DATA_RACE.md)

--- 
- [Java Thread Programming (Part 1)](https://foojay.io/today/java-thread-programming-part-1/)
- [Java Thread Programming (Part 2)](https://foojay.io/today/java-thread-programming-part-2/)
- [Java Thread Programming (Part 3)](https://foojay.io/today/java-thread-programming-part-3/)
- [Java Thread Programming (Part 4)](https://foojay.io/today/java-thread-programming-part-4/)
- [Java Thread Programming (Part 5)](https://foojay.io/today/java-thread-programming-part-5/)
- [Java Thread Programming (Part 6)](https://foojay.io/today/java-thread-programming-part-6/)
- [Java Thread Programming (Part 7)](https://foojay.io/today/java-thread-programming-part-7/)
- [Java Thread Programming (Part 8)](https://foojay.io/today/java-thread-programming-part-8/)
- [Java Thread Programming (Part 9)](https://foojay.io/today/java-thread-programming-part-9/)
- [Java Thread Programming (Part 10)](https://foojay.io/today/java-thread-programming-part-10/)
- [Java Thread Programming (Part 11)](https://foojay.io/today/java-thread-programming-part-11/)
- [Java Thread Programming (Part 12)](https://foojay.io/today/java-thread-programming-part-12/)
- [Java Thread Programming (Part 13)](https://foojay.io/today/java-thread-programming-part-13/)
- [Java Thread Programming (Part 14)](https://foojay.io/today/java-thread-programming-part-14/)
- [Java Thread Programming (Part 15)](https://foojay.io/today/java-thread-programming-part-15/)
- [Thread-Safe Counter in Java: A Comprehensive Guide](https://foojay.io/today/thread-safe-counter-in-java-a-comprehensive-guide/)
