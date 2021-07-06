package main;
import Organisms.Animal.*;
import Organisms.Plants.Dendelion;
import Organisms.Plants.Grass;
import Organisms.Plants.Mushroom;
import java.util.*;

public class World {
    private final int X;
    private final int Y;
    private int turn;
    private ArrayList<Organism> Organisms = new ArrayList<Organism>();
    String separator = "-";

    public World(int x, int y) {
        X = x;
        Y = y;
        ArrayList<Action> result =new ArrayList<Action>();
        Wolf wolf=new Wolf(getFreePosition(x,y),this);
        result.add(new Action(ActionEnum.ADD,wolf.getPosition(),wolf));
        Grass grass=new Grass(getFreePosition(x,y),this);
        result.add(new Action(ActionEnum.ADD,grass.getPosition(),grass));
        Sheep sheep=new Sheep(getFreePosition(x,y),this);
        result.add(new Action(ActionEnum.ADD,sheep.getPosition(),sheep));
        Dendelion dendelion=new Dendelion(getFreePosition(x,y),this);
        result.add(new Action(ActionEnum.ADD,dendelion.getPosition(),dendelion));
        Mushroom mushroom=new Mushroom(getFreePosition(x,y),this);
        result.add(new Action(ActionEnum.ADD,mushroom.getPosition(),mushroom));
        Organisms.add(sheep);
        Organisms.add(grass);
        Organisms.add(wolf);
        Organisms.add(dendelion);
        Organisms.add(mushroom);
        for(Action re:result){
            System.out.println(re);
        }
        System.out.println(getBoard());
    }

    public Position getFreePosition(int x,int y){
        Random rand = new Random();
        Position position=new Position(rand.nextInt(x), rand.nextInt(y));
        while (isPositionOccupied(position)) {
            position=new Position(x, y);
        }
        return position;
    }
    public void addAlien(){
        Random random=new Random();
        int randomInt=random.nextInt(100)+1;
        if(randomInt<20){
            Alien alien=new Alien(getFreePosition(getX(),getY()),this);
            ArrayList<Action> result =new ArrayList<Action>();
            result.add(new Action(ActionEnum.ADD,alien.getPosition(),alien));
            getPositionTofreze(alien.getPosition());
            Organisms.add(alien);
            System.out.println(result);
        }
    }

