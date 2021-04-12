import java.security.cert.TrustAnchor;
import java.util.*;

public class Engimon {
    private static int numOfCreated = 0;
    private int engimon_id;
    private String engimon_name;
    private String engimon_species;

    private ArrayList<Engimon> engimon_parents;
    private ArrayList<Element> engimon_elements;
    private ArrayList<Skill> engimon_skills;

    private int level;
    private int exp;
    private int cum_exp;
    private final int level_cap = 10;

    public Engimon() {
        engimon_name = "-";
        engimon_species = "-";
        level = 1;
        exp = 0;
        cum_exp = 0;

        engimon_elements = new ArrayList<Element>();
        engimon_parents = new ArrayList<Engimon>();
        engimon_skills = new ArrayList<Skill>();

        // Untuk testing
        Element el = new Element("fire");
        engimon_elements.add(el);
        List<Element> e = new ArrayList<Element>();
        e.add(new Element("fire"));
        Skill s = new Skill("ID", "name", e, 10, "Test", true, "Dragon");
        engimon_skills.add(s);
        
        numOfCreated += 1;
        engimon_id = numOfCreated;
    }

    public Engimon(String name, String species, ArrayList<Engimon> parents, ArrayList<Element> elements, ArrayList<Skill> skills, int _level, int _exp, int _cum_exp) {
        engimon_name = name;
        engimon_species = species;
        level = _level;
        exp = _exp;
        cum_exp = _cum_exp;

        int i;
        Element el;
        engimon_elements = new ArrayList<Element>();
        for (i = 0; i < elements.size(); i++) {
            engimon_elements.add(elements.get(i));
        }

        engimon_parents = new ArrayList<Engimon>();
        for (i = 0; i < parents.size(); i++) {
            engimon_parents.add(parents.get(i));
        }

        engimon_skills = new ArrayList<Skill>();
        for (i = 0; i < skills.size(); i++) {
            engimon_skills.add(skills.get(i));
        }

        numOfCreated += 1;
        engimon_id = numOfCreated;
    }

    // Note: tidak ada copy constructor dan operator overloading

    // Getter
    public int get_engimon_id() { return engimon_id; }
    public String get_engimon_name() { return engimon_name; }
    public String get_engimon_species() { return engimon_species; }

    public ArrayList<Engimon> get_engimon_parents() { return engimon_parents; }
    public ArrayList<Element> get_engimon_elements() { return engimon_elements; }
    public ArrayList<Skill> get_engimon_skills() { return engimon_skills; }

    public int get_level() { return level; }
    public int get_exp() { return exp; }
    public int get_cum_exp() { return cum_exp; }

    public int get_total_power() {
        int sum = 0;
        Skill skill;
        for (int i = 0; i < engimon_skills.size(); i++) {
            skill = engimon_skills.get(i);
            sum += skill.getMastery() * skill.getPower();
        }
        return sum;
    }

    // Setter
    public void set_engimon_id(int id) { engimon_id = id; }
    public void set_engimon_name(String name) { engimon_name = name; }
    public void set_engimon_species(String species) { engimon_species = species; }

    // Parents
    public void set_engimon_parents(List<Engimon> parents) {
        if (engimon_parents.size() == parents.size()) {
            for(int i = 0; i < engimon_parents.size(); i++) { engimon_parents.set(i, parents.get(i)); }
        }
    }
    // Add parent satu-satu
    public void add_parent(Engimon parent) {
        engimon_parents.add(parent);
    }

    // Element
    public void set_engimon_elements(List<Element> elements) {
        if (engimon_elements.size() == elements.size()) { // Biar ga error index out of bounds
            for(int i = 0; i < engimon_elements.size(); i++) { engimon_elements.set(i, elements.get(i)); }
        }
    }
    // Add element satu-satu
    public void add_element(Element element) {
        engimon_elements.add(element);
    }

    // Skills
    public void set_engimon_skills(List<Skill> skills) {
        if (engimon_skills.size() == skills.size()) { // Biar ga error index out of bounds
            for(int i = 0; i < engimon_skills.size(); i++) { engimon_skills.set(i, skills.get(i)); }
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
        if (engimon_skills.size() > 3) {
            throw new ArithmeticException("Skill limit of 4 reached, cannot add more skill");
            // JANGAN LUPA HANDLE EXCEPTION, bisa dengan method selanjutnya
        } else if (is_skill_learned(skill)) {
            System.out.println("Skill already learned");
        } else {
            for (Element el : engimon_elements) {
                if (skill.canBeLearned(el)) {
                    engimon_skills.add(skill);
                    break;
                }
            }
            return;
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

}
