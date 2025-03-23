/* CREATE DATABASE*/
CREATE DATABASE payroll;
USE payroll;

/* CREATE TABLE*/

CREATE TABLE `account`(
    `account_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `account_username` VARCHAR(20) NOT NULL UNIQUE,
    `account_password` VARCHAR(20) NOT NULL
);

CREATE TABLE `position`(
    `position_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `position_name` VARCHAR(20) NOT NULL
);

CREATE TABLE `department`(
    `department_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `department_name` VARCHAR(20) NOT NULL
);

CREATE TABLE `job`(
    `job_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `job_basic_salary` INT NOT NULL,
    `job_department_id` INT UNSIGNED NOT NULL,
    `job_position_id` INT UNSIGNED NOT NULL
);


CREATE TABLE `employee`(
    `employee_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `employee_name` VARCHAR(20) NOT NULL,
    `employee_phone` VARCHAR(20) NOT NULL,
    `employee_payrate` DOUBLE NOT NULL,
    `employee_start_date` DATE NOT NULL,
    `employee_account_id` INT UNSIGNED NOT NULL,
    `employee_job_id` INT UNSIGNED NOT NULL
);



CREATE TABLE `attendance`(
    `attendance_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `attendance_date` DATE NOT NULL,
    `attendance_employee_id` INT UNSIGNED NOT NULL
);

CREATE TABLE `payroll`(
    `payroll_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `payroll_date` DATE NOT NULL,
    `payroll_status` VARCHAR(20) NOT NULL,
    `payroll_employee_id` INT UNSIGNED NOT NULL
);

CREATE TABLE `salary`(
    `salary_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `salary_amount` DOUBLE NOT NULL,
    `salary_description` TEXT NOT NULL,
    `salary_payroll_id` INT UNSIGNED NOT NULL
);

CREATE TABLE `deduction`(
    `deduction_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `deduction_amount` DOUBLE NOT NULL,
    `deduction_description` TEXT NOT NULL,
    `deduction_payroll_id` INT UNSIGNED NOT NULL
);
CREATE TABLE `allowance`(
    `allowance_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `allowance_amount` DOUBLE NOT NULL,
    `allowance_description` TEXT NOT NULL,
    `allowance_status` VARCHAR(20) NOT NULL,
    `allowance_payroll_id` INT UNSIGNED NOT NULL
);
CREATE TABLE `bonus`(
    `bonus_id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `bonus_amount` DOUBLE NOT NULL,
    `bonus_description` TEXT NOT NULL,
    `bonus_payroll_id` INT UNSIGNED NOT NULL
);



/* FOREIGN KEY */
ALTER TABLE
    `job` ADD CONSTRAINT `job_job_position_id_foreign` FOREIGN KEY(`job_position_id`) REFERENCES `position`(`position_id`);
ALTER TABLE
    `bonus` ADD CONSTRAINT `bonus_bonus_payroll_id_foreign` FOREIGN KEY(`bonus_payroll_id`) REFERENCES `payroll`(`payroll_id`);
ALTER TABLE
    `employee` ADD CONSTRAINT `employee_employee_job_id_foreign` FOREIGN KEY(`employee_job_id`) REFERENCES `job`(`job_id`);
ALTER TABLE
    `deduction` ADD CONSTRAINT `deduction_deduction_payroll_id_foreign` FOREIGN KEY(`deduction_payroll_id`) REFERENCES `payroll`(`payroll_id`);
ALTER TABLE
    `payroll` ADD CONSTRAINT `payroll_payroll_employee_id_foreign` FOREIGN KEY(`payroll_employee_id`) REFERENCES `employee`(`employee_id`);
ALTER TABLE
    `employee` ADD CONSTRAINT `employee_employee_account_id_foreign` FOREIGN KEY(`employee_account_id`) REFERENCES `account`(`account_id`);
ALTER TABLE
    `job` ADD CONSTRAINT `job_job_department_id_foreign` FOREIGN KEY(`job_department_id`) REFERENCES `department`(`department_id`);
ALTER TABLE
    `allowance` ADD CONSTRAINT `allowance_allowance_payroll_id_foreign` FOREIGN KEY(`allowance_payroll_id`) REFERENCES `payroll`(`payroll_id`);
ALTER TABLE
    `attendance` ADD CONSTRAINT `attendance_attendance_employee_id_foreign` FOREIGN KEY(`attendance_employee_id`) REFERENCES `employee`(`employee_id`);
ALTER TABLE
    `salary` ADD CONSTRAINT `salary_salary_payroll_id_foreign` FOREIGN KEY(`salary_payroll_id`) REFERENCES `payroll`(`payroll_id`);