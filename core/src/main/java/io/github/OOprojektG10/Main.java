package io.github.OOprojektG10;

import io.github.OOprojektG10.view.DrawPanel;
import com.badlogic.gdx.ApplicationAdapter;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    DrawPanel draw = new DrawPanel();

    @Override
    public void create() {
        draw.create();
    }

    public void resize(int width, int height) {
        draw.resize(width, height);
    }

    @Override
    public void render() {
        //mode.update????
        draw.render();
    }
    @Override
    public void dispose() {
        draw.dispose();
        //batch.dispose();
    }
}
