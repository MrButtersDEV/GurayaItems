# GurayaItems
 Custom items plugin made by request!


[![Discord](https://img.shields.io/badge/Discord-BUTTERFIELD8%233907-blue)](https://discord.gg/nnC7nkT)
[![PayPal](https://img.shields.io/badge/Donate-Paypal-orange)](https://paypal.me/ButterfieldMedia?locale.x=en_US)

### Commands:
+ Give Command: `/gitem` [item] [amt]
    - [amt] Only works for SmokeBomb as its the only item players should have multiple of!
    - Aliases: `/gitems`, `/gi`
    - Permission: `gurayaitems.use`
    - Console Format:
       + `gi [player] [item] [amt]     `
+ Reload Command: `/guraya-reload`
    - Permission: `gurayaitems.reload`



### Config:
```yaml
#Chaning this data can break any existing items players may have.
#Sounds: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html

Messages:
  onCooldown: "&c{timeleft} seconds left on cooldown!"
  msgReload: "&6Plugin reloaded!"
  msgNoPerm: "&cYou don't have permission to do that!"
  msgTracking: "&bYou are now tracking &3{target}"
  msgNoPlayers: "&cNo players are nearby."

RiggedPen:
  Cooldown: 5
  Usage: 20
  SoundEffect:
      Sound: ITEM_ARMOR_EQUIP_DIAMOND
      Volume: 0.5
      Pitch: 1
  Stage1:
    Name: "&6Rigged Pen &7- (Right Click)"
    Lore:
      - ""
      - "&aBlade Withdrawn"
      - ""
  Stage2:
    Name: "&6Rigged Pen &7- (Right Click)"
    Sharpness: 5
    Lore:
      - ""
      - "&cBlade Extended"
      - ""

SmokeBomb:
  Cooldown: 60
  Blindness: 30
  ParticleAmt: 10
  SoundEffect:
    Sound: BLOCK_FIRE_EXTINGUISH
    Volume: 30
    Pitch: 0.6
  Name: "&8Smoke Bomb"
  Lore:
    - ""
    - "&7Gives blindness to nearby enemies!"
    - ""

Tracker:
  Name: "&3Player Tracker"
  SoundEffect:
      Sound: ENTITY_ARROW_HIT_PLAYER
      Volume: 1
      Pitch: 1
  Lore:
    - ""
    - "&7Tracks nearest player within 100 blocks."
    - "&3Target: &b{target}"
    - ""

GrapplingHook:
  Cooldown: 15
  Unbreakable: true
  Name: "&bGrappling Hook"
  Lore:
    - ""
    - "&7Right click to throw"
    - "&7then right click to go!"
    - ""

BriefcaseMinigun:
  Name: "&cBriefcase Minigun"
  Cooldown: 30
  Usage: 10
  BulletVelocity: 3.00
  BulletDamage: 2.00
  SoundEffect:
      Sound: ENTITY_FIREWORK_ROCKET_LARGE_BLAST
      Volume: 1
      Pitch: 2.00
  Lore:
    - ""
    - "&7Right click to shoot"
    - ""

XRayGoggles:
  Name: "&aX-Ray Goggles"
  Lore:
    - ""
    - "&2Highlights nearby entity's!"
    - ""
```
