package src.main.java.ru.otus;

public class ApplicationHW01 {
    public static void main(String[] args) {
        HelloOtus helloOtus = new HelloOtus();

        System.out.println(helloOtus.joinerSkipNullsGuava("Harry", null, "Ron", "Hermione"));
    }
}
