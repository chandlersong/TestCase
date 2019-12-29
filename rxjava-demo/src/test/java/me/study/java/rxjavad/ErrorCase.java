package me.study.java.rxjavad;

import com.google.common.collect.Lists;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.LinkedList;


@Slf4j
public class ErrorCase {


    /**
     * it will receive 1 four times.
     * 1 is for the first time, and the last 3 is for retry
     * retry means start from first
     */
    @Test
    public void testTestRetry() {
        Disposable subscribe = Observable.fromArray(1, 2, 3)
                                         .map(this::throwError)
                                         .retry((times, throwable) -> {
                                             log.info("error is {}", throwable.getClass().getName());
                                             return times <= 3;
                                         })
                                         .subscribe(s -> log.info("receive {}", s),
                                                    (error) -> log.info("receive error:{}",
                                                                        error.getClass().getName()));

        if (subscribe.isDisposed()) {
            log.info("finish tests");
        }
    }

    /**
     * it can't ignore, because in code , the emitter has dispose
     */
    @Test
    public void testTestRetry2() {
        Disposable subscribe = Observable.create(createEmitter())
                                         .map(this::throwError)
                                         .retry((times, throwable) -> {
                                             log.info("retry receive error is {}", throwable.getClass().getName());
                                             return times <= 3;
                                         })
                                         .subscribe(s -> log.info("receive {}", s),
                                                    (error) -> log.info("subscribe receive error:{}",
                                                                        error.getClass().getName()));

        if (subscribe.isDisposed()) {
            log.info("finish tests");
        }
    }

    /**
     * ResumeNext. means resume next Observable
     */
    @Test
    public void testTestResumeOnNext() {
        Disposable subscribe = Observable.fromArray(1, 2, 3)
                                         .map(this::throwError)
                                         .onErrorResumeNext((throwable) -> {
                                             log.info("error is {}", throwable.getClass().getName());
                                             return Observable.fromArray("6", "7", "8");
                                         })
                                         .subscribe(s -> log.info("receive {}", s),
                                                    (error) -> log.info("receive error:{}",
                                                                        error.getClass().getName()));

        if (subscribe.isDisposed()) {
            log.info("finish tests");
        }
    }

    @Test
    public void testIgnoreError1() {
        Disposable subscribe = createObservable(createEmitter())
                .subscribe(s -> log.info("receive {}", s),
                           (error) -> log.info("subscribe receive error:{}", error.getClass().getName()));

        if (subscribe.isDisposed()) {
            log.info("finish tests");
        }
    }

    private ObservableOnSubscribe<Integer> createEmitter() {
        LinkedList<Integer> data = Lists.newLinkedList();
        data.add(1);
        data.add(2);
        data.add(3);

        return emitter -> {
            while (!data.isEmpty()) {
                Thread.sleep(2 * 1000);
                emitter.onNext(data.poll());
            }
            emitter.onComplete();
        };
    }

    private Observable<String> createObservable(ObservableOnSubscribe<Integer> e) {
        return Observable.create(e)
                         .map(this::throwError)
                         .onErrorResumeNext(t -> {
                             log.info("OnErrorResumeNext receive error:{}", t.getClass().getName());
                             return createObservable(e);
                         });
    }


    @Test
    public void testIgnoreError2() {
        Disposable subscribe = Observable.create(createEmitter())
                                         .flatMap(i -> Observable.just(i)
                                                                 .map(this::throwError)
                                                                 .onErrorResumeNext(t -> {
                                                                                        log.info("error is: {}", t.getClass().getName());
                                                                                        return Observable.just("error round");  //it can return Observable.empty()
                                                                                    }
                                                                 ))
                                         .subscribe(s -> log.info("receive {}", s),
                                                    (error) -> log.info("receive error:{}",
                                                                        error.getClass().getName()));

        if (subscribe.isDisposed()) {
            log.info("finish tests");
        }
    }

    private String throwError(Integer value) {
        if (value == 2) {
            throw new IllegalArgumentException();
        }
        return String.valueOf(value);
    }
}
