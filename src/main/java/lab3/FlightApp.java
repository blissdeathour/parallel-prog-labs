package lab3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import scala.collection.convert.Wrappers;

import java.util.Collection;
import java.util.Collections;

public class FlightApp {
    final private static String DELIMITER = ",";
    final private static int 


    private static String removeQuotes(String row) {
        return (row.replaceAll("\"", ""));
    }

    private static Tuple2<Long, String> parseAirportEntry(String row) {
        String rowCode, name;

        rowCode = removeQuotes(row.substring(0, row.indexOf(DELIMITER)));
        name = removeQuotes(row.substring(row.indexOf(DELIMITER) + 1, row.length()));
        return (new Tuple2<>(Long.parseLong(rowCode), name));
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.exit(-1);
        }

        SparkConf cf = new SparkConf().setAppName("FlightApp");
        JavaSparkContext sc = new JavaSparkContext(cf);

        JavaPairRDD<LongWritable, Text> airportFile = sc.hadoopFile(args[0], TextInputFormat.class, LongWritable.class, Text.class);
        JavaPairRDD<LongWritable, Text> flightFile = sc.hadoopFile(args[1], TextInputFormat.class, LongWritable.class, Text.class);

        JavaPairRDD<Long, String> airportRDD = airportFile.flatMapToPair(e -> {
            if (e._1.get() != 0) {
                return (new IteratorContainer<>(parseAirportEntry(e._2.toString())).getIterator());
            }
            else {
                return (Collections.emptyIterator())
            }
        });

        JavaPairRDD<Tuple2<Long, Long>, FlightData> flightRDD = flightFile.flatMapToPair(e -> {
            if (e._1.get() != 0) {
                String line = e._2.toString();
                String[] splitted = line.split(DELIMITER, -1);

            }
        })
    }
}
