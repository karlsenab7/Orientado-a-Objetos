package application.classes;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GameManagement {

    public static Player player;
    public static List<Engimon> engimonLiar;
    public static Peta peta;

    public GameManagement()
    {
        engimonLiar = Database.getEngimonLiarDB();
        player = new Player("Karlsen");

        if (player == null)
        {
            System.out.println("Player is null in GM");
            return;
        }

        try
        {
            peta = new Peta();
            if (engimonLiar == null)
            {
                engimonLiar = new ArrayList<>();
            }

            List<Engimon> playerEngimon = Database.getPlayerEngimonDB();
            if (playerEngimon == null)
            {
                System.out.println("player Engimon is NUlls in GM");
                return;
            }

            for (Engimon e : playerEngimon)
            {
//                System.out.println(e.get_engimon_name());
                player.addEngimon(e);
            }

            List<Skill> playerEngimonSkill = Database.getPlayerSkillItemDB();
            if (playerEngimonSkill == null)
            {
                System.out.println("Player Engimon Skill is null in GM");
                return;
            }

            for(Skill s : playerEngimonSkill)
            {
                player.getInventorySkill().addInventory(s);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception in GM");
            e.printStackTrace();
        }

        // System.out.println("dasasd");

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
