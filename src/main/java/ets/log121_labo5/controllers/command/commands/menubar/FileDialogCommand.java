package ets.log121_labo5.controllers.command.commands.menubar;


import ets.log121_labo5.controllers.command.Command;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class: FileChooserDialogCommand
 * Created on: 7/11/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class FileDialogCommand extends Command<ActionEvent> {

    public static final File DEFAULT_DIRECTORY = new File(".\\src\\main\\resources\\saves");

    // TEMPLATE METHOD
    @Override
    public final void execute(ActionEvent event) {
        FileChooser fc = new FileChooser();
        this.setDialogOptions(fc);

        File file = this.fireDialog(Command.stage, fc);
        if (file != null)
            this.invokeCommand(file);
    }

    protected void setDialogOptions(FileChooser fc) {
//        URL resUrl = getClass().getResource("src/main/resources/saves/test.ser");
//        System.out.println("URL: " + resUrl);
//        System.out.println(new File(".\\src\\main\\resources\\saves").getAbsolutePath());

        File target = FileDialogCommand.DEFAULT_DIRECTORY;
        if (target.exists())
            fc.setInitialDirectory(target);
    }

    protected abstract File fireDialog(Stage stage, FileChooser fc);

    protected abstract void invokeCommand(File file);
}
