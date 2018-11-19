/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alhelal.textpad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

/**
 *
 * @author alhelal
 */
public class TextPad extends Application {

    private final BorderPane mainPane;
    private final TabPane centerPane;
    private Scene scene;
    private final ToolBar toolbar;
    private final VBox bottomPane;
    private final TitledPane outputWindow;
    private CodeArea output;
    private final HBox StatusBar;

    private final Button btnNew;
    private final Button btnOpen;
    private final Button btnSave;

    private final Button btnCut;
    private final Button btnCopy;
    private final Button btnPaste;
    private final Button btnDelete;
    private final Button btnUndo;
    private final VBox topPane;
    private final Button btnAssemble;
    private final Button btnRun;
    private final ToggleButton btnFullScreen;
    private final Button btnRedo;
    private final Button btnSaveAll;
    private Stage stage;
    private final MenuBar mainMenu;
    private final Menu fileMenu;
    private final Menu editMenu;
    private final Menu viewMenu;
    private final Menu runMenu;
    private final Menu toolsMenu;
    private final Menu helpMenu;
    private final MenuItem menuPrint;
    private final MenuItem menuNew;
    private final MenuItem menuOpen;
    private final MenuItem menuSave;
    private final MenuItem menuSaveAs;
    private final MenuItem menuSaveAll;
    private final MenuItem menuExit;
    private final MenuItem menuCopy;
    private final MenuItem menuCut;
    private final MenuItem menuUndo;
    private final MenuItem menuPaste;
    private final MenuItem menuDelete;
    private final MenuItem menuSelectAll;
    private final MenuItem menuFindSelected;
    private final MenuItem menuRedo;
    private final MenuItem menuFind;
    private final MenuItem menuReplace;
    private final CheckMenuItem menuFullScreen;
    private final CheckMenuItem menuShowLineNumber;
    private final MenuItem menuRun;
    private final MenuItem menuAssemble;
    private final MenuItem menuSettings;
    private final MenuItem menuOpenInTerminal;
    private final MenuItem menuAbout;
    private final Label StatusBarText;
    private final TextField txtFind;
    private final HBox findBox;
    private boolean fullScreen;
    private boolean lineNumber;

