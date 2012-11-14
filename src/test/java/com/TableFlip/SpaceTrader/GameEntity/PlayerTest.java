package com.TableFlip.SpaceTrader.GameEntity;

import GUI.GuiArbiter;
import com.TableFlip.SpaceTrader.Model.Enums;
import com.TableFlip.SpaceTrader.Model.Good;
import com.TableFlip.SpaceTrader.Model.Island;
import com.TableFlip.SpaceTrader.Model.Port;
import com.TableFlip.SpaceTrader.Service.GoodsRegistry;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Dannielle
 * Date: 11/14/12
 * Time: 3:27 PM
 */
public class PlayerTest extends TestCase {

    Player _player;
    @Before
    public void setUp() throws Exception {
        GuiArbiter.newCharacter("Captain Butts", 5, 5, 5, 5);
        _player = Player.getInstance();
        com.TableFlip.SpaceTrader.Bootstrap.Bootstrapper.generateOcean();

        Ocean ocean = Ocean.getInstance();
        List<Island> islands = ocean.getIslands();
        Random random = new Random();
        int pickIsland = random.nextInt(islands.size());
        Island island = islands.get(pickIsland);
        int pickPort = random.nextInt(island.getPorts().size());
        Port port = island.getPorts().get(pickPort);

        _player.setCurrentPort(port);
    }

    @Test
    public void testBuy() throws Exception {
        GoodsRegistry goodsRegistry = GoodsRegistry.getInstance();
        Map<Good, HashMap<Enums.MarketValues, Integer>> localMarket = GuiArbiter.getLocalMarket();
        int i = 0;
        Good good = GuiArbiter.getGood(i);
        while (localMarket.get(good) == null) {
            i++;
        }
        assertEquals("More goods than possible can be bought", false, _player.buy( good, 1000));
    }
}
