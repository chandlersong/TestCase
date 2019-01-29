package me.study.java.rxjavad.operator;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.study.java.rxjavad.RxUtils;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class FlatMapExamples {

    private RxUtils rxUtils = new RxUtils();

    @Test
    public void simpleFlaMap() throws InterruptedException {
        int totalNumber = 10;
        CountDownLatch latch = new CountDownLatch(totalNumber * 2);
        Observable.create(rxUtils.SendNumber(totalNumber)).
                observeOn(Schedulers.io()).
                flatMap(val -> Observable.just(val)
                        .subscribeOn(Schedulers.computation())
                        .map(rxUtils.printThread(latch, "step1"))).
                observeOn(Schedulers.io()).
                map(rxUtils.printThread(latch, "step2")).
                subscribeOn(Schedulers.computation()).
                subscribe(System.out::println, Throwable::printStackTrace);

        latch.await();
    }
}
