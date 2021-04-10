import java.util.*;
class Skill {
    private String ID;
    private String name;
    private List<Element> element;
    private int power;
    private int mastery;
    private String description;
    private boolean isUnique;
    private String uniqueTo;
    //Konstruktor
    public Skill(String ID, String name, List<Element> element, int power, String desc, boolean isUnique, String uniqueTo){
        this.ID = ID;
        this.name = name;
        this.element = new ArrayList<>();
        this.element.addAll(element);
        this.power = power;
        this.description = desc;
        this.uniqueTo = uniqueTo;
        this.isUnique = isUnique;
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
    public boolean canBeLearned(Element element){
        if(this.element.contains(element)){
            return true;
        }
        return false;
    }
    public void print(){
        System.out.println( ID); 
        System.out.println("Nama: " + name);
        System.out.println("Elements: ");
        for(Element e : element){
            System.out.println(e.get_element());
        }
        System.out.println("Power: " + power);
        System.out.println("Deskripsi: " + description);
        if(isUnique){
            System.out.println("Unique to: " + uniqueTo);
        }
    }
    public void setMastery(int mastery){
        this.mastery = mastery;
    }
};