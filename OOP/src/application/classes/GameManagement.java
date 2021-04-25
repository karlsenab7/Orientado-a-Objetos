package application.classes;

import java.util.ArrayList;
import java.util.List;

public class GameManagement {

    private static Player player;
    private static List<Engimon> engimonLiar;
    private static Peta peta;

    public GameManagement()
    {
        engimonLiar = Database.getEngimonLiarDB();
        player = new Player("Karlsen");
        // System.out.println("dasasd");
        peta = new Peta();
        if (engimonLiar == null)
        {
            engimonLiar = new ArrayList<>();
        }
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

    public static Peta getPeta()
    {
        return peta;
    }

    public static boolean isEngimonLiar(Position p)
    {
        for (Engimon engimon : engimonLiar) {
            Position eP = engimon.get_position();
            if (eP.getX() == p.getX() && eP.getY() == p.getY())
                return true;
        }

        return false;
    }

    public static Engimon getEngimonLiarInPos(Position p)
    {
        for (Engimon engimon : engimonLiar) {
            Position eP = engimon.get_position();
            if (eP.getX() == p.getX() && eP.getY() == p.getY())
            {
                return engimon;
            }
        }

        return null;
    }
    
}
