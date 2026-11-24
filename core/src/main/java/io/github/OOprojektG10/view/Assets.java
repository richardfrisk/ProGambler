package io.github.OOprojektG10.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import io.github.OOprojektG10.model.Card;

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

    private TextureRegion[] loadSuit(String suit, int rank) {
        String filename;

        if (suit == "Spades"){
            filename = "Cards-debug-Spades.png";
        }
        else if (suit == "Clubs") {
            filename = "Cards-debug-Clubs.png";
        }
        else if (suit == "Diamond"){
            filename = "Cards-debug-Diamonds.png";
        }

        else if (suit == "Hearts"){
            filename = "Cards-debug-Hearts.png";
        }





        sheet = new Texture(filename);
        sheet.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        textureList.add(sheet);

        TextureRegion[][] cardGrid = TextureRegion.split(sheet, sheet.getWidth(), sheet.getHeight());
        TextureRegion[] cardFlat = new TextureRegion[cardWidth * cardHeight];

        int index = 0;
        for (int i = 0; i < cardHeight; i++) {
            for (int j = 0; j < cardWidth; j++) {
                cardFlat[index++] = cardGrid[i][j];

            }

            if (cardBack == null) {
                cardBack = cardFlat[13];
            }


        }
        return cardFlat;
    }



    public TextureRegion getCardSprite(Card card){

        return loadSuit(card.getSuit(), card.translateRankToInt());



    }

    public void getChip(){

    }

    public void getCardBack(){

    }

    public void dispose(){

    }
    //public void
}
