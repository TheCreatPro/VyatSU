SELECT s.имя FROM студенты AS s WHERE NOT EXISTS (SELECT 1 FROM предметы AS sub LEFT JOIN успеваемость AS u ON sub.id = u.предмет_id AND u.студент_id = s.id WHERE u.оценка <= 3 OR u.оценка IS NULL)
DELETE FROM предметы WHERE id=3;