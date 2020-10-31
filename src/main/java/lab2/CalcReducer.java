package lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class CalcReducer extends Reducer<AirportID, Text, Text, Text> {
    private static final int CONST_AVG = 0;
    private static final int CONST_COUNT = 1;
    @Override
    protected void reduce(AirportID key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        Iterator<Text>  iterator;
        String          output;
        float           minVal;
        float           maxVal;
        float           avg;
        int             count;

        iterator = values.iterator();
        minVal = Integer.MAX_VALUE;
        maxVal = Integer.MIN_VALUE;
        output = "Name: " + iterator.next().toString() + ", ";
        if (!iterator.hasNext()) {
            return;
        }
        avg = CONST_AVG;
        count = CONST_COUNT;
        while (iterator.hasNext()) {
            float val = Float.parseFloat(iterator.next().toString());
            minVal = Math.min(val, minVal);
            maxVal = Math.max(val, maxVal);
            avg = (avg * count + val) / ++count;
        }
        output += String.format("Min: %f, Max: %f, Avg: %f", minVal, maxVal, avg);
        context.write(new Text(Integer.toString(key.airportID)), new Text(output));
    }
}
