package SweetClient.mods.blockparticles.vector;

import net.minecraft.util.Vector3d;

public class FBPVector3d extends Vector3d {
   public void add(Vector3d var1) {
      this.x += var1.x;
      this.y += var1.y;
      this.z += var1.z;
   }

   public FBPVector3d partialVec(FBPVector3d var1, float var2) {
      FBPVector3d var3 = new FBPVector3d();
      var3.x = var1.x + (this.x - var1.x) * (double)var2;
      var3.y = var1.y + (this.y - var1.y) * (double)var2;
      var3.z = var1.z + (this.z - var1.z) * (double)var2;
      return var3;
   }

   public FBPVector3d(double var1, double var3, double var5) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
   }

   public void copyFrom(Vector3d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public FBPVector3d() {
   }

   public FBPVector3d(FBPVector3d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
   }

   public void zero() {
      this.x = 0.0;
      this.y = 0.0;
      this.z = 0.0;
   }

   public FBPVector3d multiply(double var1) {
      FBPVector3d var3 = new FBPVector3d(this);
      var3.x *= var1;
      var3.y *= var1;
      var3.z *= var1;
      return var3;
   }
}
