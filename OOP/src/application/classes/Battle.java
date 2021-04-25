package application.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {
    private int idxOwnEngimon;
    private Engimon second;
    // private Inventory<Engimon> inventoryEngimon;
    // private Inventory<Skill> inventorySkill;

    public Battle(int e1, Engimon e2)//, Inventory<Engimon> &inventoryEngimon, Inventory<Skill> &inventorySKill)
    {
        this.idxOwnEngimon = e1;
        this.second = e2;
        // this.inventoryEngimon = inventoryEngimon;
        // this.inventorySkill = inventorySkill;
    }

    public int get_first()
    {
        return idxOwnEngimon;
    }

    public Engimon get_second()
    {
        return this.second;
    }

    public void set_first(int e)
    {
        this.idxOwnEngimon = e;
    }

    public void set_second(Engimon e)
    {
        this.second = e;
    }

    public float type_adv(Element el1, Element el2)
    {
        return (float) el1.getAdvantage(el2);
    }

    public float sum_power(Engimon e1, List <Element> els2) //Menunggu class skill
    {
        int l1 = e1.get_level();
        List <Element> els1 = e1.get_engimon_elements();
        float maks = type_adv(els1.get(0), els2.get(0));
        for (int i = 0; i < els1.size(); i++)
        {
            for (int j = 0; j < els2.size(); j++)
            {
                float maks_tmp = type_adv(els1.get(i), els2.get(j));
                if (maks_tmp > maks)
                {
                    maks = maks_tmp;
                }
            }
        }
        int sum = e1.get_total_power();
        float sp = l1 * maks + sum;
        return sp;

    }

    public void fight()
    {
        float fp1 = sum_power(GameManagement.getPlayer().getInventoryEngimon().getInventory(idxOwnEngimon), second.get_engimon_elements());
        float fp2 = sum_power(second, GameManagement.getPlayer().getInventoryEngimon().getInventory(idxOwnEngimon).get_engimon_elements());
    
        System.out.println("Your Engimon Power : " + fp1);
        System.out.println("Enemy Power : " + fp2);
    
        //Memberi interface ke user
        if (fp1 > fp2)
        {
            win();
        }
        else
        {
            lose();
        }
    }

    public void win()
    {
        //Menampilkan interface
        System.out.println("\nYeay!!! You Win\n");
        get_reward();
    }

    public void lose()
    {
        //Menampilkan interface
        System.out.println("\nYou Lose :(\n");
        get_penalty();
    }

    public void get_reward()
    {
         List<Skill> skills = second.get_engimon_skills();
         Skill s = skills.get(0);
        // inventorySkill.addInventory(s);
        // inventoryEngimon.getInventory()[idxOwnEngimon].add_exp(40);
    
        System.out.println("REWARD : \n");
        System.out.println("Engimon : " + second.get_engimon_name() + " - Level " + second.get_level());
        System.out.println("Skill : " + s.getName() + " - Mastery Level " + s.getMastery());
        System.out.println();

    }

    public void get_penalty()
    {
        // inventoryEngimon.removeInventory(idxOwnEngimon);
        System.out.println("Your active engimon is dead!!\n\n");
    }

}
