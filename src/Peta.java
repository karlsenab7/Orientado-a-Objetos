package src;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Peta
 */
public class Peta {
    private int sizeX;
    private int sizeY;
    private ArrayList<ArrayList<Cell>> arrOfCell;

    public Peta()
    {
        arrOfCell = new ArrayList<ArrayList<Cell>> ();
        generatePeta();
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public Cell getCell(int x, int y)
    {
        return arrOfCell.get(y).get(x);
    }

    public void printStr(String str) {
        System.out.println(str);
    }

    public void generatePeta()
    {
        String mapPath = "db/map1.txt";
        try {

            Scanner sc = new Scanner(new File(mapPath));
            
            int y = 0;
            while(sc.hasNext())
            {
                String data[] = sc.nextLine().split(",");
                ArrayList<Cell> temp = new ArrayList<>();

                for (int x = 0; x < data.length; x++) {
                    int sign = Integer.parseInt(data[x]);
                    Cell c = new Cell(x, y, CellType.grassland, Content.air);
                    // System.out.println(12132);
                    if (sign == 1)
                        c.setCellType(CellType.sea);
                    else if (sign == 2)
                        c.setCellType(CellType.tundra);
                    else if (sign == 3)
                        c.setCellType(CellType.mountain);
                        // System.out.println(1);
                    Position pActiv = GameManagement.getPlayer().getActiveEngimonPosition();
                    Position pPlayer = GameManagement.getPlayer().getPosition();
                    // System.out.println(2312);
                    if (pActiv != null)
                    {
                        if (pActiv.getX() == x && pActiv.getY() == y)
                            c.setContent(Content.engimon);
                    }

                    if (pPlayer.getX() == x && pPlayer.getY() == y)
                    {
                        // pPlayer.print();
                        c.setContent(Content.player);
                    }

                    if (GameManagement.isEngimonLiar(new Position(x, y)))
                    {
                        c.setContent(Content.engimon);
                    }
                    // System.out.println(1);
                    temp.add(c);
                }
                y++;
                arrOfCell.add(temp);
            }

            if (arrOfCell.size() == 0)
            {
                sizeX = 0;
                sizeY = 0;
            }
            else
            {
                sizeY = arrOfCell.size();
                sizeX = arrOfCell.get(0).size();
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // public int randomInt()
    // {
    //     srand(time(nullptr));

    //     int num = rand() % 10;

    //     return num;
    // }

    public void showPeta() {
        for (int i = 0; i < this.sizeY; i++)
        {
            for (int j = 0; j < this.sizeX; j++)
            {
                String uiPath = arrOfCell.get(i).get(j).getCharCell();
                System.out.print(uiPath);
                new Position(j, i).print();
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showLegend()
    {
        printStr ( "Keterangan" );
        printStr ( "W/w: Water engimon                P: Player" );
        printStr ( "I/i: Ice engimon                  X: Active Engimon" );
        printStr ( "F/f: Fire engimon" );
        printStr ( "G/g: Ground engimon               - : Grassland" );
        printStr ( "E/e: Electric engimon             o : Sea" );
        printStr ( "L/l: Fire/Electric engimon" );
        printStr ( "S/s: Water/Ice engimon" );
        printStr ( "N.n: Water/Ground engimon" );
        //cout << "===================================================\n\n";
    }

    public void showPetaNLegend()
    {
        this.showPeta();
        this.showLegend();
    }

    // void spawnMonster(vector<int> arrOfEngimonGlobal) // ganti int dengan Engimon
    // {
    //     int x = 0;
    //     int y = 0;
    //     do
    //     {
    //         srand(time(nullptr)); // use current time as seed for random generator
    //         x = rand() % this.sizeX;
    //         y = rand() % this.sizeY;
    //     } while ((arrOfCell[x][y].getContent() != Content::air));

    //     this.arrOfCell[x][y].setContent(Content::engimon);
    //     int idx = arrOfEngimon.size();
    //     int i = rand() % arrOfEngimonGlobal.size();
    //     arrOfEngimon.push_back(arrOfEngimonGlobal[i]);
    //     arrOfCell[x][y].setIdxEngimonInCell(idx);
    //     // random engimon yang dimasukkan ke cell
    // }

    public void setCell(int pX, int pY, Cell c) {
        arrOfCell.get(pY).set(pX, c);
    }

    public void setCellContent(Position p, Content c)
    {
        arrOfCell.get(p.getY()).get(p.getX()).setContent(c);
    }

    public Content getCellContent(int x, int y)
    {
        return arrOfCell.get(y).get(x).getContent();
    }
    
}