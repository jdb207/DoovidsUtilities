package net.james.util;

import net.minecraft.util.Util;

import java.util.ArrayDeque;
import java.util.Deque;

public class FrameUtil {
    private static long lastFrameTime = Util.getMillis();
    private static final int size = 30;
    private static final Deque<Long> frameTimes = new ArrayDeque<>();
    private static int fps;

    public static final int CalculateFPS() {
        long currentTime = Util.getMillis();
        long frameTime = currentTime - lastFrameTime;
        frameTimes.add(frameTime);
        lastFrameTime = currentTime;
        if (frameTimes.size() > size) {
            frameTimes.removeFirst();
        }
        long total = 0;
        for(Long time : frameTimes) {
            total += time;
        }
        double averageFrameTime = (double) total / frameTimes.size();
        fps = (int) (1000 / averageFrameTime);
        return fps;
    }


}
