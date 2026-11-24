package io.github.OOprojektG10;

import io.github.OOprojektG10.model.Deck;
import io.github.OOprojektG10.model.GameModel;
import io.github.OOprojektG10.model.Player;
import io.github.OOprojektG10.view.DrawPanel;
import com.badlogic.gdx.ApplicationAdapter;

import java.util.ArrayList;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    GameModel model = new GameModel(1000,
        new Deck(),
        new ArrayList<>(){new Player, new Player
        },
        200);
    DrawPanel draw = new DrawPanel(model);

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
