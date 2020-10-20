package lab1;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String [] words = value.toString().toLowerCase().split("[^a-zа-я]");
        for (String word : words)
            context.write(new Text(word), new IntWritable(1));
//            <получаем строку из value, удаляем все спецсимволы, переводим в
//            lowercase, разбиваем на слова и каждое слово пишем в контекст с счетчиком 1
//            в контекст пишется пара — Text и IntWritable >
    }
}
