package com.hbm.handler.jei;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.machine.ItemMachineUpgrade.UpgradeType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class UpgradeDetailsDatabase {
    public static Map<UpgradeType,Map<Integer,ItemStack>> upgrades = new HashMap<>(); // chaotic
    private static boolean completed = false;
    public static Map<Block,List<UpgradeTabContent>> supportedMachines = new HashMap<>();
    public static void tryAddUpgrade(UpgradeType type, Integer tier, Item item) {
        if (!upgrades.containsKey(type)) upgrades.put(type,new HashMap<>()); // don't think it should exactly be HashMap tho...
        if (!upgrades.get(type).containsKey(tier)) upgrades.get(type).put(tier,new ItemStack(item));
    }
    public static class UpgradeTabContent {
        final UpgradeType upgrade;
        final List<String> details;
        public UpgradeTabContent(UpgradeType upgrade,List<String> details) {
            this.upgrade = upgrade;
            this.details = details; // perk name -> perk value -> penalty name -> penalty value -> always this loop
        }
    }
    protected static void tryAddMachine(Block machine,List<UpgradeType> upgrades,List<List<String>> details) {
        if (!supportedMachines.containsKey(machine)) {
            ArrayList<UpgradeTabContent> tabs = new ArrayList<>();
            for (UpgradeType upgrade:upgrades) {
                UpgradeTabContent tab = new UpgradeTabContent(upgrade,details.get(upgrades.indexOf(upgrade)));
                tabs.add(tab);
            }
            supportedMachines.put(machine,tabs);
        }
    }
    public static void init() {
        if (completed) return;
        completed = true;
        /*
        EXAMPLES FOR MY MELTY BRAIN CELLS
        Formulas like speed = speed * (1 + speedLevel);
              or like speedMod = speedMod + speedLevel;     speed = speed * speedMod => "speed","+1x" "+2x" "+3x" etc
        Formulas like speedMod = speedMod * (1+speedLevel); speed = speed * speedMod => "speed","×2"  "×3"  "×4" etc
        Formulas like speed = speed + speedLevel;                                    => "speed","+1"  "+2"  "+3"  etc

        basically no x when it's absolute change
        P.S. ah forget it this is beyond the limits of an idiot called me

        CONVERTING EXAMPLE
        "speed","×2"  "×4"  "×6" => "+1x" "+3x" "+5x"

        yeah i have no idea how to make people understand those complicated formulas in like 3 characters long
         */
        tryAddMachine(ModBlocks.machine_centrifuge,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.SCREAM,
                        UpgradeType.POWER,
                        UpgradeType.OVERDRIVE
                ),
                Arrays.asList(
                        Arrays.asList(
                                "speed","+1x",
                                "consumption","+200",

                                "speed","+2x",
                                "consumption","+400",

                                "speed","+3x",
                                "consumption","+600"
                        ),
                        Arrays.asList(
                                "speed","+6x",
                                "consumption","+1200"
                        ),
                        Arrays.asList(
                                "consumption","×1/2",
                                null,null,

                                "consumption","×1/3",
                                null,null,

                                "consumption","×1/4",
                                null,null
                        ),
                        Arrays.asList(
                                "speed","×3",
                                "consumption","+10000",

                                "speed","×5",
                                "consumption","+20000",

                                "speed","×7",
                                "consumption","+30000"
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_assembler,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.POWER
                ),
                Arrays.asList(
                        Arrays.asList(
                                "delay","-0.25x",
                                "consumption","+2x",

                                "delay","-0.35x",
                                "consumption","+5x",

                                "delay","-0.50x",
                                "consumption","+8x"
                        ),
                        Arrays.asList(
                                "consumption","-0.2x",
                                "delay","+0.25x",

                                "consumption","-0.6x",
                                "delay","+0.50x",

                                "consumption","-0.8x",
                                "delay","+1.00x"
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_chemplant,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.POWER
                ),
                Arrays.asList(
                        Arrays.asList(
                                "delay","-0.25x",
                                "consumption","+2x",

                                "delay","-0.35x",
                                "consumption","+5x",

                                "delay","-0.50x",
                                "consumption","+8x"
                        ),
                        Arrays.asList(
                                "consumption","-0.2x",
                                "delay","+0.25x",

                                "consumption","-0.6x",
                                "delay","+0.50x",

                                "consumption","-0.8x",
                                "delay","+1.00x"
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_excavator,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.POWER
                ),
                Arrays.asList(
                        Arrays.asList(
                                "speed","+0.5x",
                                "consumption","+1x",

                                "speed","+1.0x",
                                "consumption","+2x",

                                "speed","+1.5x",
                                "consumption","+3x"
                        ),
                        Arrays.asList(
                                "consumption","×1/2",
                                null,null,

                                "consumption","×1/3",
                                null,null,

                                "consumption","×1/4",
                                null,null
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_crystallizer,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.SCREAM,
                        UpgradeType.EFFECT,
                        UpgradeType.OVERDRIVE
                ),
                Arrays.asList(
                        Arrays.asList(
                                "delay","-0.25x",
                                "consumption","+1000",

                                "delay","-0.50x",
                                "consumption","+2000",

                                "delay","-0.75x",
                                "consumption","+3000"
                        ),
                        Arrays.asList(
                                "delay","-0.90x",
                                "consumption","+6000"
                        ),
                        Arrays.asList(
                                "free","+5%",
                                "consumption_a","+3x (+500mB max)",

                                "free","+10%",
                                "consumption_a","+4x (+500mB max)",

                                "free","+15%",
                                "consumption_a","+5x (+500mB max)"
                        ),
                        Arrays.asList(
                                "speed","×3",
                                "consumption","×3",

                                "speed","×5",
                                "consumption","×5",

                                "speed","×7",
                                "consumption","×7"
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_turbofan,
                Arrays.asList(
                        UpgradeType.AFTERBURN
                ),
                Arrays.asList(
                        Arrays.asList(
                                "production","+1x",
                                "consumption","+1x",

                                "production","+2x",
                                "consumption","+2x",

                                "production","+3x",
                                "consumption","+3x"
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_flare,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.EFFECT
                ),
                Arrays.asList(
                        Arrays.asList(
                                "production","+1x",
                                "consumption","+1x",

                                "production","+2x",
                                "consumption","+2x",

                                "production","+3x",
                                "consumption","+3x"
                        ),
                        Arrays.asList(
                                "production","+1/3x",
                                null,null,

                                "production","+2/3x",
                                null,null,

                                "production","+3/3x",
                                null,null
                        )
                )
        );
        /*
            this.speedLevel = Math.min(upgradeManager.getLevel(UpgradeType.SPEED), 3);
            this.energyLevel = Math.min(upgradeManager.getLevel(UpgradeType.POWER), 3);
            this.overLevel = Math.min(upgradeManager.getLevel(UpgradeType.OVERDRIVE), 3) + 1;

    public int getPowerReqEff() {
        int req = MachineConfig.powerConsumptionPerOperationFrackingTower;
        return (req + (this.speedLevel * req / 4) - (this.energyLevel * req / 4)) * this.overLevel;
    }

    public int getDelayEff() {
        int delay = MachineConfig.delayPerOperationFrackingTower;
        return Math.max((delay - (this.speedLevel * delay / 4) + (this.energyLevel * delay / 10)) / this.overLevel, 1);
    }
         */
        tryAddMachine(ModBlocks.machine_fracking_tower,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.POWER,
                        UpgradeType.OVERDRIVE
                ),
                Arrays.asList(
                        Arrays.asList(
                                "delay","-1/4x",
                                "consumption","+1/4x",

                                "delay","-2/4x",
                                "consumption","+2/4x",

                                "delay","-3/4x",
                                "consumption","+3/4x"
                        ),
                        Arrays.asList(
                                "consumption","-1/4x",
                                "delay","+1/10x",

                                "consumption","-2/4x",
                                "delay","+2/10x",

                                "consumption","-3/4x",
                                "delay","+3/10x"
                        ),
                        Arrays.asList(
                                "delay","×1/2",
                                "consumption","×2",

                                "delay","×1/3",
                                "consumption","×3",

                                "delay","×1/4",
                                "consumption","×4"
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_cyclotron,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.POWER,
                        UpgradeType.EFFECT
                ),
                Arrays.asList(
                        Arrays.asList(
                                "speed","+1x",
                                null,null,

                                "speed","+2x",
                                null,null,

                                "speed","+3x",
                                null,null
                        ),
                        Arrays.asList(
                                "consumption","-100000",
                                null,null,

                                "consumption","-200000",
                                null,null,

                                "consumption","-300000",
                                null,null
                        ),
                        Arrays.asList(
                                "safety","50%",
                                null,null,

                                "safety","67%",
                                null,null,

                                "safety","75%",
                                null,null
                        )
                )
        );
        tryAddMachine(ModBlocks.machine_mining_laser,
                Arrays.asList(
                        UpgradeType.SPEED,
                        UpgradeType.EFFECT,
                        UpgradeType.OVERDRIVE,
                        UpgradeType.FORTUNE,
                        UpgradeType.SCREAM,
                        UpgradeType.NULLIFIER
                ),
                Arrays.asList(
                        Arrays.asList(
                                "speed","+2x",
                                "consumption","+2x",

                                "speed","+4x",
                                "consumption","+4x",

                                "speed","+6x",
                                "consumption","+6x"
                        ),
                        Arrays.asList(
                                "range","+4",
                                null,null,

                                "range","+8",
                                null,null,

                                "range","+12",
                                null,null
                        ),
                        Arrays.asList(
                                "speed","×2",
                                "consumption","×2",

                                "speed","×3",
                                "consumption","×3",

                                "speed","×4",
                                "consumption","×4"
                        ),
                        Arrays.asList(
                                "fortune","1",
                                null,null,

                                "fortune","2",
                                null,null,

                                "fortune","3",
                                null,null
                        ),
                        Arrays.asList(
                                "speed","×16",
                                "consumption","×20"
                        ),
                        Arrays.asList(
                                "nullifier","",
                                null,null
                        )
                )
        );

    }
}
