package src;

import java.util.ArrayList;
import java.util.List;

public class GameManagement {

    private static Player player;
    private static List<Engimon> engimonLiar;

    public GameManagement()
    {
        player = new Player("Karlsen");
        engimonLiar = new ArrayList<>();
    }

    public static void addEngimonLiar(Engimon e)
    {
        engimonLiar.add(e);
    }

    public static List<Engimon> getEngimonLiar()
    {
        return engimonLiar;
    }

    public static Player getPlayer()
    {
        return player;
    }
    
}
