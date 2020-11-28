package me.therkelsen.poisonarrow;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.ChiAbility;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.UUID;

public class Poisonarrow extends ChiAbility implements AddonAbility {

    private Arrow arrow;
    private int timer;
    private Vector vec;


    public Poisonarrow(Player player) {
        super(player);
        arrow = player.launchProjectile(Arrow.class,player.getLocation().getDirection());
        timer = 0;
        vec = arrow.getVelocity();
        start();
    }

    @Override
    public void progress() {
        timer++;
        if (timer > 100) {
            remove();
        }
        if (player.isSneaking()) {
            remove();
        }
        if (arrow.getLocation().distance(player.getLocation()) > 30) {
            remove();
        }
        arrow.setVelocity(vec);
    }

    @Override
    public boolean isSneakAbility() {
        return false;
    }

    @Override
    public boolean isHarmlessAbility() {
        return false;
    }

    @Override
    public long getCooldown() {
        return 0;
    }

    @Override
    public String getName() {
        return "PoisonArrow";
    }

    @Override
    public Location getLocation() {
        return arrow.getLocation();
    }

    @Override
    public void load() {
        ProjectKorra.plugin.getServer().getPluginManager().registerEvents(new PoisonArrowListener(),ProjectKorra.plugin);
    }

    @Override
    public void stop() {

    }

    @Override
    public String getAuthor() {
        return "Therkelsen";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    public UUID getArrowUUID() {
        return arrow.getUniqueId();
    }

}