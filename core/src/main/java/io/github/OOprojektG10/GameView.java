package io.github.OOprojektG10;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class GameView {
    //behöver nåt objekt att operera på som parameter?
    private SpriteBatch batch;
    private Sprite sprite;
    private OrthographicCamera camera;

    public void create(){
        /*
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(""));
        sprite.setPosition();
        */
    }

    public void render(){
        /*
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        */
    }

    public void dispose(){
      /*
        texture = sprite.getTexture();
        texture.dispose();

        batch.dispose();


       */
    }
}

