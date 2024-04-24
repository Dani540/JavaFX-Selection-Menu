package org.battlegame.demo;

import javafx.scene.Scene;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.battlegame.demo.gui.imp.*;
import org.battlegame.demo.gui.transitions.ETransitions;
import org.battlegame.demo.gui.transitions.IMenuTransitional;
import org.battlegame.demo.gui.transitions.IOptionTransitional;
import org.battlegame.demo.gui.transitions.TransitionRepo;
import org.battlegame.demo.gui.utils.MenuRepo;
import org.battlegame.demo.observerMenu.MenuController;
import org.battlegame.demo.observerMenu.EMenu;

import static java.lang.Thread.sleep;

public class Launcher {
    /**
     * Clase encargada de configurar los elementos a usar, tanto de UI como de lógica interna.
     */
    private final boolean[] flags = {false, false, false}; // <- Marcadores para saber si en tiempo de ejecución en que etapa del despliegue del programa voy
    private final Stage stage; // <- Ventana principal
    private final Pane pane; // <- Panel principal
    private MenuRepo menuRepo; // <- Repositorio de Menus para las pruebas.
    private MenuController menuController; // <- Controlador de menus

    public Launcher(Stage stage, Pane pane) {
        this.stage = stage;
        this.pane = pane;
    }

    public void preRun(){
        if (!flags[0]){
            flags[0] = true;
            System.out.println("Preparing to run...");
            // Initialize statics for testing

            menuRepo = MenuRepo.getInstance();
            menuController = MenuController.getInstance();

            initTransitions();
        }
        else{
            System.out.println("Already preparing to run...");
        }
    }
    public void run(){
        if (flags[0] && !flags[1]){
            flags[1] = true;
            System.out.println("Running...");

            // Create Menu to MenuRepo

            initMenuRepo();


            // Configure Initial Menu

            menuController.changeCurrentMenuBehavior(EMenu.MAIN_MENU);

            // Configure Pane

            initPane();

            // Config Scene and Stage

            stage.setScene(initScene());

        }
        else if (!flags[0]){
            System.out.println("Not yet preparing to run...");
        }
        else{
            System.out.println("Already running...");
        }
    }

    private void initPane() {
        pane.setStyle("-fx-alignment: center;" +
                "-fx-background-color: rgba(12,21,21,0.90);" +
                "-fx-padding: 5px;" +
                "-fx-spacing: 10px;" +
                "-fx-border-width: 5px;");

        updateMenu();
    }

    private Scene initScene() {
        Scene scene = new Scene(pane, 640,480);

        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, keyEvent -> {
            if (MenuController.getInstance().getCurrentMenuBehavior().notifyOptionKeyboard(keyEvent.getCode())){
                updateMenu();
            }
        });

        scene.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
//            System.out.println(MenuController.getInstance().getCurrentMenuBehavior().notifyOptionMouse());
            if (MenuController.getInstance().getCurrentMenuBehavior().notifyOptionMouse()){
                updateMenu();
            }
        });

        return scene;
    }

    private void initMenuRepo() {
        menuRepo.addOptionMenuContainer(EMenu.MAIN_MENU, new MainMenu(menuController));
        menuRepo.addOptionMenuContainer(EMenu.OPTIONS, new OptionsMenu(menuController));
        menuRepo.addOptionMenuContainer(EMenu.LANGUAGE, new LanguageMenu(menuController));
        menuRepo.addOptionMenuContainer(EMenu.MOVEMENT, new MovementMenu(menuController));
        menuRepo.addOptionMenuContainer(EMenu.EXIT, new ExitMenu(menuController));
    }

    private void initTransitions(){
        TransitionRepo transitionRepo = new TransitionRepo();
        ETransitions.FADE_IN.setBehaviorTransition(transitionRepo::fadeIn);
        ETransitions.FADE_OUT.setBehaviorTransition(transitionRepo::fadeOut);
        ETransitions.OPEN.setBehaviorTransition(transitionRepo::open);
        ETransitions.NONE.setBehaviorTransition(transitionRepo::none);
    }

    private void updateMenu(){
        if ( MenuController.getInstance().getCurrentMenuBehavior() instanceof IOptionTransitional iOptionTransitional){
            iOptionTransitional.exitedTransition();
        }

        ((BorderPane)pane).setCenter(MenuController.getInstance().getCurrentMenuBehavior());

        if ( MenuController.getInstance().getCurrentMenuBehavior() instanceof IMenuTransitional iMenuTransitional){
            iMenuTransitional.startTransition();
        }else if ( MenuController.getInstance().getCurrentMenuBehavior() instanceof IOptionTransitional iOptionTransitional){
            iOptionTransitional.startedTransition();
        }
    }

    public void stop(){
        if (flags[0] && flags[1] && !flags[2]){
            flags[2] = true;
            System.out.println("Stopping...");
        }
        else if (!flags[0]){
            System.out.println("Not yet preparing to run...");
        }
        else if (!flags[1]){
            System.out.println("Not yet running...");
        }
        else{
            System.out.println("Already stopped...");
        }
    }
}
