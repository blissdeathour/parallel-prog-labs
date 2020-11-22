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
            wrongCount = minDelay >= 1e-5 ? 1 : 0;
        }
    }

    public FlightData(Float minDelay, Integer totCount, Integer wrongCount) {
        this.minDelay = minDelay;
        this.totCount = totCount;
        this.wrongCount = wrongCount;
    }

    public FlightData fold(FlightData obj) {
        float delay;
        if (minDelay > 1e-5 && obj.minDelay > 1e-5) {
            
        }
    }
}
