package me.therkelsen.poisonarrow;

import com.projectkorra.projectkorra.BendingPlayer;
import com.projectkorra.projectkorra.ability.CoreAbility;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.ComplexLivingEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.text.html.ListView;
import java.util.ArrayList;

public class PoisonArrowListener implements Listener {

    private ArrayList<Poisonarrow> arrows;

    public PoisonArrowListener() {
        this.arrows = new ArrayList<>();
    }

    @EventHandler
    private void onHit(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Arrow) {
            for (Poisonarrow arrow : arrows) {
                if (arrow.getArrowUUID() == null) {
                    arrows.remove(arrow);
                    continue;
                }
                if(arrow.getArrowUUID() == event.getEntity().getUniqueId()) {
                    if (event.getEntity() instanceof LivingEntity) {
                        LivingEntity entity =  (LivingEntity) event.getEntity();
                        entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 80, 1));
                        arrows.remove(arrow);
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    private void onSwing(PlayerAnimationEvent event) {
        if(event.getAnimationType() == PlayerAnimationType.ARM_SWING) {
            BendingPlayer bplayer = BendingPlayer.getBendingPlayer(event.getPlayer());
            if (bplayer.canBend(CoreAbility.getAbility("PoisonArrow"))) {
                if (!CoreAbility.hasAbility(event.getPlayer(),Poisonarrow.class)) {
                    Poisonarrow parrow = new Poisonarrow(event.getPlayer());
                    this.arrows.add(parrow);
                }
            }
        }

    }

}
