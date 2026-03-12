INSERT INTO question (id, question_text, subject, topic , explaination) VALUES
(1, 'What is 2 + 2?', 'Maths', 'Arithmetic','+ operation will add numbers'),
(2, 'What is 5 * 6?', 'Maths', 'Arithmetic','+ operation will add number'),
(3, 'Capital of France?', 'Geography', 'Europe','it is a fact');

INSERT INTO question_options (id, option_text, is_correct, question_id) VALUES
(1, '3', false, 1),
(2, '4', true, 1),
(3, '5', false, 1),
(4, '6', false, 1),

(5, '30', true, 2),
(6, '35', false, 2),
(7, '25', false, 2),
(8, '20', false, 2),

(9, 'Berlin', false, 3),
(10, 'Madrid', false, 3),
(11, 'Paris', true, 3),
(12, 'Rome', false, 3);