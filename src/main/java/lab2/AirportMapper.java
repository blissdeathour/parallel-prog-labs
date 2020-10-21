package lab2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportID, IntWritable> {
    
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String[]    strings;
        int         code;
        String      name;

        if (key.get() == 0)
            return ;
        strings = value.toString().split(",");
        code = Integer.parseInt(strings[0].replaceAll("\"", ""));
        name = strings[1].replaceAll("\"", "");

    }
}
