package lab3;

public class FlightData {
    public Float minDelay;
    public Integer totCount;
    public Integer wrongCount;

    public FlightData(String row) {
        totCount = 1;
        if (row.isEmpty()) {
            minDelay = 0.f;
            wrongCount = 1;
        }
        else {
            minDelay = Float.parseFloat(row);
            wrongCount = minDelay >= 
        }
    }
}
