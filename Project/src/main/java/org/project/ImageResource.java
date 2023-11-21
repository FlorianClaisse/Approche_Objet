package org.project;

import javafx.scene.image.Image;

import java.util.Objects;

public enum ImageResource {
    BACKGROUND("background.jpeg");

    private static final int size = 40;

    private final Image image;

    ImageResource(String file) {
        try {
            this.image = new Image(Objects.requireNonNull(ImageResource.class.getResourceAsStream("/textures/" + file)));
            if (image.getWidth() != size || image.getHeight() != size) {
                String msg = "File " + file + "does not have the correct size " + image.getWidth() + "x" + image.getHeight();
                throw new RuntimeException(msg);
            }
        } catch (NullPointerException e) {
            System.err.println("Resource not found : " + file);
            throw e;
        }
    }

    public Image getImage() { return image; }
}
