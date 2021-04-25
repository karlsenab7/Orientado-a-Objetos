import java.util.List;

// import javax.xml.crypto.Data;

// import javax.xml.crypto.Data;

// import javax.xml.crypto.Data;

import src.Database;
import src.Engimon;
import src.GameManagement;
import src.Player;
import src.Skill;

public class testDatabase 
{
    public static void main(String[] args) {
        Database db = new Database();

        // List<Engimon> es = Database.getEngimonDB();
        // if (es == null)
        //     return;

        // for (Engimon engimon : es) {
        //     System.out.println(engimon.get_engimon_name());
        // }

        // List<Skill> ss = Database.getSkillDB();
        // if (ss == null)
        //     return;
        // for (Skill skill : ss) {
        //     skill.print();
        // }

        GameManagement gm = new GameManagement();
        GameManagement.getPeta().showPeta();
        

    }    
}
