package com.hbm.util;

import java.lang.reflect.Field;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.text.NumberFormat;
import java.util.function.ToIntFunction;

import javax.annotation.Nullable;
import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import com.hbm.main.ClientProxy;
import com.hbm.main.MainRegistry;
import com.hbm.render.amlfrom1710.Vec3;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BobMathUtil {

	public static Field r_viewMat;
	public static Random rand = new Random();

	public static String getShortNumber(long number){
		if(number < 1000D){
			return ""+number;
		}else if(number < 1000D){
			return String.format("%6.2fk", number/1000F);
		}else if(number < 1000000D){
			return String.format("%6.2fM", number/1000000F);
		}else if(number < 1000000000D){
			return String.format("%6.2fG", number/1000000000F);
		}else if(number < 1000000000000D){
			return String.format("%6.2fT", number/1000000000000F);
		}else if(number < 1000000000000000D){
			return String.format("%6.2fE", number/1000000000000000F);
		}else if(number < 1000000000000000000D){
			return String.format("%6.2fP", number/1000000000000000000F);
		}
		return "INFINTE";
	}
			
	public static double getAngleFrom2DVecs(double x1, double z1, double x2, double z2) {

		double upper = x1 * x2 + z1 * z2;
		double lower = Math.sqrt(x1 * x1 + z1 * z1) * Math.sqrt(x2 * x2 + z2 * z2);

		double result = Math.toDegrees(Math.cos(upper / lower));

		if(result >= 180)
			result -= 180;

		return result;
	}
	
	public static double getCrossAngle(Vec3d vel, Vec3d rel) {

		vel = vel.normalize();
		rel = rel.normalize();

		double angle = Math.toDegrees(Math.acos(vel.dotProduct(rel)));

		if(angle >= 180)
			angle -= 180;

		return angle;
	}
	
	public static double getCrossAngle(Vec3 vel, Vec3 rel) {

		vel = vel.normalize();
		rel = rel.normalize();

		double angle = Math.toDegrees(Math.acos(vel.dotProduct(rel)));

		if(angle >= 180)
			angle -= 180;

		return angle;
	}

	public static float remap(float num, float min1, float max1, float min2, float max2){
		return ((num - min1) / (max1 - min1)) * (max2 - min2) + min2;
	}

	public static float remap01(float num, float min1, float max1){
		return (num - min1) / (max1 - min1);
	}
	
	public static float remap01_clamp(float num, float min1, float max1){
		return MathHelper.clamp((num - min1) / (max1 - min1), 0, 1);
	}

	public static Vec3d lerp(Vec3d vec0, Vec3d vec1, float interp){
		return new Vec3d(
				vec0.x + (vec1.x - vec0.x)*interp,
				vec0.y + (vec1.y - vec0.y)*interp,
				vec0.z + (vec1.z - vec0.z)*interp);
	}
	
	public static Vec3 getEulerAngles(Vec3 vec) {
		double yaw = Math.toDegrees(Math.atan2(vec.xCoord, vec.zCoord));
		double sqrt = MathHelper.sqrt(vec.xCoord * vec.xCoord + vec.zCoord * vec.zCoord);
		double pitch = Math.toDegrees(Math.atan2(vec.yCoord, sqrt));
		return Vec3.createVectorHelper(yaw, pitch, 0);
	}
	
	/**
	 *
	 * @param vec vector
	 * @return vec3 containing yaw, pitch, nothing.
	 */
	public static Vec3d getEulerAngles(Vec3d vec) {
		double yaw = Math.toDegrees(Math.atan2(vec.x, vec.z));
		double sqrt = MathHelper.sqrt(vec.x * vec.x + vec.z * vec.z);
		double pitch = Math.toDegrees(Math.atan2(vec.y, sqrt));
		return new Vec3d(yaw, pitch-90, 0);
	}
	
	public static Vec3d getVectorFromAngle(float yaw, float pitch){
		Vec3d vec = new Vec3d(0, 1, 0);
		return vec.rotatePitch((float) Math.toRadians(pitch)).rotateYaw((float) Math.toRadians(yaw));
	}
	
	public static Vec3d getVectorFromAngle(Vec3d vec){
		return getVectorFromAngle((float)vec.x, (float)vec.y);
	}
	
	/**
	 * !!EXPERIMENTAL!!
	 * Gets the minecraft world position from the opengl local positions.
	 * @param positions - the list of positions to be returned in world space
	 * @return an array of newly transformed vectors
	 */
	@SuppressWarnings("deprecation")
	@SideOnly(Side.CLIENT)
	public static Vec3d[] worldFromLocal(Vector4f... positions){
		Entity renderView = Minecraft.getMinecraft().getRenderViewEntity();
		float partialTicks = MainRegistry.proxy.partialTicks();
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
		Matrix4f mv_mat = new Matrix4f();
		mv_mat.load(ClientProxy.AUX_GL_BUFFER);
		ClientProxy.AUX_GL_BUFFER.rewind();
		if(r_viewMat == null){
			//It says model view matrix, but it's actually just the view matrix because it's grabbed right after setting up the camera.
			//We can use the inverse of this to remove the view part of the open gl transform and get the model part alone that we need for this method.
			r_viewMat = ReflectionHelper.findField(ActiveRenderInfo.class, "MODELVIEW", "field_178812_b");
		}
		try {
			FloatBuffer VIEW_MAT = (FloatBuffer) r_viewMat.get(null);
			VIEW_MAT.rewind();
			Matrix4f v_mat = new Matrix4f();
			v_mat.load(VIEW_MAT);
			VIEW_MAT.rewind();
			v_mat.invert();
			Matrix4f.mul(v_mat, mv_mat, mv_mat);
		} catch(IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		Vec3d[] retArr = new Vec3d[positions.length];
		for(int i = 0; i < positions.length; i ++){
			Vector4f pos = new Vector4f(positions[i].x, positions[i].y, positions[i].z, positions[i].w);
			Matrix4f.transform(mv_mat, pos, pos);
			Vec3d entPos = renderView.getPositionEyes(partialTicks);
			Vec3d pos2 = new Vec3d(pos.x, pos.y, pos.z);
			retArr[i] = pos2.add(new Vec3d(entPos.x, entPos.y-renderView.getEyeHeight(), entPos.z));
		}
		return retArr;
	}
	
	@SideOnly(Side.CLIENT)
	public static Vec3d[] viewFromLocal(Vector4f... positions){
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
		Matrix4f mv_mat = new Matrix4f();
		mv_mat.load(ClientProxy.AUX_GL_BUFFER);
		ClientProxy.AUX_GL_BUFFER.rewind();
		Vec3d[] retArr = new Vec3d[positions.length];
		for(int i = 0; i < positions.length; i ++){
			Vector4f pos = new Vector4f(positions[i].x, positions[i].y, positions[i].z, positions[i].w);
			Matrix4f.transform(mv_mat, pos, pos);
			Vec3d pos2 = new Vec3d(pos.x, pos.y, pos.z);
			retArr[i] = pos2;
		}
		return retArr;
	}
	
	@SideOnly(Side.CLIENT)
	public static Vec3d[] viewToLocal(Vector4f... positions){
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, ClientProxy.AUX_GL_BUFFER);
		Matrix4f mv_mat = new Matrix4f();
		mv_mat.load(ClientProxy.AUX_GL_BUFFER);
		mv_mat.invert();
		ClientProxy.AUX_GL_BUFFER.rewind();
		Vec3d[] retArr = new Vec3d[positions.length];
		for(int i = 0; i < positions.length; i ++){
			Vector4f pos = new Vector4f(positions[i].x, positions[i].y, positions[i].z, positions[i].w);
			Matrix4f.transform(mv_mat, pos, pos);
			Vec3d pos2 = new Vec3d(pos.x, pos.y, pos.z);
			retArr[i] = pos2;
		}
		return retArr;
	}
	
	//https://www.euclideanspace.com/maths/geometry/rotations/conversions/quaternionToMatrix/index.htm
	//TODO See if I can replace with the more optimized looking version from glm?
	public static void matrixFromQuat(Matrix3f m, Quat4f q){
		m.m00 = 1-2*q.y*q.y-2*q.z*q.z;
		m.m01 = 2*q.x*q.y-2*q.z*q.w;
		m.m02 = 2*q.x*q.z+2*q.y*q.w;
		
		m.m10 = 2*q.x*q.y+2*q.z*q.w;
		m.m11 = 1-2*q.x*q.x-2*q.z*q.z;
		m.m12 = 2*q.y*q.z-2*q.x*q.w;
		
		m.m20 = 2*q.x*q.z-2*q.y*q.w;
		m.m21 = 2*q.y*q.z+2*q.x*q.w;
		m.m22 = 1-2*q.x*q.x-2*q.y*q.y;
	}
	
	public static boolean epsilonEquals(float num1, float num2, float eps){
		float diff = num1-num2;
		return Math.abs(diff) < eps;
	}
	
	public static boolean epsilonEquals(double num1, double num2, double eps){
		double diff = num1-num2;
		return Math.abs(diff) < eps;
	}
	
	public static boolean epsilonEquals(Vec3d a, Vec3d b, double eps){
		double dx = Math.abs(a.x-b.x);
		double dy = Math.abs(a.y-b.y);
		double dz = Math.abs(a.z-b.z);
		
		return dx < eps && dy < eps && dz < eps;
	}
	
	public static int absMaxIdx(double... numbers){
		int idx = 0;
		double max = -Double.MAX_VALUE;
		for(int i = 0; i < numbers.length; i ++){
			double num = Math.abs(numbers[i]);
			if(num > max){
				idx = i;
				max = num;
			}
		}
		return idx;
	}
	
	public static Vec3 randVecInCone(Vec3 coneDirection, float angle){
		return randVecInCone(coneDirection, angle, rand);
	}
	
	public static Vec3 randVecInCone(Vec3 coneDirection, float angle, Random rand){
		//Gets a random vector rotated within a cone and then rotates it to the particle data's direction
		//Create a new vector and rotate it randomly about the x axis within the angle specified, then rotate that by random degrees to get the random cone vector
		Vec3 up = Vec3.createVectorHelper(0, 1, 0);
		up.rotateAroundX((float) Math.toRadians(rand.nextFloat()*(angle+rand.nextFloat()*angle)));
		up.rotateAroundY((float) Math.toRadians(rand.nextFloat()*360));
		//Finds the angles for the particle direction and rotate our random cone vector to it.
		Vec3 direction = Vec3.createVectorHelper(coneDirection.xCoord, coneDirection.yCoord, coneDirection.zCoord);
		Vec3 angles = BobMathUtil.getEulerAngles(direction);
		Vec3 newDirection = Vec3.createVectorHelper(up.xCoord, up.yCoord, up.zCoord);
		newDirection.rotateAroundX((float) Math.toRadians(angles.yCoord-90));
		newDirection.rotateAroundY((float) Math.toRadians(angles.xCoord));
		return newDirection;
	}
	
	public static Vec3d randVecInCone(Vec3d coneDirection, float angle){
		return randVecInCone(new Vec3(coneDirection), angle).toVec3d();
	}
	
	public static Vec3d randVecInCone(Vec3d coneDirection, float angle, Random rand){
		return randVecInCone(new Vec3(coneDirection), angle, rand).toVec3d();
	}
	
	public static Vec3d mix(Vec3d a, Vec3d b, float amount){
		return new Vec3d(a.x + (b.x - a.x)*amount, a.y + (b.y - a.y)*amount, a.z + (b.z - a.z)*amount);
	}
	
	public static Vec3d mat4Transform(Vec3d vec, @Nullable Matrix4f mat){
		if(mat != null){
			double x = mat.m00 * vec.x + mat.m10 * vec.y + mat.m20 * vec.z + mat.m30;
			double y = mat.m01 * vec.x + mat.m11 * vec.y + mat.m21 * vec.z + mat.m31;
			double z = mat.m02 * vec.x + mat.m12 * vec.y + mat.m22 * vec.z + mat.m32;
			return new Vec3d(x, y, z);
		}
		return vec;
	}

	public static String toPercentage(float amount, float total) {
		return NumberFormat.getPercentInstance().format(amount / total);
	}

	public static double convertScale(double toScale, double oldMin, double oldMax, double newMin, double newMax) {
		double prevRange = oldMax - oldMin;
		double newRange = newMax - newMin;
		return (((toScale - oldMin) * newRange) / prevRange) + newMin;
	}

	public static String[] ticksToDate(long ticks, int tickHour) {
		int tickDay = 24 * tickHour;
		int tickYear = 365 * tickDay;
		double tickMinute = tickHour / 60D;
		double tickSecond = tickHour / 3600D;

		final String[] dateOut = new String[5];
		long year = Math.floorDiv(ticks, tickYear);
		int day = (int) Math.floorDiv(ticks - tickYear * year, tickDay);
		int h = (int) Math.floorDiv(ticks - tickYear * year-tickDay * day, tickHour);
		int min = (int) Math.floor((ticks - tickYear * year-tickDay * day-tickHour * h) / tickMinute);
		int s = (int) Math.floor((ticks - tickYear * year-tickDay * day-tickHour * h-min * tickMinute) / tickSecond);
		dateOut[0] = String.valueOf(year);
		dateOut[1] = String.valueOf(day);
		dateOut[2] = String.valueOf(h);
		dateOut[3] = String.valueOf(min);
		dateOut[4] = String.valueOf(s);
		return dateOut;
	}

	public static String[] ticksToDate(long ticks) {
		return ticksToDate(ticks, 1000);
	}

	public static String ticksToDateString(long ticks, int tickHour) {
		return toDate(ticksToDate(ticks, tickHour));
	}

	public static String toDate(String[] input){
		if(!input[0].equals("0"))
			return input[0]+ "y " + input[1]+ "d " + input[2]+ "h " + input[3]+ "m " + input[4]+ "s";
		else if(!input[1].equals("0"))
			return input[1]+ "d " + input[2]+ "h " + input[3]+ "m " + input[4]+ "s";
		else if(!input[2].equals("0"))
			return input[2]+ "h " + input[3]+ "m " + input[4]+ "s";
		else if(!input[3].equals("0"))
			return input[3]+ "m " + input[4]+ "s";
		else
			return input[4]+ "s";
	}

	public static int interpolateColor(int colorA, int colorB, float percentB) {
		float rA = (colorA >> 16 & 0xFF);
		float gA = (colorA >> 8 & 0xFF);
		float bA = (colorA & 0xFF);
		float rB = (colorB >> 16 & 0xFF);
		float gB = (colorB >> 8 & 0xFF);
		float bB = (colorB & 0xFF);

		float r = rA + (rB-rA) * percentB;
		float g = gA + (gB-gA) * percentB;
		float b = bA + (bB-bA) * percentB;
		return (((int)r & 0xFF) << 16) | (((int)g & 0xFF) << 8) | ((int)b & 0xFF);
	}

	public static double interp(double x, double y, float interp) {
		return x + (y - x) * interp;
	}

	// I am sick of trying to remember the ridiculous quirks of Java 8
	// so I wrote this thing that can shit any int-ish list-ish into a regular fucking int[]
	// made by mellow, thrown here by 70k, thrown into 1.12.2 by Th3_Sl1ze
	public static int[] intCollectionToArray(Collection<Integer> in) {
		return intCollectionToArray(in, i -> (int)i);
	}

	public static int[] intCollectionToArray(Collection<Integer> in, ToIntFunction<? super Object> mapper) {
		return Arrays.stream(in.toArray()).mapToInt(mapper).toArray();
	}

	public static int[] collectionToIntArray(Collection<? extends Object> in, ToIntFunction<? super Object> mapper) {
		return Arrays.stream(in.toArray()).mapToInt(mapper).toArray();
	}
}