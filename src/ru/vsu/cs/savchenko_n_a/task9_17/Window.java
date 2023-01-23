package ru.vsu.cs.savchenko_n_a.task9_17;

import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.JTableUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Objects;

import static ru.vsu.cs.savchenko_n_a.task9_17.Task.sort;

public class Window extends JFrame {
    private JPanel panel;
    private JButton buttonInput;
    private JButton buttonOutput;
    private JButton buttonExecute;
    private JTable tableInput;
    private JTable tableExecuted;
    private JTextField textFieldIndex1Input;
    private JTextField textFieldIndex2Input;
    private final JFileChooser fileChooserSave;

    public Window() {

        this.setTitle("TASK 9 17");
        this.setContentPane(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        tableInput.setRowHeight(25);
        tableExecuted.setRowHeight(25);

        JTableUtils.initJTableForArray(tableInput, 40, false, true, false, true);
        JTableUtils.initJTableForArray(tableExecuted, 40, false, true, false, true);

        JFileChooser fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);
        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");


        buttonInput.addActionListener(e -> {
            try {
                if (fileChooserOpen.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    JTableUtils.writeArrayToJTable(tableInput, ArrayUtils.readIntArrayFromFile(fileChooserOpen.getSelectedFile().getPath()));
                }
            } catch (Exception e1) {
                SwingUtils.showErrorMessageBox(e1);
            }
        });


        buttonOutput.addActionListener(e -> {
            try {
                if (fileChooserSave.showSaveDialog(panel) == JFileChooser.APPROVE_OPTION) {
                    String file = fileChooserSave.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    ArrayUtils.writeArrayToFile(file, JTableUtils.readIntMatrixFromJTable(tableExecuted));
                }
            } catch (Exception e1) {
                SwingUtils.showErrorMessageBox(e1);
            }
        });


        buttonExecute.addActionListener(e -> {
            try {
                List<Integer> l = Converter.arrToList(Objects.requireNonNull(JTableUtils.readIntArrayFromJTable(tableInput)));
                if (textFieldIndex1Input.getText().equals("") || textFieldIndex2Input.getText().equals("")) {
                    SwingUtils.showInfoMessageBox("Вы не верно ввели значения", "Ошибка");
                } else {
                    int index1 = Integer.parseInt(textFieldIndex1Input.getText());
                    int index2 = Integer.parseInt(textFieldIndex2Input.getText());
                    sort(l, index1, index2);
                    JTableUtils.writeArrayToJTable(tableExecuted, Converter.listToArr(l));
                }

            } catch (Exception e1) {
                SwingUtils.showErrorMessageBox(e1);
            }


        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(e -> {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    List<Integer> l = Converter.arrToList(Objects.requireNonNull(JTableUtils.readIntArrayFromJTable(tableInput)));
                    if (textFieldIndex1Input.getText().equals("") || textFieldIndex2Input.getText().equals("")) {
                        SwingUtils.showInfoMessageBox("Вы не верно ввели значения", "Ошибка");
                    } else {
                        int index1 = Integer.parseInt(textFieldIndex1Input.getText());
                        int index2 = Integer.parseInt(textFieldIndex2Input.getText());
                        sort(l, index1, index2);
                        JTableUtils.writeArrayToJTable(tableExecuted, Converter.listToArr(l));
                    }
                } catch (Exception e1) {
                    SwingUtils.showErrorMessageBox(e1);
                }
            }
            return false;
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

}
