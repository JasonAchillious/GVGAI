package ontology.effects.binary;

import core.vgdl.VGDLSprite;
import core.content.InteractionContent;
import core.game.Game;
import core.logging.Logger;
import core.logging.Message;
import ontology.Types;
import ontology.effects.Effect;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 * Date: 04/11/13
 * Time: 15:56
 * This is a Java port from Tom Schaul's VGDL - https://github.com/schaul/py-vgdl
 */
public class AttractGaze extends Effect
{
    public boolean align = false;

    public AttractGaze(InteractionContent cnt)
    {
        this.parseParameters(cnt);
        setStochastic();
    }

    @Override
    public void execute(VGDLSprite sprite1, VGDLSprite sprite2, Game game)
    {
	if(sprite1 == null || sprite2 == null){
	    String[] className = this.getClass().getName().split(".");
	    Logger.getInstance().addMessage(new Message(Message.WARNING, "[" + className[className.length - 1]  + "] Either sprite1 or sprite2 is equal to null."));
	    return;
	}
	
        if(sprite1.is_oriented && sprite2.is_oriented)
        {
            if(game.getRandomGenerator().nextDouble() < prob) {
                sprite1.orientation = sprite2.orientation.copy();

                if(align)
                {
                    if(sprite1.orientation.equals(Types.DLEFT) || sprite1.orientation.equals(Types.DRIGHT))
                    {
                        //Need to align on the Y coordinate.
                        sprite1.rect = new Rectangle(sprite1.rect.x, sprite2.rect.y,
                                sprite1.rect.width, sprite1.rect.height);

                    }else{
                        //Need to align on the X coordinate.
                        sprite1.rect = new Rectangle(sprite2.rect.x, sprite1.rect.y,
                                sprite1.rect.width, sprite1.rect.height);
                    }
                }


            }
        }
    }
}
