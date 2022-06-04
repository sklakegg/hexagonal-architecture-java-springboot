package rebelsrescue.swapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rebelsrescue.fleet.StarShip;
import rebelsrescue.fleet.spi.StarShipInventory;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;

@Component
public class SwapiClient implements StarShipInventory {
    private final RestTemplate restTemplate;

    @Value("${swapi.base-uri}")
    private String swapiBaseUri;

    public SwapiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<StarShip> starShips() {
        var swapiResponse = getStarShipsFromSwapi();
        List<StarShip> starShips = swapiResponse.results().stream()
                .map(swapiStarShip -> new StarShip(swapiStarShip.name(), parseInt(swapiStarShip.passengers())))
                .collect(toList());
        return starShips;
    }

    private SwapiResponse getStarShipsFromSwapi() {
        return restTemplate.getForObject(swapiBaseUri + "/api/starships", SwapiResponse.class);
    }
}
