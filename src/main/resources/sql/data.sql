INSERT INTO users (id, user_name, password, enable, locked, role)
VALUES (1, 'user007', '$2a$10$IgUgwizLh3qtW6yJ06PTJ.Cej7CIwusCfnPf8u/JOIzJOUW4BFb0K', true, false, 0);
INSERT INTO users (id, user_name, password, enable, locked, role)
VALUES (2, 'Joãozinho', '$2a$10$IgUgwizLh3qtW6yJ06PTJ.Cej7CIwusCfnPf8u/JOIzJOUW4BFb0K', true, false, 1);

INSERT INTO accounts (id, id_user, name, phone, email, birth_date)
VALUES (1, 1, 'James Bond 007', '(007) 007', '007@007.com', '1992-03-22');

INSERT INTO accounts (id, id_user, name, phone, email, birth_date)
VALUES (2, 2, 'Joaozinho 22', '(022) 022', '022@022.com', '1992-03-22');

UPDATE users SET id_account = 1 WHERE id = 1;
UPDATE users SET id_account = 2 WHERE id = 2;

INSERT INTO companies (id, name, phone, email)
VALUES (1, 'MI6', '(036) 034', 'mi6@mi6.coms');

INSERT INTO companies (id, name, phone, email)
VALUES (2, 'MI7', '(037) 033', 'mi7@mi7.coms');

INSERT INTO companies (id, name, phone, email)
VALUES (3, 'MI8', '(038) 032', 'mi8@mi8.coms');

INSERT INTO companies (id, name, phone, email)
VALUES (4, 'MI9', '(039) 031', 'mi9@mi9.coms');

INSERT INTO companies (id, name, phone, email)
VALUES (5, 'MI10', '(040) 030', 'mi10@mi10.coms');

INSERT INTO accounts_companies(id_account, id_company)
VALUES (1, 1);
INSERT INTO accounts_companies(id_account, id_company)
VALUES (1, 2);
