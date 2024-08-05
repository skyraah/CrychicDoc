package com.simibubi.create.content.contraptions.actors.roller;

import com.simibubi.create.content.trains.graph.TrackEdge;
import com.simibubi.create.content.trains.graph.TrackGraph;
import com.simibubi.create.content.trains.track.BezierConnection;
import com.simibubi.create.foundation.utility.Iterate;
import com.simibubi.create.foundation.utility.Pair;
import com.simibubi.create.foundation.utility.VecHelper;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class TrackPaverV2 {

    public static void pave(PaveTask task, TrackGraph graph, TrackEdge edge, double from, double to) {
        if (edge.isTurn()) {
            paveCurve(task, edge.getTurn(), from, to);
        } else {
            Vec3 location1 = edge.node1.getLocation().getLocation();
            Vec3 location2 = edge.node2.getLocation().getLocation();
            Vec3 diff = location2.subtract(location1);
            Vec3 direction = VecHelper.clampComponentWise(diff, 1.0F);
            int extent = (int) Math.round((to - from) / direction.length());
            double length = edge.getLength();
            BlockPos pos = BlockPos.containing(edge.getPosition(graph, Mth.clamp(from, 0.0625, length - 0.0625) / length).subtract(0.0, diff.y != 0.0 ? 1.0 : 0.5, 0.0));
            paveStraight(task, pos, direction, extent);
        }
    }

    public static void paveStraight(PaveTask task, BlockPos startPos, Vec3 direction, int extent) {
        Set<BlockPos> toPlaceOn = new HashSet();
        Vec3 start = VecHelper.getCenterOf(startPos);
        Vec3 mainNormal = direction.cross(new Vec3(0.0, 1.0, 0.0));
        Vec3 normalizedDirection = direction.normalize();
        boolean isDiagonalTrack = direction.multiply(1.0, 0.0, 1.0).length() > 1.125;
        double r1 = task.getHorizontalInterval().getFirst();
        int flip = (int) Math.signum(r1);
        double r2 = r1 + (double) flip;
        if (isDiagonalTrack) {
            r1 /= (double) Mth.SQRT_OF_TWO;
            r2 /= (double) Mth.SQRT_OF_TWO;
        }
        int currentOffset = (int) (Math.abs(r1) * 2.0 + 0.5);
        int nextOffset = (int) (Math.abs(r2) * 2.0 + 0.5);
        for (int i = 0; i < extent; i++) {
            Vec3 offset = direction.scale((double) i);
            Vec3 mainPos = start.add(offset.x, offset.y, offset.z);
            Vec3 targetVec = mainPos.add(mainNormal.scale((double) (flip * (int) ((double) currentOffset / 2.0))));
            if (!isDiagonalTrack) {
                toPlaceOn.add(BlockPos.containing(targetVec));
            } else {
                boolean placeRow = currentOffset % 2 == 0 || nextOffset % 2 == 1;
                boolean placeSides = currentOffset % 2 == 1 || nextOffset % 2 == 0;
                if (placeSides) {
                    for (int side : Iterate.positiveAndNegative) {
                        Vec3 sideOffset = normalizedDirection.scale((double) side).add(mainNormal.normalize().scale((double) flip)).scale(0.5);
                        toPlaceOn.add(BlockPos.containing(targetVec.add(sideOffset)));
                    }
                }
                if (placeRow) {
                    if (Math.abs(currentOffset % 2) == 1) {
                        targetVec = mainPos.add(mainNormal.scale((double) (flip * (int) ((double) (currentOffset + 1) / 2.0))));
                    }
                    toPlaceOn.add(BlockPos.containing(targetVec));
                }
            }
        }
        toPlaceOn.forEach(task::put);
    }

    public static void paveCurve(PaveTask task, BezierConnection bc, double from, double to) {
        Map<Pair<Integer, Integer>, Double> yLevels = new HashMap();
        Map<Pair<Integer, Integer>, Double> tLevels = new HashMap();
        BlockPos tePosition = bc.tePositions.getFirst();
        double radius = -task.getHorizontalInterval().getFirst();
        double r1 = radius - 0.575;
        double r2 = radius + 0.575;
        double handleLength = bc.getHandleLength();
        Vec3 start = bc.starts.getFirst().subtract(Vec3.atLowerCornerOf(tePosition)).add(0.0, 0.1875, 0.0);
        Vec3 end = bc.starts.getSecond().subtract(Vec3.atLowerCornerOf(tePosition)).add(0.0, 0.1875, 0.0);
        Vec3 startHandle = bc.axes.getFirst().scale(handleLength).add(start);
        Vec3 endHandle = bc.axes.getSecond().scale(handleLength).add(end);
        Vec3 startNormal = bc.normals.getFirst();
        Vec3 endNormal = bc.normals.getSecond();
        int segCount = bc.getSegmentCount();
        float[] lut = bc.getStepLUT();
        double localFrom = from / bc.getLength();
        double localTo = to / bc.getLength();
        for (int i = 0; i < segCount; i++) {
            float t = i == segCount ? 1.0F : (float) i * lut[i] / (float) segCount;
            float t1 = i + 1 == segCount ? 1.0F : (float) (i + 1) * lut[i + 1] / (float) segCount;
            if (!((double) t1 < localFrom) && !((double) t > localTo)) {
                Vec3 vt = VecHelper.bezier(start, end, startHandle, endHandle, t);
                Vec3 vNormal = startNormal.equals(endNormal) ? startNormal : VecHelper.slerp(t, startNormal, endNormal);
                Vec3 hNormal = vNormal.cross(VecHelper.bezierDerivative(start, end, startHandle, endHandle, t).normalize()).normalize();
                vt = vt.add(vNormal.scale(-1.175F));
                Vec3 vt1 = VecHelper.bezier(start, end, startHandle, endHandle, t1);
                Vec3 vNormal1 = startNormal.equals(endNormal) ? startNormal : VecHelper.slerp(t1, startNormal, endNormal);
                Vec3 hNormal1 = vNormal1.cross(VecHelper.bezierDerivative(start, end, startHandle, endHandle, t1).normalize()).normalize();
                vt1 = vt1.add(vNormal1.scale(-1.175F));
                Vec3 a3 = vt.add(hNormal.scale(r2));
                Vec3 b3 = vt1.add(hNormal1.scale(r2));
                Vec3 c3 = vt1.add(hNormal1.scale(r1));
                Vec3 d3 = vt.add(hNormal.scale(r1));
                Vec2 a = vec2(a3);
                Vec2 b = vec2(b3);
                Vec2 c = vec2(c3);
                Vec2 d = vec2(d3);
                AABB aabb = new AABB(a3, b3).minmax(new AABB(c3, d3));
                double y = vt.add(vt1).y / 2.0;
                for (int scanX = Mth.floor(aabb.minX); (double) scanX <= aabb.maxX; scanX++) {
                    for (int scanZ = Mth.floor(aabb.minZ); (double) scanZ <= aabb.maxZ; scanZ++) {
                        Vec2 p = new Vec2((float) scanX + 0.5F, (float) scanZ + 0.5F);
                        if (isInTriangle(a, b, c, p) || isInTriangle(a, c, d, p)) {
                            Pair<Integer, Integer> key = Pair.of(scanX, scanZ);
                            if (!yLevels.containsKey(key) || (Double) yLevels.get(key) > y) {
                                yLevels.put(key, y);
                                tLevels.put(key, (double) (t + t1) / 2.0);
                            }
                        }
                    }
                }
            }
        }
        for (Entry<Pair<Integer, Integer>, Double> entry : yLevels.entrySet()) {
            double yValue = (Double) entry.getValue();
            int floor = Mth.floor(yValue);
            BlockPos targetPos = new BlockPos((Integer) ((Pair) entry.getKey()).getFirst(), floor, (Integer) ((Pair) entry.getKey()).getSecond()).offset(tePosition);
            task.put(targetPos.m_123341_(), targetPos.m_123343_(), (float) targetPos.m_123342_() + (yValue - (double) floor >= 0.5 ? 0.5F : 0.0F));
        }
    }

    private static Vec2 vec2(Vec3 vec3) {
        return new Vec2((float) vec3.x, (float) vec3.z);
    }

    private static boolean isInTriangle(Vec2 a, Vec2 b, Vec2 c, Vec2 p) {
        float pcx = p.x - c.x;
        float pcy = p.y - c.y;
        float cbx = c.x - b.x;
        float bcy = b.y - c.y;
        float d = bcy * (a.x - c.x) + cbx * (a.y - c.y);
        float s = bcy * pcx + cbx * pcy;
        float t = (c.y - a.y) * pcx + (a.x - c.x) * pcy;
        return d < 0.0F ? s <= 0.0F && t <= 0.0F && s + t >= d : s >= 0.0F && t >= 0.0F && s + t <= d;
    }

    public static double lineToPointDiff2d(Vec3 l1, Vec3 l2, Vec3 p) {
        return Math.abs((l2.x - l1.x) * (l1.z - p.z) - (l1.x - p.x) * (l2.z - l1.z));
    }
}