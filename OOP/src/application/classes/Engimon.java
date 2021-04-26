package application.classes;

import java.security.Principal;
// import java.security.cert.TrustAnchor;
import java.util.*;

public class Engimon {
    private static int numOfCreated = 0;
    private int engimon_id;
    private String engimon_name;
    private String engimon_species;
    private Position position;

    // private ArrayList<Engimon> engimon_parents;
    private ArrayList<String> engimon_parentsName;
    private ArrayList<String> engimon_parentSpecies;
    private ArrayList<Element> engimon_elements;
    private ArrayList<Skill> engimon_skills;
    private String icon;
    private String deskripsi;

    private int live;
    private int level;
    private int exp;
    private int cum_exp;
    private final int level_cap = 10;
    private int active = 0;

    public Engimon() {
        engimon_name = "-";
        engimon_species = "-";
        level = 1;
        exp = 0;
        cum_exp = 0;
        this.icon = "1.png";
        position = new Position(0,0);

        engimon_elements = new ArrayList<Element>();
        engimon_parentsName = new ArrayList<String>();
        engimon_parentSpecies = new ArrayList<String>();
        engimon_skills = new ArrayList<Skill>();

        // Untuk testing
        Element el = new Element("fire");
        engimon_elements.add(el);
        List<Element> e = new ArrayList<Element>();
        e.add(new Element("fire"));
        Skill s = new Skill("ID", "name", e, 10, 1,"Test", true, "Dragon", "id.png");
        engimon_skills.add(s);
        
        numOfCreated += 1;
        // engimon_id = numOfCreated;
    }

    public Engimon(int id, String name, String species, ArrayList<String> parents, ArrayList<String> parentSpecies, ArrayList<Element> elements, ArrayList<Skill> skills, int _level, int _exp, int _cum_exp, String icon) {
        engimon_id = id;
        engimon_name = name;
        engimon_species = species;
        level = _level;
        exp = _exp;
        cum_exp = _cum_exp;
        this.icon = icon;
        int i;
        Element el;
        engimon_elements = new ArrayList<Element>();
        for (i = 0; i < elements.size(); i++) {
            engimon_elements.add(elements.get(i));
        }

        engimon_parentsName = new ArrayList<String>();
        for (i = 0; i < parents.size(); i++) {
            engimon_parentsName.add(parents.get(i));
        }

        engimon_parentSpecies = new ArrayList<String>();
        for (i = 0; i < parentSpecies.size(); i++) {
            engimon_parentSpecies.add(parentSpecies.get(i));
        }

        engimon_skills = new ArrayList<Skill>();
        for (i = 0; i < skills.size(); i++) {
            engimon_skills.add(skills.get(i));
        }
        position = new Position(0,0);
        numOfCreated += 1;
        // engimon_id = numOfCreated;
    }

    // Note: tidak ada copy constructor dan operator overloading

    // Getter
    public int get_engimon_id() { return engimon_id; }
    public String get_engimon_name() { return engimon_name; }
    public String get_engimon_species() { return engimon_species; }
    public String get_icon(){return this.icon; }
    public void set_icon(String icon) {this.icon = icon; }

    public int get_live(){return this.live; }
    public void set_live(int live) {this.live = live; }

    public String get_deskripsi() {return this.deskripsi;}
    public void set_deskripsi(String des) {this.deskripsi = des;}


    public ArrayList<String> get_engimon_parentName() { return engimon_parentsName; }
    public ArrayList<String> get_engimon_parentSpecies() { return engimon_parentSpecies; }
    public ArrayList<Element> get_engimon_elements() { return engimon_elements; }
    public ArrayList<Skill> get_engimon_skills() { return engimon_skills; }

    public int get_level() { return level; }
    public int get_exp() { return exp; }
    public int get_cum_exp() { return cum_exp; }

    public int get_active() {return active;}
    public void set_active(int active) {this.active = active;}

    public int get_total_power() {
        int sum = 0;
        Skill skill;
        for (Skill engimon_skill : engimon_skills) {
            skill = engimon_skill;
            sum += skill.getMastery() * skill.getPower();
        }
        return sum;
    }

    // Setter
    public void set_engimon_id(int id) { engimon_id = id; }
    public void set_engimon_name(String name) { engimon_name = name; }
    public void set_engimon_species(String species) { engimon_species = species; }

    // Parents
    public void set_engimon_parentName(List<String> parents) {
        engimon_parentsName.clear();
        engimon_parentsName.addAll(parents);
    }
    public void set_engimon_parentSpecies(List<String> parents) {
        engimon_parentSpecies.clear();
        engimon_parentSpecies.addAll(parents);
    }
    // Add parent satu-satu
    public void add_parentName(String parent) {
        engimon_parentsName.add(parent);
    }

