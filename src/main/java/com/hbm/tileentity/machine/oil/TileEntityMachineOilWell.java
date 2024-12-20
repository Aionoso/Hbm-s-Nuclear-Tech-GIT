package com.hbm.tileentity.machine.oil;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.MachineConfig;
import com.hbm.entity.particle.EntityGasFX;
import com.hbm.explosion.ExplosionLarge;
import com.hbm.forgefluid.FFUtils;
import com.hbm.items.ModItems;
import com.hbm.lib.Library;
import com.hbm.packet.AuxElectricityPacket;
import com.hbm.packet.FluidTankPacket;
import com.hbm.packet.PacketDispatcher;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityMachineOilWell extends TileEntityOilDrillBase {

	// private static final int[] slots_top = new int[] {1};
	// private static final int[] slots_bottom = new int[] {2, 0};
	// private static final int[] slots_side = new int[] {0};

	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.getCustomName() : "container.oilWell";
	}

	@Override
    public long getMaxPower() {
        return 100000L;
    }

	@SuppressWarnings("deprecation")
	@Override
	public void update() {
		int timer = MachineConfig.delayPerOperationDerrick;

		age++;
		age2++;
		if(age >= timer)
			age -= timer;
		if(age2 >= 20)
			age2 -= 20;
		if(!world.isRemote) {
			this.updateConnections();
			int tank0Amount = tanks[0].getFluidAmount();
			int tank1Amount = tanks[1].getFluidAmount();
			if(age2 == 9 || age2 == 19) {
				fillFluidInit(tanks[0]);
				fillFluidInit(tanks[1]);
			}

			if(FFUtils.fillFluidContainer(inventory, tanks[0], 1, 2))
				needsUpdate = true;
			if(FFUtils.fillFluidContainer(inventory, tanks[1], 3, 4))
				needsUpdate = true;

			if(needsUpdate) {
				needsUpdate = false;
			}
			power = Library.chargeTEFromItems(inventory, 0, power, getMaxPower());

			if(power >= MachineConfig.powerConsumptionPerOperationDerrick && !(tank0Amount >= tanks[0].getCapacity() || tank1Amount >= tanks[1].getCapacity())) {

				// operation start

				if(age == timer - 1) {
					warning = 0;

					// warning 0, green: derrick is operational
					// warning 1, red: derrick is full, has no power or the
					// drill is jammed
					// warning 2, yellow: drill has reached max depth

					for(int i = pos.getY() - 1; i > pos.getY() - 1 - 250; i--) {

						if(i <= 0) {
							// Code 2: The drilling ended
							warning = 2;
							break;
						}

						Block b = world.getBlockState(new BlockPos(pos.getX(), i, pos.getZ())).getBlock();
						if(b == ModBlocks.oil_pipe)
							continue;

						if((b.isReplaceable(world, new BlockPos(pos.getX(), i, pos.getZ())) || b.getExplosionResistance(null) < 1000) && !(b == ModBlocks.ore_oil || b == ModBlocks.ore_oil_empty || b == ModBlocks.ore_bedrock_oil)) {
							world.setBlockState(new BlockPos(pos.getX(), i, pos.getZ()), ModBlocks.oil_pipe.getDefaultState());

							// Code 2: The drilling ended
							if(i == pos.getY() - 250)
								warning = 2;
							break;

						} else if(this.tanks[0].getFluidAmount() < this.tanks[0].getCapacity() && this.tanks[1].getFluidAmount() < this.tanks[1].getCapacity()) {
							if(succ(pos.getX(), i, pos.getZ()) == 1) {

								int oilCollected = MachineConfig.oilPerDepositBlockMinDerrick + ((MachineConfig.oilPerDepositBlockMaxExtraDerrick > 0) ? world.rand.nextInt(MachineConfig.oilPerDepositBlockMaxExtraDerrick) : 0);
								int gasCollected = MachineConfig.gasPerDepositBlockMinDerrick + ((MachineConfig.gasPerDepositBlockMaxExtraDerrick > 0) ? world.rand.nextInt(MachineConfig.gasPerDepositBlockMaxExtraDerrick) : 0);

								this.tanks[0].fill(new FluidStack(tankTypes[0], oilCollected), true);
								this.tanks[1].fill(new FluidStack(tankTypes[1], gasCollected), true);
								needsUpdate = true;

								ExplosionLarge.spawnOilSpills(world, pos.getX() + 0.5F, pos.getY() + 5.5F, pos.getZ() + 0.5F, 3);
								world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_GENERIC_SWIM, SoundCategory.BLOCKS, 2.0F, 0.5F);

								break;
							} else {
								warning = 2;
								break;
							}

						} else {
							// Code 1: Drill jammed
							warning = 1;
							break;
						}
					}
				}

				// operation end

				power -= MachineConfig.powerConsumptionPerOperationDerrick;
			} else {
				warning = 1;
			}

			warning2 = 0;
			if(tanks[1].getFluidAmount() > 0) {
				if(inventory.getStackInSlot(5).getItem() == ModItems.fuse || inventory.getStackInSlot(5).getItem() == ModItems.screwdriver) {
					warning2 = 2;
					tanks[1].drain(50, true);
					needsUpdate = true;
					world.spawnEntity(new EntityGasFX(world, pos.getX() + 0.5F, pos.getY() + 6.5F, pos.getZ() + 0.5F, 0.0, 0.0, 0.0));
				} else {
					warning2 = 1;
				}
			}

			PacketDispatcher.wrapper.sendToAllAround(new AuxElectricityPacket(pos, power), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			PacketDispatcher.wrapper.sendToAllAround(new FluidTankPacket(pos, new FluidTank[] { tanks[0], tanks[1] }), new TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
			if(tank0Amount != tanks[0].getFluidAmount() || tank1Amount != tanks[1].getFluidAmount()){
				markDirty();
			}
		}
	}

	protected void updateConnections() {
		this.trySubscribe(world, pos.getX() + 2, pos.getY(), pos.getZ(), Library.POS_X);
		this.trySubscribe(world, pos.getX() - 2, pos.getY(), pos.getZ(), Library.NEG_X);
		this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() + 2, Library.POS_Z);
		this.trySubscribe(world, pos.getX(), pos.getY(), pos.getZ() - 2, Library.NEG_Z);
	}

	public void fillFluidInit(FluidTank tank) {
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(-2, 0, 0), 2000) || needsUpdate;
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(2, 0, 0), 2000) || needsUpdate;
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(0, 0, -2), 2000) || needsUpdate;
		needsUpdate = FFUtils.fillFluid(this, tank, world, pos.add(0, 0, 2), 2000) || needsUpdate;
	}


	@Override
	public FluidStack drain(FluidStack resource, boolean doDrain) {
		if(resource == null) {
			return null;
		} else if(resource.getFluid() == tankTypes[0]) {
			int prevAmount = tanks[0].getFluidAmount();
			FluidStack drained = tanks[0].drain(resource.amount, doDrain);
			if(tanks[0].getFluidAmount() != prevAmount)
				needsUpdate = true;
			return drained;
		} else if(resource.getFluid() == tankTypes[1]) {
			int prevAmount = tanks[1].getFluidAmount();
			FluidStack drained = tanks[1].drain(resource.amount, doDrain);
			if(tanks[1].getFluidAmount() != prevAmount)
				needsUpdate = true;
			return drained;
		} else {
			return null;
		}
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if(tanks[0].getFluidAmount() > 0) {
			int prevAmount = tanks[0].getFluidAmount();
			FluidStack drained = tanks[0].drain(maxDrain, doDrain);
			if(tanks[0].getFluidAmount() != prevAmount)
				needsUpdate = true;
			return drained;
		} else if(tanks[1].getFluidAmount() > 0) {
			int prevAmount = tanks[1].getFluidAmount();
			FluidStack drained = tanks[1].drain(maxDrain, doDrain);
			if(tanks[1].getFluidAmount() != prevAmount)
				needsUpdate = true;
			return drained;
		} else {
			return null;
		}
	}






	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return TileEntity.INFINITE_EXTENT_AABB;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public double getMaxRenderDistanceSquared() {
		return 65536.0D;
	}
}