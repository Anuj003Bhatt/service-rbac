INSERT INTO
PLATFORM_USERS(user_id, username, password) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'user1', 'password1'),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'user2', 'password2'),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID, 'user3', 'password3'),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID, 'user4', 'password4'),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID, 'user5', 'password5'),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'user6', 'password6'),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID, 'user7', 'password7'),
('efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID, 'user8', 'password8');

INSERT INTO
ROLES(role_id, role_name, description) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'ROLE 1', 'ROLE 1 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'ROLE 2', 'ROLE 2 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID, 'ROLE 3', 'ROLE 3 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID, 'ROLE 4', 'ROLE 4 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID, 'ROLE 5', 'ROLE 5 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'ROLE 6', 'ROLE 6 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID, 'ROLE 7', 'ROLE 7 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID, 'ROLE 8', 'ROLE 8 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b38'::UUID, 'ROLE 9', 'ROLE 9 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b39'::UUID, 'ROLE 10', 'ROLE 10 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b3a'::UUID, 'ROLE 11', 'ROLE 11 DESCRIPTION'),
('efb15e8f-0caa-48fe-a058-f4c116836b3b'::UUID, 'ROLE 12', 'ROLE 12 DESCRIPTION');

INSERT INTO
PERMISSIONS(permission_id, permission_name, access_type, description) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'PERMISSION 1', 'C', 'PERMISSION 1 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'PERMISSION 2', 'R', 'PERMISSION 2 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID, 'PERMISSION 3', 'U', 'PERMISSION 3 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID, 'PERMISSION 4', 'D', 'PERMISSION 4 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID, 'PERMISSION 5', 'C', 'PERMISSION 5 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'PERMISSION 6', 'R', 'PERMISSION 6 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID, 'PERMISSION 7', 'U', 'PERMISSION 7 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID, 'PERMISSION 8', 'D', 'PERMISSION 8 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b38'::UUID, 'PERMISSION 9', 'C', 'PERMISSION 9 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b39'::UUID, 'PERMISSION 10', 'R', 'PERMISSION 10 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b3a'::UUID, 'PERMISSION 11', 'U', 'PERMISSION 11 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b3b'::UUID, 'PERMISSION 12', 'D', 'PERMISSION 12 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b3c'::UUID, 'PERMISSION 13', 'C', 'PERMISSION 13 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b3d'::UUID, 'PERMISSION 14', 'R', 'PERMISSION 14 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b40'::UUID, 'PERMISSION 15', 'U', 'PERMISSION 15 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b41'::UUID, 'PERMISSION 16', 'D', 'PERMISSION 16 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b42'::UUID, 'PERMISSION 17', 'C', 'PERMISSION 17 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b43'::UUID, 'PERMISSION 18', 'R', 'PERMISSION 18 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b44'::UUID, 'PERMISSION 19', 'U', 'PERMISSION 19 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b45'::UUID, 'PERMISSION 20', 'D', 'PERMISSION 20 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b46'::UUID, 'PERMISSION 21', 'C', 'PERMISSION 21 Description Text');

INSERT INTO ROLE_PERMISSION_ASSOCIATIONS(ROLE_ID,PERMISSION_ID) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b38'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b39'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3a'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3a'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3b'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3c'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3d'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b41'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b42'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b43'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b44'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b45'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID);

-- USER1: ROLE 1, ROLE 10
-- USER2: ROLE 3, ROLE 9
-- USER3: ROLE 1
-- USER4: ROLE 2, ROLE 12
-- USER5: ROLE 4, ROLE 5
-- USER6: ROLE 1, ROLE 2, ROLE 4, ROLE 5
-- USER7: ROLE 6, ROLE 7, ROLE 8
INSERT INTO USER_ROLES(USER_ID, ROLE_ID) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b39'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b38'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b3b'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID);

INSERT INTO USER_GROUPS(USER_GROUP_ID, USER_GROUP_NAME, DESCRIPTION) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'GROUP 1', 'GROUP 1 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'GROUP 2', 'GROUP 2 Description Text'),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID, 'GROUP 3', 'GROUP 3 Description Text');

-- GROUP1: USER1, USER2
-- GROUP2: USER3, USER4, USER5
-- GROUP3: USER6
INSERT INTO USER_GROUP_USERS(USER_GROUP_ID, USER_ID) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID, 'efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID);

-- ROLE 1 - PERMISSION 1, 2, 3
-- ROLE 2 - PERMISSION 1, 5, 7
-- ROLE 3 - PERMISSION 4, 12, 20
-- ROLE 4 - PERMISSION 6, 8, 9
-- ROLE 5 - PERMISSION 10, 11, 12
-- ROLE 6 - PERMISSION 13, 14, 15
-- ROLE 7 - PERMISSION 16, 5, 17, 18, 19, 20
INSERT INTO ROLE_PERMISSION_ASSOCIATIONS(ROLE_ID, PERMISSION_ID) VALUES
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b30'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b31'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3b'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b32'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b45'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b37'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b33'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b38'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b39'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3a'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3b'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3c'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b3d'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b35'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b40'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b41'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b34'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b42'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b43'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b44'::UUID),
('efb15e8f-0caa-48fe-a058-f4c116836b36'::UUID,'efb15e8f-0caa-48fe-a058-f4c116836b45'::UUID);

