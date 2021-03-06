/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package middlewareVision.core.nodes;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import kmiddle2.nodes.activities.Activity;
import middlewareVision.core.nodes.FrameActivity;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import utils.Config;
import utils.Convertor;
import utils.FileUtils;
import utils.layoutManager;

/**
 *
 * @author HumanoideFilms
 */
public class ClickFrame<T extends ClickFrameActivity> extends javax.swing.JFrame {

    int index;
    String title;
    T process;
    Mat mat;
    String path;

    /**
     * Creates new form Frame
     */
    public ClickFrame(T process, String path, int index) {
        
        try {
            this.index = index;
            this.process = process;
            this.path = path;
            initComponents();
            this.setLocation(layoutManager.points.get(index));
            mat = new Mat();
            setVisible(true);
            setSize(Config.width,Config.heigth);
            createImage();
            
        } catch (IOException ex) {
            Logger.getLogger(ClickFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setImage(BufferedImage image, String title) {
        setVisible(true);
        if (title != null) {
            this.setTitle(title);
            this.title = title;
        } else {
            this.setTitle("cuayoyotolt");
        }
        jLabel1.setIcon(new ImageIcon(image));
        repaint();
    }

    /**
     * put the image on the retina
     *
     * @return
     * @throws IOException
     */
    public BufferedImage createImage() throws IOException {
        String path2 = getImageName(path);
        File file = new File(path2);
        BufferedImage img = ImageIO.read(file);
        mat = Convertor.bufferedImageToMat(img);
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.resize(mat, mat, new Size(Config.width, Config.heigth));
        mat.convertTo(mat, CvType.CV_32FC3);
        //Normalization
        Core.divide(mat, Scalar.all(255), mat);
        setImage(Convertor.ConvertMat2Image(mat), "saliency filter");
        process.clickAction();
        return img;
    }

    /**
     * pick a random image fro the IMAGES folder
     *
     * @return
     */
    int count = 0;

    String getImageName(String path) {
        String imageName = "";
        String[] files = FileUtils.getFiles(path);
        if (files != null) {
            int size = files.length;
            imageName = files[(count++) % size];
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

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addContainerGap(316, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 273, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    int x;
    int y;
    boolean lock;

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        // TODO add your handling code here:
        if (!lock) {
            x = -evt.getX();
            y = -evt.getY();
            lock = true;
        }
        this.setLocation(new Point(x + evt.getXOnScreen(), y + evt.getYOnScreen()));
        //System.out.println(x+" "+y);
    }//GEN-LAST:event_formMouseDragged


    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        lock = false;
    }//GEN-LAST:event_formMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        try {
            // TODO add your handling code here:

            createImage();

        } catch (IOException ex) {
            Logger.getLogger(ClickFrame.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
