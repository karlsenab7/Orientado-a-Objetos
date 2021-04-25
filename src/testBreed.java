package src;

public class testBreed {
    public static void main(String[] args) 
    {
        try {

            Engimon e1 = new Engimon();
            Engimon e2 = new Engimon();
            
            e1.set_level(4);
            e2.set_level(6);
            Breed br = new Breed(e1, e2);
            Engimon child = br.get_child();
            e1 = br.get_first();
            e2 = br.get_second();

            System.out.println("Child level : " + child.get_level());
            System.out.println("Child name : " + child.get_engimon_name());
            System.out.println("First level : " + e1.get_level());
            System.out.println("Second level : " + e2.get_level());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
}