    public boolean isPositionOccupied(Position p) {
        for (Organism o : Organisms ) {
            if (o.getPosition().equals(p) ) {
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn += 1;
    }

    public ArrayList<Organism> getOrganisms() {
        return Organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        Organisms = organisms;
    }

    public String getSeparator() {
        return separator;
    }

    public String getBoard()
    {
        System.out.println("Turn:"+getTurn());
        setTurn(getTurn());

        String[][] board = new String[X][Y];
        for(int x = 0; x < X; x++)
        {
            for(int y = 0; y < X; y++)
            {
                board[x][y] = getSeparator();
            }
        }

        for (Organism org : Organisms) {
            board[org.getPosition().getY()][org.getPosition().getX()] = String.valueOf(org.getSign());
        }

        String text = "";
        for(int x = 0; x < X; x++)
        {
            for(int y = 0; y < X; y++)
            {
                text += board[x][y];
            }
            text += "\n";
        }
        return text;
    }

    public void makeTurn(){
        int size= Organisms.size();
        for(int x=0;x<size;x++){
            Organism organism =Organisms.get(x);
            if(organism.getLiveLength()>0){
                organism.roundComplete(organism);
            }
        }
        makeMove();
    }
    public void makeMove(){
        List deadAnimals = new ArrayList();
        List tofreeze=new ArrayList();
        for(Organism organisms: Organisms)
        {
            if(organisms.getSign()=="A"){
                for(Position p:getPositionTofreze(organisms.getPosition()))
                    tofreeze.add(p);
            }
        }
        for (Organism org :Organisms){
            Random random=new Random();
            if (org.getLiveLength() == 0 ) {
                continue;
            }
            else
            if(org.getSign()=="G"||org.getSign()=="M"||org.getSign()=="D") {

            }
            else if(org.getSign()=="W"){
                if(getAllPositionforWolf(org.getPosition()).isEmpty()){
                    org.setPosition(getAllFreePosition(org.getPosition()).get(random.nextInt(getAllFreePosition(org.getPosition()).size())));
                    ArrayList<Action> result =new ArrayList<Action>();
                    result.add(new Action(ActionEnum.MOVE,org.getPosition(),org));
                    System.out.println(result);
                }
                else {
                    Organism enemy = getOranismFromPosition(getAllPositionforWolf(org.getPosition()).get(0));
                    if(enemy.getSign()=="M"){
                        deadAnimals.add(org);
                        deadAnimals.add(enemy);
                        System.out.println("The attacked organism is a toadstool. Both organisms die: "+org.getClass().getSimpleName()+" "+enemy.getClass().getSimpleName());
                    }
                    else if (org.getPower() < enemy.getPower()) {
                        deadAnimals.add(org);
                        System.out.println(org.getClass().getSimpleName() + " died as a result of fighting with " + enemy.getClass().getSimpleName());

                    }
                    else {
                        System.out.println(enemy.getClass().getSimpleName()+" died as a result of fighting with " + org.getClass().getSimpleName());
                        org.setPosition(enemy.getPosition());
                        deadAnimals.add(enemy);
                    }
                }
            }
            else if(org.getSign()=="S"){
                if(getAllPositionforSheep(org.getPosition()).isEmpty()){
                    org.setPosition(getAllFreePosition(org.getPosition()).get(random.nextInt(getAllFreePosition(org.getPosition()).size())));
                    ArrayList<Action> result =new ArrayList<Action>();
                    result.add(new Action(ActionEnum.MOVE,org.getPosition(),org));
                    System.out.println(result);
                }
                else {
                    Organism enemy = getOranismFromPosition(getAllPositionforSheep(org.getPosition()).get(0));
                    deadAnimals.add(enemy);
                    org.setPosition(enemy.getPosition());
                    System.out.println(enemy.getClass().getSimpleName() + " died as a result of fighting with " + org.getClass().getSimpleName());
                }
            }
        }
        for (Object org : deadAnimals) {
            Organisms.remove(org);
        }
    }
    public void ifHeGoDied(){
        ArrayList<Organism>died=new ArrayList<Organism>();
        for(Organism org : Organisms){
            if(org.getLiveLength()<=0){
                died.add(org);
            }
        }
        for (Organism org:died){
            ArrayList<Action> result =new ArrayList<Action>();
            result.add(new Action(ActionEnum.REMOVE,new Position(-1,-1),org));
            System.out.println(result);
            Organisms.remove(org);
        }
    }

    public Organism getOranismFromPosition(Position position)
    {
        for (Organism org : Organisms) {
            if(org.getPosition().compareTo(position) > 0)
            { return org; }
        }
        return null;
    }

    public void addNewOrganisms(Organism organisms){
        if(organisms.canMove()){ Organisms.add(organisms.clone()); }
        else{
            System.out.println("The organism cannot move "+organisms.getClass().getSimpleName());
        }
    }

    public ArrayList<Position> getAllFreePosition(Position position){
        ArrayList<Position> freePos =new ArrayList<Position>();
        int posX=position.getX();
        int posY=position.getY();
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                Organism organism=getOranismFromPosition(new Position(posX+x,posY+y));
                if(isCorrectPossition(posX, posY, x, y))
                    if(organism==null){
                        freePos.add(new Position(posX+x,posY+y));
                }
            }
        }
        if(freePos.isEmpty()){
        }
        return freePos;
    }