    public void add_parentSpecies(String parent) {
        engimon_parentSpecies.add(parent);
    }

    public void set_position(Position p)
    {
        this.position = p;
    }

    public Position get_position()
    {
        return this.position;
    }

    // Element
    public void set_engimon_elements(List<Element> els) {
        engimon_elements.clear();
        engimon_elements.addAll(els);
    }
    // Add element satu-satu
    public void add_element(Element element) {
        engimon_elements.add(element);
    }

    // Skills
    public void set_engimon_skills(List<Skill> skills) {
        engimon_skills.clear();
        for (Skill skill : skills) {
            engimon_skills.add(skill);
        }
    }
    // Learned checker, utk method selanjutnya
    public boolean is_skill_learned(Skill skill) {
        // Masih string
        boolean learned = false;
        Skill sk;
        for(int i = 0; i < engimon_skills.size(); i++) {
            sk = engimon_skills.get(i);
            if (sk.getID() == skill.getID()) {
                learned = true;
                break;
            }
        }
        return learned;
    }
    // Tambah skill satu-satu
    public void add_skill(Skill skill) {
        // Masih string
        // TIDAK mengatur batasan elemen dalam belajar skill

        if (engimon_skills.size() > 4) {

            throw new ArithmeticException("Skill limit of 4 reached, cannot add more skill");
            // JANGAN LUPA HANDLE EXCEPTION, bisa dengan method selanjutnya
        } else if (is_skill_learned(skill)) {
            System.out.println("Skill already learned");
        } else {
            for (Element el : engimon_elements) {
                if (skill.canBeLearned(el)) {

                    skill.setSlot(engimon_skills.size());
                    engimon_skills.add(skill);
                    return;
                }
            }
//            System.out.println("Learning the skill .....");
        }
        System.out.println("Skill not learnable by engimon");
    }
    // Ganti skill indeks tertentu
    public void replace_skill(Skill skill, int index) {
        // Masih string
        if (is_skill_learned(skill)) {
            System.out.println("Skill already learned");
        } else {
            for (Element el : engimon_elements) {
                if (skill.canBeLearned(el)) {
                    engimon_skills.set(index, skill);
                    break;
                }
            }
            return;
        }
        System.out.println("Skill not learnable by engimon");
    }

    public void set_level(int _level) { level = _level; }
    public void set_exp(int _exp) { exp = _exp; }
    public void set_cum_exp(int _cum_exp) { cum_exp = _cum_exp; }

    public void add_exp(int add) {
        cum_exp += add;
        exp += add;
        while (exp > 100) {
            level += 1;
            exp -= 100;
        }
        if (level >= level_cap) {
            throw new ArithmeticException("Level cap reached");
            // JANGAN LUPA HANDLE EXCEPTION
        }
    }

    public static Engimon clone(Engimon s)
    {
        Engimon temp = new Engimon(s.engimon_id,
                s.engimon_name,
                s.engimon_species,
                new ArrayList<String>(),
                new ArrayList<String>(),
                new ArrayList<Element>(),
                new ArrayList<Skill>(),
                s.level, s.exp, s.cum_exp, s.icon);
        temp.set_engimon_parentName(s.engimon_parentsName);
        temp.set_engimon_parentSpecies(s.engimon_parentSpecies);
        temp.set_engimon_elements(s.engimon_elements);
        temp.set_engimon_skills(s.engimon_skills);
        temp.deskripsi = s.deskripsi;
        return temp;
    }

    public static boolean isEqual (Engimon e1, Engimon e2)
    {
        boolean skillSame = true;

        if (e1.engimon_skills.size() != e2.engimon_skills.size())
            return false;
        else
        {
            for (int i = 0; i < e1.engimon_skills.size(); i++)
            {
                if (!Skill.isEqual(e1.engimon_skills.get(i), e2.engimon_skills.get(i)))
                {
                    return  false;
                }
            }
        }

        if (e1.engimon_elements.size() != e2.engimon_elements.size())
        {
            return false;
        }
        else
        {
            for (int i = 0; i < e1.engimon_elements.size(); i++)
            {
                String name1 = e1.engimon_elements.get(i).get_element();
                String name2 = e2.engimon_elements.get(i).get_element();
                if (!name1.equals(name2))
                    return false;
            }
        }

        return false;
    }

    public void takeDamage(int i) {
        this.live -= i;
        if (live <= 0)
        {
            System.out.println("Engimon " + engimon_name + " is death");
            GameManagement.player.getInventoryEngimon().getInventory().remove(this);
            GameManagement.player.set_activeEngimonIdx(-1);
        }
    }
}
