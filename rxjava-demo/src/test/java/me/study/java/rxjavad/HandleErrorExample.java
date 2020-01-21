package me.study.java.rxjavad;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HandleErrorExample {

    private RxUtils rxUtils = new RxUtils();

    @Test
    public void testThrowException() throws InterruptedException {

        int totalNumber = 10;
        final CountDownLatch latch = new CountDownLatch(totalNumber);
        Observable.create(rxUtils.SendNumber(totalNumber)).
                observeOn(Schedulers.computation()).
                map(i -> {
                    Integer value = Integer.valueOf(i.toString());
                    latch.countDown();
                    if ((value % 2) == 0) {
                        throw new IllegalAccessException();
                    }

                    return value;
                }).
                observeOn(Schedulers.io()).
                subscribeOn(Schedulers.computation()).
                subscribe(System.out::println, Throwable::printStackTrace);

        latch.await(2, TimeUnit.SECONDS);
        log.info("latch size:{}", latch.getCount());
    }


    @Test
    public void testNotBreakProcess() throws InterruptedException {

        int totalNumber = 10;
        final CountDownLatch latch = new CountDownLatch(totalNumber);
        ObservableOnSubscribe<Integer> source = rxUtils.SendNumber(totalNumber);
        Observable<Integer> map = Observable.create(source).
                observeOn(Schedulers.computation()).
                map(i -> {
                    log.info("mapping step one {}", i);
                    Integer value = Integer.valueOf(i.toString());
                    latch.countDown();
                    if (value == 5) {
                        throw new IllegalAccessException();
                    }

                    return value;
                });

        map.onErrorResumeNext(t -> {
            log.info("error item,{}", t);
            return map;
        }).map(i -> {
            log.info("mapping step two:{}", i);
            return i;
        }).
                observeOn(Schedulers.io()).
                subscribeOn(Schedulers.computation()).subscribe(System.out::println, Throwable::printStackTrace);

        Thread.sleep(5 * 1000);
        log.info("latch size:{}", latch.getCount());
    }
}