    public ArrayList<Position> getAllPositionforWolf(Position position){
        ArrayList<Position> freesPos =new ArrayList<Position>();
        int posX=position.getX();
        int posY=position.getY();
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                Organism organism=getOranismFromPosition(new Position(posX+x,posY+y));
                if(isCorrectPossition(posX, posY, x, y)){
                    if (organism!=null && organism.getPosition()!=position){
                        freesPos.add(new Position(posX+x,posY+y));
                    }
                }
            }
        }
        return freesPos;
    }

    public boolean isCorrectPossition(int posX, int posY, int moveX, int moveY){
        return !((posX+moveX<0||posY+moveY<0) || (posX + moveX >= getX() || posY + moveY >= getY()));
    }

    public ArrayList<Position> getAllPositionforSheep(Position position){
        ArrayList<Position> freesPos =new ArrayList<Position>();
        int posX=position.getX();
        int posY=position.getY();
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                Organism organism=getOranismFromPosition(new Position(posX+x,posY+y));
                if(isCorrectPossition(posX, posY, x, y))
                    if (organism!=null && organism.getPosition()!=position && organism.getSign()=="G"){
                        freesPos.add(new Position(posX+x,posY+y));
                }
            }
        }
        return freesPos;
    }

    public ArrayList<Position> getPositionTofreze(Position position){
        ArrayList<Position> frezePosition =new ArrayList<Position>();
        int posX=position.getX();
        int posY=position.getY();
        for(int y=-1;y<=1;y++){
            for(int x=-1;x<=1;x++){
                Organism organism=getOranismFromPosition(new Position(posX+x,posY+y));
                if(isCorrectPossition(posX, posY, x, y))
                    if (organism!=null && organism.getPosition()!=position){
                        frezePosition.add(new Position(posX+x,posY+y));
                }
            }
        }
        return frezePosition;
    }

    public void ifReproduce() {
        ArrayList<Organism> organisms=new ArrayList<Organism>();
        for (Organism o :Organisms){
            if(o.getPower()>=o.getPowerToReproduce()){
                organisms.add(o);
                o.setPower(o.getPower()/2);
            }
        }
        if(organisms.size()>0){
            for(Organism org:organisms) {
                addNewOrganisms(org);
                }
        }
    }

    public void natureProtection(){
        Map<String, Integer> characterCounter=new HashMap<>();
        for(Organism o:Organisms){
            Integer value=characterCounter.get(o.getSign());
            if(value!=null){ value++; }
            else { value=1; }
            characterCounter.put(o.getSign(),value);
        }
        Integer min=Collections.min(characterCounter.values());
        Integer max=Collections.max(characterCounter.values());
        System.out.println("It is currently on the board "+characterCounter);

        if(max-min>=4){
            String maxOrganismSign = "";
            for (Map.Entry<String, Integer> entry : characterCounter.entrySet()) {
                if (entry.getValue()==max) {
                    maxOrganismSign = entry.getKey();
                }
            }
            ArrayList<Organism> todelete=new ArrayList<Organism>();
            for(Organism o:Organisms){
                if(o.getSign().equals(maxOrganismSign)){
                    todelete.add(o);
                }
            }
            Random random=new Random();

            for (int i=0;i<3;i++){
                Organism org=todelete.get(random.nextInt(todelete.size()));
                ArrayList<Action> result =new ArrayList<Action>();
                result.add(new Action(ActionEnum.NATUREPROTECTION,org.getPosition(),org));
                System.out.println(result);
                todelete.remove(org);
                Organisms.remove(org);
            }
            int sheep=0;
            int grass=0;
            int wolf=0;
            int dendylion=0;
            int mushroom=0;
            for (Organism o: Organisms){
                if(o.getSign()=="S"){
                    sheep+=1;
                }
                if(o.getSign()=="W"){
                    sheep+=1;
                }
                if(o.getSign()=="D"){
                    sheep+=1;
                }
                if(o.getSign()=="G"){
                    sheep+=1;
                }
                if(o.getSign()=="M"){
                    sheep+=1;
                }
            }
            if(sheep==0){
                Sheep sheeps=new Sheep(getFreePosition(getX(),getY()),this);
                Organisms.add(sheeps);
            }
            if(wolf==0){
                Wolf wolf1=new Wolf(getFreePosition(getX(),getY()),this);
                Organisms.add(wolf1);
            }
            if(dendylion==0){
                Dendelion dendelion=new Dendelion(getFreePosition(getX(),getY()),this);
                Organisms.add(dendelion);
            }
            if(mushroom==0){
                Mushroom mushroom1=new Mushroom(getFreePosition(getX(),getY()),this);
                Organisms.add(mushroom1);
            }
            if(grass==0){
                Grass grass1=new Grass(getFreePosition(getX(),getY()),this);
                Organisms.add(grass1);
            }
        }

    }
    public void killAllToTest(){
        Organisms.removeAll(getOrganisms());
    }
    public void addOrganismToetst(Organism organism){
        Organisms.add(organism);
    }

}
