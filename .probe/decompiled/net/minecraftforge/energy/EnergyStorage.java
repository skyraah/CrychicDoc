package net.minecraftforge.energy;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class EnergyStorage implements IEnergyStorage, INBTSerializable<Tag> {

    protected int energy;

    protected int capacity;

    protected int maxReceive;

    protected int maxExtract;

    public EnergyStorage(int capacity) {
        this(capacity, capacity, capacity, 0);
    }

    public EnergyStorage(int capacity, int maxTransfer) {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public EnergyStorage(int capacity, int maxReceive, int maxExtract) {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public EnergyStorage(int capacity, int maxReceive, int maxExtract, int energy) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        if (!this.canReceive()) {
            return 0;
        } else {
            int energyReceived = Math.min(this.capacity - this.energy, Math.min(this.maxReceive, maxReceive));
            if (!simulate) {
                this.energy += energyReceived;
            }
            return energyReceived;
        }
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        if (!this.canExtract()) {
            return 0;
        } else {
            int energyExtracted = Math.min(this.energy, Math.min(this.maxExtract, maxExtract));
            if (!simulate) {
                this.energy -= energyExtracted;
            }
            return energyExtracted;
        }
    }

    @Override
    public int getEnergyStored() {
        return this.energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }

    @Override
    public Tag serializeNBT() {
        return IntTag.valueOf(this.getEnergyStored());
    }

    @Override
    public void deserializeNBT(Tag nbt) {
        if (nbt instanceof IntTag intNbt) {
            this.energy = intNbt.getAsInt();
        } else {
            throw new IllegalArgumentException("Can not deserialize to an instance that isn't the default implementation");
        }
    }
}