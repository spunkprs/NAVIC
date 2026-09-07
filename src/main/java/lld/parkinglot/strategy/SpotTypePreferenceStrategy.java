package lld.parkinglot.strategy;

import lld.parkinglot.model.SpotType;

import java.util.List;

public interface SpotTypePreferenceStrategy {

    List<SpotType> fetchSpotTypePreference();
}
