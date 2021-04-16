import java.util.Scanner;

public class Player {
    private String name;
    private Position position;
    private Position activeEngimonPosition = new Position();
    private int activeEngimonIdx;
    private Inventory<Skill> inventorySkill = new Inventory<>();

    private Inventory<Engimon> inventoryEngimon = new Inventory<>() {
        @Override
        public void addInventory(Engimon items) {
            current_capacity++;
            this.bagCount.add(1);
            this.bag.add(items);
        }
    };

    public Player(String playername) {
        this.position = new Position();
        this.name = playername;
        this.activeEngimonIdx = 0;

        Engimon eng = new Engimon();
        eng.set_engimon_name("Stater Pack Engimon");
        try {
            addEngimon(eng);
        } catch (Exception e) {
            // do nothing
        }
    }

    public void set_name(String name) {
        this.name = name;
    }

    public Engimon getActiveEngimon() {
        return this.inventoryEngimon.getInventory(this.activeEngimonIdx);
    }

    public Position getActiveEngimonPosition() {
        return this.activeEngimonPosition;
    }

    public void change_active_engimon(int engimonidx) {
        if (this.inventoryEngimon.get_total_stored_item() >= engimonidx + 1) {
            this.activeEngimonIdx = engimonidx;
        }
    }

    private void move(int dirX, int dirY) throws Exception {
        if (this.position.getY() + dirY < 0) {
            throw new Exception("Out of Boundaries");
        } else if (this.position.getX() + dirX < 0) {
            throw new Exception("Out of Boundaries");
        } else {
            this.position.setY(this.position.getY() + dirY);
            this.position.setX(this.position.getX() + dirX);
        }
    }

    public void moveUP() {
        move(0, -1);
    }

    public void moveDOWN() {
        move(0, 1);
    }

    public void moveLEFT() {
        move(-1, 0);
    }

    public void moveRIGHT() {
        move(1, 0);
    }

    public void showEngimon(int index) {
        Engimon engimon = inventoryEngimon.getInventory(index);

        // Ditampilkan di UI
        System.out.println(" ID : " + engimon.get_engimon_id());
        System.out.println(" Name : " + engimon.get_engimon_name());
        System.out.println(" species : " + engimon.get_engimon_species());
        System.out.println(" level : " + engimon.get_level());
        System.out.println(" exp : " + engimon.get_cum_exp());
        System.out.println();

        // Show Parent and its species
        System.out.println("Parent Name | Species");
        for (Engimon parent : engimon.get_engimon_parents()) {
            System.out.println(parent.get_engimon_name() + " | " + parent.get_engimon_species());
        }
        System.out.println();

        // Show Element
        System.out.print("Element : ");
        for (Element el : engimon.get_engimon_elements()) {
            System.out.print(el.get_element());
        }
        System.out.println();

        // Show Skill
        System.out.println("Skill : ");
        for (Skill skill : engimon.get_engimon_skills()) {
            System.out.println(skill.getName());
        }
    }

    public void addEngimon(Engimon newEngimon) throws Exception {
        this.inventoryEngimon.addInventory(newEngimon);
    }

    public void showSkillItemList() {
        // Menampilkan di UI
        if (!this.inventorySkill.isEmpty()) {
            for (Skill SI : inventorySkill.getInventory()) {
                System.out.print(SI.getName() + " | " + SI.getPower() + " | ");
                for (Element el : SI.getElement()) {
                    System.out.print(el.get_element() + " ");
                }
            }
            System.out.println();
        } else {
            System.out.println("You don't have any Skill Item!");
        }
    }

    public void useSkillItem(int idxSkill, int idxEngimon) {
        Skill item = inventorySkill.getInventory(idxSkill);
        Engimon engimon = inventoryEngimon.getInventory(idxEngimon);

        try {
            engimon.add_skill(item);
            inventorySkill.removeInventory(idxSkill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void breed(int engimon1, int engimon2) {
        try {
            Breed seed = new Breed(this.inventoryEngimon.getInventory(engimon1),
                    this.inventoryEngimon.getInventory(engimon2));
            // Menambahkan child ke Inventory
            this.addEngimon(seed.get_child());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void battle(Engimon enemy) {
        Battle newBattle = new Battle(activeEngimonIdx, enemy, inventoryEngimon, inventorySkill);
        newBattle.fight();
    }

    // Membuang X jumlah item skill
    public void discardItem(int idx, int amount) {
        inventorySkill.removeInventory(idx);
    }

    // Melepaskan Engimon
    public void discardEngimon(int index) {
        inventoryEngimon.removeInventory(index);
    }

    public void changeEngimonName(int index, String name) {
        inventoryEngimon.getInventory(index).set_engimon_name(name);
    }

    public void save() {
        // SAVE GAME
    }

    public boolean gameover() {
        return inventoryEngimon.isEmpty();
    }
}