package Organisms.Plants;

import Organisms.Animal.Organism;
import main.Action;
import main.ActionEnum;
import main.Position;
import main.World;


public class Dendelion extends Plant {
    public Dendelion(Position position, World world){
        super(0,0,position,6,2,"D",world);

    }

    @Override
    public void initParams() {
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setPower(0);
        this.setSign("D");
        this.setPowerToReproduce(3);
    }
    @Override
    public Organism clone() {
        Dendelion dendelion=new Dendelion(getCalculatedPosition(),this.getWorld());
        return dendelion;

    }
}
