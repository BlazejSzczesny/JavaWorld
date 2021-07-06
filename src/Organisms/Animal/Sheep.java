package Organisms.Animal;
import main.Position;
import main.World;
import java.util.ArrayList;


public class Sheep extends Animal {
    public Sheep(Position position, World world){
        super(3,3,position,10,6,"S",world);


    }

    @Override
    public ArrayList move() {
        return null;
    }

    @Override
    public ArrayList action() {
        return null;
    }

    @Override
    public void initParams() {
        this.setPower(3);
        this.setInitiative(3);
        this.setLiveLength(10);
        this.setPowerToReproduce(6);
        this.setSign("G");

    }

    @Override
    public Organism clone() {
        Sheep sheep=new Sheep(getCalculatedPosition(),this.getWorld());
        return sheep;
    }



}
