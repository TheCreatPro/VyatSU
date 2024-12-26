-- DROP TABLE students;  -- удалить таблицу
CREATE TABLE if not exists students
(
    id    serial,
    name  text,
    score int
);

ALTER TABLE students  -- сделать айди уникальным
ADD UNIQUE (id);

INSERT INTO students
VALUES (3, 'Anna',100);

INSERT INTO students (name, score)
VALUES ('Bob1',60);
INSERT INTO students (name, score)
VALUES ('Bob2',60);
INSERT INTO students (name, score)
VALUES ('Bob3',60);
INSERT INTO students (name, score)
VALUES ('Bob4',60);

-- вставка сразу несколько данных
INSERT INTO students (name, score)
VALUES ('Bob6', 30),
       ('Bob7', 15) RETURNING *;  --RETURNING * - посмотреть всё что изменилось

SELECT id, name  -- * -все поля
FROM students
WHERE id > 5;

SELECT * FROM students
WHERE id > 1 ORDER BY score;  -- ORDER BY -сортировка. после score DESC - в обратном порядке limit 5 - ограничение

DELETE FROM students WHERE name = 'Bob3' RETURNING *;

-- маска
SELECT * FROM students WHERE name LIKE 'Bob%'; -- % - это несколько символ (можно использовать *)
SELECT * FROM students WHERE name LIKE '_o__'; -- _ - это один символ до о
SELECT * FROM students WHERE name LIKE '%o__'; -- несколько символов до о

SELECT * FROM students WHERE score BETWEEN 50 AND 75;

-- можем сконвертировать 100-бальную систему в 5-ти бальную:
SELECT name, score, score/20 an_score FROM students;

-- выдаёт только уникальные значение (без повторений)
SELECT DISTINCT score FROM students;

SELECT * FROM students ORDER BY score DESC LIMIT 3 OFFSET 3;  -- пропустить трёх лучших по баллам и берём три следующих


CREATE TABLE if not exists book
(
    id    serial,
    title  text,  --varchar на нужное колво символов
    author_id int
);
CREATE TABLE if not exists authors
(
    id    serial,
    name  text
);
-- если данные хранятся в разных таблицах
SELECT b.id, b.title, a.name
FROM book b
        JOIN authors a
                ON (b.author_id=a.id)

-- ALTER TABLE students
--      ADD UNIQUE (id);
-- FOREIGN KEY - связывает данные, для того чтобы если удалили из таблицы А удалилось в таблицу Б