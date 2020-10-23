package lab2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportID implements WritableComparable<AirportID> {
    int     airportID;
    Boolean identifier;

    AirportID() {}

    AirportID(int airportID, Boolean identifier) {
        this.airportID = airportID;
        this.identifier = identifier;
    }

    @Override
    public void write(DataOutput output) throws IOException {
        output.writeInt(airportID);
        output.writeBoolean(identifier);
    }

    @Override
    public void readFields(DataInput input) throws IOException {
        airportID = input.readInt();
        identifier = input.readBoolean();
    }

    @Override
    public int compareTo(AirportID obj) {
        if (airportID == obj.airportID)
            return identifier ? -1 : 1;
        return airportID - obj.airportID;
        return (this.airportID == obj.airportID ? (identifier ? -1 : 1)  : this.airportID - obj.airportID);
    }

    @Override
    public String toString() {
        return (String.format("%d %d\n", airportID, identifier ? 1 : 0));
    }
}
