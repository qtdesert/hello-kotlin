package aquarium5;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class JavaRun {
    public static void runNow(Runnable runnable)
    {
        runnable.run();
    }

    // Had to add this function for the JavaRun.runNow with lambda to work
    public static void runNow(Function0<Unit> function) {
    }
}
