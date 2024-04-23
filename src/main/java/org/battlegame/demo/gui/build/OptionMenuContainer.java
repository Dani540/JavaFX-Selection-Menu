package org.battlegame.demo.gui.arq;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.battlegame.demo.gui.utils.MenuRepo;
import org.battlegame.demo.observerMenu.MenuController;

import java.util.ArrayList;
import java.util.List;

public abstract class OptionMenuContainer extends VBox{
    private final MenuController menuController;
    private final List<OptionListener> optionListeners;
    private int optionIndex = 0;

    public OptionMenuContainer(MenuController menuController, List<OptionListener> optionListeners) {
        this.menuController = menuController;
        this.optionListeners = optionListeners;
        init();
    }

    public OptionMenuContainer(MenuController menuController) {
        this.menuController = menuController;
        this.optionListeners = new ArrayList<>();
        init();
    }

    private void init() {
        optionListeners.forEach( optionListener -> {
            this.getChildren().add(optionListener.getOption());
        });
        this.requestFocus();
        this.setStyle("-fx-alignment: center;");
        this.setMaxSize(170, 200);

        optionListeners.get(0).getOption().setSelected(true);
        optionListeners.get(0).onOptionEntered();

        setStyle();
    }

    public void addOptionListener(OptionListener optionListener) {
        optionListeners.add(optionListener);
    }

    public void removeOptionListener(OptionListener optionListener) {
        optionListeners.remove(optionListener);
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

    public abstract boolean onEnterPressed();

    private void moveOptionDown() {
        if (optionIndex < optionListeners.size() - 1) {
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
        optionListeners.forEach(op ->{
            op.getOption().setSelected(false);
            op.onOptionEntered();
        });
        setCurrentOption();
    }

    private void setCurrentOption(){
        optionListeners.get(optionIndex).onOptionEntered();
    }

    private void setStyle(){
        this.setStyle("-fx-alignment: center;" +
                "-fx-background-color: rgba(0,0,0,0.59);" +
                "-fx-text-fill: #ffffff;" +
                "-fx-font-size: 15px;" +
                "-fx-border-color: rgb(0,0,0);" +
                "-fx-border-width: 5px;" +
                "-fx-border-radius: 5px;" +
                "-fx-padding: 5px;" +
                "-fx-spacing: 10px;");
    }

    public int getOptionIndex() {
        return optionIndex;
    }

    public MenuController getMenuController() {
        return menuController;
    }
}
