package application.classes;
import javafx.geometry.Pos;
import javafx.stage.PopupWindow;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// import javax.xml.crypto.Data;

// import javax.swing.Icon;

// import jdk.vm.ci.code.site.Site;

// import src.Engimon;
// import src.Skill;
// import src.Element;

/**
 * Database
 */
public class Database {

    public static String fileNameEngimon;
    public static String fileNameSkill;
    public static String fileNameEngimonSkillPlayer;
    public static String fileNamePlayerEngimon;
    public static String fileNamePlayerSkillItem;
    public static String fileNameEngimonLiar;
    public static String fileNameMapDB;
    public static String fileNamePosPlayerPosActive;

    public static String path = "src/application/db/";

    public Database()
    {
        fileNameEngimon = "engimon.txt";
        fileNameSkill = "skill.txt";
        fileNameEngimonSkillPlayer = "engimonSkillPlayer.txt";
        fileNamePlayerEngimon = "playerEngimon.txt";
        fileNamePlayerSkillItem = "playerSkillItem.txt";
        fileNameEngimonLiar = "engimonLiar.txt";
        fileNameMapDB = "mapSave.txt";
        fileNamePosPlayerPosActive = "posPlayerPosActive.txt";
    }

    public static List<Engimon> getEngimonDB()
    {
        int DATA_LENGTH = 6;
        try {
            List<Engimon> temp = new ArrayList<>();
            Scanner sc = new Scanner(new File(path + fileNameEngimon));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");
                
                if (data.length >= DATA_LENGTH)
                {
                    String idTemp = data[0];
                    String elementTemp = data[1];
                    String idSkillUniqueTemp = data[2];
                    String speciesTemp = data[3];
                    String deskripsiTemp = data[4];
                    String iconTemp = data[5];
                    
                    
                    int id = Integer.parseInt(idTemp);
                    String elementArray[] = elementTemp.split("/");
                    List<Element> els = new ArrayList<Element>();
                    for (String string : elementArray) {
                        Element el = new Element(string);
                        els.add(el);
                    }
                    
                    Skill skill = getSkillDBbyID(idSkillUniqueTemp);
                    skill.setSlot(0);
                    List<Skill> skills = new ArrayList<>();
                    skills.add(skill);
                    String species = speciesTemp;
                    String deskripsi = deskripsiTemp;
                    String icon = iconTemp;
                    
                    Engimon e = new Engimon();
                    
                    e.set_engimon_name(species);
                    e.set_engimon_id(id);
                    e.set_engimon_elements(els);
                    e.set_engimon_skills(skills);
                    e.set_engimon_species(species);
                    e.set_deskripsi(deskripsi);
                    e.set_icon(icon);

                    temp.add(e);
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public static HashMap<Integer, List<Skill>> getEngimonSkillPlayerDB()
    {

        int DATA_LENGTH = 5;
        try {
            HashMap<Integer, List<Skill>> temp = new HashMap<>();
            Scanner sc = new Scanner(new File(path + fileNameEngimonSkillPlayer));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");

                if (data.length >= DATA_LENGTH)
                {
//                    System.out.println(data[0]);
                    String idEngimonTemp = data[0];
                    String idSkillTemp = data[1];
                    String powerTemp = data[2];
                    String masteryTemp = data[3];
                    String slotTemp = data[4];
                    
                    int idEngimon = Integer.parseInt(idEngimonTemp);
                    String idSkill = idSkillTemp;
                    int power = Integer.parseInt(powerTemp);
                    int mastery = Integer.parseInt(masteryTemp);
                    int slot = Integer.parseInt(slotTemp);

                    Skill s = getSkillDBbyID(idSkill);
                    s.setPower(power);
                    s.setMastery(mastery);
                    s.setSlot(slot);

                    if (temp.containsKey(idEngimon))
                    {
//                        System.out.println("Add skill for engimon " + idEngimon);
                        temp.get(idEngimon).add(s);
                    }
                    else
                    {
//                        System.out.println("Engimon id in has map "+idEngimon);
                        List<Skill> ss = new ArrayList<>();
                        ss.add(s);
                        temp.put(idEngimon, ss);
                    }
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println("Exception in engimonSkillPlayer");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Skill> getEngimonSkillPlayerDBbyID(int id)
    {
        HashMap<Integer, List<Skill>> ls = getEngimonSkillPlayerDB();
        if (ls == null)
        {
            System.out.println("List skill getEngimonSKillplayerDBbyID is Null");
            return new ArrayList<Skill>();
        }
        try {
//            System.out.println("Length of hasmap " + ls.size());
            if (ls.get(id) == null)
            {
                System.out.println("ID "+ id + " Engimon SKill is nul in hasMap, in getEngimonSkillplayerdbbyid");
            }
            return ls.get(id);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Exception in getEngimonSkillPlayerDBbyID");
            System.out.println(e.getMessage());
            return new ArrayList<Skill>();
        }
    }

    public static Engimon getEngimonDBbyID(int id)
    {
        List<Engimon> en = getEngimonDB();
        if (en == null)
            return null;
        
        for (Engimon engimon : en) {
            if (engimon.get_engimon_id() == id)
                return engimon;
        }

        return null;
    }

    public static List<Engimon> getEngimonDBbyElement(Element element)
    {
        List<Engimon> en = getEngimonDB();
        if (en == null)
            return null;

        List<Engimon> engimonByElement = new ArrayList<>();
        for (Engimon engimon : en) {
            List<Element> els = engimon.get_engimon_elements();
            for (Element el : els)
            {
                if (el.get_element().equals(element.get_element())) {
                    engimonByElement.add(engimon);
                    break;
                }
            }
        }

        return engimonByElement;
    }

    public static Skill getSkillDBbyID(String id)
    {
        List<Skill> skills = getSkillDB();

        if (skills == null)
        {
            System.out.println("skills is null in getSkillDBbyID");
            return null;
        }

        for (Skill skill : skills) {
            if (skill.getID().equals(id))
                return skill;
        }

        return null;
    }

    public static List<Engimon> getPlayerEngimonDB()
    {

        int DATA_LENGTH = 8;
        try {
            List<Engimon> temp = new ArrayList<>();
            Scanner sc = new Scanner(new File(path + fileNamePlayerEngimon));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");
                
                if (data.length >= DATA_LENGTH)
                {
                    String idTemp = data[0];
                    String nameTemp = data[1];
                    String parentNameTemp = data[2];
                    String parentSpeciesTemp = data[3];
                    String levelTemp = data[4];
                    String liveTemp = data[5];
                    String cumExpTemp = data[6];
                    String activeTemp = data[7];

                    List<String> parentName = new ArrayList<>();
                    for (String string : parentNameTemp.split("/")) {
                        parentName.add(string);
                    }
                    
                    List<String> parentSpecies = new ArrayList<>();
                    for (String string : parentSpeciesTemp.split("/")) {
                        parentSpecies.add(string);
                    }

                    int idEngimon = Integer.parseInt(idTemp);
                    String name = nameTemp;
                    int level = Integer.parseInt(levelTemp);
                    int live = Integer.parseInt(liveTemp);
                    int cumExp = Integer.parseInt(cumExpTemp);
                    int active = Integer.parseInt(activeTemp);


                    List<Skill> engimonSkill = getEngimonSkillPlayerDBbyID(idEngimon);

                    // engimonSkill.get(0).print();
                    // System.out.println(12);
                    Engimon e = getEngimonDBbyID(idEngimon);
                    // if (e == null) System.out.println(5);
                    // System.out.println(6);
                    e.set_engimon_name(name);
                    e.set_engimon_parentName(parentName);
                    // System.out.println(9);
                    e.set_engimon_parentSpecies(parentSpecies);
                    e.set_level(level);
                    e.set_cum_exp(cumExp);
                    // System.out.println(10);
                    e.set_exp(cumExp % 100);
                    e.set_live(live);
                    e.set_active(active);
                    // System.out.println(10);
                    if (engimonSkill == null)
                        System.out.println("engimon skill is null, in getplayerengimon");
                    e.set_engimon_skills(engimonSkill);

                    // e.get_engimon_skills().get(0).print();
                    // System.out.println(4);
                    
                    // if (engimonSkill == null) System.out.println(4);
                    // System.out.println(3);
                    temp.add(e);
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println("Exception In getPlayerEngimon");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static Engimon getPlayerEngimonDBbyID(int id)
    {
        List<Engimon> ls = getPlayerEngimonDB();
        if (ls == null)
            return null;
        // System.out.print("2");
        for (Engimon engimon : ls) {
            if (engimon.get_engimon_id() == id)
                return engimon;
        }

        return null;
    }

    public static List<Skill> getSkillDB()
    {

        int DATA_LENGTH = 7;
        try {
            List<Skill> temp = new ArrayList<>();
            Scanner sc = new Scanner(new File(path + fileNameSkill));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");
                
                if (data.length >= DATA_LENGTH)
                {
                    String idTemp = data[0];
                    String elementTemp = data[1];
                    String nameTemp = data[2];
                    String basePowerTemp = data[3];
                    String baseMasteryTemp = data[4];
                    String deskripsiTemp = data[5];
                    String iconTemp = data[6];
                    
                    
                    String id = idTemp;
                    String elementArray[] = elementTemp.split("/");
                    List<Element> els = new ArrayList<Element>();
                    for (String string : elementArray) {
                        Element el = new Element(string);
                        els.add(el);
                    }

                    String name = nameTemp;
                    int basePower = Integer.parseInt(basePowerTemp);
                    int baseMastery = Integer.parseInt(baseMasteryTemp);
                    String deskripsi = deskripsiTemp;
                    String icon = iconTemp;
                    
                    Skill e = new Skill(id, name, els, basePower, baseMastery, deskripsi, false, "", icon);

                    temp.add(e);
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Skill> getPlayerSkillItemDB()
    {

        int DATA_LENGTH = 4;
        try {
            List<Skill> temp = new ArrayList<>();
            Scanner sc = new Scanner(new File(path + fileNamePlayerSkillItem));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");
                
                if (data.length >= DATA_LENGTH)
                {
                    String idTemp = data[0];
                    String powerTemp = data[1];
                    String masteryTemp = data[2];
                    String numTemp = data[3];
                    
                    
                    String id = idTemp;
                    int power = Integer.parseInt(powerTemp);
                    int mastery = Integer.parseInt(masteryTemp);
                    int num = Integer.parseInt(numTemp);
                    
                    Skill e = getSkillDBbyID(id);
                    e.setPower(power);
                    e.setMastery(mastery);

                    for (int i = 0; i < num; i++) {
                        temp.add(e);
                    }
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<Engimon> getEngimonLiarDB()
    {

        int DATA_LENGTH = 3;
        try {
            List<Engimon> temp = new ArrayList<>();
            Scanner sc = new Scanner(new File(path + fileNameEngimonLiar));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");
                
                if (data.length >= DATA_LENGTH)
                {
                    String idTemp = data[0];
                    String xTemp = data[1];
                    String yTemp = data[2];
                    
                    
                    int id = Integer.parseInt(idTemp);
                    int x = Integer.parseInt(xTemp);
                    int y = Integer.parseInt(yTemp);
                    
                    Engimon e = getEngimonDBbyID(id);
                    e.set_position(new Position(x, y));

                    temp.add(e);
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }

    public static List<Position> getPosPlayerPosActiveDB()
    {

        int DATA_LENGTH = 2;
        try {
            List<Position> temp = new ArrayList<>();
            Scanner sc = new Scanner(new File(path + fileNamePosPlayerPosActive));
            while (sc.hasNext())
            {
                String[] data = sc.nextLine().split(",");

                if (data.length >= DATA_LENGTH)
                {
                    String xTemp = data[0];
                    String yTemp = data[1];

                    int x = Integer.parseInt(xTemp);
                    int y = Integer.parseInt(yTemp);

                    Position p = new Position(x, y);
                    temp.add(p);
                }
            }

            return temp;

        } catch (Exception e) {
            System.out.println("Exception in getPosPlayerPosActiveDB");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void saveEngimonSkillPlayerDB()
    {
        try {
            PrintWriter writer = new PrintWriter(path + fileNameEngimonSkillPlayer, "UTF-8");
            int count = 0;
            for (Engimon e : GameManagement.player.getInventoryEngimon().getInventory())
            {
                if (e.get_engimon_skills().size() == 0)
                    continue;

                for (int i = 0; i < e.get_engimon_skills().size(); i++)
                {
                    Skill s = e.get_engimon_skills().get(i);

                    String data = "";
                    data += e.get_engimon_id() + ",";
                    data += s.getID() + ",";
                    data += s.getPower() + ",";
                    data += s.getMastery() + ",";
                    data += s.getSlot();
                    writer.println(data);
                    count++;
                }
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in saveEngimonSkillPlayerDB DB");
            System.out.print(e.getMessage());
        }
    }

    public static void savePosPlayerPosActiveDB()
    {
        try {
            PrintWriter writer = new PrintWriter(path + fileNamePosPlayerPosActive, "UTF-8");
            int count = 0;

            String data = "";
            data += GameManagement.player.getPosition().getX() + ",";
            data += GameManagement.player.getPosition().getY();

            writer.println(data);

            data = "";
            if (GameManagement.player.getActiveEngimonIdx() == -1)
            {
                data += "-1" + "," + "-1";
            }else
            {
                data += GameManagement.player.getActiveEngimonPosition().getX() + ",";
                data += GameManagement.player.getActiveEngimonPosition().getY();
            }

            writer.println(data);

            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in saveEngimonSkillPlayerDB DB");
            System.out.print(e.getMessage());
        }
    }

    public static void savePlayerEngimonDB()
    {
        try {
            PrintWriter writer = new PrintWriter(path + fileNamePlayerEngimon, "UTF-8");
            int count = 0;
            for (Engimon e : GameManagement.player.getInventoryEngimon().getInventory())
            {
                String data = "";
                data += e.get_engimon_id() + ",";
                data += e.get_engimon_name() + ",";

                String parent = "";
                if (e.get_engimon_parentName().size() == 2)
                    parent += e.get_engimon_parentName().get(0) + "/" + e.get_engimon_parentName().get(1);
                else if (e.get_engimon_parentName().size() == 1)
                    parent += e.get_engimon_parentName().get(0);
                else
                    parent += "null";
                data += parent + ",";

                String parentSpec = "";
                if (e.get_engimon_parentSpecies().size() == 2)
                    parentSpec += e.get_engimon_parentSpecies().get(0) + "/" + e.get_engimon_parentSpecies().get(1);
                else if (e.get_engimon_parentSpecies().size() == 1)
                    parentSpec += e.get_engimon_parentSpecies().get(0);
                else
                    parentSpec += "null";
                data += parentSpec + ",";

                data += e.get_level() + ",";
                data += e.get_live() + ",";
                data += e.get_cum_exp() + ",";
                data += e.get_active();

                writer.println(data);
                count++;
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in savePlayerEngimonDB");
            System.out.print(e.getMessage());
        }
    }

    public static void savePlayerSkillItemDB()
    {
        try {
            PrintWriter writer = new PrintWriter(path + fileNamePlayerSkillItem, "UTF-8");
            int count = 0;

            int n = GameManagement.getPlayer().getInventorySkill().getInventory().size();
            for (int i = 0; i < n; i++) {
                int num = GameManagement.player.getInventorySkill().getInventoryCount(i);
                Skill s = GameManagement.player.getInventorySkill().getInventory(i);

                String data = "";
                data += s.getID() + ",";
                data += s.getPower() + ",";
                data += s.getMastery() + ",";
                data += num;

                writer.println(data);
                count++;
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in savePlayerSkillItemDB");
            System.out.print(e.getMessage());
        }
    }

    public static void saveEngimonLiarDB()
    {
        try {
            PrintWriter writer = new PrintWriter(path + fileNameEngimonLiar, "UTF-8");
            int count = 0;
            for (Engimon e : GameManagement.engimonLiar)
            {
                String data = "";
                data += e.get_engimon_id() + ",";
                data += e.get_position().getX() + ",";
                data += e.get_position().getY();

                writer.println(data);
                count++;
            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Exception in savePlayerEngimonDB");
            System.out.print(e.getMessage());
        }
    }

    public static void saveDatabase()
    {
        saveEngimonSkillPlayerDB();
        savePlayerEngimonDB();
        savePlayerSkillItemDB();
        saveEngimonLiarDB();
        savePosPlayerPosActiveDB();
    }
}