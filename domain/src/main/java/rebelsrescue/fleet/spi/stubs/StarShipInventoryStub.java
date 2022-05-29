package rebelsrescue.fleet.spi.stubs;

import ddd.Stub;
import rebelsrescue.fleet.StarShip;
import rebelsrescue.fleet.spi.StarShipInventory;

import java.util.List;
import java.util.Objects;

@Stub
public final class StarShipInventoryStub implements StarShipInventory {
    private final List<StarShip> starShips;

    public StarShipInventoryStub(List<StarShip> starShips) {
        this.starShips = starShips;
    }

    public List<StarShip> starShips() {
        return starShips;
    }

}
