package lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<AirportID, Text> {
    @Override
    public int getPartition(AirportID obj, Text text, int number) {
        return ((Integer.hashCode(obj.airportID) & Integer.MAX_VALUE) % number);
    }
}
