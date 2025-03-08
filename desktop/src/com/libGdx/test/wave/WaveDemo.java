package com.libGdx.test.wave;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libGdx.test.base.LibGdxTestMain;

/**
 * 水波纹效果
 */
public class WaveDemo extends LibGdxTestMain {
    public static void main(String[] args) {
        WaveDemo waveDemo = new WaveDemo();
        waveDemo.start();
    }

    @Override
    public void useShow(Stage stage) {
        super.useShow(stage);
        new ShockwaveBackground("shuoming.png");
    }
}
