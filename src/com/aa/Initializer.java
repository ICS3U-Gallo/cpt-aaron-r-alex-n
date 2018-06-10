package com.aa;

public class Initializer {
    public static Room createMap() {

        /* Here, we're making each room (a total of 15), using the variables from Room.java.
        We do this by giving values to the variables. */

        Room entrance = new Room();
        entrance.setName("Entrance");
        entrance.setType("chamber");
        entrance.setMessage("You enter a poorly lit room with a large hole in the roof, a warm breeze comes over you.\n");


        Room hw1 = new Room();
        hw1.setName("Dark Hallway");
        hw1.setType("corridor");
        hw1.setMessage("The light around you quickly fades and the pitch darkness fills the hall.\nA bitter cold comes over you, chilling you to the bone.\nA magical message on the wall says in the Dwarven language 'BEWARE OF SENTINEL'.\n");


        Room kr = new Room();
        kr.setName("Knight Room");
        kr.setType("chamber");
        kr.setMessage("You enter a well lit chamber, statues of past Dwarven kings look down upon you from the walls.\nA horrid stench fills your nose as you realize that the ground is covered in the bones of those who have passed.\n");


        Room cr1 = new Room();
        cr1.setName("Hidden Chamber");
        cr1.setType("chamber");
        cr1.setMessage("You find a small hole in the wall, and walk in, to find yourself facing a rotted chest, years old.\n");


        Room rh = new Room();
        rh.setName("Spiked Hallway");
        rh.setType("corridor");
        rh.setMessage("You walk into a stone hallway, the floor is old and bloodstained, while the walls are covered in long sharp spikes.\n");


        Room d1 = new Room();
        d1.setName("Riddled Door");
        d1.setType("door");
        d1.setMessage("A door stops your path. There is a carvev face on the stonework.\n");


        Room cr2 = new Room();
        cr2.setName("Cavern");
        cr2.setType("chamber");
        cr2.setMessage("The Dwarven architexture rots away as you enter a large carven.\nStalgmites and stalgtites fill the top and bottom of the cave.\nYou feel a slight draft coming from the holes in the walls, however none of them are large enough for you to pass through.\n");


        Room hw2 = new Room();
        hw2.setName("Trap-filled Hallway");
        hw2.setType("corridor");
        hw2.setMessage("As you look down the corridor before you,\nyou notice that there are multiple holes, spikes, and balistas on the walls, roof, and floor.\nThe hallway is booby-trapped and will require careful footing to pass.\n");


        Room mr1 = new Room();
        mr1.setName("Ruins");
        mr1.setType("chamber");
        mr1.setMessage("You enter a cave-like room.\nPillars of Dawrven make spike out of the groud, however ubruptly end near an endless cliff.\nThis is mostlikly where the dragon crawled from the speakless depths of the cave system, and entered the dwarven castle...\n");


        Room bb = new Room();
        bb.setName("Broken Bridge");
        bb.setType("corridor");
        bb.setMessage("You enter an underground ravine, a fall from this hight would kill you,\nhowever the fall is so great, there is no way of telling when you would hit the ground.\nLuckily, the ancient dwarves created a stone bridge to cross the ravine,\nbut the years of wear on it has caused it to break in half, the only way to cross would be to jump across.\n");


        Room mr2 = new Room();
        mr2.setName("King's Court");
        mr2.setType("chamber");
        mr2.setMessage("You enter a large courtroom, dining tables on both sides and a large throne in the centre,\nhowever the ground is covered in slime, and the foul stench makes your stomach turn.\n");


        Room lh = new Room();
        lh.setName("Looping Hallway");
        lh.setType("corridor");
        lh.setMessage("You enter a long hallway, with magical runes covering the walls.\nA message on the roof reads 'ONLY THOSE WHO HAVE PROVED THEIR VALUE MAY CONTINUE'.\n");


        Room d2 = new Room();
        d2.setName("Magic Portal");
        d2.setType("door");
        d2.setMessage("A blue glowing portal stops your path, a keyhole floats in the centre.\nA message at the top explains that going through it without the key,\nwill return you to the start of the tunnel.\n");


        Room hw3 = new Room();
        hw3.setName("Empty Hallway");
        hw3.setType("corridor");
        hw3.setMessage("You enter a short hall, with a message written in gold on the walls, multiple times.\nThe ,message reads 'THOSE WHO SHOW AVARICE, WILL DIE THE WAY THEY LIVE.'\n");


        Room rt = new Room();
        rt.setName("Riddled Room");
        rt.setType("chamber");
        rt.setMessage("You enter a room filled with traps.\nYou see a stone face carved in the wall.\nWalking through blindly will be your demise, but with careful footing, you may proceed freely.\n");


        Room cr3 = new Room();
        cr3.setName("Golden Hall");
        cr3.setType("corridor");
        cr3.setMessage("The hallway before you if filled with golden coins, and artifacts from ancient worlds.\nThe vault that the dragon sleeps in is near.\nThe walls of the room have magic runes inscribed into them,\nand the only thing that is not of value, is a simple wooden chest.\nThe vast gold temps you...\n");


        Room d3 = new Room();
        d3.setName("Sealed Door");
        d3.setType("door");
        d3.setMessage("A large seal stops your path.\nNo man can open this by hand, however there is a very large keyhole on the door.\n");


        Room br = new Room();
        br.setName("Vault of Ashardalon");
        br.setType("chamber");
        br.setMessage("Finally, the vault.\nBones and gold flows out to your feet as you enter.\nThe room is the largest chamber in the entire castle.\nThe heat in the room makes you sweat.\nSmoke quickly fills the room, and as it fades, the great red dragon Ashardalon spreads it's wings in a show of power.\nMagma flows from his mouth and smoke from his nose.\nHe opens his mouth as a fire builds at the back of his throat.\nPrepare for combat!\n");


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

        hw3.setEast(rt);
        rt.setWest(hw3);

        rt.setNorth(cr3);
        cr3.setSouth(rt);

        cr3.setNorth(d3);
        d3.setSouth(cr3);

        d3.setNorth(br);

        return entrance;
    }

    public static Player initializePlayer() {
        Player p = new Player();
        p.setHealth(12);
        p.setDamage(3);
        p.setAttack(5);
        p.setArmour(16);
        p.setLevel(1);
        p.setCurrentExperience(0);
        p.setExperienceNeeded(20);
        return p;
    }

    public static Player createPlayer() {
        Player p = initializePlayer();
        p.setRoom(createMap());
        return p;
    }
}