package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class MagnetBlockEntity extends BlockEntity {

    public MagnetBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.MAGNET_BLOCK_ENTITY.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MagnetBlockEntity blockEntity) {
        // Создаем зону поиска: куб со стороной в 5 блоков во все стороны от магнита
        AABB area = new AABB(pos).inflate(5.0);

        // Ищем все выпавшие предметы (ItemEntity) в этой зоне
        List<ItemEntity> items = level.getEntitiesOfClass(ItemEntity.class, area);

        for (ItemEntity item : items) {
            // Проверяем, что предмет не зафиксирован и его можно двигать
            if (!item.isRemoved() && item.isAlive()) {
                Vec3 magnetCenter = new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                Vec3 itemPos = item.position();

                // Считаем вектор направления от предмета к магниту
                Vec3 direction = magnetCenter.subtract(itemPos).normalize();

                // Скорость притягивания (можно увеличить или уменьшить)
                double pullSpeed = 0.15;

                // Задаем предмету новую скорость в сторону магнита
                item.setDeltaMovement(direction.x * pullSpeed, direction.y * pullSpeed, direction.z * pullSpeed);
                item.hasImpulse = true; // Вынуждаем игру обновить физику предмета
            }
        }
    }
}
