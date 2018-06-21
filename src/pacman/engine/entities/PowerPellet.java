package pacman.engine.entities;

import pacman.engine.PacmanGame;
import pacman.engine.PacmanGame.State;
import java.awt.Rectangle;

public class PowerPellet extends Pellet {

    public PowerPellet(final PacmanGame game,
                       final int col,
                       final int row) {
        super(game);
        loadFrames("powerBall.png");
        setxPosition(col * 8 + 1 - 32);
        setyPosition((row + 3) * 8 + 1);
        setBoundingBox(new Rectangle(this.getxPosition() + 2, this.getyPosition() + 2, 4, 4));
        this.eaten = false;
    }

    @Override
    public void update() {
        setVisible((getGame().getState() == State.READY ||
                    getGame().getState() == State.PLAYING) &&
                    !this.eaten);
        if (!(this.eaten || getGame().getState() == State.PACMAN_DIED)) {
            if (getGame().pacmanEatsPellet(this)) {
                this.eaten = true;
                getGame().incrementFoodCount();
                setVisible(false);
                getGame().addScore(50);
                getGame().startGhostVulnerableMode();
            }
        }
    }

}
