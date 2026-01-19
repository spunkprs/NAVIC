package conceptsRevision;

public class Toad implements LandAnimal, AquaticAnimal {

    @Override
    public void swim() {
        System.out.println("Can swim in shallow waters easily !!");
    }

    @Override
    public void walk() {
        System.out.println("Can walk on the land !!");
    }

    @Override
    public void run() {
        System.out.println("Can run at slow speed on the land !!");
    }

    @Override
    public void behaviour() {
        System.out.println("Default behaviour of swimming && run/walk is there !! ");
    }
}
