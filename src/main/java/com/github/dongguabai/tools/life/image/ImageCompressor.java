package com.github.dongguabai.tools.life.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * @author dongguabai
 * @date 2024-01-25 18:18
 */

public class ImageCompressor extends JFrame {
    private JSlider slider;

    public ImageCompressor() {
        super("Image Compressor");

        slider = new JSlider(0, 100, 50);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        new DropTarget(this, new DropTargetAdapter() {
            public void drop(DropTargetDropEvent dtde) {
                try {
                    dtde.acceptDrop(dtde.getDropAction());
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        List<File> files = (List<File>) dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                        for (File file : files) {
                            String extension = getFileExtension(file);
                            BufferedImage image = ImageIO.read(file);
                            double scale = slider.getValue() / 100.0;
                            BufferedImage compressedImage = compressImage(image, scale);
                            File output = new File(file.getParent(), System.currentTimeMillis() + "compress." + extension);
                            ImageIO.write(compressedImage, extension, output);
                        }
                    } else {
                        // 处理不支持的 DataFlavor
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        add(slider, BorderLayout.NORTH);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static BufferedImage compressImage(BufferedImage sourceImage, double scale) {
        int width = (int) (sourceImage.getWidth() * scale);
        int height = (int) (sourceImage.getHeight() * scale);
        BufferedImage resized = new BufferedImage(width, height, sourceImage.getType());
        Graphics2D g = resized.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(sourceImage, 0, 0, width, height, 0, 0, sourceImage.getWidth(), sourceImage.getHeight(), null);
        g.dispose();
        return resized;
    }

    public static String getFileExtension(File file) throws Exception {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            throw new Exception("No extension found for file: " + fileName);
        }
        return fileName.substring(dotIndex + 1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageCompressor().setVisible(true));
    }
}