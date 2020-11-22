package lab3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FlightApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.exit(-1);
        }

        SparkConf cf = new SparkConf().setAppName("FlightApp");
        JavaSparkContext sc = new JavaSparkContext(cf);

        JavaPairRDD<LongWritable, Text> airportFile = sc.hadoopFile(args[0], TextInputFormat.class, LongWritable.class, Text.class);
        JavaPairRDD<LongWritable, Text> flightsFile = sc.hadoopFile(args[1], TextInputFormat.class, LongWritable.class, Text.class);

        JavaPairRDD<Long, String> airportRDD = airportFile.flatMapToPair(e -> {
            
        })
    }
}
