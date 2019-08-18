INSERT INTO `authority`(`name`, `id`) VALUES ('ROLE_ADMIN', 1);
INSERT INTO `authority`(`name`, `id`) VALUES ('ROLE_USER', 2);
INSERT INTO `user` (`id`, `username`, `password`, `dateCreated`) VALUES (1,'ironman','$2a$10$ErJZsL9/jaaHNdlXmoSm7uEAhfzZIKehXFNnytcP0NNI.L0KwXHNu','2015-11-15 22:14:54');
INSERT INTO `user` (`id`, `username`, `password`, `dateCreated`) VALUES (2,'rabi','$2y$12$Ue1cKCKTjMFYZFPkzo2E7uD5qoj.Yr7y5b445f3RHcC.6JiV/j8eu','2015-10-15 22:14:54');
INSERT INTO `user_authority`(`authority_id`, `user_id`) VALUES (1, 1);
INSERT INTO `user_authority`(`authority_id`, `user_id`) VALUES (2, 2);