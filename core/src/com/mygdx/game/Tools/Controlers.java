package com.mygdx.game.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;
import com.mygdx.game.screens.PlayGround;


public class Controlers {

    Viewport viewport;
    private final TankStars app;
    Stage stage;
    boolean upPressed;
    OrthographicCamera cam;
    private PlayGround playGround;

    public Controlers(TankStars app){
        this.app = app;
        cam= new OrthographicCamera();
        viewport = new FitViewport(1900,900,cam);

        stage = new Stage(viewport,app.batch);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.right().bottom();
        Image upImg= new Image(new Texture("Right.png"));
        upImg.setSize(50,50);
        upImg.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Controlers.this.app.setScreen(Controlers.this.app.options);
            }
        });
        table.add();
        table.add(upImg).size(upImg.getWidth(),upImg.getHeight());
        table.row().pad(5);

        stage.addActor(table);


    }
    public void draw(){
        stage.draw();
    }

    public boolean isUpPressed() {
        return upPressed;
    }
    public void resize(int width,int height){
        viewport.update(width, height);
    }
}
