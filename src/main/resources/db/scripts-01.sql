create database if not exists blogger;
use blogger;

CREATE TABLE `users` (
                         `id` int NOT NULL auto_increment,
                         `user_id` varchar(50) NOT NULL,
                         `password` char(68) NOT NULL,
                         `active` tinyint NOT NULL,
                         primary key (`user_id`),
                         unique key `auto_incr_unique_id` (`id`)
) ENGINE=InnoDB default charset=latin1;

CREATE TABLE `roles` (
                         `user_id` varchar(50) NOT NULL,
                         `role` varchar(50) NOT NULL,
                         unique key `authorization_idx_1` (`user_id`, `role`),
                         constraint `authorization_ibfk_1` foreign key (`user_id`) references `users` (`user_id`)
) engine=InnoDB default charset=latin1;
-- In authorization_ibfk_1 IBFK stands for - InnoDB Foreign Key



insert into `users` (`user_id`, `password`, `active`) values
    ( "aditya", "{bcrypt}$2a$12$msNH/gttKd0wDTa7U0glAO0Tp0DAhjGffQ.dLha312SzvEDxsyZKu", 1);
-- Bcrypt password is "aditya"

insert into `roles` values
    ("aditya", "ROLE_USER");
