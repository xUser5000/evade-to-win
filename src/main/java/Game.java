import lombok.Setter;
import vector.Vector;
import lombok.Getter;
import particles.MoverParticle;
import particles.Particle;
import vector.VectorUtil;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Game {

    private int width, height;
    private Particle coin;
    private MoverParticle player;
    private List<MoverParticle> enemies;
    @Setter private Vector mouse;
    private int pointsCount;

    private static float DEFAULT_RADIUS = 25;
    private static float MAX_SPEED = 15;

    public Game (int width, int height) {
        this.width = width;
        this.height = height;
        init();
    }

    public void init () {
        coin = new Particle(
                new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                DEFAULT_RADIUS,
                Constants.COLOR_YELLOW
        );
        player = new MoverParticle(
                new Vector(width/2, height/2),
                DEFAULT_RADIUS,
                Constants.COLOR_BLUE,
                new Vector(0, 0)
        );
        enemies = new ArrayList<>();
        mouse = new Vector(width/2, height/2);
        pointsCount = 0;
    }

    public void update () {
        Vector diff = VectorUtil.subtract(mouse, player.getPosition());
        if (VectorUtil.magnitude(diff) < player.getRadius()) player.setVelocity(new Vector(0, 0));
        else {
            player.setVelocity(VectorUtil.scale(
                    VectorUtil.normalize(diff),
                    MAX_SPEED
            ));
        }
        player.update();
    }

    public void levelUp () {
        pointsCount++;
        coin = new Particle(
                new Vector(MathUtil.random(0, width), MathUtil.random(0, height)),
                DEFAULT_RADIUS,
                Constants.COLOR_YELLOW
        );
    }

    public boolean hasPlayerReachedCoin () {
        return collision(player, coin);
    }

    private boolean collision (Particle a, Particle b) {
        return VectorUtil.distance(a.getPosition(), b.getPosition()) <= a.getRadius() + b.getRadius();
    }
}
