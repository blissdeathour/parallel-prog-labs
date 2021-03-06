package lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportID, Text>{
    private static final int DEST_AIRPORT_ID_IND = 14;
    private static final int ARR_DELAY_IND = 18;
    private static final String FLIGHT_DELIMITER = ",";
    private static final boolean FLIGHT_FLAG = false;
    private static final int ARR_DELAY_CONST = 0;

    private static String[] flightSplitter(Text value) {
        return (value.toString().split(FLIGHT_DELIMITER));
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String          data[];
        int             destAirportID;
        float           arrDelay;

        if (key.get() == 0) {
            return;
        }
        data = flightSplitter(value);
        arrDelay = ARR_DELAY_CONST;
        if (!data[ARR_DELAY_IND].isEmpty())
            arrDelay = Float.parseFloat(data[ARR_DELAY_IND]);
        if (arrDelay > ARR_DELAY_CONST)
        {
            destAirportID = Integer.parseInt(data[DEST_AIRPORT_ID_IND]);
            context.write(new AirportID(destAirportID, FLIGHT_FLAG), new Text(Float.toString(arrDelay)));
        }
    }
}
