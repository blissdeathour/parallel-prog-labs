package lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportID, Text>{
    private static final int DEST_AIRPORT_ID_IND = 14;
    private static final int ARR_DELAY_IND = 
    @Override
    protected void map(LongWritable key, Text value, Context context) {

    }
}
