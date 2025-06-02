package titledemo;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tile.TitleGame;


public class TileRotation {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920*0.5f);
        config.width = (int) (1080*0.5f);
        new LwjglApplication(new TitleGame(), config);
    }
}
