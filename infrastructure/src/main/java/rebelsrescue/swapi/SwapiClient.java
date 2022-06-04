package rebelsrescue.swapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rebelsrescue.fleet.StarShip;
import rebelsrescue.fleet.spi.StarShipInventory;
import rebelsrescue.swapi.model.SwapiResponse;
import rebelsrescue.swapi.model.SwapiStarShip;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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

        return convertSwapiResponseToStarShips(swapiResponse);
    }

    private List<StarShip> convertSwapiResponseToStarShips(SwapiResponse swapiResponse) {
        return swapiResponse.results().stream()
                .filter(hasValidPassengersValue())
                .map(toStarShip())
                .collect(toList());
    }

    private Function<SwapiStarShip, StarShip> toStarShip() {
        return swapiStarShip ->
                new StarShip(
                        swapiStarShip.name(),
                        parseInt(swapiStarShip.passengers().replaceAll(",", "")));
    }

    private Predicate<SwapiStarShip> hasValidPassengersValue() {
        return swapiStarShip -> swapiStarShip.passengers() != null
                && !swapiStarShip.passengers().equalsIgnoreCase("n/a")
                && !swapiStarShip.passengers().equalsIgnoreCase("unknown");
    }

    private SwapiResponse getStarShipsFromSwapi() {
        return restTemplate.getForObject(swapiBaseUri + "/api/starships", SwapiResponse.class);
    }
}
