/*
 * This is a class that contains global variables used by the game objects as well as methods
 * that initialize the room structure, the enemy list, and the main character.
 */
package com.aa;

import java.util.ArrayList;
import java.util.Random;

class GameUtils {
    static int BoundaryScale = 99;

    static int DoorUnlockChances = 3;

    static int PlayerHp = 250;
    static int PlayerDamage = 3;
    static int PlayerAttackRating = 70;
    static int PlayerBlockRating = 75;
    static int MaxNumberOfHealingPotions = 10;

    static int getRandomBoundedValue(int boundary) {
        Random rand = new Random();
        return rand.nextInt(boundary);
    }

    static boolean getFiftyFiftyChance() {
        return getRandomBoundedValue(BoundaryScale) < 50;
    }

    private static Room createMap() {

        Room entrance = Room.getNewChamber("Entrance");
        entrance.setMessage("You enter a poorly lit room with a large hole in the roof, a warm breeze comes over you.\nYou can go north, east, and west.\n");


        Room hw1 = Room.getNewCorridor("Dark Hallway");
        hw1.setMessage("The light around you quickly fades and the pitch darkness fills the hall.\nA bitter cold comes over you, chilling you to the bone.\nA magical message on the wall says in the Dwarven language 'BEWARE OF SENTINEL'.\nYou can go east, or west.\n");


        Room kr = Room.getNewChamber("Knight Room");
        kr.setMessage("You enter a well lit chamber, statues of past Dwarven kings look down upon you from the walls.\nA horrid stench fills your nose as you realize that the ground is covered in the bones of those who have passed.\nYou can go east or south.\n");
        kr.setBoss(new Boss(false,"Abyssmal Knight", 20, 10, 45, 20, "Glowing Key", 30));


        Room cr1 = Room.getNewChamber("Hidden Chamber");
        cr1.setMessage("You find a small hole in the wall, and walk in, to find yourself facing a rotted chest, years old.\nYou can only go north from here.\n");


        Room rh = Room.getNewCorridor("Spiked Hallway");
        rh.setMessage("You walk into a stone hallway, the floor is old and bloodstained, while the walls are covered in long sharp spikes.\nYou can go east or west.\n");


        Room d1 = Room.getNewLockedDoor("Riddled Door");
        d1.setMessage("A door stops your path. There is a carved face in the stonework.\n");
        d1.setLockTypeRiddle();
        d1.setRiddleQuestion("The stone face carved into the wall says\n'In order to proceed, you must prove your worth.\nAnswer me this riddle, in order to leave safely.\nHowever if you displease me, you will meet your death.\n\nAnswer me this: I began eternity, and ended space,\nat the end of time, and in every place,\nlast in life, second to death,\nnever alone, found in your breath,\ncontained by earth, water or flame\nmy grandeur so awesome, wind dare not tame,\nnot in your mind, but in your dreams,\nvacant to kings, present to queens. What am I?");
        d1.setRiddleAnswer("e");


        Room cr2 = Room.getNewChamber("Cavern");
        cr2.setMessage("The Dwarven architecture rots away as you enter a large cavern.\nStalagmites and stalactites fill the top and bottom of the cave.\nYou feel a slight draft coming from the holes in the walls, however none of them are large enough for you to pass through.\nYou can go north or west.\n");


        Room hw2 = Room.getNewCorridor("Mining Hallway");
        hw2.setMessage("You enter an old mining hall. This is  where the dwarves mined all their goods.\nYou can go north or south.\n");


        Room mr1 = Room.getNewChamber("Ruins");
        mr1.setMessage("You enter a cave-like room.\nPillars of Dawrven make spike out of the groud, however ubruptly end near an endless cliff.\nThis is mostlikly where the dragon crawled from the speakless depths of the cave system, and entered the dwarven castle...\nYou can go south or west.\n");


        Room bb = Room.getNewCorridor("Broken Bridge");
        bb.setMessage("You enter an underground ravine, a fall from this height would kill you,\nhowever the fall is so great, there is no way of telling when you would hit the ground.\nLuckily, the ancient dwarves created a stone bridge to cross the ravine,\nbut the years of wear on it has caused it to break in half, you managed to jump over the gap.\nYou can go east or west.\n");


        Room mr2 = Room.getNewChamber("King's Court");
        mr2.setMessage("You enter a large courtroom, dining tables on both sides and a large throne in the centre,\nhowever the ground is covered in slime, and the foul stench makes your stomach turn.\nYou can go east, south, or west.\n");
        mr2.setBoss(new Boss(false,"Carnivorous Slime", 30, 15, 35, 20, "Large Iron Key", 40));


        Room lh = Room.getNewCorridor("Looping Hallway");
        lh.setMessage("You enter a long hallway, with magical runes covering the walls.\nA message on the roof reads 'ONLY THOSE WHO HAVE PROVED THEIR VALUE MAY CONTINUE'.\nYou can go north or south from here.");


        Room d2 = Room.getNewLockedDoor("Magic Portal");
        d2.setLockTypeKey();
        d2.setMessage("A blue glowing portal stops your path, a keyhole floats in the centre.\nA message at the top explains that going through it without the key,\nwill return you to the start of the tunnel.\n");
        d2.setKey("Glowing Key");


        Room hw3 = Room.getNewCorridor("Empty Hallway");
        hw3.setMessage("You enter a short hall, with a message written in gold on the walls, multiple times.\nThe ,message reads 'DEATH AWAITS'\nYou can go east or west from here.\n");


        Room rt = Room.getNewChamber("Council Room");
        rt.setMessage("You enter a council room.\nThis is where the king would seek council from his most trusted advisers.\nYou can go east or north.\n");


        Room cr3 = Room.getNewCorridor("Golden Hall");
        cr3.setMessage("The hallway before you if filled with golden coins, and artifacts from ancient worlds.\nThe vault that the dragon sleeps in is near.\nThe walls of the room have magic runes inscribed into them,\nand the only thing that is not of value, is a simple wooden chest.\nThe vast gold temps you... However you must slay the dragon before taking any.\nYou can go north or south.\n");


        Room d3 = Room.getNewLockedDoor("Sealed Door");
        d3.setLockTypeKey();
        d3.setMessage("A large seal stops your path.\nNo man can open this by hand, however there is a very large keyhole on the door.\n");
        d3.setKey("Large Iron Key");


        Room br = Room.getNewChamber("Vault of Ashardalon");
        br.setMessage("Finally, the vault.\nBones and gold flows out to your feet as you enter.\nThe room is the largest chamber in the entire castle.\nThe heat in the room makes you sweat.\nSmoke quickly fills the room, and as it fades, the great red dragon Ashardalon spreads it's wings in a show of power.\nMagma flows from his mouth and smoke from his nose.\nHe opens his mouth as a fire builds at the back of his throat.\nPrepare for combat!\n");
        br.setBoss(new Boss(true,"Red Terror Ashardalon", 50, 20, 20, 0, null, 1000));


        // Optional area




        Room ib = Room.getNewCorridor("Invisible Bridge");
        ib.setMessage("You step onto an invisible floor. It seems as if you walk on air.\nYou can go north or south from here.");


        Room totb = Room.getNewChamber("Tomb of the Beholder");
        totb.setMessage("You walk into a maze-like room. A foul stench makes your head feel light.\nIncinerated bodies and ash cover the ground.\nYou can go north, south, or west from here./n");
        totb.setBoss(new Boss(false, "Beholder", 20, 10, 40, 20, null, 30));


        Room wh = Room.getNewCorridor("Wet Hall");
        wh.setMessage("You walk into a wet hall. The roof drips green water, and it collects at your feet. The air smells of poison.\nYou can go north or south from here.\n");

        Room od1 = Room.getNewLockedDoor("Keeper's Gate");
        od1.setLockTypeKey();
        od1.setMessage("A large moss-covered door stops your path. Inscribed in it say one simple word: 'Keeper'.");
        od1.setKey("Skeleton Key");

        Room plague = Room.getNewChamber("Plague Laboratory");
        plague.setMessage("You walk into a rotted clinic. The walls crumble from old age and the air smells of corpses.\nYou can go north, east, south, or west from here.\n");


        Room aw = Room.getNewChamber("Apprentice Workshop");
        aw.setMessage("You enter a cramped room. It looks like the living quarters of a mage's assistant.\nIn the middle of the room, a small chest. Most likely the belongings of a long dead person.\nYou can only go west from here.\n");


        Room mw = Room.getNewChamber("Mage Workshop");
        mw.setMessage("You walk into a large room. There are all sorts of magical items on the tables around the room.\nClearly this room was owned by a mage, however the chest in the middle is now yours.\nYou can only go south from here.\n");


        Room potm = Room.getNewCorridor("Path of the Mage");
        potm.setMessage("You feel magic fill the air as you walk into this hall.\nPurple runes inscribe the walls.\nA stone face is carved into the wall.\nYou can go east or west from here.\n");


        Room rotm = Room.getNewLockedDoor("Mage's Riddle");
        rotm.setLockTypeRiddle();
        rotm.setRiddleQuestion("The stone face won't let you proceed unless you answer this riddle:\nI have many tongues but cannot taste\nBy me, most things are turned to waste\nI crack and snap, yet I stay whole\nI may take the largest toll\nI assisted all of the first men\nAnd I will pay them back again\nAround me, people snuggle and sleep\nYet run when I am released from my keep\nI jump around and leap and bound\nThe cold man wishes I he had found");
        rotm.setRiddleAnswer("fire");


        Room pott = Room.getNewCorridor("Path of the Thief");
        pott.setMessage("Shadows quickly take you, and the only thing that lights the room is a single blue flame.\nIt sits in front of a stone face.\nYou can go north or east from here.\n");


        Room rott = Room.getNewLockedDoor("Thief's Riddle");
        rott.setLockTypeRiddle();
        rott.setRiddleQuestion("The stone face asks you this riddle:\nI weaken all men for hours each day.\nI show you strange visions while you are away.\nI take you by night, by day take you back. None suffer to have me, but do from my lack. What am I?");
        rott.setRiddleAnswer("sleep");


        Room potw = Room.getNewCorridor("Path of the Warrior");
        potw.setMessage("As you walk into the corridor, you notice blood everywhere.\nFresh corpses cover the ground, as if they all fought to death.\nA blood-covered stone face is in the wall.\nYou can go north or south from here.\n");


        Room rotw = Room.getNewLockedDoor("Warrior's Riddle");
        rotw.setLockTypeRiddle();
        rotw.setRiddleQuestion("The face asks you this riddle:\nStronger than steel,\nAnd older than time;\nThey are more patient than death\nand shall stand even when the stars have ceased to shine.\nTheir strength is embedded\nin roots buried deep\nWhere the sands and frosts of ages\ncan never hope to touch or reach.\nWhat is it?");
        rotw.setRiddleAnswer("mountain");


        Room totw = Room.getNewChamber("Trial of the Wanderer");
        totw.setMessage("You enter a large, flaming colosseum.\nThe screams and cries of pained souls fill the arena.\nThe air smells of cinders and burnt flesh.\nYou can go east or south from here.\n");
        totw.setBoss(new Boss(false, "Demon Lord", 40, 20, 20, 50, "Moon Key", 100));


        Room fl = Room.getNewCorridor("Fading Light");
        fl.setMessage("The light around you is taken over and consumed by the dark.\nA slight breeze carrying the smell of the dead comes over you.\nThe deeper you walk into the hall, the more of your body turns to dust.\nYou can go east or west from here.\n");


        Room hotg = Room.getNewCorridor("Hall of the Gods");
        hotg.setMessage("You enter a hall. Detailed statues of dwarven gods come out of the walls.\nAt closer inspection, it seems almost as if they were people turned to stone.\nA stone face has been carved in the wall.\nYou can go east or west from here.\n");


        Room gr = Room.getNewLockedDoor("Kings Riddle");
        gr.setLockTypeRiddle();
        gr.setRiddleQuestion("The face asks you this riddle:\nWhat is it: A box without hinges, key, or lid,\nyet golden treasure inside is hid.");
        gr.setRiddleAnswer("egg");


        Room das = Room.getNewCorridor("Dark Stairway");
        das.setMessage("You look down a long, narrow stairway. The stairs are dark and hard to see down. Hopefully you don't trip.\nYou can go north, south, east, or west from here.");


        Room colp = Room.getNewCorridor("Cold Path");
        colp.setMessage("This path is cold. It chills you to the bone.\nAs you breathe, you can see your breath.\nFrost covers the walls.\nA frozen face is carved in the wall.\nYou can go north or south from here.");


        Room fd = Room.getNewLockedDoor("Frozen Door");
        fd.setLockTypeRiddle();
        fd.setRiddleQuestion("The face asks you this riddle:\nOf no use to one\nYet absolute bliss to two.\nThe small boy gets it for nothing.\nThe young man has to lie for it.\nThe old man has to buy it. What is it?");
        fd.setRiddleAnswer("a kiss");


        Room icr = Room.getNewChamber("Iced Room");
        icr.setMessage("You enter an ice filled room. You nearly slip on your way in. In the middle of the room there's a chest.\nYou can only go south from here.");


        Room potl = Room.getNewCorridor("Path of the Lost");
        potl.setMessage("As you walk into this hall, you see multiple souls. They all walk in unison towards some sort of temple.\nYou can go north or south from here.\n");


        Room totl = Room.getNewChamber("Temple of the Lost");
        totl.setMessage("As you enter the temple, you see multiple white souls praying towards a statue of a woman with a crown.\nMoonlight reaches the statue, however there isn't any way moonlight can get down here...\nThere is a chest at the back of the room.\nYou can only go north from here.\n");


        Room mau = Room.getNewChamber("Mausoleum");
        mau.setMessage("You enter a mausoleum. A horrid stench irritates your skin.\nCorpses hang from the walls. The floor is filled with fecal matter and bones.\nYou can go north, south, east, or west from here.\n");
        mau.setBoss(new Boss(false, "Keeper of Souls", 20, 10, 50, 20, "Skeleton Key", 50));


        Room de = Room.getNewCorridor("Dead End");
        de.setMessage("You look down this corridor to see a dead end. The roof has collapsed here, and there is nowhere to go.\nYou can only go east from here.\n");


        Room sj = Room.getNewCorridor("Short Jump");
        sj.setMessage("You come across a short fall, nothing too difficult to do.\nYou can go north or south from here.\n");


        Room gkl = Room.getNewLockedDoor("Large Gate");
        gkl.setLockTypeKey();
        gkl.setMessage("A large gate stops your path. The skeleton key should be able to open this.");
        gkl.setKey("Skeleton Key");


        Room kq = Room.getNewChamber("Keeper's Quaters");
        kq.setMessage("You enter the Keeper's Quarters. The Keeper of Souls seemed to tend to souls who have not yet passed.\nHe also stoped any intruders from accessing the queen.\nHis belongings are kept inside the chest before you.\nYou can only go north from here.\n");


        Room pathdark = Room.getNewCorridor("Path of the Darkmoon");
        pathdark.setMessage("Fog and moonlight fill this path.\nIn front of you there is a door with a lock shaped like the waxing moon.\nYou can go north or south from here.\n");


        Room mood = Room.getNewLockedDoor("Moon Door");
        mood.setLockTypeKey();
        mood.setMessage("A large blue seal lies before you. You will require a moon-shaped key to open the door.\nThere is a hint on the door:\n'Past the one that oversees the maze, enter the moss.\nThe clinic awaits, and paths must be taken, only then will the trial begin,\nand the Darkmoon rise again.'\n");
        mood.setKey("Moon Key");


        Room burn = Room.getNewCorridor("Burning Hall");
        burn.setMessage("The hall you enter is burning, however does not produce heat or smoke.\nNor does it wither or lessen, a strange magic holds the flame here...\nYou can go north, east, or south from here.\n");


        Room kingh = Room.getNewCorridor("King's Hall");
        kingh.setMessage("The hall before you is lined with gold and velvet, the king used to reside near here.\nYou can go east or west from here.\n");


        Room kingdoor = Room.getNewLockedDoor("King's Door");
        kingdoor.setLockTypeKey();
        kingdoor.setMessage("A red door stops your access. Only the king's key will be able to open it.");
        kingdoor.setKey("King's Key");

        Room kingkeep = Room.getNewChamber("King's Keep");
        kingkeep.setMessage("You enter a room containing the valuables of the king within a chest.");


        Room kingroom = Room.getNewChamber("King's Living Space");
        kingroom.setMessage("This is where the king lived. In the middle of the room you see a corpse.\nIt's the king...or at least a husk of him...\nYou can go north or south from here.\n");
        kingroom.setBoss(new Boss(false, "King of Husks", 1, 5, 50, 50, "King's Key", 500));


        Room hidd = Room.getNewChamber("Hidden Room");
        hidd.setMessage("You find a hidden chamber. The ground is covered by dried blood...\nYou can go north or south from here.\n");


        Room tort = Room.getNewChamber("Torture Room");
        tort.setMessage("You enter a large torture room. Bodies hang from the roof and blood covers the floor.\nYou can go south or west from here.\n");


        Room moon = Room.getNewChamber("Infinite Moonlight");
        moon.setMessage("You enter a large room filled with fog and moonlight that seemingly comes from nowhere.\nYou smell fresh air and feel cold, however you quickly realize it's an illusion.\nYou can go east or west from here.\n");


        Room goddoor = Room.getNewLockedDoor("Darkmoon Shield");
        goddoor.setLockTypeKey();
        goddoor.setMessage("You see a large moonlight door.\nA curse has come over this castle, and it's due to moonlight magic.\nHowever who could do such a thing?");
        goddoor.setKey("Moon Key");


        Room darkmoon = Room.getNewChamber("Tomb of the Darkmoon");
        darkmoon.setMessage("You walk into an abyssmal room. Blackness fills the room, except for streaks of moonlight across the air.\nYou can only go north from here...\n");
        darkmoon.setBoss(new Boss(false, "Moonkissed Queen", 50, 10, 60, 100, null, 1000));


        Room fad = Room.getNewCorridor("Fading Dark");
        fad.setMessage("As you walk through the hall, you begin to glow.\nThe queen was cursed by Ashardalon.\nShe caused the downfall of the kingdom.\nYou can go north or south from here.\n");





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



        // optional area




        mr1.setNorth(ib);
        ib.setSouth(mr1);

        ib.setNorth(totb);
        totb.setSouth(ib);

        totb.setNorth(wh);
        wh.setSouth(totb);
        totb.setWest(hotg);
        hotg.setEast(totb);

        wh.setNorth(od1);
        od1.setSouth(wh);

        od1.setNorth(plague);
        plague.setSouth(od1);

        plague.setEast(aw);
        aw.setWest(plague);
        plague.setNorth(mw);
        mw.setSouth(plague);
        plague.setWest(potm);
        potm.setEast(plague);

        potm.setWest(rotm);
        rotm.setEast(potm);

        rotm.setWest(pott);
        pott.setEast(rotm);

        pott.setNorth(rott);
        rott.setSouth(pott);

        rott.setNorth(potw);
        potw.setSouth(rott);

        potw.setNorth(rotw);
        rotw.setSouth(potw);

        rotw.setNorth(totw);
        totw.setSouth(rotw);

        totw.setEast(fl);
        fl.setWest(totw);

        fl.setEast(de);

        hotg.setWest(gr);
        gr.setEast(hotg);

        gr.setWest(das);
        das.setEast(gr);

        das.setNorth(colp);
        colp.setSouth(das);
        das.setSouth(potl);
        potl.setNorth(das);
        das.setWest(mau);
        mau.setEast(das);

        colp.setNorth(fd);
        fd.setSouth(colp);

        fd.setNorth(icr);
        icr.setSouth(fd);

        potl.setSouth(totl);
        totl.setNorth(potl);

        mau.setWest(de);
        de.setEast(mau);
        mau.setSouth(sj);
        sj.setNorth(mau);
        mau.setNorth(pathdark);
        pathdark.setSouth(mau);

        sj.setSouth(gkl);
        gkl.setNorth(sj);

        gkl.setSouth(kq);
        kq.setNorth(gkl);

        pathdark.setNorth(mood);
        mood.setSouth(pathdark);

        moon.setNorth(burn);
        burn.setSouth(mood);

        burn.setEast(kingh);
        kingh.setWest(burn);

        kingh.setEast(kingdoor);
        kingdoor.setWest(kingh);

        kingdoor.setEast(kingkeep);
        kingkeep.setWest(kingdoor);

        burn.setNorth(kingroom);
        kingroom.setSouth(burn);

        kingroom.setNorth(hidd);
        hidd.setSouth(kingroom);

        hidd.setNorth(tort);
        tort.setSouth(hidd);

        tort.setWest(moon);
        moon.setEast(tort);

        moon.setWest(goddoor);
        goddoor.setEast(moon);

        goddoor.setWest(darkmoon);

        darkmoon.setNorth(fad);
        fad.setSouth(darkmoon);

        fad.setNorth(entrance);





        return entrance;
        
    }
    
