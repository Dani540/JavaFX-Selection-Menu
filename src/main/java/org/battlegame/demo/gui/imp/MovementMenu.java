package org.battlegame.demo.gui.imp;

import javafx.util.Duration;
import org.battlegame.demo.gui.build.OptionDTO;
import org.battlegame.demo.gui.build.OptionListener;
import org.battlegame.demo.gui.build.OptionMenuContainer;
import org.battlegame.demo.gui.transitions.ETransitions;
import org.battlegame.demo.gui.transitions.IOptionTransitional;
import org.battlegame.demo.observerMenu.EMenu;
import org.battlegame.demo.observerMenu.MenuController;

import java.util.List;

public class MovementMenu extends OptionMenuContainer implements IOptionTransitional {
    public MovementMenu(MenuController menuController) {
        super(menuController, List.of(
                new OptionListener(
                        new OptionDTO("No se me \nocurrió mas")
                ),
                new OptionListener(
                        new OptionDTO("Back")
                )
        ));
    }

    @Override
    public boolean onEnterPressed() {
        toOptionMenu();
        return true;
    }

    private void toOptionMenu() {
        getMenuController().changeCurrentMenuBehavior(EMenu.OPTIONS);
    }

    @Override
    public void startedTransition() {
        getOptionListenerList().forEach(optionListener ->{
            ETransitions.FADE_IN.startTransition(Duration.millis(100), optionListener);
        });
    }

    @Override
    public void exitedTransition() {
        getOptionListenerList().forEach(optionListener ->{
            ETransitions.FADE_OUT.startTransition(Duration.millis(200), optionListener);
        });
    }
}
