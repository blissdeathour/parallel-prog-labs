package lab2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportID, Text> {
    private static final int AIRPORT_CODE_IND = 0;
    private static final int AIRPORT_NAME_IND = 1;
    private static final int LIMIT = 2;
    private static final String AIRPORT_DELIMITER = ",";
    private static final boolean AIRPORT_FLAG = true;


    private static String[] airportSplitter(Text value) {
        return (value.toString().split(AIRPORT_DELIMITER, LIMIT));
    }

    private static String removeQuoter(String string) {
        return (string.replaceAll("\"", ""));
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        String[]    strings;
        String      name;
        int         code;

        if (key.get() == 0) {
            return;
        }
        strings = airportSplitter(value);
        code = Integer.parseInt(removeQuoter(strings[AIRPORT_CODE_IND]));
        name = removeQuoter(strings[AIRPORT_NAME_IND]);
        context.write(new AirportID(code, AIRPORT_FLAG), new Text(name));
    }
}
