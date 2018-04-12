package enumtest;

/**
 *  Написать enum, который перечисляет планеты Солнечной системы и возвращает:
 *  массу планеты, ее радиус и радиус орбиты.
 */

public enum Planets {

    MERCURY (3.302e23, 2439.64, 0.2408467),
    VENUS(4.8690e24, 6051.59, 0.61519726),
    EARTH(5.972e24, 6378.1, 1.0000174),
    MARS(6.4191e23, 3397.00, 1.8808476),
    JUPITER(1.8987e27, 71492.68, 11.862615),
    SATURN(5.6851e26, 60267.14, 29.447498),
    URANUS(8.6849e25, 25557.25, 84.016846),
    NEPTUNE(1.0244e26, 24766.36, 164.79132);

    private final double mass; //kg
    private final double planetRadius; //km
    private final double orbit;//km


    Planets(double mass, double planetRadius, double orbit) {
        this.mass = mass;
        this.planetRadius = planetRadius;
        this.orbit = orbit;
    }

    public double getMass() {
        return mass;
    }

    public double getPlanetRadius() {
        return planetRadius;
    }

    public double getOrbit() {
        return orbit;
    }

    public String returnPlanetInfo(){
        return new StringBuilder("Planet name: ")
                .append(this)
                .append(" mass(kg): ")
                .append(this.getMass())
                .append(" orbit(km): ")
                .append(this.getOrbit())
                .append(" radius(km): ")
                .append(this.getPlanetRadius())
                .toString();
    }


}
