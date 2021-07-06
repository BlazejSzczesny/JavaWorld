package main;

import Organisms.Animal.Organism;
import Organisms.Animal.Sheep;
import Organisms.Animal.Wolf;
import Organisms.Plants.Dendelion;
import Organisms.Plants.Grass;
import Organisms.Plants.Mushroom;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaWorldTest {
    World world=new World(9,9);

    @Test
    public void AddGrass(){
        world.killAllToTest();
        Grass grass=new Grass(new Position(8,7),world);
        world.addOrganismToetst(grass);
        assertEquals(world.getOrganisms().size(),1);
    }

    @Test
    public void addMuchOrganism(){
        world.killAllToTest();
        Grass grass=new Grass(new Position(8,7),world);
        world.addOrganismToetst(grass);
        Wolf wolf=new Wolf(new Position(1,7),world);
        world.addOrganismToetst(wolf);
        Dendelion dendelion=new Dendelion(new Position(1,7),world);
        world.addOrganismToetst(dendelion);
        assertEquals(world.getOrganisms().size(),3);
    }
    @Test
    public void sheepAttackGrass(){
        world.killAllToTest();
        Sheep sheep=new Sheep(new Position(8,8),world);
        Grass grass=new Grass(new Position(8,7),world);
        Grass grass1=new Grass(new Position(7,7),world);
        Grass grass2=new Grass(new Position(7,8),world);
        Grass grass3=new Grass(new Position(5,8),world);
        world.addNewOrganisms(grass);
        world.addNewOrganisms(grass1);
        world.addNewOrganisms(grass2);
        world.addNewOrganisms(grass3);
        world.addNewOrganisms(sheep);
        world.makeMove();
        int licznik=0;
        for (Organism o:world.getOrganisms())
            if(o.getSign()=="G")
                licznik+=1;
        assertEquals(licznik,3);
    }
    @Test
    public void wolfAttackOtherOrganism(){
        world.killAllToTest();
        Wolf wolf =new Wolf(new Position(8,8),world);
        Sheep sheep1=new Sheep(new Position(8,7),world);
        Dendelion dendelion=new Dendelion(new Position(7,7),world);
        Sheep sheep=new Sheep(new Position(7,8),world);
        Dendelion dendelion1=new Dendelion(new Position(5,8),world);
        world.addOrganismToetst(sheep1);
        world.addOrganismToetst(dendelion);
        world.addOrganismToetst(sheep);
        world.addOrganismToetst(dendelion1);
        world.addOrganismToetst(wolf);
        world.makeMove();

        assertEquals(world.getOrganisms().size(),4);
    }

    @Test
    public void organismAttackToadstool(){
        world.killAllToTest();
        Wolf wolf =new Wolf(new Position(8,8),world);
        Mushroom mushroom=new Mushroom(new Position(8,7),world);
        Mushroom mushroom1=new Mushroom(new Position(7,7),world);
        Mushroom mushroom2=new Mushroom(new Position(7,8),world);
        Mushroom mushroom3=new Mushroom(new Position(5,8),world);
        world.addOrganismToetst(mushroom);
        world.addOrganismToetst(mushroom1);
        world.addOrganismToetst(mushroom2);
        world.addOrganismToetst(mushroom3);
        world.addOrganismToetst(wolf);
        world.makeMove();
        int licznikw=0;
        int licznikm=0;
        for (Organism o:world.getOrganisms())
            if(o.getSign()=="W"){
                licznikw+=1;
            }
            else if(o.getSign()=="M"){
                licznikm+=1;
            }

        assertEquals(licznikw,0);
        assertEquals(licznikm,3);
    }
    @Test
    public void natureProtection(){
        world.killAllToTest();
        Wolf wolf =new Wolf(new Position(8,8),world);
        Mushroom mushroom=new Mushroom(new Position(8,7),world);
        Mushroom mushroom1=new Mushroom(new Position(7,7),world);
        Mushroom mushroom2=new Mushroom(new Position(7,8),world);
        Mushroom mushroom3=new Mushroom(new Position(5,8),world);
        Mushroom mushroom4=new Mushroom(new Position(1,1),world);
        world.addOrganismToetst(mushroom);
        world.addOrganismToetst(mushroom1);
        world.addOrganismToetst(mushroom2);
        world.addOrganismToetst(mushroom3);
        world.addOrganismToetst(mushroom4);
        world.addOrganismToetst(wolf);
        world.natureProtection();
        int licznikm=0;
        for(Organism o: world.getOrganisms()){
            if(o.getSign()=="M"){
                licznikm+=1;
            }
        }
        assertEquals(licznikm,3);
    }
    @Test
    public void reproduceOrganism(){
        world.killAllToTest();
        Grass grass=new Grass(new Position(0,0),world);
        Dendelion dendelion =new Dendelion(new Position(8,8),world);
        Mushroom mushroom=new Mushroom(new Position(5,5),world);
        world.addOrganismToetst(grass);
        world.addOrganismToetst(dendelion);
        world.addOrganismToetst(mushroom);
        world.makeTurn();
        world.makeTurn();
        world.ifReproduce();
        assertEquals(world.getOrganisms().size(),5);
    }
    @Test
    public void cannotMove(){
        world.killAllToTest();
        Mushroom mushroom=new Mushroom(new Position(8,8),world);
        Mushroom mushroom1=new Mushroom(new Position(7,7),world);
        Mushroom mushroom2=new Mushroom(new Position(8,7),world);
        Mushroom mushroom3=new Mushroom(new Position(7,8),world);

        world.addOrganismToetst(mushroom);
        world.addOrganismToetst(mushroom1);
        world.addOrganismToetst(mushroom2);
        world.addOrganismToetst(mushroom3);
        String odp="The organism can move.";
        if(world.getAllFreePosition(mushroom.getPosition()).isEmpty()){
            odp="The organism cannot move.";
        }
        assertEquals(odp,"The organism cannot move.");
    }





}
