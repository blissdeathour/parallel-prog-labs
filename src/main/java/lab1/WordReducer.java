package lab1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordReducer extends Reducer<Text, IntWritable, Text, LongWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws
            IOException, InterruptedException {
        long count = 0;

        for (IntWritable val : values)
            count++;
        context.write(key, new LongWritable(count));
//<считаем количество записей в итераторе и генерируем запись в контекст
//        В контекст пишется пара — Text и LongWritable>
    }
}