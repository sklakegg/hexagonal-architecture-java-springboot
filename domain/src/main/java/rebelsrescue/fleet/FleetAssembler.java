package rebelsrescue.fleet;

import ddd.DomainService;
import rebelsrescue.fleet.api.AssembleAFleet;
import rebelsrescue.fleet.spi.StarShipInventory;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparingInt;

@DomainService
public class FleetAssembler implements AssembleAFleet {
    private final StarShipInventory starshipsInventory;

    public FleetAssembler(StarShipInventory starShipsInventory) {
        this.starshipsInventory = starShipsInventory;
    }

    @Override
    public Fleet forPassengers(int numberOfPassengers) {
        List<StarShip> starShips = retrieveStarShips();

        List<StarShip> rescueStarShips = selectStarShips(numberOfPassengers, starShips);

        return new Fleet(rescueStarShips);
    }

    private List<StarShip> selectStarShips(int numberOfPassengers, List<StarShip> starShips) {
        List<StarShip> rescueStarShips = new ArrayList<>();
        while (numberOfPassengers > 0 ){
            var starShip = starShips.remove(0);
            numberOfPassengers -= starShip.capacity();
            rescueStarShips.add(starShip);
        }
        return rescueStarShips;
    }

    private List<StarShip> retrieveStarShips() {
        List<StarShip> starships =
                starshipsInventory.starShips().stream()
                        .filter(starShip -> starShip.capacity() > 0)
                        .sorted(comparingInt(StarShip::capacity)).toList();
        return new ArrayList<>(starships);
    }
}
