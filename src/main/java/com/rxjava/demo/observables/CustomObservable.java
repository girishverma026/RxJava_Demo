package com.rxjava.demo.observables;

import com.rxjava.demo.subscriber.CustomSubscriber;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CustomObservable {
    private static List<String> myData = Arrays.asList( "Apple", "Orange", "Banana");

    private CustomObservable() {}

    public static CustomObservable create( CustomSubscriber subscriber ) {
        while(true) {
            int randomIndex = ThreadLocalRandom.current().nextInt( 0, 2 + 1 );
            subscriber.onNext( myData.get( randomIndex ) );
        }
//        return null;
    }

    public static CustomObservable subscribe() {
        return null;
    }
}
