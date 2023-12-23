import data_structures.Cube;
import data_structures.List;
import data_structures.*;
import data_structures.Map;
import enums.Move;
import solve.Solve;
import utils.CubeUtils;


import java.io.*;
import java.util.*;

public class Main {
    public static double time = 0;

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                time++;
            }
        };

        Cube c = CubeUtils.getCubeFromCamera();
        System.out.println("\nstarted solving");
        timer.schedule(tt, 0, 1);
        List<Move> solution = Solve.kociembasMethod(c);
        timer.cancel();
        System.out.println("solution: " + solution);
        System.out.println("number of moves: " + solution.size());
        System.out.println("total time: " + time / 1000.0);
    }
}