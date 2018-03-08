package beans.models;

import java.util.List;

public class Investment {

    private List<Result> results;

    private long remainCash;

    public Investment(){}

    public Investment(List<Result> results, long remainCash) {
        this.results = results;
        this.remainCash = remainCash;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public long getRemainCash() {
        return remainCash;
    }

    public void setRemainCash(long remainCash) {
        this.remainCash = remainCash;
    }
}
