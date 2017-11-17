package com.rxjava.demo;


import rx.Observable;

import java.util.UUID;

public class MultipleSubscribers {

    public static void main( String[] args ) throws InterruptedException {
        Observable<String> feed = getFeed();
        new Thread(() -> {
            feed.subscribe( (data) -> {
                System.out.println("subscriber 1   " + data);
            }, (err) -> System.out.println(err), () -> System.out.println("i am done 1") );
        }).start();

        feed.subscribe( (data) -> {
            System.out.println("subscriber 2   " + data);
        }, (err) -> System.out.println(err), () -> System.out.println("i am done 2") );

        System.out.println("i am ending main");
    }

    private static Observable<String> getFeed() {
        Observable<String> feed = Observable.create( subscriber -> {
            int count = 0;
            while(true) {
                String randomString = UUID.randomUUID().toString();
                subscriber.onNext( randomString );
                sleep();
                count++;
                System.out.println(count);
//                subscriber.onCompleted();
            }
        } );
        return feed;
    }

    private static void sleep() {
        try {
            System.out.println("sleeping");
            Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

}
