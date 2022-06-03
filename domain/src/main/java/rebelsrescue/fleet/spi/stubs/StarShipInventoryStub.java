package rebelsrescue.fleet.spi.stubs;

import ddd.Stub;
import rebelsrescue.fleet.StarShip;
import rebelsrescue.fleet.spi.StarShipInventory;

import java.util.List;

import static java.util.Arrays.asList;

@Stub
public final class StarShipInventoryStub implements StarShipInventory {

    private static final List<StarShip> DEFAULT_STARSHIPS = asList(
            new StarShip("X-Wing", 0),
            new StarShip("Millennium Falcon", 6),
            new StarShip("Rebel transport", 90),
            new StarShip("Mon Calamari Star Cruisers", 1200),
            new StarShip("CR90 corvette", 600),
            new StarShip("EF76 Nebulon-B escort frigate", 800));

    private final List<StarShip> starShips;

    public StarShipInventoryStub() {
        starShips = DEFAULT_STARSHIPS;
    }

    public StarShipInventoryStub(List<StarShip> starShips) {
        this.starShips = starShips;
    }

    public List<StarShip> starShips() {
        return starShips;
    }

}
