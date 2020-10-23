package lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class CalcReducer extends Reducer<AirportID, Text, Text, Text> {
    @Override
    protected void reduce(AirportID key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        Iterator<Text>  iterator;
        String          output = "";
        float           minVal;
        float           maxVal;
        float           avg;
        int             count;

        iterator = values.iterator();
        minVal = Integer.MAX_VALUE;
        maxVal = Integer.MIN_VALUE;
//        output = "Name: " + iterator.next().toString() + ", ";
        System.out.printf("Name: %s \n", iterator.next().toString());
        if (!iterator.hasNext())
            return ;
        avg = 0;
        count = 1;
        while (iterator.hasNext()) {
            float val = 0;
            System.out.printf("Float: %s \n", iterator.next().toString());
//            float val = Float.parseFloat(iterator.next().toString());
            minVal = Math.min(val, minVal);
            maxVal = Math.max(val, maxVal);
            avg = (avg * count + val) / ++count;
        }
//        output += String.format("Min: %f, Max: %f, Avg: %f", minVal, maxVal, avg);
        context.write(new Text(Integer.toString(key.airportID)), new Text(output));
    }
}
