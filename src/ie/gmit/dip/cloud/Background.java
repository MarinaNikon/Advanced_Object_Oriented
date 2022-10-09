package ie.gmit.dip.cloud;

/**
 * @author Nikonchuk Marina
 */

public enum Background {
    COLOR("#2f2f2f");

    private final String colour;

    Background(String colour) {
        this.colour = colour;
    }

    public String colour() {
        return colour;
    }
}
