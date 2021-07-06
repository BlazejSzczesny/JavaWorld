package Organisms.Plants;
import Organisms.Animal.Organism;
import main.Position;
import main.World;



public class Mushroom extends Plant{
    public Mushroom(Position position, World world){
        super(0,0,position,12,4,"M",world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(12);
        this.setPowerToReproduce(4);
        this.setSign("M");
    }

    @Override
    public Organism clone() {
        Mushroom mushroom=new Mushroom(getCalculatedPosition(),this.getWorld());
        return mushroom;
    }

}
