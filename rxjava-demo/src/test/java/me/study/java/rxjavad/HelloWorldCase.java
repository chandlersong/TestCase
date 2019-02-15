package me.study.java.rxjavad;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class HelloWorldCase {

    private RxUtils rxUtils = new RxUtils();

    @Test
    public void helloWorld() {
        Flowable.just("Hello world").subscribe(System.out::println);

        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);

        Disposable subscribe = flow.subscribe(System.out::println);
        System.out.println(subscribe.isDisposed());
    }

    @Test
    public void runTime() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void backgroundExecute() throws InterruptedException {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("main start to sleep");
        Thread.sleep(2000);
        System.out.println("main start to sleep");
    }


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
}
