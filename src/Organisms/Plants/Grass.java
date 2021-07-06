package Organisms.Plants;

import Organisms.Animal.Organism;
import Organisms.Animal.Sheep;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;


public class Grass extends Plant{
    public Grass(Position position, World world){
        super(0,0,position,6,3,"G",world);
    }

    @Override
    public Organism clone() {
        Grass grass=new Grass(getCalculatedPosition(),this.getWorld());
        return grass;
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setSign("G");
        this.setPowerToReproduce(3);


    }
}
