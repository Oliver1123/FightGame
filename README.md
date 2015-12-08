# FightGame
Unit

Main abstract program entity, that represent some king of fighter
Has parameters: health, strength, mana, range and position.
Can do some action: get damage, attack, hit  regenerate, counterattack. Each action returns text description of itself. Also can find out if unit can attack enemy.
Can move in some of specified direction.

Rules:
     - if health = 0   - dead
     - can regenerate, heal [mana +/- random(0; mana/4)]  health points. But can not recover more than the initial level.
     
     - can hit. Hit power calculate as [strength +/- random (0; strength/4] points.
     
     - can counterattack opponent with half a power.
     
     - can attack if distance between unit and enemy is less as range.
     
     
Berserk extends Unit.
Fighter can make double damage but also get a double damage.

BladeMaster extends Unit.
Extra parameter critical strike chance.
Fighter has a chance to make double strike.

Blocker extends Unit.
Extra parameter block percents.
Fighter can block [block percent]% points of damage.

Knight extends Unit.
Extra parameter armor.
Fighter can block [armor] points of damage.

Ninja extends Unit.
Extra parameter evasion chance.
Fighter has a chance to avoid attack.

Class Arena represent a square area with list of fighters on it. Size of arena specified in constructor.  Each position Pair<Integer, Integer> can be occupied or available. After creation each unit has random position.
To each fighter can be found the nearest enemy. Each fighter can be moved in the direction to another fighter. Also the position of each player can be changed


TournamentTask is a background task that get a list of fighter, arena size and CustomLogger as an input parameter. 
After execution, Arena with given fighters and size will be created.Then battle begin.
Find out the nearest alive opponent for each fighter. 
If distance between fighter and enemy is less as fighter range, than he attack opponent, regenerate some hp, and if opponent still alive get a counterattack from him. 
If distance between fighter and enemy is larger as fighter range, than he movo in direction to enemy. 
Each move will be stored in CustomLogger. Battle continues until just one fighter survive. Last Fighter is declared as the winner.

Testing:
  - regeneration test: unit hp can not be regenerate more than the initial level. 
  - immortality test: after receiving damage over current hp unit must be dead.
  - finding the nearest enemy and moving in direction to enemy test.
  - basic activity test(creation, button click response, seekbar progress change response).
