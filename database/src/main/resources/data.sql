Insert into Part (part) values (1),(2),(3);


INSERT INTO COURSES (code, instrument, clef, octave_shift, low_note,high_note,part_part) values
("SRTrHI",2,0,1,28,36,1),
("SRTrHII",2,0,1,35,41,2),
("SRTrHIII",2,0,1,28,41,3),
("SRAHI",2,2,2,28,36,1),
("SRAHII",2,2,2,35,41,2),
("SRAHIII",2,2,2,28,41,3),
("SRTeHI",2,3,2,28,36,1),
("SRTeHII",2,3,2,35,41,2),
("SRTeHIII",2,3,2,28,41,3),
("SRBHI",2,1,3,28,36,1),
("SRBHII",2,1,3,35,41,2),
("SRBHIII",2,1,3,28,41,3),
("ARTrHI",1,0,0,24,32,1),
("ARTrHII",1,0,0,31,37,2),
("ARTrHIII",1,0,0,24,37,3),
("ARAHI",1,2,1,24,32,1),
("ARAHII",1,2,1,31,37,2),
("ARAHIII",1,2,1,24,37,3),
("ARTeHI",1,3,1,24,32,1),
("ARTeHII",1,3,1,31,37,2),
("ARTeHIII",1,3,1,24,37,3),
("ARBHI",1,1,2,24,32,1),
("ARBHII",1,1,2,31,37,2),
("ARBHIII",1,1,2,24,37,3),
("SRTrLI",2,0,2,28,36,1),
("SRTrLII",2,0,2,35,41,2),
("SRTrLIII",2,0,2,28,41,3),
("SRALI",2,2,3,28,36,1),
("SRALII",2,2,3,35,41,2),
("SRALIII",2,2,3,28,41,3),
("SRTeLI",2,3,3,28,36,1),
("SRTeLII",2,3,3,35,41,2),
("SRTeLIII",2,3,3,28,41,3),
("SRBLI",2,1,4,28,36,1),
("SRBLII",2,1,4,35,41,2),
("SRBLIII",2,1,4,28,41,3),
("ARTrLI",1,0,1,24,32,1),
("ARTrLII",1,0,2,31,37,2),
("ARTrLIII",1,0,2,24,37,3),
("ARALI",1,2,2,24,32,1),
("ARALII",1,2,2,31,37,2),
("ARALIII",1,2,2,24,37,3),
("ARTeLI",1,3,2,24,32,1),
("ARTeLII",1,3,2,31,37,2),
("ARTeLIII",1,3,2,24,37,3),
("ARBLI",1,1,3,24,32,1),
("ARBLII",1,1,3,31,37,2),
("ARBLIII",1,1,3,24,37,3);


Insert into challenges(id,keym,accidentals,accid_Slider,number_Of_Notes,challenge_Number) values
("01",0,'OFF',0,30,1),
("02",0,'ON',30,30,2),
("03",0,'ON',70,30,3),
("11",1,'OFF',0,30,1),
("12",1,'ON',30,30,2),
("13",1,'ON',100,30,3),
("21",2,'OFF',0,30,1),
("22",2,'ON',30,30,2),
("23",2,'ON',100,30,3),
("31",3,'OFF',0,30,1),
("32",3,'ON',30,30,2),
("33",3,'ON',100,30,3),
("41",4,'OFF',0,30,1),
("42",4,'ON',30,30,2),
("43",4,'ON',100,30,3),
("81",8,'OFF',0,30,1),
("82",8,'ON',30,30,2),
("83",8,'ON',100,30,3),
("91",9,'OFF',0,30,1),
("92",9,'ON',30,30,2),
("93",9,'ON',100,30,3),
("101",10,'OFF',0,30,1),
("102",10,'ON',30,30,2),
("103",10,'ON',100,30,3),
("111",11,'OFF',0,30,1),
("112",11,'ON',30,30,2),
("113",11,'ON',100,30,3);


