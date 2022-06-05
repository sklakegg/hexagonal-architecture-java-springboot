package rebelsrescue.fleet;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import rebelsrescue.fleet.api.AssembleAFleet;
import rebelsrescue.fleet.spi.Fleets;
import rebelsrescue.fleet.spi.StarShipInventory;
import rebelsrescue.fleet.spi.stubs.InMemoryFleets;
import rebelsrescue.fleet.spi.stubs.StarShipInventoryStub;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class AssembleAFleetFunctionalTest {

    @Test
    void should_assemble_a_fleet_for_1050_passengers() {
        //Given
        var starShips = asList(
                new StarShip("no-passenger-ship", 0),
                new StarShip("xs", 10),
                new StarShip("s", 50),
                new StarShip("m", 200),
                new StarShip("l", 800),
                new StarShip("xl", 2000));
        var numberOfPassengers = 1050;

        StarShipInventory starShipsInventory = new StarShipInventoryStub(starShips);
        Fleets fleets = new InMemoryFleets();
        AssembleAFleet assembleAFleet = new FleetAssembler(starShipsInventory, fleets);

        //When
        Fleet fleet = assembleAFleet.forPassengers(numberOfPassengers);

        //Then
        System.out.println(fleet);
        assertThat(fleet.starships())
                .has(enoughCapacityForThePassengers(numberOfPassengers))
                .allMatch(hasPassengersCapacity());

        assertThat(fleets.getById(fleet.id())).isEqualTo(fleet);
    }

    private Predicate<? super StarShip> hasPassengersCapacity() {
        return starShip -> starShip.capacity() > 0;
    }

    private Condition<? super List<? extends StarShip>> enoughCapacityForThePassengers(int numberOfPassengers) {
        return new Condition<>(
                starShips ->
                        starShips.stream()
                                .map(StarShip::capacity)
                                .reduce(0, Integer::sum) >= numberOfPassengers,
                "capacity check");
    }

}
