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

public class Resume implements Screen {

    private final TankStars app;

    private Stage stage;
    private Skin skin;
    private TextButton buttonPlay,buttonResume,buttonResume2,buttonResume3;
    private Image splashImg,player1,player2;


    private ShapeRenderer shapeRenderer;

    public Resume(final TankStars app) {
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
        Texture splashTex = app.assets.get("Green.png",Texture.class);
        splashImg= new Image(splashTex);
        splashImg.setPosition(0, 0);
        stage.addActor(splashImg);
        initButtons();
        imagecomp();

    }



    private void update(float delta) {
        stage.act(delta);
    }
    private void imagecomp(){
        Texture splashTex = app.assets.get("Left.png",Texture.class);
        Image Icon = new Image(splashTex);
        Icon.setPosition(100, 300);
        stage.addActor(Icon);

        Texture splashT = app.assets.get("Right.png",Texture.class);
        Image cent = new Image(splashT);
        cent.setPosition(250, 300);
        stage.addActor(cent);

        Texture splash = app.assets.get("Left.png",Texture.class);
        Image newimage = new Image(splash);
        newimage.setPosition(950, 300);
        stage.addActor(newimage);

        Texture Resume3 = app.assets.get("Right.png",Texture.class);
        Image newResume = new Image(Resume3);
        newResume.setPosition(1100, 300);
        stage.addActor(newResume);

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
        buttonPlay=new TextButton("Resume Game 1",skin,"default");
        buttonPlay.setPosition(120,100);
        buttonPlay.setSize(280,100);
        buttonPlay.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.playGround);
            }
        });

        buttonResume=new TextButton("Resume Game 2",skin,"default");
        buttonResume.setPosition(560,100);
        buttonResume.setSize(280,100);
        buttonResume.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonResume.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        buttonResume2=new TextButton("Resume Game 3",skin,"default");
        buttonResume2.setPosition(1000,100);
        buttonResume2.setSize(280,100);
        buttonResume2.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonResume2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        buttonResume3=new TextButton("Resume Game 4",skin,"default");
        buttonResume3.setPosition(1440,100);
        buttonResume3.setSize(280,100);
        buttonResume3.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonResume3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.mainMenuScreen);
            }
        });

        //click listener
        stage.addActor(buttonPlay);
        stage.addActor(buttonResume);
        stage.addActor(buttonResume2);
        stage.addActor(buttonResume3);

    }
}