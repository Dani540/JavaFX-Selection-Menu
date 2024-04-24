package org.battlegame.demo.gui.build;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import org.battlegame.demo.observerMenu.MenuController;

import java.util.ArrayList;
import java.util.List;

public abstract class OptionMenuContainer extends VBox{
    /**
     * Clase abstracta que funciona como representacion de un contenedor de opciones
     */
    private final MenuController menuController;
    private final List<OptionListener> optionListenerList;
    private int optionIndex = 0;

    public OptionMenuContainer(MenuController menuController, List<OptionListener> optionListenerList) {
        this.menuController = menuController;
        this.optionListenerList = optionListenerList;
        init();
    }

    public OptionMenuContainer(MenuController menuController) {
        this.menuController = menuController;
        this.optionListenerList = new ArrayList<>();
        init();
    }

    private void init() {
        optionListenerList.forEach(option -> {
            this.getChildren().add(option);
        });
        this.requestFocus();
        this.setStyle("-fx-alignment: center;");
        this.setMaxSize(170, 200);

        optionListenerList.get(0).setSelected(true);
        optionListenerList.get(0).onOptionEntered();

        setStyle();

        notifyOptionMouse();
    }

    public void addOptionListener(OptionListener optionListener) {
        optionListenerList.add(optionListener);
    }

    public void removeOptionListener(OptionListener optionListener) {
        optionListenerList.remove(optionListener);
    }

    public boolean notifyOptionKeyboard(KeyCode keyCode) {
        switch (keyCode) {
            case UP -> {
                moveOptionUp();
            }
            case DOWN -> {
                moveOptionDown();
            }
            case ENTER -> {
                return onEnterPressed();
            }
        }
        return false;
    }

    public boolean notifyOptionMouse(){
        optionListenerList.forEach(option ->{
            option.setOnMouseEntered(mouseEvent -> {
                optionIndex = optionListenerList.indexOf(option);
                cleanOptions();
            });

            option.setOnMousePressed(mouseEvent -> {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    onEnterPressed();
                }
            });

        });

        return optionListenerList.get(optionIndex).isSelected();
    }



    public abstract boolean onEnterPressed();

    private void moveOptionDown() {
        if (optionIndex < optionListenerList.size() - 1) {
            optionIndex++;

            cleanOptions();
        }
    }

    private void moveOptionUp() {
        if (optionIndex > 0) {
            optionIndex--;
            cleanOptions();
        }
    }

    private void cleanOptions(){
        optionListenerList.forEach(op ->{
            op.setSelected(false);
            op.onOptionEntered();
        });
        setCurrentOption();
    }

    private void setCurrentOption(){
        optionListenerList.get(optionIndex).onOptionEntered();
    }

    private void setStyle(){
        this.setStyle("-fx-alignment: center;" +
                "-fx-background-color: rgba(0,0,0,0.4);" +
                "-fx-background-radius: 10px;" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 12px;" +
//                "-fx-border-color: rgb(0,0,0,0.8);" +
                "-fx-border-width: 3px;" +
                "-fx-border-radius: 10px;" +
                "-fx-padding: 5px;" +
                "-fx-spacing: 10px;");
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    protected List<OptionListener> getOptionListenerList() {
        return optionListenerList;
    }
}
