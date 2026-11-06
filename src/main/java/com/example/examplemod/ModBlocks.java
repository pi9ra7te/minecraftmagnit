package com.example.examplemod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ExampleMod.MODID);

    // Регистрируем Блок с прочностью железного блока (как в ЛБ)
    public static final RegistryObject<Block> MAGNET_BLOCK = BLOCKS.register("magnet_block",
            () -> new MagnetBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).strength(3.0f, 6.0f).requiresCorrectToolForDrops()));

    // Регистрируем Предмет для блока
    public static final RegistryObject<Item> MAGNET_BLOCK_ITEM = ITEMS.register("magnet_block",
            () -> new BlockItem(MAGNET_BLOCK.get(), new Item.Properties()));

 // Добывается только инструментом (киркой)

    // Регистрируем Логику BlockEntity
    @SuppressWarnings("DataFlowIssue")
    public static final RegistryObject<BlockEntityType<MagnetBlockEntity>> MAGNET_BLOCK_ENTITY = BLOCK_ENTITIES.register("magnet_block",
            () -> BlockEntityType.Builder.of(MagnetBlockEntity::new, MAGNET_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
    }
}
