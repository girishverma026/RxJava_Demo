package com.rxjava.demo.measurement;

/**
 * Created by girishv on 16/11/17.
 */
public interface Unit {

    String getType();
    int getBaseValue();

    default boolean canAdd(Unit unit){
        return this.getType() == unit.getType();
    }

    default Measurement convertTo( double value, Unit unit ) {
        if(!( this.getType() == unit.getType()) )
            throw new RuntimeException( "invalid operation" );
        return new Measurement(value*this.getBaseValue()/unit.getBaseValue(), unit);
    }

    default Measurement add( double value1, Measurement measurement) {
        if ( !this.canAdd( measurement.unit ) )
            throw new RuntimeException( "invalid operation" );
        double sum = value1 * this.getBaseValue() + measurement.value * measurement.unit.getBaseValue();
        double conversion = sum / getBaseValue();
        return new Measurement( conversion,  this);
    }

    enum Length implements Unit{
        INCH(1), FOOT(12);
        int baseValue;
        Length( int baseValue ) {
            this.baseValue = baseValue;
        }

         @Override
         public String getType() {
             return "Length";
         }

         @Override
         public int getBaseValue() {
             return this.baseValue;
         }

     }

    enum Volume implements Unit{
        MILLILITRE(1), LITRE(1000);

        private int baseValue;

        Volume( int baseValue ) {
            this.baseValue = baseValue;
        }

        @Override
        public String getType() {
            return "Volume";
        }

        @Override
        public int getBaseValue() {
            return this.baseValue;
        }
    }

    enum Temperature implements  Unit{
        Celsius, Fahrenheit;

        @Override
        public String getType() {
            return "Temperature";
        }

        @Override
        public int getBaseValue() {
            throw new RuntimeException( "not supported" );
        }

        @Override
        public boolean canAdd( Unit unit ) {
            return false;
        }

        @Override
        public Measurement convertTo( double value, Unit unit ) {
            if(unit.getType() != "Temperature") {
                throw new RuntimeException( "invalid operation" );
            }

            if( this == Celsius) {
                return new Measurement( value*1.8 + 32, Fahrenheit );
            } else {
                return new Measurement( (value- 32)/1.8, Celsius );
            }
        }

        @Override
        public Measurement add( double value1, Measurement measurement ) {
            throw new RuntimeException( "invalid operation" );
        }
    }
}
