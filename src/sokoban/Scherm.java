/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sokoban;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;

/**
 *
 * @author Jhoog_000
 */
public class Scherm extends javax.swing.JFrame {
    private int hoogte;
    private int breedte;
    private Speelveld speelveld;
    private Menu menu;
    
    /**
     * Creates new form Scherm
     */
    public Scherm() {
        System.out.println("testGit");
        this.setPreferredSize(new Dimension(800, 1000));     
        this.getContentPane().setBackground(Color.GRAY);
        this.setTitle("Sokoban");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setLayout(new BorderLayout());
        
        
        
        Menu menu = new Menu();
        menu.setBackground(Color.BLUE);
        menu.setSize(new Dimension (800,200));
        JButton start = new JButton("start");
        menu.add(start);
     //   this.add(menu, BorderLayout.NORTH);
        setup();
        
    //    this.add(menu,BorderLayout.NORTH);
        Speelveld speelveld = new Speelveld();
       // this.add(speelveld,BorderLayout.CENTER);
        
        initComponents();
        this.setVisible(true); 
    }
    public void setup(){
        Speelveld speelveld = new Speelveld();
        speelveld.setBackground(Color.GREEN);
        speelveld.setSize(new Dimension(800,800));
        this.add(speelveld, BorderLayout.SOUTH);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
