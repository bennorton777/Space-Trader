package com.TableFlip.SpaceTrader.GameEntity;

import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/**
 * Class description here
 * Package: com.TableFlip.SpaceTrader.GameEntity
 */
public class OceanTest extends TestCase {
    @Test
    public void testGenerateIslands() throws Exception {
        HashMap<Integer, HashMap<Integer, Boolean>> save=new HashMap<Integer, HashMap<Integer, Boolean>>();
        HashMap<Integer, Boolean> secondary;
        for (Island island : Ocean.getInstance().getIslands()){
            for (Port port : island.getPorts()){
                if (!save.containsKey(port.getCoordinates().getxPos())){
                    secondary=new HashMap<Integer, Boolean>();
                    secondary.put(port.getCoordinates().getyPos(), true);
                    save.put(port.getCoordinates().getxPos(),secondary);
                }
                else if (!save.get(port.getCoordinates().getxPos()).containsKey(port.getCoordinates().getyPos())){
                    save.get(port.getCoordinates().getxPos()).put(port.getCoordinates().getyPos(), true);
                }
                else{
                    assertTrue("Port collision at x="+port.getCoordinates().getxPos()+" and y="+port.getCoordinates().getyPos(), false);
                }
            }
        }
    }
}
