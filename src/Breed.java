package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Breed
 */

 public class Breed 
{
    private Engimon first;
    private Engimon second;
    private Engimon child;

    public Breed(Engimon e1, Engimon e2) throws Exception
    {
        this.first = e1;
        this.second = e2;

        if (e1.get_level() < 4 || e2.get_level() < 4)
            throw new Exception("Parents Level is not enough!!");
        
        this.child = breed();
    }

    public Engimon get_first()
    {
        return this.first;
    }

    public Engimon get_second()
    {
        return this.second;
    }

    public Engimon get_child()
    {
        return this.child;
    }

    public Engimon breed()
    {
        String name = getChildName();
        List<Skill> childSkills = inheritSkills();

        String species = "";
        List<Element> childElements = inheritElements(species);

        // AMBIL DARI DATABASE
        Engimon e = new Engimon();

        List<Skill> eSkill = e.get_engimon_skills();
        int idx = childSkills.indexOf(eSkill.get(0));

        if (idx != -1)
        {
            e.set_engimon_skills(childSkills);
        }
        else
        {
            for (Skill skill : childSkills) 
            {
                e.add_skill(skill);
            }
        }

        e.set_engimon_name(name);

        e.set_engimon_elements(childElements);

        List<String> parents = new ArrayList<String>();
        parents.add(first.get_engimon_name());
        parents.add(second.get_engimon_name());
        e.set_engimon_parentName(parents);

        List<String> parentSpecies = new ArrayList<String>();
        parentSpecies.add(first.get_engimon_species());
        parentSpecies.add(second.get_engimon_species());
        e.set_engimon_parentSpecies(parentSpecies);

        e.set_engimon_species(species);
        
        // MENGURANGI LEVEL INDUK

        first.set_level(first.get_level()-3);
        second.set_level(second.get_level()-3);
        
        return e;
    }

    public String getChildName()
    {
        String in;
        Scanner sc = new Scanner(System.in);
        System.out.print("Name : ");
        in = sc.nextLine();

        return in;
    }

    public List<Skill> inheritSkills()
    {        
        List<Skill> s1 = first.get_engimon_skills();
        List<Skill> s2 = second.get_engimon_skills();
        List<Skill> s = new ArrayList<Skill>();
        for (int i = 0; i < s1.size()+s2.size(); i++) 
        {
            if (i >= s2.size())
                s.add(s1.get(i-s2.size()));
            else
                s.add(s2.get(i));    
        }
        sortSkill(s);

        List<Skill> inherit = new ArrayList<Skill>();

        int count = 0;
        while (count < 3 && !s1.isEmpty() && !s2.isEmpty() && count < s.size() && !s.isEmpty())
        {
            int lastIdx = s.size()-1;
            Skill c = Skill.clone(s.get(0));
            s.remove(lastIdx);

            int idx1 = s1.indexOf(c);
            int idx2 = s2.indexOf(c);
            int idx = s.indexOf(c);

            if (idx1 != -1 && idx2 != -1 && idx != -1)
            {
                if (s1.get(idx1).getMastery() == s2.get(idx2).getMastery())
                {
                    if (c.getMastery() < 3)
                        c.setMastery(c.getMastery()+1);
                }
                else
                {
                    if (s1.get(idx1).getMastery() > s2.get(idx2).getMastery())
                    {
                        c = Skill.clone(s1.get(idx1));
                    }
                    else
                    {
                        c = Skill.clone(s2.get(idx2));
                    }
                }

                s1.remove(idx1);
                s2.remove(idx2);
                s.remove(idx);
            }
            else
            {
                if (idx1 != -1)
                    s1.remove(idx1);
                
                if (idx2 != -1)
                    s2.remove(idx2);
            }

            inherit.add(c);
            count++;
        }

        return inherit;
    }

    void sortSkill(List<Skill> s)
    {
        for (int i = 0; i < s.size()-1; i++) 
        {
            int idxMin = i;
            for (int j = i+1; j < s.size(); j++)
            {
                if (s.get(idxMin).getMastery() > s.get(j).getMastery())
                {
                    idxMin = j;
                }
            }    

            Skill temp = Skill.clone(s.get(i));
            Skill skillMin = Skill.clone(s.get(idxMin));
            s.set(i, skillMin);
            s.set(idxMin, temp);
        }
    }

    public List<Element> inheritElements(String species)
    {
        List<Element> els1 = first.get_engimon_elements();
        List<Element> els2 = second.get_engimon_elements();

        List<Element> inherit;

        // Single Element
        if (els1.size() + els2.size() == 2)
        {
            inherit = getKombinasiElements(species, els1.get(0), els2.get(0));
        }
        else // Dual Elements
        {
            inherit = getKombinasiElements(species, els1.get(0), els2.get(0));
        }

        return inherit;
    }

    public List<Element> getKombinasiElements(String species, Element el1, Element el2)
    {
        List<Element> inherit = new ArrayList<Element>();
        if (el1.isEqual(el2))
        {
            inherit.add(el1);
        }
        else
        {
            double n1 = el1.getAdvantage(el2);
            double n2 = el2.getAdvantage(el1);

            if (n1 == n2)
            {
                species = first.get_engimon_species();
                inherit.add(el1);
                inherit.add(el2);
            }
            else if (n1 > n2)
            {
                species = first.get_engimon_species();
                inherit.add(el1);
            }
            else
            {
                species = second.get_engimon_species();
                inherit.add(el2);
            }
        }

        return inherit;
    }
}