insert into level (part_part,level, low_Note, high_Note, keym, accidentals, accidental_slider, points, challenge_id, max_star) values
(1,1,4,6,1,"OFF",0,NULL,NULL,0),
(1,2,4,7,1,"OFF",0,NULL,NULL,0),
(1,3,3,7,1,"OFF",0,NULL,NULL,0),
(1,4,2,7,1,"OFF",0,NULL,NULL,0),
(1,5,1,7,1,"OFF",0,NULL,NULL,0),
(1,6,1,8,1,"OFF",0,NULL,NULL,0),
(1,7,0,8,1,"OFF",0,20000,"11",1),
(1,8,0,8,2,"OFF",0,20000,"21",1),
(1,9,0,8,3,"OFF",0,20000,"31",1),
(1,10,0,8,4,"OFF",0,20000,"41",1),
(1,11,0,8,0,"OFF",0,20000,"01",1),
(1,12,0,8,8,"OFF",0,20000,"81",1),
(1,13,0,8,9,"OFF",0,20000,"91",1),
(1,14,0,8,10,"OFF",0,20000,"101",1),
(1,15,0,8,11,"OFF",0,20000,"111",1),
(1,16,0,8,1,"OFF",0,60000,"11",2),
(1,17,0,8,2,"OFF",0,60000,"21",2),
(1,18,0,8,3,"OFF",0,60000,"31",2),
(1,19,0,8,4,"OFF",0,60000,"41",2),
(1,20,0,8,0,"OFF",0,60000,"01",2),
(1,21,0,8,8,"OFF",0,60000,"81",2),
(1,22,0,8,9,"OFF",0,60000,"91",2),
(1,23,0,8,10,"OFF",0,60000,"101",2),
(1,24,0,8,11,"OFF",0,60000,"111",2),
(1,25,0,8,1,"OFF",0,100000,"11",3),
(1,26,0,8,2,"OFF",0,100000,"21",3),
(1,27,0,8,3,"OFF",0,100000,"31",3),
(1,28,0,8,4,"OFF",0,100000,"41",3),
(1,29,0,8,0,"OFF",0,100000,"01",3),
(1,30,0,8,8,"OFF",0,100000,"81",3),
(1,31,0,8,9,"OFF",0,100000,"91",3),
(1,32,0,8,10,"OFF",0,100000,"101",3),
(1,33,0,8,11,"OFF",0,100000,"111",3),
(1,34,0,8,1,"ON",30,20000,"12",1),
(1,35,0,8,2,"ON",30,20000,"22",1),
(1,36,0,8,3,"ON",30,20000,"32",1),
(1,37,0,8,4,"ON",30,20000,"42",1),
(1,38,0,8,0,"ON",30,20000,"02",1),
(1,39,0,8,8,"ON",30,20000,"82",1),
(1,40,0,8,9,"ON",30,20000,"92",1),
(1,41,0,8,10,"ON",30,20000,"102",1),
(1,42,0,8,11,"ON",30,20000,"112",1),
(1,43,0,8,1,"ON",30,60000,"12",2),
(1,44,0,8,2,"ON",30,60000,"22",2),
(1,45,0,8,3,"ON",30,60000,"32",2),
(1,46,0,8,4,"ON",30,60000,"42",2),
(1,47,0,8,0,"ON",30,60000,"02",2),
(1,48,0,8,8,"ON",30,60000,"82",2),
(1,49,0,8,9,"ON",30,60000,"92",2),
(1,50,0,8,10,"ON",30,60000,"102",2),
(1,51,0,8,11,"ON",30,60000,"112",2),
(1,52,0,8,1,"ON",30,100000,"12",3),
(1,53,0,8,2,"ON",30,100000,"22",3),
(1,54,0,8,3,"ON",30,100000,"32",3),
(1,55,0,8,4,"ON",30,100000,"42",3),
(1,56,0,8,0,"ON",30,100000,"02",3),
(1,57,0,8,8,"ON",30,100000,"82",3),
(1,58,0,8,9,"ON",30,100000,"92",3),
(1,59,0,8,10,"ON",30,100000,"102",3),
(1,60,0,8,11,"ON",30,100000,"112",3),
(2,1,0,2,1,"OFF",0,NULL,NULL,0),
(2,2,0,3,1,"OFF",0,NULL,NULL,0),
(2,3,0,4,1,"OFF",0,NULL,NULL,0),
(2,4,0,5,1,"OFF",0,NULL,NULL,0),
(2,5,0,6,1,"OFF",0,20000,"11",1),
(2,6,0,6,2,"OFF",0,20000,"21",1),
(2,7,0,6,3,"OFF",0,20000,"31",1),
(2,8,0,6,4,"OFF",0,20000,"41",1),
(2,9,0,6,0,"OFF",0,20000,"01",1),
(2,10,0,6,8,"OFF",0,20000,"81",1),
(2,11,0,6,9,"OFF",0,20000,"91",1),
(2,12,0,6,10,"OFF",0,20000,"101",1),
(2,13,0,6,11,"OFF",0,20000,"111",1),
(2,14,0,6,1,"OFF",0,60000,"11",2),
(2,15,0,6,2,"OFF",0,60000,"21",2),
(2,16,0,6,3,"OFF",0,60000,"31",2),
(2,17,0,6,4,"OFF",0,60000,"41",2),
(2,18,0,6,0,"OFF",0,60000,"01",2),
(2,19,0,6,8,"OFF",0,60000,"81",2),
(2,20,0,6,9,"OFF",0,60000,"91",2),
(2,21,0,6,10,"OFF",0,60000,"101",2),
(2,22,0,6,11,"OFF",0,60000,"111",2),
(2,23,0,6,1,"OFF",0,100000,"11",3),
(2,24,0,6,2,"OFF",0,100000,"21",3),
(2,25,0,6,3,"OFF",0,100000,"31",3),
(2,26,0,6,4,"OFF",0,100000,"41",3),
(2,27,0,6,0,"OFF",0,100000,"01",3),
(2,28,0,6,8,"OFF",0,100000,"81",3),
(2,29,0,6,9,"OFF",0,100000,"91",3),
(2,30,0,6,10,"OFF",0,100000,"101",3),
(2,31,0,6,11,"OFF",0,100000,"111",3),
(2,32,0,6,1,"ON",30,20000,"12",1),
(2,33,0,6,2,"ON",30,20000,"22",1),
(2,34,0,6,3,"ON",30,20000,"32",1),
(2,35,0,6,4,"ON",30,20000,"42",1),
(2,36,0,6,0,"ON",30,20000,"02",1),
(2,37,0,6,8,"ON",30,20000,"82",1),
(2,38,0,6,9,"ON",30,20000,"92",1),
(2,39,0,6,10,"ON",30,20000,"102",1),
(2,40,0,6,11,"ON",30,20000,"112",1),
(2,41,0,6,1,"ON",30,60000,"12",2),
(2,42,0,6,2,"ON",30,60000,"22",2),
(2,43,0,6,3,"ON",30,60000,"32",2),
(2,44,0,6,4,"ON",30,60000,"42",2),
(2,45,0,6,0,"ON",30,60000,"02",2),
(2,46,0,6,8,"ON",30,60000,"82",2),
(2,47,0,6,9,"ON",30,60000,"92",2),
(2,48,0,6,10,"ON",30,60000,"102",2),
(2,49,0,6,11,"ON",30,60000,"112",2),
(2,50,0,6,1,"ON",30,100000,"12",3),
(2,51,0,6,2,"ON",30,100000,"22",3),
(2,52,0,6,3,"ON",30,100000,"32",3),
(2,53,0,6,4,"ON",30,100000,"42",3),
(2,54,0,6,0,"ON",30,100000,"02",3),
(2,55,0,6,8,"ON",30,100000,"82",3),
(2,56,0,6,9,"ON",30,100000,"92",3),
(2,57,0,6,10,"ON",30,100000,"102",3),
(2,58,0,6,11,"ON",30,100000,"112",3),
(3,1,0,13,1,"OFF",0,60000,"11",4),
(3,2,0,13,2,"OFF",0,60000,"21",4),
(3,3,0,13,3,"OFF",0,60000,"31",4),
(3,4,0,13,4,"OFF",0,60000,"41",4),
(3,5,0,13,0,"OFF",0,60000,"01",4),
(3,6,0,13,8,"OFF",0,60000,"81",4),
(3,7,0,13,9,"OFF",0,60000,"91",4),
(3,8,0,13,10,"OFF",0,60000,"101",4),
(3,9,0,13,11,"OFF",0,60000,"111",4),
(3,10,0,13,1,"OFF",0,100000,"11",5),
(3,11,0,13,2,"OFF",0,100000,"21",5),
(3,12,0,13,3,"OFF",0,100000,"31",5),
(3,13,0,13,4,"OFF",0,100000,"41",5),
(3,14,0,13,0,"OFF",0,100000,"01",5),
(3,15,0,13,8,"OFF",0,100000,"81",5),
(3,16,0,13,9,"OFF",0,100000,"91",5),
(3,17,0,13,10,"OFF",0,100000,"101",5),
(3,18,0,13,11,"OFF",0,100000,"111",5),
(3,19,0,13,1,"ON",70,60000,"13",4),
(3,20,0,13,2,"ON",70,60000,"23",4),
(3,21,0,13,3,"ON",70,60000,"33",4),
(3,22,0,13,4,"ON",70,60000,"43",4),
(3,23,0,13,0,"ON",70,60000,"03",4),
(3,24,0,13,8,"ON",70,60000,"83",4),
(3,25,0,13,9,"ON",70,60000,"93",4),
(3,26,0,13,10,"ON",70,60000,"103",4),
(3,27,0,13,11,"ON",70,60000,"113",4),
(3,28,0,13,1,"ON",70,100000,"13",5),
(3,29,0,13,2,"ON",70,100000,"23",5),
(3,30,0,13,3,"ON",70,100000,"33",5),
(3,31,0,13,4,"ON",70,100000,"43",5),
(3,32,0,13,0,"ON",70,100000,"03",5),
(3,33,0,13,8,"ON",70,100000,"83",5),
(3,34,0,13,9,"ON",70,100000,"93",5),
(3,35,0,13,10,"ON",70,100000,"103",5),
(3,36,0,13,11,"ON",70,100000,"113",5);

