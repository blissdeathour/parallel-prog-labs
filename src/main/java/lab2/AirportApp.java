package lab2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;
import org.apache.hadoop.mapreduce.Job;

public class AirportApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 3)
        {
            System.exit(-1);
        }
        Job job = Job.getInstance();
        job.setJarByClass(AirportApp.class);
        job.setJobName("Flights Joiner");
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, FlightMapper.class);
    }
}
