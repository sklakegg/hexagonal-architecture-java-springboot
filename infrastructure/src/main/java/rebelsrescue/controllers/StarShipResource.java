package rebelsrescue.controllers;

import rebelsrescue.fleet.StarShip;

public record StarShipResource(String name, int capacity) {
    public StarShipResource(StarShip starShip) {
        this(starShip.name(), starShip.passengersCapacity());
    }
}
