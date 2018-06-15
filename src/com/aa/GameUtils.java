package com.aa;

import java.util.ArrayList;
import java.util.Random;

class GameUtils {

    static String LotsOfln = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    static int BoundaryScale = 99;
    static int FiftyFiftyChance = 50;

    static int DoorUnlockChances = 3;

    static int PlayerHp = 150;
    static int PlayerDamage = 3;
    static int PlayerAttackRating = 75;
    static int PlayerBlockRating = 25;
    static int MaxNumberOfHealingPotions = 5;

    static int getRandomBoundedValue() {
        Random rand = new Random();
        return rand.nextInt(BoundaryScale);
    }

    static boolean beatsChance(int chance) {
        return getRandomBoundedValue() < chance;
    }

    static boolean getFiftyFiftyChance() {
        return beatsChance(FiftyFiftyChance);
    }

    static Room createMap() {
        

        /* Here, we're making each room (a total of 15), using the variables from Room.java.
        We do this by giving values to the variables. */

        Room entrance = new Room();
        entrance.setName("Entrance");
        entrance.setType("chamber");
        entrance.setMessage("You enter a poorly lit room with a large hole in the roof, a warm breeze comes over you.\nYou can go north, east, and west.\n");


        Room hw1 = new Room();
        hw1.setName("Dark Hallway");
        hw1.setType("corridor");
        hw1.setMessage("The light around you quickly fades and the pitch darkness fills the hall.\nA bitter cold comes over you, chilling you to the bone.\nA magical message on the wall says in the Dwarven language 'BEWARE OF SENTINEL'.\nYou can go east, or west.\n");


        Room kr = new Room();
        kr.setName("Knight Room");
        kr.setType("chamber");
        kr.setMessage("You enter a well lit chamber, statues of past Dwarven kings look down upon you from the walls.\nA horrid stench fills your nose as you realize that the ground is covered in the bones of those who have passed.\nYou can go east or south.\n");
        kr.setKeyId("Glowing Key");
        kr.setBoss(new Enemy("Abyssmal Knight", 20, 10, 45, 20, "Glowing Key"));


        Room cr1 = new Room();
        cr1.setName("Hidden Chamber");
        cr1.setType("chamber");
        cr1.setMessage("You find a small hole in the wall, and walk in, to find yourself facing a rotted chest, years old.\nYou can only go north from here.\n");


        Room rh = new Room();
        rh.setName("Spiked Hallway");
        rh.setType("corridor");
        rh.setMessage("You walk into a stone hallway, the floor is old and bloodstained, while the walls are covered in long sharp spikes.\nYou can go east or west.\n");


        Room d1 = new Room();
        d1.setName("Riddled Door");
        d1.setType("door");
        d1.setMessage("A door stops your path. There is a carved face in the stonework.\n");
        rh.setRiddleQuestion("The stone face carved into the wall says\n'In order to proceed, you must prove your worth.\nAnswer me this riddle, in order to leave safely.\nHowever if you displease me, you will meet your death.\n\nAnswer me this: I began eternity, and ended space,\nat the end of time, and in every place,\nlast in life, second to death,\nnever alone, found in your breath,\ncontained by earth, water or flame\nmy grandeur so awesome, wind dare not tame,\nnot in your mind, but in your dreams,\nvacant to kings, present to queens. What am I?");
        rh.setRiddleQuestion("e");


        Room cr2 = new Room();
        cr2.setName("Cavern");
        cr2.setType("chamber");
        cr2.setMessage("The Dwarven architexture rots away as you enter a large carven.\nStalgmites and stalgtites fill the top and bottom of the cave.\nYou feel a slight draft coming from the holes in the walls, however none of them are large enough for you to pass through.\nYou can go north or east.\n");


        Room hw2 = new Room();
        hw2.setName("Trap-filled Hallway");
        hw2.setType("corridor");
        hw2.setMessage("As you look down the corridor before you,\nyou notice that there are multiple holes, spikes, and balistas on the walls, roof, and floor.\nThe hallway is booby-trapped and will require careful footing to pass.\nYou can go north or south.\n");


        Room mr1 = new Room();
        mr1.setName("Ruins");
        mr1.setType("chamber");
        mr1.setMessage("You enter a cave-like room.\nPillars of Dawrven make spike out of the groud, however ubruptly end near an endless cliff.\nThis is mostlikly where the dragon crawled from the speakless depths of the cave system, and entered the dwarven castle...\nYou can go south or west.\n");


        Room bb = new Room();
        bb.setName("Broken Bridge");
        bb.setType("corridor");
        bb.setMessage("You enter an underground ravine, a fall from this hight would kill you,\nhowever the fall is so great, there is no way of telling when you would hit the ground.\nLuckily, the ancient dwarves created a stone bridge to cross the ravine,\nbut the years of wear on it has caused it to break in half, the only way to cross would be to jump across.\nYou can go east or west.\n");


        Room mr2 = new Room();
        mr2.setName("King's Court");
        mr2.setType("chamber");
        mr2.setMessage("You enter a large courtroom, dining tables on both sides and a large throne in the centre,\nhowever the ground is covered in slime, and the foul stench makes your stomach turn.\nYou can go east, south, or west.\n");
        mr2.setBoss(new Enemy("Carnivorous Slime", 30, 15, 35, 20, "Large Iron Key"));


        Room lh = new Room();
        lh.setName("Looping Hallway");
        lh.setType("corridor");
        lh.setMessage("You enter a long hallway, with magical runes covering the walls.\nA message on the roof reads 'ONLY THOSE WHO HAVE PROVED THEIR VALUE MAY CONTINUE'.\nYou can go north or south from here.");


        Room d2 = new Room();
        d2.setName("Magic Portal");
        d2.setType("door");
        d2.setMessage("A blue glowing portal stops your path, a keyhole floats in the centre.\nA message at the top explains that going through it without the key,\nwill return you to the start of the tunnel.\n");
        d2.setKeyId("Glowing Key");


        Room hw3 = new Room();
        hw3.setName("Empty Hallway");
        hw3.setType("corridor");
        hw3.setMessage("You enter a short hall, with a message written in gold on the walls, multiple times.\nThe ,message reads 'THOSE WHO SHOW AVARICE, WILL DIE THE WAY THEY LIVE.'\nYou can go east or west from here.\n");


        Room rt = new Room();
        rt.setName("Traped Room");
        rt.setType("chamber");
        rt.setMessage("You enter a room filled with traps.\nWalking through blindly will be your demise, but with careful footing, you may proceed freely.\nYou can go east or north.\n");


        Room cr3 = new Room();
        cr3.setName("Golden Hall");
        cr3.setType("corridor");
        cr3.setMessage("The hallway before you if filled with golden coins, and artifacts from ancient worlds.\nThe vault that the dragon sleeps in is near.\nThe walls of the room have magic runes inscribed into them,\nand the only thing that is not of value, is a simple wooden chest.\nThe vast gold temps you...\nYou can go north or south.\n");


        Room d3 = new Room();
        d3.setName("Sealed Door");
        d3.setType("door");
        d3.setMessage("A large seal stops your path.\nNo man can open this by hand, however there is a very large keyhole on the door.\n");
        d3.setKeyId("Large Iron Key");


        Room br = new Room();
        br.setName("Vault of Ashardalon");
        br.setType("chamber");
        br.setMessage("Finally, the vault.\nBones and gold flows out to your feet as you enter.\nThe room is the largest chamber in the entire castle.\nThe heat in the room makes you sweat.\nSmoke quickly fills the room, and as it fades, the great red dragon Ashardalon spreads it's wings in a show of power.\nMagma flows from his mouth and smoke from his nose.\nHe opens his mouth as a fire builds at the back of his throat.\nPrepare for combat!\n");
        br.setBoss(new Enemy("Red Terror Ashardalon", 50, 20, 20, 0, null));


        // now tying it all together

        cr1.setNorth(kr);
        kr.setSouth(cr1);

        kr.setEast(hw1);
        hw1.setWest(kr);

        hw1.setEast(entrance);
        entrance.setWest(hw1);
        entrance.setNorth(lh);
        lh.setSouth(entrance);
        entrance.setEast(rh);
        rh.setWest(entrance);

        rh.setEast(d1);
        d1.setWest(rh);

        d1.setEast(cr2);
        cr2.setWest(d1);

        cr2.setNorth(hw2);
        hw2.setSouth(cr2);

        hw2.setNorth(mr1);
        mr1.setSouth(hw2);

        mr1.setWest(bb);
        bb.setEast(mr1);

        bb.setWest(mr2);
        mr2.setEast(bb);

        mr2.setSouth(d2);
        d2.setNorth(mr2);
        mr2.setWest(hw3);
        hw3.setEast(mr2);

        d2.setSouth(lh);
        lh.setNorth(d2);

        hw3.setWest(rt);
        rt.setEast(hw3);

        rt.setNorth(cr3);
        cr3.setSouth(rt);

        cr3.setNorth(d3);
        d3.setSouth(cr3);

        d3.setNorth(br);

        return entrance;
        
    }
    
