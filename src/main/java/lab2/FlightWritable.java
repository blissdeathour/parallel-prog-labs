package lab2;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlightWritable implements Writable {
    int     destAirportID;
    float   arrDelay;

    public FlightWritable() {}

    public FlightWritable(int destAirportID, float arrDelay) {
        this.destAirportID = destAirportID;
        this.arrDelay = arrDelay;
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        destAirportID = in.readInt();
        arrDelay = in.readFloat();
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(destAirportID);
        output.writeFloat(arrDelay);
    }

    @Override
    public String toString() {
        return (String.format("%d %f\n", destAirportID, arrDelay));
    }
}