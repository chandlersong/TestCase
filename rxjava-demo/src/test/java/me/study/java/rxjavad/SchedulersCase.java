package me.study.java.rxjavad;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class SchedulersCase {

    private Random r = new Random();

    @Test
    public void testSchedulesCase() throws InterruptedException {

        int totalNumber = 10;
        CountDownLatch latch = new CountDownLatch(totalNumber * 2);
        Observable.create(SendNumber(totalNumber)).
                observeOn(Schedulers.computation()).
                map(printThread(latch, "step1")).
                observeOn(Schedulers.io()).
                map(printThread(latch, "step2")).
                subscribe(System.out::println, Throwable::printStackTrace);

        latch.await();
    }

    private Function<Object, Integer> printThread(CountDownLatch latch, String step) {
        return x -> {
            int i = Integer.parseInt(x.toString());
            log.info("{} in thread {} process {}", step, Thread.currentThread().getId(), x);
            Thread.sleep(100 + r.nextInt(100));
            latch.countDown();
            return i;
        };
    }

    private ObservableOnSubscribe<Object> SendNumber(int totalNumber) {
        return emitter -> {
            while (!emitter.isDisposed()) {

                for (int i = 0; i < totalNumber; i++) {
                    emitter.onNext(i);
                    log.info("send on {} in thread {}", i, Thread.currentThread().getId());
                    Thread.sleep(100 + r.nextInt(100));
                }
                emitter.onComplete();
            }
        };
    }
}
