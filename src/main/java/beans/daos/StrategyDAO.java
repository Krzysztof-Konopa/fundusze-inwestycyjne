package beans.daos;

import beans.models.Strategy;

import java.util.Objects;

public interface StrategyDAO {

    public Strategy getByName(String name);

    static void validateStrategy(Strategy strategy) {
        if(Objects.isNull(strategy)) {
            throw new NullPointerException("Strategy is [null]");
        }
        if(Objects.isNull(strategy.getVolumesMap())) {
            throw new NullPointerException("Strategy volume collection is [null]. Strategy: [" + strategy + "]");
        }
    }
}
