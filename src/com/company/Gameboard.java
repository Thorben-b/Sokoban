package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Sefa Yavuz on 17-12-2014.
 */
public class Gameboard extends JPanel implements ActionListener {

    private final int BOXSIZE      = 50;
    private final int BOXGAP       = 0;

    private final int GRIDSIZE     = 10; //Set the gridsize
    private Box grid[][]           = new Box[this.GRIDSIZE][this.GRIDSIZE]; // 2D Array thats holds all Boxes

    private Pacman pacman          = new Pacman();
    private DrunkGhost drunkGhost1 = new DrunkGhost();
    private DrunkGhost drunkGhost2 = new DrunkGhost();
    private SmartGhost smartGhost1 = new SmartGhost();
    private SmartGhost smartGhost2 = new SmartGhost();

    public Timer timer             = new Timer(200, this);

    private KeyHandler keyHandler  = new KeyHandler(this.pacman);

    //2D Array that holds the structure
    // 0 = Nothing ( Pathway )
    // 1 = Walls
    // 2 = Pacman
    // 3 = DrunkGhost1
    // 4 = DrunkGhost2
    // 5 = SmartGhost1
    // 6 = SmartGhost2
    // 7 = Fruit
    private int gridStructure[][] = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 7, 7, 7, 7, 7, 7, 7, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 3, 4, 5, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };

    public Gameboard()
    {
        PacmanFrame.frame.addKeyListener(keyHandler);
        createEverything();
        setNeighbors();
        System.out.println(grid[8][8].getGameElements().toString());

    }

    protected void reset()
    {
        if(timer.isRunning())
        {
            resetPosition(this.pacman, this.grid[1][1]);
            resetPosition(this.drunkGhost1, this.grid[8][5]);
            resetPosition(this.drunkGhost2, this.grid[8][6]);
            resetPosition(this.smartGhost1, this.grid[8][7]);
            resetPosition(this.smartGhost2, this.grid[8][8]);

            repaint();
        }
    }

    protected void start()
    {
        if(!timer.isRunning())
        {
            timer.start();
        }
        else
        {
            timer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        drunkGhost1.moveRandom();
        drunkGhost2.moveRandom();
        repaint();
    }

    private void createEverything()
    {
        for(int row = 0;  row < this.GRIDSIZE; row++)
        {
            for(int col = 0; col < this.GRIDSIZE; col++)
            {
                if(this.gridStructure[row][col] == 0) // Box
                {
                    this.grid[row][col] = new Box();
                }
                else if(this.gridStructure[row][col] == 1) // Wall
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new Wall());
                }
                else if(this.gridStructure[row][col] == 2) // Pacman
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.pacman);
                    this.pacman.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 3) // DrunkGhost 1
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.drunkGhost1);
                    this.drunkGhost1.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 4) // DrunkGhost 2
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.drunkGhost2);
                    this.drunkGhost2.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 5) // SmartGhost 1
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.smartGhost1);
                    this.smartGhost1.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 6) // SmartGhost 2
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, this.smartGhost2);
                    this.smartGhost2.setBox(this.grid[row][col]);
                }
                else if(this.gridStructure[row][col] == 7)
                {
                    this.grid[row][col] = new Box();
                    insertInitialElement(row, col, new Fruit());
                }
            }
        }
    }

    private void setNeighbors()
    {
        for(int row = 0;  row < this.GRIDSIZE; row++)
        {
            for(int col = 0; col < this.GRIDSIZE; col++)
            {
                // Neighbors from each box
                int upRow =  row - 1;   // TOP
                int downRow = row + 1;  // BOTTOM
                int leftCol = col - 1;  // LEFT
                int rightCol = col + 1; // RIGHT

                // Checks whether the row values are correct with the condition and adds them the Neighbor HashMap in the Box class
                if (upRow >= 0)
                {
                    grid[row][col].addNeighbor("Top", grid[upRow][col]);
                }
                if (downRow <= (this.GRIDSIZE -1))
                {
                    grid[row][col].addNeighbor("Bottom", grid[downRow][col]);
                }
                if(leftCol >=0 )
                {
                    grid[row][col].addNeighbor("Left", grid[row][leftCol]);
                }
                if (rightCol <= (this.GRIDSIZE - 1))
                {
                    grid[row][col].addNeighbor("Right", grid[row][rightCol]);
                }
            }
        }
    }

    private void insertInitialElement(int row, int col, GameElement ge)
    {
        grid[row][col].addGameElement(ge);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Draw the white box that will divide the smaller 10x10 boxes.
        g.setColor(Color.white);
        g.fillRect(0, 0, 600, 600);

        // Draw the 10x10 boxes that make the grid.
        for (int row = 0; row < this.GRIDSIZE; row++)
        {
            for (int col = 0; col < this.GRIDSIZE; col++) {
                int newBoxSize = this.BOXSIZE + this.BOXGAP;

                int newRow = row * newBoxSize;
                int newCol = col * newBoxSize;

                g.setColor(Color.black);
                g.fillRect(newCol, newRow, this.BOXSIZE, this.BOXSIZE);

                ArrayList elements = grid[row][col].getGameElements();

                if(elements.size() == 1)
                {
                    paintGameElement(g, (GameElement) elements.get(0), newRow, newCol);
                }
                if(elements.size() > 1)
                {
                    int lastIndex = elements.size()-1;
                    paintGameElement(g, (GameElement) elements.get(lastIndex), newRow, newCol);

                }


            }
        }
    }

    private void paintGameElement(Graphics g, GameElement ge, int newRow, int newCol)
    {
        if(ge instanceof Wall)
        {
            g.setColor(Color.blue);
            g.fillRect(newCol, newRow, this.BOXSIZE, this.BOXSIZE);
        }
        if(ge.equals(this.pacman))
        {
            g.setColor(Color.yellow);
            g.fillArc(newCol, newRow, this.BOXSIZE, this.BOXSIZE, 90 / 2, 360 - 90);
        }
        if(ge instanceof Fruit)
        {
            g.setColor(Color.white);

            g.fillOval(newCol + 20, newRow + 20, this.BOXSIZE / 4, this.BOXSIZE / 4);
        }
        if(ge instanceof Ghost)
        {
            if(ge.equals(this.drunkGhost1))
            {
                g.setColor(Color.RED);
            }
            else if(ge.equals(this.drunkGhost2))
            {
                g.setColor(Color.ORANGE);
            }
            else if(ge.equals(this.smartGhost1))
            {
                g.setColor(Color.CYAN);
            }
            else if(ge.equals(this.smartGhost2))
            {
                g.setColor(Color.PINK);
            }

            // Body of Ghosts.
            g.fillOval(newCol, newRow, this.BOXSIZE, this.BOXSIZE);

            g.setColor(Color.black);  // Facial features of the Ghosts are black.

            // Left eye of Ghost.
            g.fillOval(newCol + 15, newRow + 20, 5, 5);

            // Right eye of Ghost.
            g.fillOval(newCol + 30, newRow + 20, 5, 5);
        }

    }




    private void resetPosition(Icon icon, Box grid)
    {
        icon.getBox().removeGameElement(icon);
        icon.setBox(grid);
        icon.getBox().addGameElement(icon);
    }

    public Pacman getPacman()
    {
        return this.pacman;
    }
}
