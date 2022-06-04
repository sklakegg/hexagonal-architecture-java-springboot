package rebelsrescue.swapi;

import java.util.List;

public record SwapiResponse(int count, String next, List<SwapiStarShip> results) {}
