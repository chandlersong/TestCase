package me.study.java.rxjavad;

import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class RxUtils {

    private Random r = new Random();

    public Function<Object, Integer> printThread(CountDownLatch latch, String step) {
        return x -> {
            int i = Integer.parseInt(x.toString());
            log.info("{} in thread {} process {}", step, Thread.currentThread().getId(), x);
            Thread.sleep(1000 + r.nextInt(500));
            latch.countDown();
            return i;
        };
    }

    public ObservableOnSubscribe<Integer> SendNumber(int totalNumber) {
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
