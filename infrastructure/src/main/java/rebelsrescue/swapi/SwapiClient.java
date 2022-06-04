package rebelsrescue.swapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rebelsrescue.fleet.StarShip;
import rebelsrescue.fleet.spi.StarShipInventory;

import java.util.Collections;
import java.util.List;

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
        return Collections.emptyList();
    }
}
