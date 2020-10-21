package lab2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    AirportGroupingComparator() {
        super(AirportID.class, true);
    }

    @Override
    public int compare(WritableComparator key1, WritableComparator key2) {
        AirportID k = key1;
        return (Integer.compare(((AirportID)key1).airportID, ((AirportID)key2).airportID));
    }
}