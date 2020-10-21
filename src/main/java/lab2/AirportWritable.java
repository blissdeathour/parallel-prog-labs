package lab2;

import org.apache.hadoop.io.Writable;

public class AirportWritable implements Writable {
    int     code;
    String  description;

    public AirportWritable() {};

    public AirportWritable(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    @Override
    public void write()
}
