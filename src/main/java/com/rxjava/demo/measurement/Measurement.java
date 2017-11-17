package com.rxjava.demo.measurement;

/**
 * Created by girishv on 16/11/17.
 */
public class Measurement {

    double value;
    Unit unit;

    public Measurement( double value, Unit unit ) {
        this.value = value;
        this.unit = unit;
    }

    public static void main( String[] args ) {
        Measurement measurement1 = new Measurement(5, Unit.Length.INCH);
        Measurement measurement2 = new Measurement(10, Unit.Length.FOOT);

        Measurement measurement3 = new Measurement( 40, Unit.Temperature.Celsius );

        System.out.println(measurement1.add(measurement2));

        System.out.println(measurement2.convert(Unit.Length.INCH));

        System.out.println(measurement3.convert( Unit.Temperature.Fahrenheit ));

        System.out.println(measurement3.convert( Unit.Temperature.Fahrenheit ));
    }

    private Measurement convert( Unit unit ) {
        return this.unit.convertTo( this.value, unit );
    }

    private Measurement add( Measurement measurement ) {
        return this.unit.add( this.value, measurement );
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
