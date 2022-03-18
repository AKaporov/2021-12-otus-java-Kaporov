# Определение нужного размера хипа. Сборщик мусора (JVM Garbage Collectors).

### Цель: на примере простого приложения понять какое влияние оказывают сборщики мусора.

Описание/Пошаговая инструкция выполнения домашнего задания:
1) Есть готовое приложение (модуль homework). Запустите его с размером хипа 256 Мб и посмотрите в логе время выполнения:
   - Увеличьте размер хипа до 2Гб, замерьте время выполнения.
   - Результаты запусков записывайте в таблицу.
   - Определите оптимальный размер хипа, т.е. размер, превышение которого, не приводит к сокращению времени выполнения приложения.
2) Оптимизируйте работу приложения:
   - т.е. не меняя логики работы (но изменяя код), сделайте так, чтобы приложение работало быстро с минимальным хипом.
   Повторите измерения времени выполнения программы для тех же значений размера хипа.

Пример вывода:
- spend msec:18284, sec:18

* [Классический доклад Владимира Иванова — G1 Garbage Collector](https://www.youtube.com/watch?v=iGRfyhE02lA)
* [VisualVM - программа для мониторинга (начиная с java 9 не входит в jdk)](https://visualvm.github.io/download.html)
* [О формате логов](http://openjdk.java.net/jeps/158)

---
###Результаты запусков
Размеры heap при запуске (до оптимизации): 
1. 256  Mb:
   1. Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
   2. Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
   3. Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
2. 512  Mb:
   1. spend msec:24977, sec:24
   2. spend msec:20862, sec:20
   3. spend msec:22185, sec:22
   ####Среднее время работы = spend msec:22674, sec:22
3. 768  Mb:
   1. spend msec:19031, sec:19
   2. spend msec:18191, sec:18
   3. spend msec:18513, sec:18
   ####Среднее время работы = spend msec:18578, sec:18.3
4. 1024 Mb:
   1. spend msec:17876, sec:17
   2. spend msec:18075, sec:18
   3. spend msec:17911, sec:17
   ####Среднее время работы = spend msec:17954, sec:17.3
5. 1280 Mb:
   1. spend msec:16625, sec:16
   2. spend msec:17117, sec:17
   3. spend msec:17010, sec:17
   ####Среднее время работы = spend msec:16917, sec:16.6
6. 1536 Mb:
   1. spend msec:16825, sec:16
   2. spend msec:16579, sec:16
   3. spend msec:15835, sec:15
   ####Среднее время работы = spend msec:16413, sec:15.6
7. 1792 Mb:
   1. spend msec:14469, sec:14
   2. spend msec:15225, sec:15
   3. spend msec:16537, sec:16
   ####Среднее время работы = spend msec:15410, sec:15
8. 2048 Mb:
   1. spend msec:16032, sec:16
   2. spend msec:15084, sec:15
   3. spend msec:16590, sec:16
   ####Среднее время работы = spend msec:15902, sec:15.6

Размеры heap при запуске (после оптимизации):
1. 256  Mb:
   1. spend msec:6505, sec:6
   2. spend msec:6082, sec:6
   3. spend msec:6921, sec:6
   ####Среднее время работы = spend msec:6502, sec:6
2. 512  Mb:
   1. spend msec:4842, sec:4
   2. spend msec:4824, sec:4
   3. spend msec:4738, sec:4
   ####Среднее время работы = spend msec:4801, sec:4
3. 768  Mb:
   1. spend msec:4556, sec:4
   2. spend msec:4587, sec:4
   3. spend msec:4630, sec:4
   ####Среднее время работы = spend msec:4591, sec:4
4. 1024 Mb:
   1. spend msec:4533, sec:4
   2. spend msec:4841, sec:4
   3. spend msec:4232, sec:4
   ####Среднее время работы = spend msec:4535, sec:4
5. 1280 Mb:
   1. spend msec:6493, sec:6
   2. spend msec:5284, sec:5
   3. spend msec:5090, sec:5
   ####Среднее время работы = spend msec:5622, sec:5.3
6. 1536 Mb:
   1. spend msec:4734, sec:4
   2. spend msec:4508, sec:4
   3. spend msec:4799, sec:4
   ####Среднее время работы = spend msec:4680, sec:4
7. 1792 Mb:
   1. spend msec:4614, sec:4
   2. spend msec:4927, sec:4
   3. spend msec:4708, sec:4
   ####Среднее время работы = spend msec:4749, sec:4
8. 2048 Mb:
   1. spend msec:5252, sec:5
   2. spend msec:4716, sec:4
   3. spend msec:4816, sec:4
   ####Среднее время работы = spend msec:4928, sec:4.3
