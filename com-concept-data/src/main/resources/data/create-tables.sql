    create table ROLE (
        CVE_ROLE char(3) not null,
        DESC_ROLE varchar(30),
        primary key (CVE_ROLE)
    );
    
    create table USER 
    (
        ID char(8) not null,
        FIRST_NAME varchar(25),
        LAST_NAME varchar(40) not null,
        BIRTH_DATE DATE not null,
        CVE_ROLE char(3) not null,
        SUPERIOR_ID char(8),
        primary key (id)
    ); 
    
    alter table USER 
        add constraint FK_ROLE 
        foreign key (CVE_ROLE) 
        references ROLE(CVE_ROLE);

INSERT INTO ROLE(CVE_ROLE,DESC_ROLE) VALUES ('MAN','Manager');
INSERT INTO ROLE(CVE_ROLE,DESC_ROLE) VALUES ('EMP','Employee');
INSERT INTO ROLE(CVE_ROLE,DESC_ROLE) VALUES ('DEV','Developer');

CREATE UNIQUE INDEX UNIQUEINDEX_USER_ID 
ON USER (ID);


ALTER TABLE user ADD COLUMN EMAIL VARCHAR(40) NOT NULL;
ALTER TABLE user ADD COLUMN CREATED_BY VARCHAR(10) NOT NULL;
ALTER TABLE user ADD COLUMN CREATED_DATETIME timestamp NOT NULL;
ALTER TABLE user ADD COLUMN UPDATED_BY VARCHAR(10);
ALTER TABLE user ADD COLUMN UPDATED_DATETIME timestamp;
