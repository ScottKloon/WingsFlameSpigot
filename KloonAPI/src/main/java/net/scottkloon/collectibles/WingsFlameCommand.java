package net.scottkloon.collectibles;

import net.scottkloon.KloonAPI;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WingsFlameCommand implements CommandExecutor {

    private final KloonAPI plugin; // Referência do plugin

    public WingsFlameCommand(KloonAPI plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Adiciona partículas de asas de fogo ao jogador
            addWingParticles(player);
            return true;
        } else {
            sender.sendMessage("Este comando só pode ser executado por jogadores.");
            return false;
        }
    }

    private void addWingParticles(Player player) {
        // Matriz que representa o formato das asas de borboleta
        final byte[][] wingPattern = {
                // Seu padrão de partículas
        };

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!player.isOnline()) {
                    this.cancel();
                    return;
                }

                // Obtém a localização do jogador
                for (int y = 0; y < wingPattern.length; y++) {
                    for (int x = 0; x < wingPattern[y].length; x++) {
                        if (wingPattern[y][x] == 1) {
                            // Calcula a posição da partícula relativa ao jogador
                            double offsetX = (x - wingPattern[y].length / 2) * 0.3 + 0.15; // Ajusta o deslocamento X para centralizar
                            double offsetY = (wingPattern.length - y) * 0.3; // Inverte a posição Y
                            player.getWorld().spawnParticle(Particle.FLAME,
                                    player.getLocation().add(offsetX, offsetY, -0.5),
                                    0, 0, 0, 0);
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 2); // Executa a cada 2 ticks (0.1 segundo) com referência do plugin
    }
}
