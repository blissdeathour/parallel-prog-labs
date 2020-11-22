package lab3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import scala.collection.convert.Wrappers;

public class FlightApp {
    final private static String DELIMETER = ",";


    private static Tuple2<Long, String> parseAirportEntry(String row) {
        int delim = row.indexOf(DELIMETER);
        String row
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.exit(-1);
        }

        SparkConf cf = new SparkConf().setAppName("FlightApp");
        JavaSparkContext sc = new JavaSparkContext(cf);

        JavaPairRDD<LongWritable, Text> airportFile = sc.hadoopFile(args[0], TextInputFormat.class, LongWritable.class, Text.class);
        JavaPairRDD<LongWritable, Text> flightsFile = sc.hadoopFile(args[1], TextInputFormat.class, LongWritable.class, Text.class);

        JavaPairRDD<Long, String> airportRDD = airportFile.flatMapToPair(e -> {
            if (e._1.get() != 0) {
                return new IteratorContainer<>(parseAirportEntry(e._2.toString())).
            }
        })
    }
}
