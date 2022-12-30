package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.TankStars;

import java.util.HashMap;

public class LoadingScreen implements Screen {
    private final TankStars app;

    private ShapeRenderer shapeRenderer;
    private float progress;
    public LoadingScreen(final TankStars app){
        this.app = app;
        this.shapeRenderer=new ShapeRenderer();
    }
    private void queueAssets(){
        //here we load 2nd page what we want to see;
        app.assets.load("background.png", Texture.class);
        app.assets.load("menu.png",Texture.class);
        app.assets.load("TanksSelect/Tankcover.png",Texture.class);
        app.assets.load("TanksSelect/tank.png",Texture.class);
        app.assets.load("TanksSelect/player1.png",Texture.class);
        app.assets.load("TanksSelect/player2.png",Texture.class);
        app.assets.load("TanksSelect/tankgrid.png",Texture.class);
        app.assets.load("pauseBG.png",Texture.class);
        app.assets.load("Green.png",Texture.class);
        app.assets.load("Left.png",Texture.class);
        app.assets.load("Right.png",Texture.class);
        app.assets.load("ui/uiskin.atlas", TextureAtlas.class);
    }
    @Override
    public void show() {
        System.out.println("Loading");
        this.progress=0f;
        queueAssets();
    }
    private void update(float delta){

        progress= MathUtils.lerp(progress,app.assets.getProgress(),.1f);

        if (app.assets.update() && progress>=app.assets.getProgress()-.001f){
            app.setScreen(app.splashScreen);
        }
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f,.25f,.25f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(32,app.camera.viewportHeight/2-8,app.camera.viewportWidth-64,16);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(32,app.camera.viewportHeight/2-8,progress*(app.camera.viewportWidth-64),16);
        shapeRenderer.end();
        app.batch.begin();
        app.font.draw(app.batch,"Screen: Loading",20,20);
        app.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
        shapeRenderer.dispose();
    }

}
