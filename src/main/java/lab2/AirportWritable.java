package lab2;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
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
        out.writeInt(code);
        out.writeChars(description);
        out.writeChar('\n');
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        code = in.readInt();
        description = in.readLine();
    }
}
