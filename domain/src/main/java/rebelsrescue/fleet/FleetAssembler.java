package rebelsrescue.fleet;

import ddd.DomainService;
import rebelsrescue.fleet.api.AssembleAFleet;
import rebelsrescue.fleet.spi.Fleets;
import rebelsrescue.fleet.spi.StarShipInventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparingInt;

@DomainService
public class FleetAssembler implements AssembleAFleet {
    private final StarShipInventory starshipsInventory;
    private final Fleets fleets;
    static final BigDecimal MINIMAL_CARGO_CAPACITY = new BigDecimal("100000");

    public FleetAssembler(StarShipInventory starShipsInventory, Fleets fleets) {
        this.starshipsInventory = starShipsInventory;
        this.fleets = fleets;
    }

    @Override
    public Fleet forPassengers(int numberOfPassengers) {
        List<StarShip> starShips = retrieveStarShips();

        List<StarShip> rescueStarShips = selectStarShips(numberOfPassengers, starShips);

        return fleets.save(new Fleet(rescueStarShips));
    }

    private List<StarShip> selectStarShips(int numberOfPassengers, List<StarShip> starShips) {

        starShips.removeIf(doesntHaveEnoughCargoCapacity());

        List<StarShip> rescueStarShips = new ArrayList<>();
        while (numberOfPassengers > 0) {
            var starShip = starShips.remove(0);
            numberOfPassengers -= starShip.capacity();
            rescueStarShips.add(starShip);
        }
        return rescueStarShips;
    }

    private Predicate<? super StarShip> doesntHaveEnoughCargoCapacity() {
        return starShip -> starShip.cargoCapacity().compareTo(MINIMAL_CARGO_CAPACITY) < 0;
    }

    private List<StarShip> retrieveStarShips() {
        List<StarShip> starships =
                starshipsInventory.starShips().stream()
                        .filter(starShip -> starShip.capacity() > 0)
                        .sorted(comparingInt(StarShip::capacity)).toList();
        return new ArrayList<>(starships);
    }
}
