package lab2;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class CalcReducer extends Reducer<AirportID, Text, Text, Text> {
    @Override
    protected void reduce(AirportID key, Iterable<Text> values, Context context) throws IOException,
            InterruptedException {
        Iterator<Text> iterator = values.iterator();
        if (!iterator.hasNext())
            return ;
        float minVal = Integer.MAX_VALUE;
        float maxVal = Integer.MIN_VALUE;
        String name = iterator.next().toString();
        float avg = 0;
        int count = 1;
        while (iterator.hasNext()) {
            float val = Float.parseFloat(iterator.next().toString());
            minVal = Math.min(val, minVal);
            maxVal = Math.max(val, maxVal);
            avg = (avg * count + val) / ++count;
        }
    }
}
