package Organisms.Animal;

import main.Position;
import main.World;

import java.util.List;

public abstract class Animal extends Organism  {
    Position lastposition;

    public Animal(int power, int initiative, Position position, int liveLength, int powerToReproduce, String sign, World world) {
        super(power, initiative, position, liveLength, powerToReproduce, sign, world);
        lastposition = position;
    }

}
