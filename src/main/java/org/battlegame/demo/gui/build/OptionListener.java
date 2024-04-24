package org.battlegame.demo.gui.build;

import javafx.scene.control.Label;

public class OptionListener extends Label {
    /**
     * Clase que representa una opcion de seleccion en un menu
     */

    // region[Campos de configuracion de la clase Label]
    private final double minWidth = 100.00;
    private final double minHeight = 20.00;
    private final double prefWidth = 150.00;
    private final double prefHeight = 30.00;
    private final double maxWidth = 200.00;
    private final double maxHeight = 40.00;
    private final String mark = "";

    // endregion
    private OptionDTO option; // <- Para guardar la informacion de las opciones, en caso de ser vacia pasar como argumento [""]
    private boolean selected = false; // <- Para saber si la opcion esta siendo seleccionada, de forma que la clase que cumpla el rol de encapsulador de la opcion pueda modificar el comportamiento de la seleccion en base a un observador con esta variable

    public OptionListener(OptionDTO option, double minWidth, double minHeight, double prefWidth, double prefHeight, double maxWidth, double maxHeight) {
        this.option = option;
        this.setText(option.text());
        this.setMinSize(minWidth, minHeight);
        this.setPrefSize(prefWidth, prefHeight);
        this.setMaxSize(maxWidth, maxHeight);

        init();
    }

    public OptionListener(OptionDTO option, double prefWidth, double prefHeight, double anotherWidth, double anotherHeight) {
        this.option = option;
        this.setText(option.text());
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


    public OptionListener(OptionDTO option) {
        this.option = option;
        this.setText(option.text());
        this.setMinSize(minWidth, minHeight);
        this.setPrefSize(prefWidth, prefHeight);
        this.setMaxSize(maxWidth, maxHeight);

        init();
    }

    private void init() {
        setStyle();
    }

    public void setOption(OptionDTO option) {
        this.option = option;
        this.setText(option.text());
    }

    /**
     * Implementacion basica para la notificacion a las opciones, en caso de ser la seleccionada se cambia el texto de la opcion,,
     * La nocion basica de añadir algo al texto no me gustó asi que lo invertí, se le añade un texto (un espacio) a toda opcion  no seleccionada, de forma que la opcion seleccionada no tenga espacio y quede mas bonita
     */
    public void onOptionEntered(){
        changeSelection();
        OptionDTO auxOptionText = new OptionDTO(option.text().replace(mark, ""));
        setOption(isSelected()? auxOptionText : new OptionDTO(mark + auxOptionText.text()));
    }


    public OptionDTO getOption() {
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
                        "-fx-text-fill: white;" +
                "-fx-spacing: 5px 0 5px 0;" +
                "-fx-padding: 2px;" +
                "-fx-font-family: 'Bahnschrift';"
//                "-fx-border-width: 2px;"
                //"-fx-border-color: rgba(0,0,0,0.20);"
                );
    }

    /**
     * Contains interactive style for: text-fill, background-color, border-color
     * @param selected Booleana que indica si la opcion está seleccionada o no
     * @return devuelve el estilo para ambos casos, opcion seleccionada y deseleccionada
     */
    private String getStyle(boolean selected) {

        //style += "-fx-background-color: rgba(0,0,0,0.20);";

        return selected ?
//                "-fx-text-fill: rgba(0,0,0,0.5);" +
//                "-fx-border-color: #a1e7ff;"+
//                "-fx-background-color: rgba(133,185,204,0.2);"
                "-fx-background-color: rgba(0,0,0,0.3);"
                :
//                "-fx-text-fill: #3f5959;" +
//                        "-fx-border-color: rgba(0,0,0,0.4);"+
                        "-fx-background-color: rgba(0,0,0,0);"
                ;
    }
}
