package me.study.java;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.Test;

public class ConstructorsDemo {


    @Test
    public void testConstructor() {
        new AllConstructorsEntry();
        new AllConstructorsEntry("aa", "bb", "cc");
        AllConstructorsEntry.of("cc");

    }
}

@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@AllArgsConstructor
class AllConstructorsEntry {

    private String value;

    private String name;

    @NonNull
    private String notNull;
}
