package coupang.lld.parkinglot.model;

public class LevelSpotTypeCombination {
    private int Level;
    private SpotType spotType;

    public LevelSpotTypeCombination(int level, SpotType spotType) {
        Level = level;
        this.spotType = spotType;
    }

    public int getLevel() {
        return Level;
    }

    public SpotType getSpotType() {
        return spotType;
    }
}
