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


    private static String removeQuotes(String row) {
        return (row.replaceAll("\"", ""));
    }

    private static Tuple2<Long, String> parseAirportEntry(String row) {
        String rowCode, name;

        rowCode = removeQuotes(row.substring(0, row.indexOf(DELIMETER)));
        name = removeQuotes(row.substring(row.indexOf(DELIMETER) + 1, row.length()));
        return (new Tuple2<>(Long.parseLong(rowCode), name));
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
