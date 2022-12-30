package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.TankStars;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class Pause implements Screen {

    private final TankStars app;

    private Stage stage;
    private Skin skin;
    private TextButton buttonPlay,buttonResume,buttonExit;
    private Image splashImg,player1,player2;


    private ShapeRenderer shapeRenderer;

    public Pause(final TankStars app) {
        this.app = app;
        this.stage = new Stage(new FitViewport(TankStars.V_WIDTH, TankStars.V_HEIGHT, app.camera));
        this.shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        this.skin=new Skin();
        this.skin.addRegions(app.assets.get("ui/uiskin.atlas",TextureAtlas.class));
        this.skin.add("default-font",app.font);
        this.skin.load(Gdx.files.internal("ui/uiskin.json"));
        Texture splashTex = app.assets.get("pauseBG.png",Texture.class);
        splashImg= new Image(splashTex);
        splashImg.setPosition(0, 0);
        stage.addActor(splashImg);
        initButtons();

    }



    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        shapeRenderer.dispose();
    }
    private void initButtons() {
        buttonPlay=new TextButton("Resume",skin,"default");
        buttonPlay.setPosition(800,450);
        buttonPlay.setSize(280,100);
        buttonPlay.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.playGround);
            }
        });

        buttonResume=new TextButton("Save Data",skin,"default");
        buttonResume.setPosition(800,300);
        buttonResume.setSize(280,100);
        buttonResume.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonResume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        //click listener
        stage.addActor(buttonPlay);
        stage.addActor(buttonResume);


    }
}