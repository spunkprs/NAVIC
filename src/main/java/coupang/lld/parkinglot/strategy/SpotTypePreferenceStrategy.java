package coupang.lld.parkinglot.strategy;

import coupang.lld.parkinglot.model.SpotType;

import java.util.List;

public interface SpotTypePreferenceStrategy {

    List<SpotType> fetchSpotTypePreference();
}
