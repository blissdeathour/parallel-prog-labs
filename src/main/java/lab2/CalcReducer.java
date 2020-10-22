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
        float maxVal - Integer.MIN_VALUE;
        String 
    }
}
