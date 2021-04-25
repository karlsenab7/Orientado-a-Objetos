package application.classes;

import com.sun.nio.sctp.PeerAddressChangeNotification;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class GameManagement {

    public static Player player;
    public static List<Engimon> engimonLiar;
    public static Peta peta;

    public GameManagement(boolean newGame)
    {
        try {
            if (newGame) {
                getNewGame();
            }
            else {
                getContinueGame();
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception in GM");
            System.out.println(e.getMessage());
        }

    }

    public static void getNewGame()
    {
        engimonLiar = new ArrayList<>();
        player = new Player("User1");
        peta = new Peta();
    }

    public static void getContinueGame()
    {
        try
        {
            engimonLiar = Database.getEngimonLiarDB();
            player = new Player("Karlsen");

            boolean status = loadDatbase();
            if (!status)
            {
                System.out.println("Cannot load database, goto newGame");
                getNewGame();
                return;
            }
            peta = new Peta();
        }
        catch (Exception e)
        {
            System.out.println("Exception in getContinueGame");
            e.printStackTrace();
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

    public static boolean loadDatbase()
    {
        try {
            engimonLiar = Database.getEngimonLiarDB();
            if (engimonLiar == null)
            {
                System.out.println("EngimonLiar is Null in loadDatabase");
                engimonLiar = new ArrayList<>();
            }

            List<Engimon> playerEngimon = Database.getPlayerEngimonDB();
            List<Position> playerPosAndActivePos = Database.getPosPlayerPosActiveDB();
            if (playerEngimon == null)
            {
                System.out.println("player Engimon is NUlls in GM");
//            playerEngimon = new ArrayList<>();
            }
            if (playerPosAndActivePos == null)
            {
                System.out.println("Player pos playerPosAndActivePos is null in GM");
//            playerPosAndActivePos = new ArrayList<>();
//            playerPosAndActivePos.add(new Position(0, 0));
//            playerPosAndActivePos.add(new Position(-1, -1));
            }

            player.getPosition().setX(playerPosAndActivePos.get(0).getX());
            player.getPosition().setY(playerPosAndActivePos.get(0).getY());

            int count = 0;
            for (Engimon e : playerEngimon)
            {
//                System.out.println(e.get_engimon_name());
//                System.out.println(e.get_engimon_skills().size());
                if (e.get_active() == 1)
                {
                    e.get_position().setX(playerPosAndActivePos.get(1).getX());
                    e.get_position().setY(playerPosAndActivePos.get(1).getY());
                    player.addEngimon(e);
                    player.set_activeEngimonIdx(count);
                }
                else
                {
                    player.addEngimon(e);
                }

                count++;

            }

            List<Skill> playerEngimonSkill = Database.getPlayerSkillItemDB();
            if (playerEngimonSkill == null)
            {
                System.out.println("Player Engimon Skill is null in loadDatabase GM");
//                return;
            }

            for(Skill s : playerEngimonSkill)
            {
                player.getInventorySkill().addInventory(s);
            }

            return true;
        }
        catch (Exception e)
        {
            System.out.println("Exception in loadDatabase GM");
            System.out.println(e.getMessage());
            return false;
        }

    }
}
