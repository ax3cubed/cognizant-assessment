DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_task;
CREATE TABLE `tasks`
(
    `id`                  int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    `task_name`            varchar(150)  ,
    `task_description`     text  ,
    `task_input_parameter`  text  ,
    `task_output_parameter` text
);
CREATE TABLE `users`
(
    `id`    int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    `name`  varchar(200)  ,
    `email` varchar(50)
);


CREATE TABLE `user_task`
(
    `id`     int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY ,
    `task_id` int  ,
    `user_id` int  ,
    `status` bit,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`task_id`) REFERENCES `tasks` (`id`)
);

