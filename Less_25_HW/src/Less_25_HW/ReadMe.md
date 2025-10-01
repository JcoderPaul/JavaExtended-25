1. [Less_25_HW_1](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/Less_25_HW_1.java) - Написать программу, бесконечно считывающую пользовательские числа
   из консоли. Эти числа представляют собой количество секунд - N. При
   каждом вводе числа, должна создаваться задача, которая засыпает
   на введённое число секунд и затем выводит "Я спал N секунд".
   Однако нужно сделать так, чтобы все задачи выполнялись в одном и том
   же потоке в порядке ввода. Т.е. в программе есть 2 потока: главный main
   и поток для выполнения всех задач по очереди.
   При вводе отрицательного числа программа должна завершать свою работу.
2. [Less_25_HW_2](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/Less_25_HW_2.java) - Написать программу, бесконечно считывающую пользовательские числа
   из консоли. Эти числа представляют собой количество секунд - N. При
   каждом вводе числа, должна создаваться задача, которая засыпает
   на введённое число секунд и затем выводит "Я спал N секунд".
   При вводе отрицательного числа программа должна завершать свою работу.
   - Все задачи должны выполняться в нескольких потоках (в пуле потоков)
   - Посчитать кол-во обработанных задач каждым потоком.
3. Задан массив случайных целых чисел
   (от 1 до 300) длинной 1 млн элементов.
   Найти максимальный элемент в массиве двумя способами:
   - в одном потоке;
   - при помощи 10 потоков;
   - сравнить затраченное в обоих случаях время.

   Данная задача решается по шагам:
   - [HW_3_Step1](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/Less_25_HW_3_Step1.java) - Задача решается сквозным кодом, кроме статического
     метода *.getArrayOfRandomElement().
     Комментарии расписывают, что происходит.
     Для понимания и повторения (см. [InterfaceSupplier](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/InterfaceSupplier.txt), [InterfaceIntStream](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/InterfaceIntStream.txt), [FutureCallable](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/FutureCallable.txt), [ClassOptional](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/ClassOptional.txt))
   - [HW_3_Step2](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/Less_25_HW_3_Step2.java) - Попытка оптимизировать предыдущий код, разделив отдельные операции на классы и методы.
   - [HW_3_Step3](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/Less_25_HW_3_Step3.java) - Повторная оптимизация с применением интерфейса [Supplier](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/InterfaceSupplier.txt).

Русифицированная документация по применяемым классам и методам JAVA:
- [ThreadLocal](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/ThreadLocal.md)
- [InterfaceSupplier](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/InterfaceSupplier.txt)
- [InterfaceIntStream](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/InterfaceIntStream.txt)
- [FutureCallable](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/FutureCallable.txt)
- [ClassOptional](https://github.com/JcoderPaul/JavaExtended-25/blob/master/Less_25_HW/src/Less_25_HW/ClassOptional.txt)
