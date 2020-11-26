INSERT INTO account_type
    (name, is_active, created_at, updated_at)
VALUES
    ('user', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('specialist', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO specialist_type
    (name, is_active, created_at, updated_at)
VALUES
    ('nutritionist', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('coach', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO account
    (type_id, email, first_name, last_name, password, salt, is_active, created_at, updated_at)
VALUES
    (2, 's@test.com', 'Sportacus', 'Helth', '$2a$10$PpeMuMYmKOYzh7bVlmTEieySEVWE.mwpwDfZ1uiBn/9kAOr/WuE8S', '$2a$10$PpeMuMYmKOYzh7bVlmTEie', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 's2@test.com', 'Super', 'Man', '$2a$10$PpeMuMYmKOYzh7bVlmTEieySEVWE.mwpwDfZ1uiBn/9kAOr/WuE8S', '$2a$10$PpeMuMYmKOYzh7bVlmTEie', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 'a@test.com', 'Test', 'User', '$2a$10$PpeMuMYmKOYzh7bVlmTEieySEVWE.mwpwDfZ1uiBn/9kAOr/WuE8S', '$2a$10$PpeMuMYmKOYzh7bVlmTEie', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO specialist_account
    (account_id, specialist_type_id, document_number, description, is_active, created_at, updated_at)
VALUES
    (1, 1, '103212345', 'Doctor in nutrition', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 2, '103212346', 'Doctor in sport', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO user_account
    (account_id, payment_method, auto_renew_subscription, is_active, created_at, updated_at)
VALUES
    (3, false, false, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO subscription_type (subscription_type_name,subscription_type_description,subscription_type_price,is_active, created_at, updated_at)
VALUES
    ('Premium','Usuario premium con acceso a rutinas de entrenamiento y dietas a seguir sugeridas por profesionales recomendados',20, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO physical_exercise_type
    (name, is_active, created_at, updated_at)
VALUES
    ('strength', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('intensive', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('stretching', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('balance', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('cardio', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO physical_exercise
    (type_id, name, description, is_active, created_at, updated_at)
VALUES
    (1, 'Pull Ups', 'Elbows flex and the shoulders adduct and extend to bring the elbows to the torso', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 'Push Ups', 'By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (1, 'Pistol Squats', 'Increase single leg strength, balance, and improve movement mechanics of the lower body', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, 'Pull Ups', 'Elbows flex and the shoulders adduct and extend to bring the elbows to the torso', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Knee rotation', 'Maintain flexibility', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Knee rotation', 'Maintain flexibility', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Knee rotation', 'Maintain flexibility Maintain flexibility Maintain flexibility', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Standing knee lift', 'Feel steadier on your feet and helps prevent falls', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (4, 'Standing knee lift', 'feel steadier on your feet and helps prevent falls', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Outside Running', 'Get along with your body Get along with your body Get along with your body Get along with your body', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (5, 'Outside Running 2', 'Get along with your body Get along with your body Get along with your body Get along with your body', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;

INSERT INTO week_day
    (name, is_active, created_at, updated_at)
VALUES
    ('monday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('tuesday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('wednesday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('thursday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('friday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('saturday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('sunday', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
;
INSERT INTO user_medical_data
    (id,  is_active, created_at, updated_at,height_in_centimeters,weight_in_kilograms,birth_day,user_account_user_account_id)
VALUES
    (2,true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 150,54, CURRENT_TIMESTAMP, 1)
;

INSERT INTO user_subscription
    (is_active, created_at, updated_at, expiration_date, subscription_type_id,user_account_id )
VALUES
    (true, CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP, 1, 1)
;
UPDATE user_account SET payment_method= true
    WHERE user_account_id = 2;

UPDATE user_account SET specialist_account_id= 1
        WHERE user_account_id = 2;

INSERT INTO user_goal
    (created_at ,is_active , updated_at , description , expiration_date , user_account_user_account_id)
VALUES
    (CURRENT_TIMESTAMP,true,CURRENT_TIMESTAMP,'Bajar de peso', '2020-12-17', 1)
;
INSERT INTO sport_plan
    (created_at , is_active , updated_at , description , end_date , start_date , user_goal_id  )
VALUES
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,'descripcion sport plan','2020-12-17',CURRENT_TIMESTAMP,1)
;
INSERT INTO daily_sport_plan
    (created_at , is_active , updated_at , date , description , sport_plan_id , week_day_id)
VALUES
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-16','monday',1,1),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-17','tuesday',1,2),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-18','wednesday',1,3),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-19','thursday',1,4),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-20','friday',1,5),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-21','saturday',1,6),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP, '2020-11-22','sunday', 1,7)
;
INSERT INTO planned_exercise
    (created_at , is_active , updated_at ,checked ,repetitions_amount , series_amount , daily_sport_plan_id , physical_exercise_id)
VALUES
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,1,1),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,1,2),

    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,2,2),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,2,3),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,2,4),

    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,3,3),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,3,5),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,3,1),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,3,2),

    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,4,1),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,4,2),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,4,3),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,4,4),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,4,5),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,4,6),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,4,7),

    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,5,5),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,5,6),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,5,7),

    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,6,6),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,6,1),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,6,2),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,6,3),

    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,true,10,3,7,7),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,7,7),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,7,7),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,7,7),
    (CURRENT_TIMESTAMP,true, CURRENT_TIMESTAMP,false,10,3,7,7)
    ;



