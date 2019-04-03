--------------------------------------------------------
--  DDL for Table FORUM
--------------------------------------------------------

  CREATE TABLE "SYSTEM"."FORUM" 
   (	"FORUMID" NUMBER(10,0), 
	"CREATEDDATE" TIMESTAMP (6), 
	"FORUMCONTENT" VARCHAR2(4000 CHAR), 
	"FORUMTITLE" VARCHAR2(255 CHAR), 
	"STATUS" VARCHAR2(255 CHAR), 
	"USERNAME" VARCHAR2(255 CHAR)
   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;