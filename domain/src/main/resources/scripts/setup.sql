SET FOREIGN_KEY_CHECKS = 0;
drop table if exists tbtMovie;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE tbtMovie (
    MovieId int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Title varchar(255) NOT NULL,
    Rating varchar(20) NOT NULL,
    Image blob NOT NULL
);