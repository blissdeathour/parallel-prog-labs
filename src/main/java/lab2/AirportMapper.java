package lab2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportID, IntWritable> {
    @Override
    public void map(LongWritable key, Text text, Context context) throws IOException,
            InterruptedException {
        
    }
}
