package Organisms.Animal;

import main.Position;
import main.World;

import java.util.ArrayList;
import java.util.List;

public class Wolf extends Animal{
    public Wolf(Position position, World world){
        super(8,5,position,20,16,"W",world);


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
        this.setPower(8);
        this.setInitiative(5);
        this.setLiveLength(20);
        this.setPowerToReproduce(16);
        this.setSign("W");
    }

    @Override
    public Organism clone() {
        Wolf wolf=new Wolf(getCalculatedPosition(),this.getWorld());
        return wolf;
    }


}

