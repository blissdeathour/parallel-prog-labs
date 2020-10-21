package lab2;

import org.apache.hadoop.io.WritableComparable;

public class AirportID implements WritableComparable<AirportID> {
    int     airportID;
    Boolean identifier;

    AirportID() {}

    AirportID(int airportID, Boolean identifier)
    {
        this.airportID = airportID;
        this.identifier = identifier;
    }

    @Override
    public void write()
}
