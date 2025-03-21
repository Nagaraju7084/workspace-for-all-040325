use test_medi_jwt_service040325;

/* tables are already created just we want to insert the data */
/* if our tables should have to create by using jpa entities */
/* then first run our application without script(comment the property in application.properties), once tables created */
/* then uncomment the property(the property in application.properties) and run the application */

INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_permission` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `name`) VALUES ('1', 'admin', '2021-06-10 00:00:00.000000', 'admin', '2021-06-10 00:00:00.000000', 'VIEW');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_permission` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `name`) VALUES ('2', 'admin', '2021-06-10 00:00:00.000000', 'admin', '2021-06-10 00:00:00.000000', 'SEARCH');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_permission` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `name`) VALUES ('3', 'admin', '2021-06-10 00:00:00.000000', 'admin', '2021-06-10 00:00:00.000000', 'DELETE');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_permission` (`id`, `created_by`, `created_date`, `modified_by`, `modified_date`, `name`) VALUES ('4', 'admin', '2021-06-10 00:00:00.000000', 'admin', '2021-06-10 00:00:00.000000', 'MODIFY');


INSERT IGNORE INTO `user_role` VALUES (1, 'Admin');
INSERT IGNORE INTO `user_role` VALUES (2, 'Doctor');
INSERT IGNORE INTO `user_role` VALUES (3, 'Patient');



INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('1', '1', '1');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('2', '2', '1');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('3', '3', '1');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('4', '4', '1');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('5', '1', '2');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('6', '2', '2');
INSERT IGNORE INTO `test_medi_jwt_service040325`.`user_role_to_permission` (`id`, `user_permission_id`, `user_role_id`) VALUES ('7', '4', '2');
