package reseau;

import java.awt.*;

public abstract class Element {

    protected Point position;

    public Element() {
    }

    public Element(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Element{" +
                "position=" + position +
                '}';
    }
}
