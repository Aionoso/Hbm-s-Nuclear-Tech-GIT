package com.hbm.hfr.render.loader;

import com.hbm.render.amlfrom1710.TextureCoordinate;
import com.hbm.render.amlfrom1710.Vec3;
import com.hbm.render.amlfrom1710.Vertex;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class S_Face {
	
	public int[] verticesID;
	public Vertex[] vertices;
	public Vertex[] vertexNormals;
	public Vertex faceNormal;
	public TextureCoordinate[] textureCoordinates;
	
	public float normalX;
	public float normalY;
	public float normalZ;

	public void addFaceForRender(BufferBuilder tessellator) {
		addFaceForRender(tessellator, 0.0F);
	}

	public void addFaceForRender(BufferBuilder tessellator, float textureOffset) {
		
		if (this.faceNormal == null) {
			this.faceNormal = calculateFaceNormal();
		}
		
		normalX = this.faceNormal.x;
		normalY = this.faceNormal.y;
		normalZ = this.faceNormal.z;

		float averageU = 0.0F;
		float averageV = 0.0F;
		
		if ((this.textureCoordinates != null) && (this.textureCoordinates.length > 0)) {
			
			for (int i = 0; i < this.textureCoordinates.length; i++) {
				averageU += this.textureCoordinates[i].u;
				averageV += this.textureCoordinates[i].v;
			}
			
			averageU /= this.textureCoordinates.length;
			averageV /= this.textureCoordinates.length;
		}
		
		for (int i = 0; i < this.vertices.length; i++) {
			
			if ((this.textureCoordinates != null) && (this.textureCoordinates.length > 0)) {
				
				float offsetU = textureOffset;
				float offsetV = textureOffset;
				
				if (this.textureCoordinates[i].u > averageU) {
					offsetU = -offsetU;
				}
				if (this.textureCoordinates[i].v > averageV) {
					offsetV = -offsetV;
				}
				if ((this.vertexNormals != null) && (i < this.vertexNormals.length)) {
					normalX = this.vertexNormals[i].x;
					normalY = this.vertexNormals[i].y;
					normalZ = this.vertexNormals[i].z;
				}
				
				tessellator.pos(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z).tex(this.textureCoordinates[i].u + offsetU, this.textureCoordinates[i].v + offsetV).normal(normalX, normalY, normalZ).endVertex();
			} else {
				tessellator.pos(this.vertices[i].x, this.vertices[i].y, this.vertices[i].z).tex(0, 0).normal(normalX, normalY, normalZ).endVertex();
			}
		}
	}

	public Vertex calculateFaceNormal() {
		
		Vec3 v1 = Vec3.createVectorHelper(this.vertices[1].x - this.vertices[0].x, this.vertices[1].y - this.vertices[0].y, this.vertices[1].z - this.vertices[0].z);
		Vec3 v2 = Vec3.createVectorHelper(this.vertices[2].x - this.vertices[0].x, this.vertices[2].y - this.vertices[0].y, this.vertices[2].z - this.vertices[0].z);
		Vec3 normalVector = null;

		normalVector = v1.crossProduct(v2).normalize();

		return new Vertex((float) normalVector.xCoord, (float) normalVector.yCoord, (float) normalVector.zCoord);
	}
}
