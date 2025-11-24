import java.util.List;
import java.util.ArrayList;

public class Field{
    private Object[][] field;
    
    public Field(int depth, int width){
        field = new Object[depth][width];
    }
    
    public void clear(Location location){
        field[location.getRow()][location.getCol()] = null;
    }
    
    public void place(Object object, Location location){
        field[location.getRow()][location.getCol()] = object;
    }
    
    public Object getObjectAt(Location location){
        return field[location.getRow()][location.getCol()];
    }
    
    public Location freeAdjacentLocation(Location location){
        List<Location> free = new ArrayList<>();
        List<Location> adjacent = adjacentLocations(location);
        for(Location loc : adjacent){
            if(getObjectAt(loc) == null){
                free.add(loc);
            }
        }
        return free.isEmpty() ? null : free.get(0);
    }
    
    public List<Location> adjacentLocations(Location location){
        List<Location> locations = new ArrayList<>();
        int row = location.getRow();
        int col = location.getCol();
        for(int roffset = -1; roffset <= 1; roffset++){
            int nextRow = row + roffset;
            if(nextRow >= 0 && nextRow < field.length){
                for(int coffset = -1; coffset <= 1; coffset++){
                    int nextCol = col + coffset;
                    if(nextCol >= 0 && nextCol < field[0].length && (roffset != 0 || coffset != 0)){
                        locations.add(new Location(nextRow, nextCol));
                    }
                }
            }
        }
        return locations;
    }
    
    public int getDepth(){
        return field.length;
    }
    
    public int getWidth(){
        return field[0].length;
    }
}
