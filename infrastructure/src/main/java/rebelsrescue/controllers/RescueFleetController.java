package rebelsrescue.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rebelsrescue.fleet.Fleet;
import rebelsrescue.fleet.api.AssembleAFleet;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/rescueFleets")
public class RescueFleetController {
    private AssembleAFleet assembleAFleet;

    public RescueFleetController(AssembleAFleet assembleAFleet) {
        this.assembleAFleet = assembleAFleet;
    }

    @PostMapping
    public ResponseEntity<Fleet> assembleAFleet(@RequestBody RescueFleetRequest rescueFleetRequest) throws URISyntaxException {
        var fleet = assembleAFleet.forPassengers(rescueFleetRequest.numberOfPassengers);
        return created(new URI("http://localhost")).body(fleet);
    }
}
