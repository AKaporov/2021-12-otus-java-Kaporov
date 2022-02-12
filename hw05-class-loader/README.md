# Автоматическое логирование. Байт код, class-loader, инструментация, asm.

### Цель: Понять как реализуется AOP, какие для этого есть технические средства.

Разработайте такой функционал:
метод класса можно пометить самодельной аннотацией @Log, например, так:

class TestLogging {
  @Log
  public void calculation(int param) {};
}

При вызове этого метода "автомагически" в консоль должны логироваться значения параметров.
Например так.

class Demo {
  public void action() {
    new TestLogging().calculation(6);
  }
}

В консоле дожно быть:
executed method: calculation, param: 6

Обратите внимание: явного вызова логирования быть не должно.

Учтите, что аннотацию можно поставить, например, на такие методы:
public void calculation(int param1)
public void calculation(int param1, int param2)
public void calculation(int param1, int param2, String param3)

P.S. Выбирайте реализацию с ASM, если действительно этого хотите и уверены в своих силах.

* [GitHub - petrelevich/otus_java_2021_12](https://github.com/petrelevich/otus_java_2021_12)
* [ASM](https://asm.ow2.io/)
* [Динамический proxy](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/reflect/Proxy.html)
* [Как загружаются классы](https://docs.oracle.com/javase/specs/jvms/se10/html/jvms-5.html)
* [Youtub - Некоторые особенности SpringBoot](https://www.youtube.com/watch?v=5sfE-tR1TJ8)