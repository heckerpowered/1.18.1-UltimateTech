package heckerpowered.ultimatetech.common.network;

import heckerpowered.ultimatetech.common.capabilities.Capabilities;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

@Deprecated
public final class UpdateEnergyPacket implements IUltimateTechPacket {
    private final int id;
    private final int slot;
    private final double energy;
    private final double maxEnergy;

    public UpdateEnergyPacket(int id, int slot, double energy, double maxEnergy) {
        this.id = id;
        this.slot = slot;
        this.energy = energy;
        this.maxEnergy = maxEnergy;
    }

    @Override
    public void handle(Context context) {
        var minecraft = Minecraft.getInstance();
        var player = minecraft.player;
        if (player != null) {
            player.getInventory().getItem(slot).getCapability(Capabilities.ITEM_ENERGY_CAPABILITY).ifPresent(e -> {
                e.setEnergy(energy);
                e.setMaxEnergy(maxEnergy);
            });
        }
    }

    @Override
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(id);
        buffer.writeInt(slot);
        buffer.writeDouble(energy);
        buffer.writeDouble(maxEnergy);
    }

    public static UpdateEnergyPacket decode(FriendlyByteBuf buffer) {
        return new UpdateEnergyPacket(buffer.readInt(), buffer.readInt(), buffer.readDouble(), buffer.readDouble());
    }
}
