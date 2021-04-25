package application.classes;

// import javax.xml.crypto.Data;

import javafx.geometry.Pos;

/**
 * Cell
 */

// getCharCell tunggu engimon dulu



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

    public String getCharCellType()
    {
        String grassIconPath = "grass.png";
        String seaIconPath = "sea.png";
        String tundraIconPath = "tundra.png";
        String mountainIconPath = "mountain.png";

        if (cellType == CellType.sea)
            return seaIconPath;
        else if (cellType == CellType.tundra)
            return tundraIconPath;
        else if (cellType == CellType.mountain)
            return mountainIconPath;

        return grassIconPath;
    }

    public String getCharCellContent()
    {
        String playerIconPath = "player.png";

        int x = position.getX();
        int y = position.getY();

        if (position.getX() == GameManagement.player.getPosition().getX() && position.getY() == GameManagement.player.getPosition().getY())
        {
            return playerIconPath;
        }

        if (GameManagement.player.getActiveEngimonIdx() != -1)
        {
            Position ac = GameManagement.player.getActiveEngimonPosition();
            if (x == ac.getX() && y == ac.getY())
            {
                return GameManagement.player.getActiveEngimon().get_icon();
            }
        }

        for (Engimon e : GameManagement.engimonLiar)
        {
            if (x == e.get_position().getX() && y == e.get_position().getY())
            {
                return e.get_icon();
            }
        }

        return "air";
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
