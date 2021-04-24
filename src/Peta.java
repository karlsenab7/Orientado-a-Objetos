package src;

import java.util.ArrayList;
import java.util.List;

/**
 * Peta
 */
public class Peta {
    private int sizeX;
    private int sizeY;
    private ArrayList<ArrayList<Cell>> arrOfCell;

    public Peta()
    {
        // arrOfCell = new vector<vector<Cell>>();
        //generatePeta();
    }

    public int getSizeX() {
        return this.sizeX;
    }

    public int getSizeY() {
        return this.sizeY;
    }

    public Cell getCell(int x, int y)
    {
        return arrOfCell.get(x).get(y);
    }

    public void printStr(String str) {
        System.out.println(str);
    }

    // public void generatePeta()
    // {
    //     EngimonDatabase db;
    //     printStr ( "Loading..." );
    //     String filename;
    //     String dir = "../map/map1.txt";
    //     String myText;
    //     // cin >> filename;
    //     System.out.println();

    //     ifstream MyReadFile(dir); // buka file

    //     if (MyReadFile.is_open()) // jika file berhasil terbuka
    //     {
    //         int j = 0;
    //         while (getline(MyReadFile, myText))
    //         {
    //             // memproses masukkan dari file agar dapat dibaca oleh program
    //             vector<Cell> temp;
    //             for (int i = 0; i < myText.length(); i++)
    //             {
    //                 char ch = myText[i];
    //                 if (ch == 'o') 
    //                 {
    //                     Cell c(i, j, CellType::sea, Content::air);

    //                     int timeToSpawn = randomInt();
    //                     if (timeToSpawn < 3 && i != 0 && j != 0)
    //                     {
    //                         if (timeToSpawn % 2 == 0)
    //                         {
    //                             Element el("Water");
    //                             int idx = db.get_idx_random_engimon_by_element(el);
    //                             c.setContent(Content::engimon);
    //                             c.setIdxEngimonInCell(idx);
                                
    //                         }
    //                         else
    //                         {
    //                             Element el("Ice");
    //                             int idx = db.get_idx_random_engimon_by_element(el);
    //                             c.setContent(Content::engimon);
    //                             c.setIdxEngimonInCell(idx);
    //                         }
    //                     }
                        
    //                     temp.push_back(c);
    //                 } else 
    //                 {
    //                     Cell c(i, j, CellType::grassland, Content::air);

    //                     int timeToSpawn = randomInt();

    //                     if (timeToSpawn < 3 && i != 0 && j != 0)
    //                     {
    //                         if (timeToSpawn == 0)
    //                         {
    //                             Element el("Ground");
    //                             int idx = db.get_idx_random_engimon_by_element(el);
    //                             //cout << idx;
    //                             c.setContent(Content::engimon);
    //                             c.setIdxEngimonInCell(idx);
    //                         }
    //                         else if (timeToSpawn == 1)
    //                         {
    //                             Element el("Electric");
    //                             int idx = db.get_idx_random_engimon_by_element(el);
    //                             //cout << idx << " ";
    //                             c.setContent(Content::engimon);
    //                             c.setIdxEngimonInCell(idx);
    //                         }
    //                         else
    //                         {
    //                             Element el("Fire");
    //                             int idx = db.get_idx_random_engimon_by_element(el);
    //                             //cout << idx << " ";
    //                             c.setContent(Content::engimon);
    //                             c.setIdxEngimonInCell(idx);
    //                         }
    //                     }

    //                     temp.push_back(c);
    //                 }
    //             }
    //             j++;
    //             this.arrOfCell.push_back(temp);
    //         }
    //         this.sizeX = arrOfCell.size();
    //         this.sizeY = arrOfCell[0].size();
    //     }
    //     else
    //     {
    //         printStr ( "format file salah atau file tidak ditemukan" );
    //     }
    //     System.out.println();
        
    //     // menutup file
    //     MyReadFile.close();
    // }

    // public int randomInt()
    // {
    //     srand(time(nullptr));

    //     int num = rand() % 10;

    //     return num;
    // }

    public void showPeta() {
        for (int i = 0; i < this.sizeX; i++)
        {
            for (int j = 0; j < this.sizeY; j++)
            {
                String uiPath = arrOfCell.get(i).get(j).getCharCell();
                System.out.println(uiPath);
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
        this.arrOfCell[pX][pY] = c;
    }

    public void setCellContent(Position p, Content c)
    {
        arrOfCell[p.getX()][p.getY()].setContent(c);
    }
    
}