package lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<AirportID, Text> {
    @Override
    public int getPartition(AirportID key, Text value, int numPart) {
        return ((Integer.hashCode(key.airportID) & Integer.MAX_VALUE) % numPart);
    }
}
