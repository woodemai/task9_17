package ru.vsu.cs.savchenko_n_a.task9_17;


import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;


public class Console {

    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public int index1;
        public int index2;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 3) {
                params.help = true;
                params.error = true;
                return params;
            }
            params.inputFile = args[0];
            if (args[1] != null && args[2] != null) {
                params.index1 = Integer.parseInt(args[1]);
                params.index2 = Integer.parseInt(args[2]);
            }

            if (args.length > 3) {
                params.outputFile = args[3];
            }
            if (args.length > 4) {
                params.help = true;
                params.error = true;
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }


    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("  <cmd> --help");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            Locale.setDefault(Locale.ROOT);
            SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()  );
            java.awt.EventQueue.invokeLater(() -> new Window().setVisible(true));
        } else {
            int[] arr = ArrayUtils.readIntArrayFromFile(params.inputFile);
            if (arr == null) {
                System.err.printf("Can't read array from \"%s\"%n", params.inputFile);
                System.exit(2);
            }
            int index1 = params.index1;
            int index2 = params.index2;
            List<Integer> list = Converter.arrToList(arr);
            Task.sort(list, index1, index2);
            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            out.printf(ArrayUtils.toString(Converter.listToArr(list)));
            out.close();
        }
    }
}