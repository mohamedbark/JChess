package com.chess.engine.board;


import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final int tileCoordinate; // Protected so that it can be accessed by it's sub-classes.
                                        // final: once that it is set inside the constructor it can't be change

    private static final Map<Integer,EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer,EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer,EmptyTile> emptyTileMap = new HashMap<>();
        for(int i = 0; i< 64 ; i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }

    private Tile(int tileCoordinate){
        this.tileCoordinate =  tileCoordinate;
    }
    public static Tile create(final int tileCoordinate, final Piece piece){
        return piece!= null ? new OccupiedTile(tileCoordinate,piece): EMPTY_TILES_CACHE.get(tileCoordinate);
    }
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

    public static final class  EmptyTile extends Tile{
        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }
    public static final class OccupiedTile extends  Tile{
        private final Piece pieceOneTile;
        private OccupiedTile(int coordinate,Piece piece){
            super(coordinate);
            this.pieceOneTile = piece;
        }
        @Override
        public boolean isTileOccupied(){
            return true;
        }
        @Override
        public Piece getPiece() {
            return pieceOneTile;
        }
    }
}
