package lab3;

public class FlightData {
    public Float minDelay;
    public Integer totCount;
    public Integer wrongCount;
    final static double CONST_MIN = 1e-5;

    public FlightData(String row) {
        totCount = 1;
        if (row.isEmpty()) {
            minDelay = 0.f;
            wrongCount = 1;
        }
        else {
            minDelay = Float.parseFloat(row);
            wrongCount = minDelay >= CONST_MIN ? 1 : 0;
        }
    }

    public FlightData(Float minDelay, Integer totCount, Integer wrongCount) {
        this.minDelay = minDelay;
        this.totCount = totCount;
        this.wrongCount = wrongCount;
    }

    public float getRatio() {
        return (float) wrongCount / (float) totCount;
    }

    public FlightData fold(FlightData obj) {
        float delay;
        if (minDelay > CONST_MIN && obj.minDelay > CONST_MIN) {
            delay = Math.min(minDelay, obj.minDelay);
        }
        else if (minDelay > CONST_MIN && obj.minDelay <= CONST_MIN)
        {
            delay = minDelay;
        }
        else if (minDelay <= CONST_MIN && obj.minDelay > CONST_MIN)
        {
            delay = obj.minDelay;
        }
        else {
            delay = 0.f;
        }
        return (new FlightData(delay, totCount + obj.totCount, wrongCount + obj.wrongCount));
    }

}
