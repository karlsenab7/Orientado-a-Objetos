package application.classes;

// import java.security.Principal;
// import java.time.temporal.TemporalAdjuster;
import java.util.*;

// import javax.swing.Icon;

// import jdk.tools.jaotc.StubInformation;
public class Skill {
    private String ID;
    private String name;
    private List<Element> element;
    private int power;
    private int mastery;
    private String description;
    private boolean isUnique;
    private String uniqueTo;
    private int slot;
    private String icon;
    //Konstruktor
    public Skill(String ID, String name, List<Element> element, int power, int mastery, String desc, boolean isUnique, String uniqueTo, String icon){
        this.ID = ID;
        this.name = name;
        this.element = new ArrayList<>();
        this.element.addAll(element);
        this.power = power;
        this.mastery = mastery;
        this.description = desc;
        this.uniqueTo = uniqueTo;
        this.isUnique = isUnique;
        this.icon = icon;
        this.slot = -1;
    }
    // getter
    public String getID(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public List<Element> getElement(){
        return element;
    }
    public int getPower(){
        return power;
    }
    public int getMastery(){
        return mastery;
    }
    public String getDescription(){
        return description;
    }
    // Lain-Lain
    public void masteryUp(){
        mastery++;
    }

    public String getIcon()
    {
        return this.icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public void setPower(int power)
    {
        this.power = power;
    }

    public void setSlot(int slot)
    {
        this.slot = slot;
    }

    public int getSlot()
    {
        return this.slot;
    }

    // public void setMastery(int mastery)
    // {
    //     this.mastery = mastery;
    // }

    public boolean canBeLearned(Element element){
        for(Element el : this.element)
        {
            if (el.get_element().equals(element.get_element()))
            {
                return true;
            }
        }
        return false;
    }
    public void print(){
        System.out.println("ID : " + this.ID); 
        System.out.println("Nama: " + name);
        System.out.println("Elements: ");
        for(Element e : element){
            System.out.println(e.get_element());
        }
        System.out.println("Power: " + power);
        System.out.println("Mastery: " + mastery);
        System.out.println("Deskripsi: " + description);
        System.out.println("Slot : " + slot);
        if(isUnique){
            System.out.println("Unique to: " + uniqueTo);
        }
    }
    public void setMastery(int mastery){
        this.mastery = mastery;
    }
    public static Skill clone(Skill s)
    {
        Skill temp = new Skill(s.ID, s.name, s.element, s.power, s.mastery, s.description, s.isUnique, s.uniqueTo, s.icon);
        return temp;
    }

    public static boolean isEqual(Skill s1, Skill s2)
    {
//        boolean sameElement = true;
        if (s1.element.size() != s2.element.size())
        {
            return false;
        }
        else
        {
            for (int i = 0; i < s1.element.size(); i++)
            {
                String name1 = s1.element.get(i).get_element();
                String name2 = s2.element.get(i).get_element();
                if (!name1.equals(name2))
                    return false;
            }
        }
        return s1.getID().equals(s2.getID()) &&
                s1.getName().equals(s2.getName()) &&
                s1.getPower() == s2.getPower() &&
                s1.getMastery() == s2.getMastery() &&
                s1.getDescription().equals(s2.getDescription());
    }
}