package application.classes;

import com.sun.nio.sctp.PeerAddressChangeNotification;
import javafx.geometry.Pos;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManagement {

    public static Player player;
    public static List<Engimon> engimonLiar;
    public static Peta peta;
    public static int moveCounter;

    public static Position spawnPosition[];
    public static Element spawnElement[];


    public GameManagement(boolean newGame)
    {
        moveCounter = 0;
        initSpawn();
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

    public static void initSpawn()
    {
        spawnPosition = new Position[6];
        spawnPosition[0] = new Position(2, 1);
        spawnPosition[1] = new Position(4, 9);
        spawnPosition[2] = new Position(10, 8);
        spawnPosition[3] = new Position(17, 9);
        spawnPosition[4] = new Position(20, 1);
        spawnPosition[5] = new Position(23, 7);

        spawnElement = new Element[6];
        spawnElement[0] = new Element("ice");
        spawnElement[1] = new Element("water");
        spawnElement[2] = new Element("fire");
        spawnElement[3] = new Element("electric");
        spawnElement[4] = new Element("water");
        spawnElement[5] = new Element("ground");

    }

    public static void incMove()
    {
        moveCounter++;
        if (moveCounter % 5 == 0)
        {
            if (moveCounter % 10 == 0)
                spawnMonster();
            else
                moveEngimon();
        }
    }

    public static int getMove()
    {
        return moveCounter;
    }

    public static void moveEngimon()
    {
        for (Engimon engimon : engimonLiar)
        {
            int timeToMove = randomInteger(1, 10);
            if (timeToMove < 6)
            {
                int pos = randomInteger(0, 3);
                Position prevPos = engimon.get_position();
                Position newPos;
                if (pos == 0)
                    newPos = new Position(prevPos.getX()+1, prevPos.getY());
                else if (pos == 1)
                    newPos = new Position(prevPos.getX()-1, prevPos.getY());
                else if (pos == 2)
                    newPos = new Position(prevPos.getX(), prevPos.getY()+1);
                else
                    newPos = new Position(prevPos.getX(), prevPos.getY()-1);

                if (!peta.canMove(newPos, engimon.get_engimon_elements()))
                    continue;

                if (getEngimonLiarInPos(newPos) != null)
                    continue;

                if (!isPlayerOrActiveEngimonInPos(newPos))
                    engimon.move(newPos);
            }
        }
    }

    public static void spawnMonster()
    {
        int x = randomInteger(0, 8);
        if (x < 6)
        {
            Position position = spawnPosition[x];

            if (isPlayerOrActiveEngimonInPos(position) || getEngimonLiarInPos(position) != null)
                return;

            Element element = spawnElement[x];
            List<Engimon> engimons = Database.getEngimonDBbyElement(element);
            if (engimons == null)
                return;
            int num = engimons.size();
            if (num > 0)
            {
                int idx = randomInteger(0, num-1);
                Engimon e = engimons.get(idx);
                e.set_position(position);
                engimonLiar.add(e);
            }

        }
    }

    public static void gameOver()
    {
        System.out.println("Game Over");
    }

    public static boolean isPlayerOrActiveEngimonInPos(Position position)
    {
        if (player.getPosition().getX() == position.getX() && player.getPosition().getY() == position.getY())
        {
            return true;
        }

        if (player.getActiveEngimonIdx() != -1)
        {
            if (player.getActiveEngimon().get_position().getX() == position.getX() && player.getActiveEngimon().get_position().getY() == position.getY())
            {
                return true;
            }
        }

        return false;
    }


    public static int randomInteger(int min, int max)
    {
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }


    public static void getNewGame()
    {
        try {
            engimonLiar = new ArrayList<>();
            player = new Player("User1");
            player.setPosition(new Position(13, 0));
            peta = new Peta();

            Engimon engimonAwal = Database.getEngimonDB().get(0);
            Skill s1 = Database.getSkillDB().get(0);
            Skill s2 = Database.getSkillDB().get(1);

            player.getInventorySkill().addInventory(s1);
            player.getInventorySkill().addInventory(s2);
            player.addEngimon(engimonAwal);

            spawnFirst();
        }
        catch (Exception e)
        {
            System.out.println("Exception in getNewGame");
            System.out.println(e.getMessage());
        }
    }

    public static void spawnFirst()
    {
        for (int i = 0; i < spawnPosition.length; i++)
        {
            Position position = spawnPosition[i];
            Element element = spawnElement[i];

            List<Engimon> engimons = Database.getEngimonDBbyElement(element);
            if (engimons == null)
                return;
            int num = engimons.size();
            if (num > 0)
            {
                int idx = randomInteger(0, num-1);
                Engimon e = engimons.get(idx);
                e.set_position(position);
                engimonLiar.add(e);
            }
        }
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
//                    System.out.println("Exceptisdadasdsa");
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
