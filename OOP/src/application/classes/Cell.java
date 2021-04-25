package application.classes;

// import javax.xml.crypto.Data;

/**
 * Cell
 */

// getCharCell tunggu engimon dulu


enum CellType {grassland, sea, tundra, mountain};
enum Content {player, engimon, air};

public class Cell {

    private Position position = new Position(0, 0);
    private CellType cellType;
    private Content content;
    private int idEngimonInCell;

    public Cell()
    {
        position = new Position(0, 0);
        cellType = CellType.grassland;
        content = Content.air;
        idEngimonInCell = -1;
    }

    public Cell(Position newPosition, CellType newCellType, Content newContent) {
        this.position = newPosition;
        this.cellType = newCellType;
        this.content = newContent;
        this.idEngimonInCell = -1;
    }

    public Cell(int pX, int pY, CellType newCellType, Content newContent) {
        this.position = new Position(pX, pY);
        this.cellType = newCellType;
        this.content = newContent;
        this.idEngimonInCell = -1;
    }

    public Position getPosition()
    {
        return this.position;
    }
    
    public CellType getCellType()
    {
        return this.cellType;
    }
    
    public String getCharCell()
    {
        String playerIconPath = "img/player.png";
        String grassIconPath = "img/grass.png";
        String seaIconPath = "img/sea.png";
        String tundraIconPath = "img/tundra.png";
        String mountainIconPath = "img/mountain.png";

        if (content == Content.player)
        {
            return playerIconPath;
        }
        else if (content == Content.engimon)
        {
            if (idEngimonInCell != -1)
            {
                Position p = GameManagement.getPlayer().getActiveEngimon().get_position();

                if (position.getX() == p.getX() && position.getY() == p.getY())
                {
                    return GameManagement.getPlayer().getActiveEngimon().get_icon();
                }

                for (Engimon e : GameManagement.getEngimonLiar()) {
                    Position pLiar = e.get_position();
                    if (pLiar.getX() == position.getX() && pLiar.getY()==position.getY())
                    {
                        return e.get_icon();
                    }
                }
            }
        }
        else
        {
            if (cellType == CellType.grassland)
            {
                return grassIconPath;
            }
            else if (cellType == CellType.sea)
            {
                return seaIconPath;
            }
            else if (cellType == CellType.tundra)
            {
                return tundraIconPath;
            }
            else
            {
                return mountainIconPath;
            }
        }

        return grassIconPath;
    }
    
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
        this.idEngimonInCell = idx;
    }

    public int getIdEngimonInCell() {
        return this.idEngimonInCell;
    }

}
