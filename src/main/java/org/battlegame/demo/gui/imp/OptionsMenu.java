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

public class OptionsMenu extends OptionMenuContainer implements IOptionTransitional {
    public OptionsMenu(MenuController menuController) {
        super(
                menuController,
                List.of(
                        new OptionListener(
                                new OptionDTO("Sound")
                        ),
                        new OptionListener(
                                new OptionDTO("Language")
                        ),
                        new OptionListener(
                                new OptionDTO("Movimiento")
                        ),
                        new OptionListener(
                                new OptionDTO("Volver")
                        )
                )
        );
    }
    @Override
    public boolean onEnterPressed() {
        switch (getOptionIndex()) {
            case 0 -> toNone();
            case 1 -> toLanguageMenu();
            case 2 -> toMovementMenu();
            case 3 -> toMainMenu();
        }
        return true;
    }

    private void toMainMenu() {
        MenuController.getInstance().changeCurrentMenuBehavior(EMenu.MAIN_MENU);
    }

    private void toMovementMenu() {
        MenuController.getInstance().changeCurrentMenuBehavior(EMenu.MOVEMENT);

    }

    private void toLanguageMenu() {
        MenuController.getInstance().changeCurrentMenuBehavior(EMenu.LANGUAGE);

    }

    private void toNone() {
        System.out.println("Sound");
    }

    @Override
    public void startedTransition() {
        getOptionListenerList().forEach(optionListener -> {
            ETransitions.FADE_IN.startTransition(Duration.millis(200), optionListener);
        });
    }

    @Override
    public void exitedTransition() {
        getOptionListenerList().forEach(optionListener -> {
            ETransitions.FADE_OUT.startTransition(Duration.millis(200), optionListener);
        });
    }
}
