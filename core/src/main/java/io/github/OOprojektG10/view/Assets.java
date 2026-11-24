package io.github.OOprojektG10.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.List;
import java.util.Map;

public class Assets {
    private static final int cardWidth = 4, cardHeight = 5;
    //Texture cardTexture;
    Texture sheet;
    List<Texture> textureList;
    TextureRegion cardBack;
    Map<String, TextureRegion[]> suits;

    public void load(){



    }

    private void loadSuit(String suit, String filename){

        sheet =  new Texture(filename);
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        textureList.add(sheet);

        TextureRegion[][] cardGrid = TextureRegion.split(sheet, sheet.getWidth(), sheet.getHeight());
        TextureRegion[] cardFlat = new TextureRegion[cardWidth * cardHeight];

        int index = 0;
        for (int i = 0; i < cardHeight; i++){
            for (int j = 0; j < cardWidth; j++){
                cardFlat[index++] = cardGrid[i][j];

            }

        if (cardBack == null){
            cardBack = cardFlat[13];
        }
        }



    }

    public void getCard(String suite, int rank){


    }

    public void getChip(){

    }

    public void getCardBack(){

    }

    public void dispose(){

    }
    //public void
}
