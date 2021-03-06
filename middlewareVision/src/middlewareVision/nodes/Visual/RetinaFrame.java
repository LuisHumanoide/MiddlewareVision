/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewareVision.nodes.Visual;

import gui.ActivityFrame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import utils.Config;
import utils.FileUtils;
import utils.layoutManager;

/**
 *
 * @author Luis Humanoide
 */
public class RetinaFrame extends ActivityFrame<RetinaProccess> {
    int index;
    /**
     * Creates new form RetinaFrame
     */
    public RetinaFrame(RetinaProccess activity, int index) {
        super(activity);
        initComponents();
        this.index=index;
        this.setLocation(layoutManager.points.get(index));
    }

    public void setIndex(int index){
        this.index=index;
    }
    
    public void setImage(BufferedImage image) {
        jLabel1.setIcon(new ImageIcon(image));
        repaint();
    }
    
    /**
     * put the image on the retina
     * @return
     * @throws IOException 
     */
    public BufferedImage createImage() throws IOException{
        String path=getImageName();
        File file = new File(path);
        BufferedImage img = ImageIO.read(file);    
        return img;
    }
    
    /**
     * pick a random image fro the IMAGES folder
     * @return 
     */
    int count=0;
     String getImageName() {
        String path = "images/";
        String imageName="";
        String[] files = FileUtils.getFiles(path);
        if (files != null) {
            int size = files.length;
            imageName=files[(count++)%size];
        }
        return imageName;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 473, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(383, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        if(Config.option==Config.CLICK){
            try {
                activity.setImage();
            } catch (IOException ex) {
                Logger.getLogger(RetinaFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
