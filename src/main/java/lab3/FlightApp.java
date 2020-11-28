package lab3;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;
import scala.collection.convert.Wrappers;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class FlightApp {
    final private static String DELIMITER = ",";
    final private static int DEPART_AIRPORT = 11;
    final private static int DEST_AIRPORT = 14;
    final private static int DEL_IND = 18;


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
                return (Collections.emptyIterator());
            }
        });

        JavaPairRDD<Tuple2<Long, Long>, FlightData> flightRDD = flightFile.flatMapToPair(e -> {
            if (e._1.get() != 0) {
                String line = e._2.toString();
                String[] splintedStr = line.split(DELIMITER, -1);
                long depCode = Long.parseLong(splintedStr[DEPART_AIRPORT]);
                long destCode = Long.parseLong(splintedStr[DEST_AIRPORT]);
                String rowDelay = splintedStr[DEL_IND];

                return (new IteratorContainer<>(new Tuple2<>(new Tuple2<>(depCode, destCode), new FlightData(rowDelay))).getIterator());
            }
            else {
                return (Collections.emptyIterator());
            }
        });

        final Broadcast<Map<Long, String>> airportInfoBroadcasted = sc.broadcast(airportRDD.collectAsMap());
        JavaPairRDD<Tuple2<Long, Long>, FlightData> reducedByKey = flightRDD.reduceByKey(FlightData::fold);
        JavaPairRDD<String, String> result = reducedByKey.mapToPair(e -> {
            String name = String.format("[%d] %s -> [%d] %s", e._1._1, airportInfoBroadcasted.value().get(e._1._1),
                    e._1._2, airportInfoBroadcasted.value().get(e._1._2));
            String val = String.format("Min: %f; Ration: %.2f%%; Total: %d", e._2.minDelay, e._2.getRatio() * 100.f, e._2.totCount);
            return (new Tuple2<>(name, val));
        });


        result.saveAsTextFile(args[2]);
    }
}
