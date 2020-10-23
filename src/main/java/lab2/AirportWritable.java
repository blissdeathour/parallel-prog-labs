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
    public void write(DataOutput output) throws IOException {
        output.writeInt(code);
        output.writeChars(description);
        output.writeChar('\n');
    }

    @Override
    public void readFields(DataInput input) throws IOException {
        code = input.readInt();
        description = input.readLine();
    }

    @Override
    public String toString() {
        return (String.format("%d %s", code, description));
    }
}
