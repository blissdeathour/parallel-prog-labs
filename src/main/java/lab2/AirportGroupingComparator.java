package lab2;

import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    AirportGroupingComparator() {
        super(AirportID.class, true);
    }

    @Override
    public int compare(WritableComparator )
}
