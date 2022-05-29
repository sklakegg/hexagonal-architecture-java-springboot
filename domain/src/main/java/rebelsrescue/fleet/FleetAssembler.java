package rebelsrescue.fleet;

import ddd.DomainService;
import rebelsrescue.fleet.api.AssembleAFleet;

@DomainService
public class FleetAssembler implements AssembleAFleet {
    @Override
    public Fleet forPassengers(int numberOfPassengers) {
        return null;
    }
}
