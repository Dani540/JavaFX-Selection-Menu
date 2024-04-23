package org.battlegame.demo.gui.arq;

import javafx.scene.control.Label;

public class Option extends Label {
    /**
     * Clase que representa una opcion de seleccion en un menu
     */

    // region[Campos de configuracion de la clase Label]
    private final double minWidth = 100.00;
    private final double minHeight = 50.00;
    private final double prefWidth = 150.00;
    private final double prefHeight = 50.00;
    private final double maxWidth = 200.00;
    private final double maxHeight = 70.00;
    // endregion
    private String option; // <- Para guardar la informacion de las opciones, en caso de ser vacia pasar como argumento [""]
    private boolean selected = false; // <- Para saber si la opcion esta siendo seleccionada, de forma que la clase que cumpla el rol de encapsulador de la opcion pueda modificar el comportamiento de la seleccion en base a un observador con esta variable

    public Option(String option, double minWidth, double minHeight, double prefWidth, double prefHeight, double maxWidth, double maxHeight) {
        this.option = option;
        this.setText(option);
        this.setMinSize(minWidth, minHeight);
        this.setPrefSize(prefWidth, prefHeight);
        this.setMaxSize(maxWidth, maxHeight);

        init();
    }

    public Option(String option, double prefWidth, double prefHeight, double anotherWidth, double anotherHeight) {
        this.option = option;
        this.setText(option);
        if (anotherWidth  >= prefWidth && anotherHeight >= prefHeight) {
            this.setMaxSize(anotherWidth, anotherHeight);
            this.setMinSize(minWidth, minHeight);
        }else {
            this.setMaxSize(prefWidth, prefHeight);
            this.setMinSize(minWidth, minHeight);
        }
        this.setPrefSize(prefWidth, prefHeight);

        init();
    }


    public Option(String option) {
        this.option = option;
        this.setText(option);
        this.setMinSize(minWidth, minHeight);
        this.setPrefSize(prefWidth, prefHeight);
        this.setMaxSize(maxWidth, maxHeight);

        init();
    }

    private void init() {
        setStyle();
    }

    public void setOption(String option) {
        this.option = option;
        this.setText(option);
    }


    public String getOption() {
        return option;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void changeSelection() {
        setStyle();
        selected = !selected;
    }

    public void setStyle() {
        this.setStyle( getStyle(isSelected()) +
                "-fx-alignment: center;" +
                "-fx-spacing: 5px;" +
                "-fx-padding: 10px;" +
                "-fx-font-family: 'Bahnschrift';" +
                "-fx-border-width: 5px;"
                //"-fx-border-color: rgba(0,0,0,0.20);"
                );
    }

    /**
     * Contains interactive style for: text-fill, background-color, border-color
     * @param selected
     * @return
     */
    private String getStyle(boolean selected) {
        String style;
        if (selected){
            style = "-fx-text-fill: #a1e7ff;" +
                    "-fx-border-color: rgba(212,255,248,0.2);"+
                    "-fx-background-color: rgba(0,0,0,0.1);";
        }else{
            style = "-fx-text-fill: rgba(45,45,45,0.96);" +
                    "-fx-border-color: rgba(0,0,0,0.20);"+
                    "-fx-background-color: rgba(0,0,0,0.3);";
        }
        //style += "-fx-background-color: rgba(0,0,0,0.20);";

        return style;
    }
}
