/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iaj.fbla2017.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.iaj.fbla2017.map.utils.Room;

/**
 *
 * @author Jan Fic
 */
public class BasicRoomScreen implements Screen {

    Room room;
    TiledMap map;
    private final IsometricTiledMapRenderer mapRenderer;
    private final SpriteBatch batch;
    private final Game game;
    ShapeRenderer sr;

    public BasicRoomScreen(Game g) {
        this.game = g;
        sr = new ShapeRenderer();
        batch = new SpriteBatch();

        map = new TmxMapLoader().load("map/school/schoolRooms/test.tmx");
        room = new Room(map, new FitViewport(1200,800));
        mapRenderer = new IsometricTiledMapRenderer(map, 1, batch);
        ((OrthographicCamera) (this.room.getViewport().getCamera())).zoom = 0.75f;
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(0);
        this.room.getViewport().getCamera().position.set(layer.getWidth() * layer.getTileWidth() / 2, 0, 0);
        this.room.getViewport().getCamera().update();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        mapRenderer.setView((OrthographicCamera) room.getViewport().getCamera());
        mapRenderer.render();

        room.act();
        room.draw();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.BLACK);
        sr.line(-50, 0, 50, 0);
        sr.line(0, 50, 0, -50);
        //sr.line(-50, 0, 50, 0);
        sr.end();

    }

    @Override
    public void resize(int width, int height) {
        room.getViewport().update(width, height);
        
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
    }

}
