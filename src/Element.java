package src;

public class Element
{
    private String el;
    public Element(String el){
        this.el = el;
    }

    public String get_element(){
        return this.el;
    }
    public double getAdvantage(Element el){
        String e1 = this.el;
        String e2 = el.get_element();
    
        int idxEl1 = -1;
        int idxEl2 = -1;
    
        if (e1.equals("Fire"))
            idxEl1 = 0;
        else if (e1.equals("Water"))
            idxEl1 = 1;
        else if (e1.equals("Electric"))
            idxEl1 = 2;
        else if (e1.equals("Ground"))
            idxEl1 = 3;
        else if (e1.equals("Ice"))
            idxEl1 = 4;
        
        if (e2.equals("Fire"))
            idxEl2 = 0;
        else if (e2.equals("Water"))
            idxEl2 = 1;
        else if (e2.equals("Electric"))
            idxEl2 = 2;
        else if (e2.equals("Ground"))
            idxEl2 = 3;
        else if (e2.equals("Ice"))
            idxEl2 = 4;
    
        double[][] adva = 
        {
            {1, 0, 1, 0.5, 2},
            {2, 1, 0, 1, 1},
            {1, 2, 1, 0, 1.5},
            {1.5, 1, 2, 1, 0},
            {0, 1, 0.5, 2, 1}
        };
    
        if (idxEl1 != -1 && idxEl2 != -1)
            return adva[idxEl1][idxEl2];
        else
            return -1;
    }
    public boolean isEqual(Element el2){
        return this.el.equals(el2.get_element());
    }
}