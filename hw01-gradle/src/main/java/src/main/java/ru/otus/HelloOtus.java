package src.main.java.ru.otus;

import com.google.common.base.Joiner;
import org.checkerframework.checker.nullness.qual.Nullable;

import javax.annotation.CheckForNull;

public class HelloOtus {

    public String joinerSkipNullsGuava(@CheckForNull Object first, @CheckForNull Object second, @Nullable Object... rest) {
        Joiner joiner = Joiner.on(" - ").skipNulls();
        return joiner.join(first, second, rest);
    }

}
