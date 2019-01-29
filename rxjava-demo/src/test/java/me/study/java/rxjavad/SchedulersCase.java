package me.study.java.rxjavad;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class SchedulersCase {


    private RxUtils rxUtils = new RxUtils();
    @Test
    public void testSchedulesCase() throws InterruptedException {

        int totalNumber = 10;
        CountDownLatch latch = new CountDownLatch(totalNumber * 2);
        Observable.create(rxUtils.SendNumber(totalNumber)).
                observeOn(Schedulers.computation()).
                map(rxUtils.printThread(latch, "step1")).
                observeOn(Schedulers.io()).
                map(rxUtils.printThread(latch, "step2")).
                subscribeOn(Schedulers.computation()).
                subscribe(System.out::println, Throwable::printStackTrace);

        latch.await();
    }

    @Test
    public void testOperatorConcurrent() throws InterruptedException {

        int totalNumber = 10;
        CountDownLatch latch = new CountDownLatch(totalNumber * 2);
        Observable.create(rxUtils.SendNumber(totalNumber)).
                observeOn(Schedulers.io()).
                flatMap(val -> Observable.just(val)
                        .subscribeOn(Schedulers.computation())
                        .map(rxUtils.printThread(latch, "step1"))).
                subscribeOn(Schedulers.computation()).
                subscribe(System.out::println, Throwable::printStackTrace);

        latch.await();
    }


}
