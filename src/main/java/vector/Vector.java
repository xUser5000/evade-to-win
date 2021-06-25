package vector;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vector {
    private float x, y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
