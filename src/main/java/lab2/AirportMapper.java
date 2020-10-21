package lab2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportID, Text> {
    private static final int AIRPORT_CODE = 0;
    private static final int AIRPORT_NAME = 1;
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String[]    strings;
        String      name;
        int         code;

        if (key.get() == 0)
            return ;
        strings = value.toString().split(",", 2);
        code = Integer.parseInt(strings[AIRPORT_CODE].replaceAll("\"", ""));
        name = strings[AIRPORT_NAME].replaceAll("\"", "");
        context.write(new AirportID(code, true), new Text(name));
    }
}
