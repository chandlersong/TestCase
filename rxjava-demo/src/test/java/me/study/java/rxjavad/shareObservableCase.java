package me.study.java.rxjavad;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class shareObservableCase {

    private RxUtils rxUtils = new RxUtils();

    @Test
    public void testShare() {
        int totalNumber = 10;
        Observable<Integer> share = Observable.create(rxUtils.SendNumber(totalNumber)).share();

        share.map(i -> i++).subscribe(i -> log.info("plus chain,{}", i));
        share.map(i -> i--).subscribe(i -> log.info("delete chain,{}", i));


    }


    @Test
    public void testPublish() {
        int totalNumber = 10;
        ConnectableObservable<Integer> publish = Observable.create(rxUtils.SendNumber(totalNumber)).map(i -> {
            log.info("origin {}", i);
            return i;
        }).publish();

        publish.map(i -> i++).subscribe(i -> log.info("plus chain,{}", i));
        publish.map(i -> i--).subscribe(i -> log.info("delete chain,{}", i));
        publish.connect();


    }
}
