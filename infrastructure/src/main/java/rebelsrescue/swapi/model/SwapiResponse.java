package rebelsrescue.swapi.model;

import java.util.List;

public record SwapiResponse(String next, List<SwapiStarShip> results) {}
