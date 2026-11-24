package io.github.OOprojektG10.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.github.OOprojektG10.model.Card;
import io.github.OOprojektG10.model.GameModel;

public class DrawPanel {
    Assets assets = new Assets();

    //behöver nåt objekt att operera på som parameter?
    private SpriteBatch spritebatch;
    private Sprite sprite;
    private OrthographicCamera camera;
    private GameModel dealer;
    FitViewport viewport;

    public DrawPanel(GameModel model) {
        this.dealer = model;
    }

    public void create() {

        assets.load();


        //camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //spritebatch = new SpriteBatch();
        //sprite = new Sprite(new Texture("Cards-debug-Spades.png"));
        //sprite.setPosition(300, 240);
        viewport = new FitViewport(8, 5);
        //cardTexture = new Texture("Cards-debug-Spades.png");
    }

    public void resize (int width, int height) {
        viewport.update(width, height, true);
    }


    public void render() {
        input();
        logic();
        draw();


        /*

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite.draw(batch);
        batch.end();
*/

    }

    private void input(){

    }
    private void logic(){

    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        spritebatch.setProjectionMatrix(viewport.getCamera().combined);
        spritebatch.begin();

        for (Card c : dealer.getRiver()) {
            spritebatch.draw(assets.getCard(c.getSuit(), c.translateRankToInt()), 0.67, 1);
        }

        spritebatch.end();
    }

    public void pause () {

    }


    public void resume () {

    }


    public void dispose() {
        /*
        texture = sprite.getTexture();
        texture.dispose();

        batch.dispose();
        */

    }

}

