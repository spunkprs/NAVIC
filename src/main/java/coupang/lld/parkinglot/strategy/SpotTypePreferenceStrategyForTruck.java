package coupang.lld.parkinglot.strategy;

import coupang.lld.parkinglot.model.SpotType;

import java.util.ArrayList;
import java.util.List;

public class SpotTypePreferenceStrategyForTruck implements SpotTypePreferenceStrategy {
    @Override
    public List<SpotType> fetchSpotTypePreference() {
        List<SpotType> spotTypes = new ArrayList<>();
        spotTypes.add(SpotType.Truck);
        return spotTypes;
    }
}
