package me.study.java.rxjavad;

import io.reactivex.Observable;
import org.junit.Test;


public class CreateObservableCase {


    @Test
    public void testFromCallable() {
        Observable.fromCallable(() -> 1).subscribe(System.out::println);
    }
}
