package application.classes;

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
    
        if (e1.equals("fire"))
            idxEl1 = 0;
        else if (e1.equals("water"))
            idxEl1 = 1;
        else if (e1.equals("electric"))
            idxEl1 = 2;
        else if (e1.equals("ground"))
            idxEl1 = 3;
        else if (e1.equals("ice"))
            idxEl1 = 4;
        
        if (e2.equals("fire"))
            idxEl2 = 0;
        else if (e2.equals("water"))
            idxEl2 = 1;
        else if (e2.equals("electric"))
            idxEl2 = 2;
        else if (e2.equals("ground"))
            idxEl2 = 3;
        else if (e2.equals("ice"))
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