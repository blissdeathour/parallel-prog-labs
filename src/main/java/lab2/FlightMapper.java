package lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportID, Text>{
    private static final int DEST_AIRPORT_ID_IND = 14;
    private static final int ARR_DELAY_IND = 18;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        if (key.get() == 0)
            return ;
        String  data[];
        int     destAirportID;
        String  arrDelay;

        data = value.toString().split(",");
        arrDelay = data[ARR_DELAY_IND];
        if (!arrDelay.isEmpty())
        {
            destAirportID = Integer.parseInt(data[DEST_AIRPORT_ID_IND]);

        }
    }
}
