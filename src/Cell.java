/**
 * Cell
 */

// getCharCell tunggu engimon dulu


enum CellType {grassland, sea};
enum Content {player, engimon, air};

public class Cell {

    private Position position;
    private CellType cellType;
    private Content content;
    private int idxEngimonInCell;

    public Cell(Position newPosition, CellType newCellType, Content newContent) {
    this.position = newPosition;
    this.cellType = newCellType;
    this.content = newContent;
    this.idxEngimonInCell = -1;
    }

    public Cell(int pX, int pY, CellType newCellType, Content newContent) {
    this.position.setX(pX);
    this.position.setY(pY);
    this.cellType = newCellType;
    this.content = newContent;
    this.idxEngimonInCell = -1;
    }

    public Position getPosition()
    {
        return this.position;
    }
    
    public CellType getCellType()
    {
        return this.cellType;
    }
    
    // public char getCharCell()
    // {
    //     EngimonDatabase db;
    
    //     if (content == Content.player)
    //     {
    //         return 'P';
    //     }
    //     else if (content == Content.engimon)
    //     {
    //         if (idxEngimonInCell == -1)
    //             return '-';
    
    //         //cout << "sdasd" << endl;
    //         Engimon e = db.get_engimon_by_idx(idxEngimonInCell);
    //         vector<Element> el = e.get_engimon_elements();
    //         //cout << "ssssss";
    //         if (el.size() > 1)
    //         {
    //             string el1 = el[0].get_element();
    //             string el2 = el[1].get_element();
    
    //             if ((el1 == "Fire" && el2 == "Electric") || (el1 == "Electric" && el2 == "Fire") )
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'L';
    //                 else
    //                     return 'l';
    //             }
    //             else if ((el1 == "Water" && el2 == "Ice") || (el1 == "Ice" && el2 == "Water") )
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'S';
    //                 else
    //                     return 's';
    //             }
    //             else
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'N';
    //                 else
    //                     return 'n';
    //             }
    //         }
    //         else
    //         {
    //             if (el[0].get_element() == "Fire")
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'F';
    //                 else
    //                     return 'f';
    //             }
    //             else if (el[0].get_element() == "Water")
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'W';
    //                 else
    //                     return 'w';
    //             }
    //             else if (el[0].get_element() == "Ice")
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'I';
    //                 else
    //                     return 'i';
    //             }
    //             else if (el[0].get_element() == "Ground")
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'G';
    //                 else
    //                     return 'g';
    //             }
    //             else
    //             {
    //                 if (e.get_level() >= 30)
    //                     return 'E';
    //                 else
    //                     return 'e';
    //             }
    //         }
    
    //     }
    //     else if (content == Content.air)
    //     {
    //         if (cellType == CellType.grassland)
    //         {
    //             return '-';
    //         } else if (cellType == CellType.sea)
    //         {
    //             return 'o';
    //         }
    //     }
        
    //     return '#';
    // }
    
    public Content getContent()
    {
        return this.content;
    }
    
    public void setPosition(Position newposition)
    {
        this.position.setX(newposition.getX());
        this.position.setY(newposition.getY());
    }
    
    public void setPosition(int pX, int pY)
    {
        this.position.setX(pX);
        this.position.setY(pY);
    }
    
    public void setCellType(CellType newCellType)
    {
        this.cellType = newCellType;
    }
    
    public void setContent(Content newContent)
    {
        this.content = newContent;
    }
    
    public void setIdxEngimonInCell(int idx) {
        this.idxEngimonInCell = idx;
    }

}