    public TextPad() {

        fullScreen = false;
        lineNumber = true;
        mainMenu = new MenuBar();
        mainMenu.getStylesheets().add(
                this.getClass().getResource("/com/alhelal/resource/toolbar_style.css").toExternalForm());
        fileMenu = new Menu("File");
        
        editMenu = new Menu("Edit");
        viewMenu = new Menu("View");
        runMenu = new Menu("Run");
        toolsMenu = new Menu("Tools");
        helpMenu = new Menu("Help");

        menuPrint = new MenuItem("Print           ");
        menuPrint.setId("menuPrint");
        menuPrint.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN));

        menuNew = new MenuItem("New File       ");
        menuNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        menuOpen = new MenuItem("Open File        ");
        menuOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        menuSaveAs = new MenuItem("Save File As   ");
        
        menuSave= new MenuItem("Save           ");
        menuSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        
        menuSaveAll = new MenuItem("Save All   ");
        menuSaveAll.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN,
                KeyCombination.SHIFT_DOWN));
        
        menuExit = new MenuItem("Exit           ");
        menuExit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        
        menuCopy = new MenuItem("Copy           ");
        menuCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
        
        menuCut = new MenuItem("Cut      ");
        menuCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));

        menuPaste = new MenuItem("Paste        ");
        menuPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));

        menuFindSelected = new MenuItem("Find Selection        ");
        menuFindSelected.setAccelerator(new KeyCodeCombination(KeyCode.F3, KeyCombination.CONTROL_DOWN));
        
        menuDelete= new MenuItem("Delete           ");
        menuDelete.setAccelerator(new KeyCodeCombination(KeyCode.DELETE, KeyCombination.CONTROL_ANY));
        
        menuSelectAll = new MenuItem("Select All   ");
        menuSelectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        
        menuFind = new MenuItem("Find           ");
        menuFind.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        
        menuReplace = new MenuItem("Replace           ");
        menuReplace.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN));
        
        menuUndo = new MenuItem("Undo           ");
        menuUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
        
        menuRedo = new MenuItem("Redo           ");
        menuRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
        
        menuFullScreen = new CheckMenuItem("View Full Screen     ");
        menuFullScreen.setAccelerator(new KeyCodeCombination(KeyCode.F11, KeyCombination.CONTROL_ANY));

        menuRun = new MenuItem("Run            ");
        menuRun.setAccelerator(new KeyCodeCombination(KeyCode.F9, KeyCombination.CONTROL_ANY));
        
        menuAssemble= new MenuItem("Assemble           ");
        menuAssemble.setAccelerator(new KeyCodeCombination(KeyCode.F10, KeyCombination.CONTROL_ANY));
        
        menuSettings = new MenuItem(" Preferences             ");
                
        menuShowLineNumber = new CheckMenuItem("Show Line Number     ");
        menuShowLineNumber.setSelected(true);
        
        menuOpenInTerminal = new MenuItem(" Open in Terminal        ");
        menuAbout = new MenuItem("  About                ");
          
        

        mainPane = new BorderPane();
        topPane = new VBox();
        centerPane = new TabPane();
        toolbar = new ToolBar();
        toolbar.getStylesheets().add(
                getClass().getResource("/com/alhelal/resource/toolbar_style.css").toExternalForm());
        bottomPane = new VBox();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setStyle("-fx-width: 100%");

        outputWindow = new TitledPane("Log",
                new TextArea("Once upon a time \nthere many uninhabited lands"));
        outputWindow.setExpanded(false);
        outputWindow.setStyle("-fx-width: 100%");

        StatusBar = new HBox();
        StatusBar.setPadding(new Insets(10, 10, 10, 10));

        StatusBarText = new Label("Ready");

        btnNew = new Button();
        btnNew.setId("new");
        btnNew.setPrefSize(28, 28);
        btnNew.setTooltip(new Tooltip("New File(Ctrl+N)"));

        btnOpen = new Button();
        btnOpen.setId("open");
        btnOpen.setPrefSize(28, 28);
        btnOpen.setTooltip(new Tooltip("Open File(Ctrl+O)"));

        btnSave = new Button();
        btnSave.setId("save");
        btnSave.setPrefSize(28, 28);
        btnSave.setTooltip(new Tooltip("Save File(Ctrl+O)"));

        btnSaveAll = new Button();
        btnSaveAll.setId("save_all");
        btnSaveAll.setPrefSize(28, 28);
        btnSaveAll.setTooltip(new Tooltip("Save All Files(Ctrl+Shift+S)"));
        btnSaveAll.setPadding(new Insets(0, 20, 0, 20));

        btnCut = new Button();
        btnCut.setId("cut");
        btnCut.setPrefSize(28, 28);
        btnCut.setTooltip(new Tooltip("Cut(Ctrl+X)"));

        btnCopy = new Button();
        btnCopy.setId("copy");
        btnCopy.setPrefSize(28, 28);
        btnCopy.setTooltip(new Tooltip("Copy(Ctrl+C)"));

        btnPaste = new Button();
        btnPaste.setId("paste");
        btnPaste.setPrefSize(28, 28);
        btnPaste.setTooltip(new Tooltip("Paste(Ctrl+V)"));

        btnDelete = new Button();
        btnDelete.setId("delete");
        btnDelete.setPrefSize(28, 28);
        btnDelete.setTooltip(new Tooltip("Delete(Del)"));

        btnUndo = new Button();
        btnUndo.setId("undo");
        btnUndo.setPrefSize(28, 28);
        btnUndo.setTooltip(new Tooltip("Undo(Ctrl+Z)"));

        btnRedo = new Button();
        btnRedo.setId("redo");
        btnRedo.setPrefSize(28, 28);
        btnRedo.setTooltip(new Tooltip("Redo(Ctrl+Y)"));

        btnAssemble = new Button();
        btnAssemble.setId("assemble");
        btnAssemble.setPrefSize(28, 28);
        btnAssemble.setTooltip(new Tooltip("Assemble File(F10)"));

        btnRun = new Button();
        btnRun.setId("run");
        btnRun.setPrefSize(28, 28);
        btnRun.setTooltip(new Tooltip("Run File(F9)"));

        btnFullScreen = new ToggleButton();
        btnFullScreen.setId("full_screen");
        btnFullScreen.setPrefSize(28, 28);
        btnFullScreen.setTooltip(new Tooltip("Full Screen(F11)"));
        
        txtFind = new TextField();
        txtFind.setPromptText("Find Text(Ctrl + F)");
        txtFind.setId("txtFind");
        
        findBox = new HBox();
        findBox.setAlignment(Pos.CENTER_RIGHT);

    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        fileMenu.getItems().addAll(menuNew, new SeparatorMenuItem(), menuOpen,
                new SeparatorMenuItem(), menuSave, menuSaveAs, menuSaveAll, 
                new SeparatorMenuItem(), menuPrint, new SeparatorMenuItem(), menuExit);
        
        editMenu.getItems().addAll(menuUndo, menuRedo, new SeparatorMenuItem(), menuCut,
                menuCopy, menuPaste, menuDelete,new SeparatorMenuItem(), menuSelectAll,
                new SeparatorMenuItem(), menuFind, menuFindSelected, menuReplace);
        
        viewMenu.getItems().addAll(menuShowLineNumber, menuFullScreen);
        runMenu.getItems().addAll(menuRun, menuAssemble);
        toolsMenu.getItems().addAll(menuOpenInTerminal, menuSettings);
        helpMenu.getItems().add(menuAbout);

        //findBox.getChildren().add(txtFind);
        HBox.setHgrow(findBox, Priority.ALWAYS);
        topPane.getChildren().addAll(mainMenu, toolbar);
        mainMenu.getMenus().addAll(fileMenu, editMenu, viewMenu, runMenu, toolsMenu, helpMenu);
        toolbar.getItems().addAll(btnNew, btnOpen, btnSave, btnSaveAll, new Separator(),
                btnCopy, btnPaste, btnCut, btnDelete, new Separator(),
                btnUndo, btnRedo, new Separator(),
                btnAssemble, btnRun, new Separator(),
                btnFullScreen,findBox,txtFind);
        

        StatusBar.getChildren().add(StatusBarText);
        bottomPane.getChildren().addAll(outputWindow, StatusBar);
        
       

        mainPane.setCenter(centerPane);
        mainPane.setTop(topPane);
        mainPane.setBottom(bottomPane);

        btnNew.setOnAction(evt -> newFile(primaryStage));
        btnFullScreen.setOnAction(evt -> toggleFullScreen());
        btnOpen.setOnAction(evt -> openFile());
        btnSave.setOnAction(evt -> saveFile());
        menuPrint.setOnAction(evt -> printFile(new TextArea(getCodeAreaFromTab(
                centerPane.getSelectionModel().getSelectedItem()).getText())));
        
        menuFullScreen.setOnAction(evt -> toggleFullScreen());
        menuShowLineNumber.setOnAction(evt -> toggleLineNumber(getCodeAreaFromTab(
                centerPane.getSelectionModel().getSelectedItem())));
        
        menuExit.setOnAction(evt -> System.exit(0));
        menuNew.setOnAction(evt -> newFile(primaryStage));
        menuSave.setOnAction(evt -> saveFile());
        menuOpen.setOnAction(evt -> openFile());
        
        
        
        

        scene = new Scene(mainPane, 800, 600);

        primaryStage.setScene(scene);
        //scene.getStylesheets().add(
        //      getClass().getResource("/com/alhelal/resource/main_style.css").toExternalForm());
        primaryStage.setTitle("TextPad");
        primaryStage.show();
        newFile(primaryStage);

        //primaryStage.setOnHiding(evt -> intellisense.hide());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private Tab addTab(Stage primaryStage) {
        
        Editor ed = new Editor(primaryStage);
        Tab tb = new Tab("*Untitled", 
                new VirtualizedScrollPane<CodeArea>(ed.getEditArea()));
        tb.setOnSelectionChanged((Event event) -> {
            if(tb.isSelected())
                getCodeAreaFromTab(tb).requestFocus();
        });
        
        
        return tb;
    }

    private void newFile(Stage primaryStage) {
        Tab newTab = addTab(primaryStage);
        centerPane.getTabs().add(newTab);
        centerPane.getSelectionModel().select(newTab);
        getCodeAreaFromTab(centerPane.getSelectionModel().getSelectedItem()).requestFocus();
    }

    private void toggleFullScreen() {
        if (!fullScreen) {
            btnFullScreen.setSelected(true);
            fullScreen = true;
            btnFullScreen.setTooltip(new Tooltip("Close Full Screen(F11)"));
            stage.setFullScreen(true);
        } else {
            btnFullScreen.setSelected(false);
            fullScreen = false;
            btnFullScreen.setTooltip(new Tooltip("Full Screen(F11)"));
            stage.setFullScreen(false);
        }
    }

    private void openFile() {
        FileChooser openfile = new FileChooser();
        openfile.setTitle("Open File");
        openfile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Assembly Files", "*.asm"));
        File file = openfile.showOpenDialog(stage);
        if(file != null)
        {
            getCodeAreaFromTab(centerPane.getSelectionModel().getSelectedItem());
            CodeArea cd = new CodeArea();
        }    
    }

    private void saveFile() {
        FileChooser savefile = new FileChooser();
        savefile.setTitle("Open File");
        savefile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Assembly Files", "*.asm"));
        //I change this portion
        File file = savefile.showSaveDialog(stage);
        // Set the new title of the window
        // setTitle(file.getName() + " | " + SimpleJavaTextEditor.NAME);
        // Create a buffered writer to write to a file
        try {
            //BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
            FileWriter fileWriter = new FileWriter(new File(file.getPath()));
            // Write the contents of the TextArea to the file
            String text = new String();
            text = output.getText();
            System.out.println("this from textArea = " + text);
            fileWriter.write(text);
            // Close the file stream
            fileWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("file is not saved, save button/option failed");
        }

    }

    private void printFile(Node node) {
        PrinterJob printer = PrinterJob.createPrinterJob();

        if (printer != null && printer.showPrintDialog(stage)) {
            boolean success = printer.printPage(node);

            if (success) {
                printer.endJob();
            }
        }
    }
    
    private CodeArea getCodeAreaFromTab(Tab tb)
    {
        Node nd = tb.getContent();
        VirtualizedScrollPane<CodeArea> cd = (VirtualizedScrollPane<CodeArea>)(nd);
        return cd.getContent();
    }
    
    private void toggleLineNumber(CodeArea cd)
    {
        if(lineNumber)
        {
            cd.setParagraphGraphicFactory(null);
            lineNumber = false;
        }
        else
        {
            cd.setParagraphGraphicFactory(LineNumberFactory.get(cd));
            lineNumber = true;
        }
    }

}
