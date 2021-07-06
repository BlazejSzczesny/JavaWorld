package Organisms.Plants;
import main.World;
import Organisms.Animal.Organism;
import main.Action;
import main.Position;


import java.util.ArrayList;

public abstract class Plant extends Organism {
    public Plant(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world){
        super(power,initiative,position,liveLength,powerToReproduce,sign,world);
    }

    @Override
    public ArrayList move() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList action() {
        ArrayList<Action> actions = new ArrayList<>();
        return actions;
    }

}
