# JDBC. Самодельный ORM

### Цель:
Научиться работать с jdbc. На практике освоить многоуровневую архитектуру приложения.

### Описание/Пошаговая инструкция выполнения домашнего задания:

Работа должна использовать базу данных в docker-контейнере .
В модуле homework реализуйте классы:
- EntityClassMetaData
- EntitySQLMetaData
- DataTemplateJdbc 

Метод main в классе HomeWork должен работать без ошибок.

* [H2 Database Engine](http://www.h2database.com/html/main.html)
* [HikariCP](https://github.com/brettwooldridge/HikariCP)
* [HikariCP — самый быстрый пул соединений на java](https://habr.com/ru/post/269023/)  - см. комментарии Паньгина 
* [Spring JDBC Template](https://spring.io/guides/gs/relational-data-access/)
* [The Law of Leaky Abstractions](https://www.joelonsoftware.com/2002/11/11/the-law-of-leaky-abstractions/) - Закон протекания абстракций
* 