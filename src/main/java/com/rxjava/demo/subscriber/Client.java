package com.rxjava.demo.subscriber;

import com.rxjava.demo.observables.CustomObservable;

public class Client {

    public static void main( String[] args ) {
        CustomObservable customObservable = CustomObservable.create( new CustomSubscriber() );
        customObservable.subscribe();
    }

}
