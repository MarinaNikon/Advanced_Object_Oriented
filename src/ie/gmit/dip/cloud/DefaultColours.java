package ie.gmit.dip.cloud;

/**
 * @author Nikonchuk Marina
 */

public enum DefaultColours {

    COLORS("#8A2BE2", "#5F9EA0", "#FF7F50", "#DC143C",
            "#008B8B","#006400", "#556B2F", "#566D7E", "#74557D",
            "#B091B9", "#800000", "#DC143C", "#8B4789", "#FF00FF",
            "#8A2BE2", "#0000FF", "#1C86EE", "#008B45", "#00FF00",
            "#008000", "#FFFF00", "#EE7600", "#EE4000", "#FF0000");

    private final String[] list;

    DefaultColours(String... list) {
        this.list = new String[list.length];
        System.arraycopy(list, 0, this.list, 0, list.length);
    }

    public String[] list() {
        return list;
    }
}
