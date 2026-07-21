package net.james.util;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CoordinateUtil {

    public static Vec3 overWorldToNether(Vec3 pos) {
        int x = Math.floorDiv((int) pos.x(), 8);
        int z = Math.floorDiv((int) pos.z(), 8);
        return new Vec3(x,(int) pos.y(),z);
    }

    public static Vec3 netherToOverworld(Vec3 pos) {
        int x = Math.multiplyExact((int) pos.x(), 8);
        int z = Math.multiplyExact((int) pos.z(), 8);
        return new Vec3(x, (int) pos.y(), z);
    }

    public static boolean isOverworld(LocalPlayer player) {
        return player.level().dimension().equals(Level.OVERWORLD);
    }

    public static boolean isNether(LocalPlayer player) {
        return player.level().dimension().equals(Level.NETHER);
    }

    public static boolean isEnd(LocalPlayer player) {
        return player.level().dimension().equals(Level.END);
    }

    public static Vec3 getCurrentCoordinates(LocalPlayer player) {
        if(player != null) {
            return new Vec3(
                    player.getBlockX(),
                    player.getBlockY(),
                    player.getBlockZ()
            );
        }
        return null;
    }

    public static Vec3 getAlternateCoordinates(LocalPlayer player, Vec3 position) {
        if(player != null) {
            if(CoordinateUtil.isNether(player)) {
                return CoordinateUtil.netherToOverworld(position);
            }
            if(CoordinateUtil.isOverworld(player)) {
                return CoordinateUtil.overWorldToNether(position);
            }
            if (CoordinateUtil.isEnd(player)) {
                return CoordinateUtil.overWorldToNether(position);
            }
        }
        return null;
    }
}
