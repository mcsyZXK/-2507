/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : localhost:3306
 Source Schema         : clzl

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 10/08/2025 12:52:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for announcements
-- ----------------------------
DROP TABLE IF EXISTS `announcements`;
CREATE TABLE `announcements`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `like_count` bigint NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of announcements
-- ----------------------------
INSERT INTO `announcements` VALUES (1, '芜湖', '芜湖~~~~~', '2025-07-21 10:41:25', 165);
INSERT INTO `announcements` VALUES (2, '阿卡丽的黑心商店', '全场十二折', '2025-07-21 10:41:28', 17);
INSERT INTO `announcements` VALUES (3, 'byd', '什么时候砍狗熊', '2025-07-21 10:41:30', 16);
INSERT INTO `announcements` VALUES (5, '111', '111', '2025-07-21 10:41:31', 13);
INSERT INTO `announcements` VALUES (6, '無敵了', '行了xdm俄洛依隨便打狗熊', '2025-07-21 10:41:34', 26);

-- ----------------------------
-- Table structure for che
-- ----------------------------
DROP TABLE IF EXISTS `che`;
CREATE TABLE `che`  (
  `che_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车牌号码',
  `xin_xi` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_zl` tinyint(1) NULL DEFAULT 0,
  `price` int NOT NULL DEFAULT 50,
  `picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`che_num`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of che
-- ----------------------------
INSERT INTO `che` VALUES ('40001', 'Toyota', 1, 91, 'Toyota.png');
INSERT INTO `che` VALUES ('40002', 'Ford', 1, 60, 'Ford.png');
INSERT INTO `che` VALUES ('40003', 'BMW', 1, 72, 'BMW.png');
INSERT INTO `che` VALUES ('40004', 'Mercedes-Benz', 1, 50, 'Mercedes-Benz.png');
INSERT INTO `che` VALUES ('40005', 'Volkswagen', 1, 90, 'Volkswagen.png');
INSERT INTO `che` VALUES ('40006', 'Honda', 1, 57, 'Honda.png');
INSERT INTO `che` VALUES ('40007', 'Audi', 1, 80, 'Audi.png');
INSERT INTO `che` VALUES ('40008', 'Chevrolet', 0, 57, 'Chevrolet.png');
INSERT INTO `che` VALUES ('40009', 'Nissan', 0, 86, 'Nissan.png');
INSERT INTO `che` VALUES ('40010', 'Hyundai', 0, 75, 'Hyundai.png');
INSERT INTO `che` VALUES ('40012', 'Subaru', 0, 68, 'Subaru.png');
INSERT INTO `che` VALUES ('40013', 'Lexus', 0, 70, 'Lexus.png');
INSERT INTO `che` VALUES ('40014', 'Kia', 0, 55, 'Kia.png');
INSERT INTO `che` VALUES ('40015', 'Tesla', 0, 84, 'Tesla.png');
INSERT INTO `che` VALUES ('40016', 'Toyota', 0, 91, 'Toyota.png');
INSERT INTO `che` VALUES ('40017', 'Ford', 0, 62, 'Ford.png');
INSERT INTO `che` VALUES ('40018', 'BMW', 0, 72, 'BMW.png');
INSERT INTO `che` VALUES ('40019', 'Mercedes-Benz', 0, 50, 'Mercedes-Benz.png');
INSERT INTO `che` VALUES ('40020', 'Volkswagen', 0, 90, 'Volkswagen.png');
INSERT INTO `che` VALUES ('40021', 'Honda', 0, 57, 'Honda.png');
INSERT INTO `che` VALUES ('40022', 'Audi', 0, 80, 'Audi.png');
INSERT INTO `che` VALUES ('40023', 'Chevrolet', 0, 57, 'Chevrolet.png');
INSERT INTO `che` VALUES ('40024', 'Nissan', 0, 86, 'Nissan.png');
INSERT INTO `che` VALUES ('40025', 'Hyundai', 0, 75, 'Hyundai.png');
INSERT INTO `che` VALUES ('40026', 'Jeep', 0, 62, 'Jeep.png');
INSERT INTO `che` VALUES ('40027', 'Subaru', 0, 68, 'Subaru.png');
INSERT INTO `che` VALUES ('40028', 'Lexus', 0, 70, 'Lexus.png');
INSERT INTO `che` VALUES ('40029', 'Kia', 0, 55, 'Kia.png');
INSERT INTO `che` VALUES ('40030', 'Tesla', 0, 84, 'Tesla.png');
INSERT INTO `che` VALUES ('40031', 'Toyota', 0, 91, 'Toyota.png');
INSERT INTO `che` VALUES ('40032', 'Ford', 0, 60, 'Ford.png');
INSERT INTO `che` VALUES ('40033', 'BMW', 0, 72, 'BMW.png');
INSERT INTO `che` VALUES ('40034', 'Mercedes-Benz', 0, 50, 'Mercedes-Benz.png');
INSERT INTO `che` VALUES ('40035', 'Volkswagen', 0, 90, 'Volkswagen.png');
INSERT INTO `che` VALUES ('40036', 'Honda', 0, 57, 'Honda.png');
INSERT INTO `che` VALUES ('40037', 'Audi', 0, 80, 'Audi.png');
INSERT INTO `che` VALUES ('40038', 'Chevrolet', 0, 57, 'Chevrolet.png');
INSERT INTO `che` VALUES ('40039', 'Nissan', 0, 86, 'Nissan.png');
INSERT INTO `che` VALUES ('40040', 'Hyundai', 0, 75, 'Hyundai.png');
INSERT INTO `che` VALUES ('40041', 'Jeep', 0, 62, 'Jeep.png');
INSERT INTO `che` VALUES ('40042', 'Subaru', 0, 68, 'Subaru.png');
INSERT INTO `che` VALUES ('40043', 'Lexus', 0, 70, 'Lexus.png');
INSERT INTO `che` VALUES ('40044', 'Kia', 0, 55, 'Kia.png');
INSERT INTO `che` VALUES ('40045', 'Tesla', 0, 84, 'Tesla.png');
INSERT INTO `che` VALUES ('40046', 'Toyota', 0, 91, 'Toyota.png');
INSERT INTO `che` VALUES ('40047', 'Ford', 0, 60, 'Ford.png');
INSERT INTO `che` VALUES ('40048', 'BMW', 0, 72, 'BMW.png');
INSERT INTO `che` VALUES ('40049', 'Mercedes-Benz', 0, 50, 'Mercedes-Benz.png');
INSERT INTO `che` VALUES ('40050', 'Volkswagen', 0, 90, 'Volkswagen.png');
INSERT INTO `che` VALUES ('40051', 'Honda', 0, 57, 'Honda.png');
INSERT INTO `che` VALUES ('40052', 'Audi', 0, 80, 'Audi.png');
INSERT INTO `che` VALUES ('40053', 'Chevrolet', 0, 57, 'Chevrolet.png');
INSERT INTO `che` VALUES ('40054', 'Nissan', 0, 86, 'Nissan.png');
INSERT INTO `che` VALUES ('40055', 'Hyundai', 0, 75, 'Hyundai.png');
INSERT INTO `che` VALUES ('40056', 'Jeep', 0, 62, 'Jeep.png');
INSERT INTO `che` VALUES ('40057', 'Subaru', 0, 68, 'Subaru.png');
INSERT INTO `che` VALUES ('40058', 'Lexus', 0, 70, 'Lexus.png');
INSERT INTO `che` VALUES ('40059', 'Kia', 0, 55, 'Kia.png');
INSERT INTO `che` VALUES ('40060', 'Tesla', 0, 84, 'Tesla.png');
INSERT INTO `che` VALUES ('40061', 'Toyota', 0, 91, 'Toyota.png');
INSERT INTO `che` VALUES ('40062', 'Ford', 0, 60, 'Ford.png');
INSERT INTO `che` VALUES ('40063', 'BMW', 0, 72, 'BMW.png');
INSERT INTO `che` VALUES ('40064', 'Mercedes-Benz', 0, 50, 'Mercedes-Benz.png');
INSERT INTO `che` VALUES ('40065', 'Volkswagen', 0, 90, 'Volkswagen.png');
INSERT INTO `che` VALUES ('40066', 'Honda', 0, 57, 'Honda.png');
INSERT INTO `che` VALUES ('40067', 'Audi', 0, 80, 'Audi.png');
INSERT INTO `che` VALUES ('40068', 'Chevrolet', 0, 57, 'Chevrolet.png');
INSERT INTO `che` VALUES ('40069', 'Nissan', 0, 86, 'Nissan.png');
INSERT INTO `che` VALUES ('40070', 'Hyundai', 0, 75, 'Hyundai.png');
INSERT INTO `che` VALUES ('40071', 'Jeep', 0, 62, 'Jeep.png');
INSERT INTO `che` VALUES ('40072', 'Subaru', 0, 68, 'Subaru.png');
INSERT INTO `che` VALUES ('40073', 'Lexus', 0, 70, 'Lexus.png');
INSERT INTO `che` VALUES ('40074', 'Kia', 0, 55, 'Kia.png');
INSERT INTO `che` VALUES ('40075', 'Tesla', 0, 84, 'Tesla.png');
INSERT INTO `che` VALUES ('40076', 'Toyota', 0, 50, 'Toyota.png');
INSERT INTO `che` VALUES ('40077', 'Toyota', 0, 50, 'Toyota.png');
INSERT INTO `che` VALUES ('40078', 'Toyota', 0, 50, 'Toyota.png');
INSERT INTO `che` VALUES ('40079', 'Toyota', 0, 50, 'Toyota.png');
INSERT INTO `che` VALUES ('41080', 'Toyota', 0, 51, 'Toyota.png');
INSERT INTO `che` VALUES ('41081', 'Toyota', 0, 52, 'Toyota.png');
INSERT INTO `che` VALUES ('41082', 'Toyota', 0, 53, 'Toyota.png');
INSERT INTO `che` VALUES ('41084', 'Toyota', 0, 55, 'Toyota.png');
INSERT INTO `che` VALUES ('41085', 'Toyota', 0, 90, 'Toyota.png');
INSERT INTO `che` VALUES ('41086', 'Toyota', 0, 156, 'Toyota.png');
INSERT INTO `che` VALUES ('41087', 'Ford', 0, 158, 'Ford.png');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `che_num` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `request_date` date NULL DEFAULT NULL,
  `status` enum('PENDING','APPROVED','REJECTED','CANCELLED') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'PENDING',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `che_num`(`che_num` ASC) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`che_num`) REFERENCES `che` (`che_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (4, 2, '40001', NULL, 'CANCELLED', '2025-07-19 19:54:53');
INSERT INTO `order` VALUES (5, 3, '40002', NULL, 'PENDING', '2025-07-23 18:58:02');
INSERT INTO `order` VALUES (6, 3, '40003', NULL, 'PENDING', '2025-07-23 18:58:37');
INSERT INTO `order` VALUES (7, 3, '40004', NULL, 'CANCELLED', '2025-07-23 18:59:10');
INSERT INTO `order` VALUES (8, 1, '40005', NULL, 'CANCELLED', '2025-07-24 15:21:04');
INSERT INTO `order` VALUES (9, 12, '40006', NULL, 'PENDING', '2025-07-24 15:54:49');
INSERT INTO `order` VALUES (10, 3, '40007', NULL, 'PENDING', '2025-07-24 16:01:18');

-- ----------------------------
-- Table structure for replies
-- ----------------------------
DROP TABLE IF EXISTS `replies`;
CREATE TABLE `replies`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `anid` int NULL DEFAULT NULL,
  `retext` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `announcements_id`(`anid` ASC) USING BTREE,
  CONSTRAINT `replies_ibfk_1` FOREIGN KEY (`anid`) REFERENCES `announcements` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of replies
-- ----------------------------
INSERT INTO `replies` VALUES (2, 1, '对的对的', 'asd', '2024-11-28 22:57:19');
INSERT INTO `replies` VALUES (3, 1, 'wocao', 'asd', '2024-11-30 21:13:36');
INSERT INTO `replies` VALUES (4, 1, '不儿', 'asd', '2024-11-30 21:21:20');
INSERT INTO `replies` VALUES (6, 1, '不对不对', 'niumo', '2024-11-30 21:31:13');
INSERT INTO `replies` VALUES (8, 2, '好耶', 'mcsy', '2024-12-01 17:53:15');
INSERT INTO `replies` VALUES (9, 3, '好的', 'mcsy', '2024-12-01 18:00:09');
INSERT INTO `replies` VALUES (10, 2, 'ASD', 'mcsy', '2024-12-01 20:07:24');
INSERT INTO `replies` VALUES (11, 1, '对吗？', '梦沉彡书远', '2025-04-25 12:50:07');
INSERT INTO `replies` VALUES (14, 1, '艾歐尼亞4=1', '2747008310', '2025-07-21 14:39:23');
INSERT INTO `replies` VALUES (15, 1, '艾歐尼亞4=1', '2747008310', '2025-07-21 14:41:02');
INSERT INTO `replies` VALUES (16, 6, 'xdm鐵男隨便開俄洛依', '2747008310', '2025-07-21 14:41:32');
INSERT INTO `replies` VALUES (17, 1, '開什麽玩笑？沒人打lol了', '360的傳人', '2025-07-21 14:46:22');
INSERT INTO `replies` VALUES (18, 1, '打野都不在我为什么要在', '梦沉彡书远', '2025-07-21 17:44:26');
INSERT INTO `replies` VALUES (19, 1, 'OTTO', '梦沉彡书远', '2025-07-24 15:14:28');
INSERT INTO `replies` VALUES (20, 1, '11111\n', NULL, '2025-07-24 15:56:09');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `picture_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` enum('ADMIN','USER') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE,
  UNIQUE INDEX `unique_username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '1124127770', '$2a$10$QBeBGc5tR2DR5SVSbl9mY.ccSGUOlquzPtvtv0.xx49lsxoqEa1xe', NULL, NULL, '1124127770@qq.com', '梦沉彡书远', 'ADMIN');
INSERT INTO `user` VALUES (2, '2747008310', '$2a$10$OOtpPX1YXk.ntTHfsqwmuuh1pKaeS4rzlcbmaanaCQqSgN/LqUipC', NULL, NULL, '2747008310@qq.com', '360的傳人', 'USER');
INSERT INTO `user` VALUES (3, 'user01', '$2a$10$mO8U.tSkSMySh6/KElZq9e78mTt1LcbpWwjDVPG1YPeeG54zii7Wm', NULL, NULL, '123456789@qq.com', 'ASD', 'USER');
INSERT INTO `user` VALUES (4, 'user02', '$2a$10$aKkTcYYR4GIuPNZwUGl95.U0Dk2wsyXhWghISiT74wBuAGQeu/5zS', NULL, NULL, '1234567890@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (5, 'user03', '$2a$10$cu3N/Zo05kRLo8U1SqAPweLCUlart6kfvaJsQ3PKPtyRiOasFiruK', NULL, NULL, '123456@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (6, 'user111', '$2a$10$UzJ4lpqPfT9v76Ub5q1ZBep5As4yo4DcIMIaMn8ASUqK1FGOFHHt6', NULL, NULL, '11232@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (7, 'user007', '$2a$10$FJamShR1J5vrX7VifyqyyOFtr2V0V5m4ebPNNfgukUHIFKGPpYg.6', NULL, NULL, '112412664@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (8, 'user006', '$2a$10$9XbOjEacJujDqZn1EVfPIezlaP8Qu7Kd4zZi9xMWPaUZN8L.F7oZe', NULL, NULL, '1231241@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (9, 'user005', '$2a$10$n.nE3JbVYxRmU4pMzkMOV.CwJu1U9NyegTtDcnqRq9Isy.pjrbGSO', NULL, NULL, '123456123@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (10, 'user189', '$2a$10$N4fJatzw0nJ2eHGS9eC1qu8z/EDU5hdMEWJl/p5Osjg51OYyGb62S', NULL, NULL, '189289@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (11, 'user710', '$2a$10$blLUlGAcFLsvNWclRkOpZudy/NQKNChV.HFKJOVS.bLkvdCDYAy1O', NULL, NULL, '15906@qq.com', NULL, 'USER');
INSERT INTO `user` VALUES (12, 'yeuser01', '$2a$10$SsnA/ErKsgmsMVUnLqtcCOcJvps8NCAhJ/YM/V.ct5sVgwPXRe9Ue', NULL, NULL, '112812770@qq.com', NULL, 'USER');

SET FOREIGN_KEY_CHECKS = 1;