    // now adding the enemies to the map

    static ArrayList<Enemy> createEnemies() {
        Enemy e;
        ArrayList enemies = new ArrayList();

        e = new Enemy("Troll", 10, 15, 20, 15, null);
        enemies.add(e);

        e = new Enemy("Skeleton", 3, 5, 50, 2, null);
        enemies.add(e);
        
        e = new Enemy("Skeleton Mage", 3, 8, 20, 5, null);
        enemies.add(e);

        e = new Enemy("Orc", 5, 5, 50, 5, null);
        enemies.add(e);
        
        e = new Enemy("Orc Archer", 5, 7, 25, 5, null);
        enemies.add(e);
        
        e = new Enemy("Orc Captain", 7, 8, 40, 10, null);
        enemies.add(e);

        e = new Enemy("Cursed Armour", 10, 5, 65, 5, null);
        enemies.add(e);

        e = new Enemy("Giant Spider", 8, 8, 65, 10, null);
        enemies.add(e);
        
        e = new Enemy("Sentinel", 10, 5, 50, 5, null);
        enemies.add(e);

        e = new Enemy("Lich", 6, 6, 20, 15, null);
        e.addSpawn(new Enemy("Undead Dragon", 10, 6, 35, 0, null));
        enemies.add(e);

        e = new Enemy("Werewolf", 7, 6, 50, 5, null);
        enemies.add(e);

        e = new Enemy("Vampire", 6, 7, 40, 10, null);
        e.addSpawn(new Enemy("Ghoul", 5, 4, 75, 0, null));
        enemies.add(e);

        e = new Enemy("Ghoul", 5, 4, 75, 2, null);
        enemies.add(e);

        e = new Enemy("Phantom", 1, 3, 100, 5, null);
        e.addSpawn(new Enemy("Phantom", 1, 3, 100, 0, null));
        e.addSpawn(new Enemy("Phantom", 1, 3, 100, 0, null));
        enemies.add(e);

        e = new Enemy("Brittle Statue", 5, 8, 40, 10, null);
        enemies.add(e);

        e = new Enemy("Cave Bear", 6, 4, 50, 2, null);
        enemies.add(e);

        e = new Enemy("Parasitic Worm", 2, 2, 40, 2, null);
        e.addSpawn(new Enemy("Parasitic Worm", 2, 2, 40, 2, null));
        enemies.add(e);

        e = new Enemy("Basilisk", 12, 10, 30, 15, null);
        enemies.add(e);

        e = new Enemy("Wyvern", 8, 7, 40, 10, null);
        enemies.add(e);
        
        e = new Enemy("Necromancer", 5, 5, 30, 10, null);
        e.addSpawn(new Enemy("Skeleton", 3, 3, 50, 0, null));
        e.addSpawn(new Enemy("Skeleton", 3, 3, 50, 0, null));
        enemies.add(e);
        
        e = new Enemy("Demon Legionaire", 7, 6, 50, 5, null);
        e.addSpawn(new Enemy("Tortured Soul", 3, 2, 30, 0, null));
        e.addSpawn(new Enemy("Tortured Soul", 3, 2, 30, 0, null));
        enemies.add(e);
        
        e = new Enemy("Balrog", 15, 8, 20, 15, null);
        enemies.add(e);
        
        e = new Enemy("Goblin", 4, 5, 70, 2, null);
        enemies.add(e);
        
        e = new Enemy("Goblin Theif", 2, 7, 30, 10, null);
        enemies.add(e);
        
        e = new Enemy("Goblin Clanmaster", 6, 6, 50, 10, null);
        enemies.add(e);
        
        e = new Enemy("Goblin Scout", 4, 1, 10, 2, null);
        e.addSpawn(new Enemy("Goblin", 4, 3, 70, 2, null));
        e.addSpawn(new Enemy("Goblin", 4, 3, 70, 2, null));
        e = new Enemy("Goblin Clanmaster", 6, 6, 50, 10, null);
        enemies.add(e);
        
        e = new Enemy("Insect Swarm", 1, 3, 90, 2, null);
        enemies.add(e);
        
        e = new Enemy("Hag", 4, 3, 25, 5, null);
        e.addSpawn(new Enemy("Insect Swarm", 1, 3, 90, 0, null));
        e.addSpawn(new Enemy("Rotted Giant", 10, 8, 40, 0, null));
        enemies.add(e);
        
        
        
        return enemies;
    }


    static Player createPlayer() {
        Player p = new Player();
        p.setRoom(createMap());
        return p;
    }
}
