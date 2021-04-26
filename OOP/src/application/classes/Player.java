package application.classes;

import application.screen.ScreenController;
import javafx.fxml.FXMLLoader;

import java.util.List;
import java.util.Scanner;

// import javax.xml.crypto.Data;

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
        this.activeEngimonIdx = -1;
        inventorySkill = new Inventory<>();
        inventoryEngimon = new Inventory<>();
        activeEngimonIdx = -1;
        activeEngimonPosition = new Position();

        // Engimon eng = new Engimon();
        // eng.set_engimon_name("Stater Pack Engimon");
//        try {
//            Engimon e1 = Database.getEngimonDBbyID(1);
//            addEngimon(e1);
//        } catch (Exception e) {
//            // do nothing
//        }
    }

    public void set_name(String name) {
        this.name = name;
    }

    public Engimon getActiveEngimon() {
        return this.inventoryEngimon.getInventory(this.activeEngimonIdx);
    }

    public void set_activeEngimonIdx(int idx)
    {
        this.activeEngimonIdx = idx;
        if (idx != -1)
            this.inventoryEngimon.getInventory().get(idx).set_active(1);
    }

    public void switchActivEngimon(int idx)
    {
        if (idx < 0 || idx >= inventoryEngimon.getInventory().size())
            return;

        for (Engimon e : inventoryEngimon.getInventory())
        {
            e.set_active(0);
        }
        inventoryEngimon.getInventory().get(idx).set_active(1);
        activeEngimonIdx = idx;
        Position p1 = new Position(this.position.getX()+1, this.position.getY());
        Position p2 = new Position(this.position.getX()-1, this.position.getY());
        Position p3 = new Position(this.position.getX(), this.position.getY()+1);
        Position p4 = new Position(this.position.getX(), this.position.getY()-1);
        String content1 = GameManagement.getPeta().getCell(p1.getX(), p1.getY()).getCharCellContent();
        String content2 = GameManagement.getPeta().getCell(p2.getX(), p2.getY()).getCharCellContent();
        String content3 = GameManagement.getPeta().getCell(p3.getX(), p3.getY()).getCharCellContent();
        String content4 = GameManagement.getPeta().getCell(p4.getX(), p4.getY()).getCharCellContent();
        if (content1.equals("air"))
        {
            getActiveEngimon().set_position(p1);
        }
        else if (content2.equals("air"))
        {
            getActiveEngimon().set_position(p2);
        }
        else if (content3.equals("air"))
        {
            getActiveEngimon().set_position(p3);
        }
        else if (content4.equals("air"))
        {
            getActiveEngimon().set_position(p4);
        }
        else
        {
            getActiveEngimon().set_active(0);
            activeEngimonIdx = -1;
        }

    }

    public int getActiveEngimonIdx()
    {
        return activeEngimonIdx;
    }

    public Position getActiveEngimonPosition() {
        if (activeEngimonIdx == -1)
            return null;

        return inventoryEngimon.getInventory().get(activeEngimonIdx).get_position();
    }

    public Position getPosition()
    {
        return position;
    }

    public void change_active_engimon(int engimonidx) {
        if (this.inventoryEngimon.get_total_stored_item() >= engimonidx + 1) {
            this.activeEngimonIdx = engimonidx;
        }
    }

    private void move(int dirX, int dirY) throws Exception {
        if (this.position.getY() + dirY < 0 || this.position.getX() + dirX >= GameManagement.getPeta().getSizeX()) {
            throw new Exception("Out of Boundaries");
        } else if (this.position.getX() + dirX < 0 || this.position.getY() + dirY>= GameManagement.getPeta().getSizeY()) {
            throw new Exception("Out of Boundaries");
        } else {
            Position targetPos = new Position(this.position.getX()+dirX, this.position.getY()+dirY);
            Position prevPos = this.position;
            if (GameManagement.getEngimonLiarInPos(targetPos) != null)
                throw new Exception("Cannot move");

            this.position = new Position(targetPos.getX(), targetPos.getY());
            if (activeEngimonIdx != -1)
            {
                this.getActiveEngimon().set_position(prevPos);
            }
        }
    }

    public void moveUP() {

        try {
            move(0, -1);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }

    public void moveDOWN() {
        try {
            move(0, 1);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public void moveLEFT() {
        try {
            move(-1, 0);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
    }

    public void moveRIGHT() {
        try {
            move(1, 0);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println(e.getMessage());
        }
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
        // for (String parent : engimon.get_engimon_parentName()) {
        //     System.out.println(parent.get_engimon_name() + " | " + parent.get_engimon_species());
        // }
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
        newEngimon.set_active(0);
        this.inventoryEngimon.addInventory(newEngimon);
    }

    public void addSkillItem(Skill item) {
        List<Skill> skill = inventorySkill.getInventory();
        if (!skill.isEmpty()) {
            int i = 0;
            while (item.getPower() < skill.get(i).getPower()) {
                i++;
            }
            skill.add(i, item);
            return;
        }
        skill.add(item);
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
                    this.inventoryEngimon.getInventory(engimon2), "lalalal");
            // Menambahkan child ke Inventory
            this.addEngimon(seed.get_child());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void battle(Engimon enemy) {
        Battle newBattle = new Battle(activeEngimonIdx, enemy);
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

    public Inventory<Engimon> getInventoryEngimon()
    {
        return inventoryEngimon;
    }

    public Inventory<Skill> getInventorySkill()
    {
        return inventorySkill;
    }
}