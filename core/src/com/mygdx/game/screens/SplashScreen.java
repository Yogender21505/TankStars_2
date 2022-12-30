package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.TankStars;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class SplashScreen implements Screen {
    private final TankStars app;
    private Stage stage;

    private Image splashImg;
    public SplashScreen(final TankStars app){
        this.app = app;
        this.stage=new Stage(new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,app.camera));

    }

    @Override
    public void show() {
        System.out.println("Show");
        Gdx.input.setInputProcessor(stage);
        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                app.setScreen(app.mainMenuScreen);
            }
        };

        Texture splashTex = app.assets.get("background.png",Texture.class);
        splashImg= new Image(splashTex);
        splashImg.setPosition(0, 0);

        splashImg.addAction(sequence(parallel(fadeIn(2f, Interpolation.pow2)), fadeOut(1.25f), run(transitionRunnable)));

        stage.addActor(splashImg);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();

        app.batch.begin();
        app.font.draw(app.batch,"LOADING....................",850,400);
        app.batch.end();

    }
    public void update(float delta){
        stage.act(delta);

    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width,height,false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
