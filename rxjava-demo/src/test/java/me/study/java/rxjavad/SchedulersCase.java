package me.study.java.rxjavad;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class SchedulersCase {

    @Test
    public void testSchedulesCase() throws InterruptedException {

        int totalNumber = 10;
        CountDownLatch latch = new CountDownLatch(totalNumber*2);
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {

                for (int i = 0; i < totalNumber; i++) {
                    emitter.onNext(i);
                    log.info("send on {} in thread {}", i, Thread.currentThread().getId());
                    Thread.sleep(100);
                }
                emitter.onComplete();
            }
        }).observeOn(Schedulers.io()).map(x -> {
            Integer i = Integer.parseInt(x.toString());
            log.info("first step {} in thread {}", x, Thread.currentThread().getId());
            Thread.sleep(100);
            latch.countDown();
            return i * i;
        }).observeOn(Schedulers.io()).map(i -> {
            log.info("second step {} in thread {}", i, Thread.currentThread().getId());
            Thread.sleep(100);
            latch.countDown();
            return i;
        }).subscribe(System.out::println, Throwable::printStackTrace);

        latch.await();
    }
}
