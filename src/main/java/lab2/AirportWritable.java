package lab2;

import org.apache.hadoop.io.Writable;

import java.io.DataOutput;
import java.io.IOException;

public class AirportWritable implements Writable {
    int code;
    String description;

    public AirportWritable() {}

    public AirportWritable(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        
    }
}
