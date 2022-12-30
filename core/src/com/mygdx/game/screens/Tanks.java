package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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

public class Tanks implements Screen {

    private final TankStars app;

    private Stage stage;
    private Skin skin;
    private TextButton buttonPlay,buttonstyle,buttonExit;
    private Image splashImg,player1,player2;
    int currentCharacter;

    private ShapeRenderer shapeRenderer;

    public Tanks(final TankStars app) {
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
        Texture splashTex = app.assets.get("TanksSelect/Tankcover.png",Texture.class);
        splashImg= new Image(splashTex);
        splashImg.setPosition(0, 0);
        stage.addActor(splashImg);
        initButtons();
        imagecomp();

    }



    private void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);
        stage.act(delta);
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
    private void imagecomp(){
        Texture splashTex = app.assets.get("TanksSelect/tank.png",Texture.class);
        Image Icon = new Image(splashTex);
        Icon.setPosition(100, 300);
        stage.addActor(Icon);
        Image newimage = new Image(splashTex);
        newimage.setPosition(900, 300);
        stage.addActor(newimage);
        Texture players = app.assets.get("TanksSelect/player1.png",Texture.class);
        Image player1 = new Image(players);
        player1.setPosition(100, 800);
        stage.addActor(player1);
        Texture player = app.assets.get("TanksSelect/player2.png",Texture.class);
        Image player2 = new Image(player);
        player2.setPosition(900, 800);
        stage.addActor(player2);
        Texture grid = app.assets.get("TanksSelect/tankgrid.png",Texture.class);
        Image tankgrid1= new Image(grid);
        tankgrid1.setPosition(100, 50);
        stage.addActor(tankgrid1);
        Image tankgrid2=new Image(grid);
        tankgrid2.setPosition(900,50);
        stage.addActor(tankgrid2);


    }
    private void initButtons() {
        buttonPlay=new TextButton("Play",skin,"default");
        buttonPlay.setPosition(800,50);
        buttonPlay.setSize(280,100);
        buttonPlay.addAction(sequence(alpha(0),parallel(fadeIn(.5f),moveBy(0,-20,.5f,Interpolation.pow5Out))));
        buttonPlay.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.setScreen(app.playGround);
            }
        });

        //click listener
        stage.addActor(buttonPlay);
        Texture player = app.assets.get("TanksSelect/player2.png",Texture.class);
        Image Tankspirite=new Image(player);
        Tankspirite.setPosition((stage.getWidth()-Tankspirite.getWidth())/2,
                (stage.getHeight()-Tankspirite.getHeight())/2);

        TextButton nextButton =new TextButton(">>>",skin,"default");
        nextButton.setPosition(900,450);
        stage.addActor(nextButton);
        TextButton prevButton = new TextButton("<<<",skin,"default");
        prevButton.setPosition(50,450);
        stage.addActor(prevButton);

    }
}