    // now adding the enemies to the map

    static ArrayList<Enemy> createEnemies() {
        Enemy e;
        ArrayList<Enemy> enemies = new ArrayList<>();

        e = new Enemy("Troll", 10, 15, 20, 15, null, 20);
        enemies.add(e);

        e = new Enemy("Skeleton", 3, 5, 50, 2, null, 3);
        enemies.add(e);
        
        e = new Enemy("Skeleton Mage", 3, 8, 20, 5, null, 5);
        enemies.add(e);

        e = new Enemy("Orc", 5, 5, 50, 5, null, 5);
        enemies.add(e);
        
        e = new Enemy("Orc Archer", 5, 7, 25, 5, null, 5);
        enemies.add(e);
        
        e = new Enemy("Orc Captain", 7, 8, 40, 10, null, 7);
        enemies.add(e);

        e = new Enemy("Cursed Armour", 10, 5, 65, 5, null, 7);
        enemies.add(e);

        e = new Enemy("Giant Spider", 8, 8, 65, 10, null, 8);
        enemies.add(e);
        
        e = new Enemy("Sentinel", 10, 5, 50, 5, null, 5);
        enemies.add(e);

        e = new Enemy("Lich", 6, 6, 20, 15, null, 20);
        e.addSpawn(new Enemy("Undead Dragon", 10, 6, 35, 0, null, 10));
        enemies.add(e);

        e = new Enemy("Werewolf", 7, 6, 50, 5, null, 8);
        enemies.add(e);

        e = new Enemy("Vampire", 6, 7, 40, 10, null, 8);
        e.addSpawn(new Enemy("Ghoul", 5, 4, 75, 0, null, 0));
        enemies.add(e);

        e = new Enemy("Ghoul", 5, 4, 75, 2, null, 2);
        enemies.add(e);

        e = new Enemy("Phantom", 1, 3, 100, 5, null, 1);
        e.addSpawn(new Enemy("Phantom", 1, 3, 100, 0, null, 1));
        e.addSpawn(new Enemy("Phantom", 1, 3, 100, 0, null, 1));
        enemies.add(e);

        e = new Enemy("Brittle Statue", 5, 8, 40, 10, null, 5);
        enemies.add(e);

        e = new Enemy("Cave Bear", 6, 4, 50, 2, null, 4);
        enemies.add(e);

        e = new Enemy("Parasitic Worm", 2, 2, 40, 2, null, 1);
        e.addSpawn(new Enemy("Parasitic Worm", 2, 2, 40, 2, null, 1));
        enemies.add(e);

        e = new Enemy("Basilisk", 12, 10, 35, 15, null, 10);
        enemies.add(e);

        e = new Enemy("Wyvern", 8, 7, 40, 10, null, 10);
        enemies.add(e);
        
        e = new Enemy("Necromancer", 5, 5, 30, 10, null, 8);
        e.addSpawn(new Enemy("Skeleton", 3, 3, 50, 0, null, 2));
        e.addSpawn(new Enemy("Skeleton", 3, 3, 50, 0, null, 1));
        enemies.add(e);
        
        e = new Enemy("Demon Legionaire", 7, 6, 50, 5, null, 8);
        e.addSpawn(new Enemy("Tortured Soul", 3, 2, 30, 0, null, 2));
        e.addSpawn(new Enemy("Tortured Soul", 3, 2, 30, 0, null, 1));
        enemies.add(e);
        
        e = new Enemy("Balrog", 15, 8, 20, 15, null, 20);
        enemies.add(e);
        
        e = new Enemy("Goblin", 4, 5, 70, 2, null, 2);
        enemies.add(e);
        
        e = new Enemy("Goblin Theif", 2, 7, 30, 10, null, 10);
        enemies.add(e);
        
        e = new Enemy("Goblin Clanmaster", 6, 6, 60, 10, null, 15);
        enemies.add(e);
        
        e = new Enemy("Goblin Scout", 4, 1, 10, 2, null, 2);
        e.addSpawn(new Enemy("Goblin", 4, 3, 70, 2, null, 2));
        e.addSpawn(new Enemy("Goblin", 4, 3, 70, 2, null, 2));
        e = new Enemy("Goblin Clanmaster", 6, 6, 50, 10, null, 15);
        enemies.add(e);
        
        e = new Enemy("Insect Swarm", 1, 3, 90, 2, null, 1);
        enemies.add(e);
        
        e = new Enemy("Hag", 4, 3, 25, 5, null, 10);
        e.addSpawn(new Enemy("Insect Swarm", 1, 3, 90, 0, null, 1));
        e.addSpawn(new Enemy("Rotted Giant", 10, 8, 40, 0, null, 20));
        enemies.add(e);
        
        
        
        return enemies;
    }


    static Player createPlayer() {
        Player p = new Player();
        p.setRoom(createMap());
        return p;
    }
}
