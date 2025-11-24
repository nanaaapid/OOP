import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulator {
    private Field field;
    private List<Animal> animals;
    
    public Simulator(int depth, int width){
        field = new Field(depth, width);
        animals = new ArrayList<>();
        populate();
    }
    
    public void simulate(int steps){
        for(int step = 1; step <= steps; step++){
            List<Animal> newAnimals = new ArrayList<>();
            for(Animal animal : animals){
                animal.act(newAnimals);
            }  
            animals.addAll(newAnimals); 
            animals.removeIf(animal -> !animal.isAlive());
            System.out.println("Step " + step + ": " + countAnimals());
        }
    }
    
    private void populate(){
        Random rand = new Random();
        int depth = field.getDepth();
        int width = field.getWidth();
        for(int row = 0; row < depth; row++){
            for(int col = 0; col < width; col++){
                double random = rand.nextDouble();
                Location location = new Location(row, col);
                if(random <= 0.08){
                    Fox fox = new Fox(field, location);
                    animals.add(fox);
                } 
                else if(random <= 0.20){
                    Rabbit rabbit = new Rabbit(field, location);
                    animals.add(rabbit);
                }
            }
        }
        System.out.println("Populasi awal: " + countAnimals());
    }
    
    private String countAnimals(){
        int foxCount = 0;
        int rabbitCount = 0;
        
        for(Animal animal : animals){
            if(animal instanceof Fox){
                foxCount++;
            }
            else if(animal instanceof Rabbit){
                rabbitCount++;
            }
        }
        return "Foxes = " + foxCount + ", Rabbits = " + rabbitCount;
    }
    
    public static void main(String[] args){
        System.out.println("SIMULASI FOXES AND RABBITS");
        Simulator simulator = new Simulator(20, 20);
        simulator.simulate(10);
    }
}
