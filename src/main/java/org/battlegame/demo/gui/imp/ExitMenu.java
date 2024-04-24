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

public class ExitMenu extends OptionMenuContainer implements IOptionTransitional {
    public ExitMenu(MenuController menuController) {
        super(menuController, List.of(
                        new OptionListener(
                                new OptionDTO("Yes")
                        ),
                        new OptionListener(
                                new OptionDTO("No")
                        )
        ));
    }

    @Override
    public boolean onEnterPressed() {
        switch (getOptionIndex()) {
            case 0 -> System.exit(0);
            case 1 -> toMainMenu();
        }
        return true;
    }

    private void toMainMenu() {
        getMenuController().changeCurrentMenuBehavior(EMenu.MAIN_MENU);
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
