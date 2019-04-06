-- 操作员
INSERT INTO `myLibrary`.`t_user`(`user_id`, `user_account`, `email`, `user_name`, `password`, `status`) VALUES (1, 'admin', 'dasheng.liang@outlook.com', '系统管理员', '123456', 0);

-- 馆藏地址
INSERT INTO `myLibrary`.`t_location`(`location_id`, `comment`, `location_name`) VALUES (1, '', '文化馆');

-- 借阅规则
INSERT INTO `myLibrary`.`t_rule`(`rule_id`, `borrow_days`, `borrow_number`, `rule_name`, `renewal_days`, `renewal_times`, `status`) VALUES (1, 30, 2, '2C30T2X15T', 15, 2, 0);

-- 读者类型
INSERT INTO `myLibrary`.`t_reader_type`(`reader_type_id`, `comment`, `reader_type_name`, `status`, `rule_id`) VALUES (1, '', '小学生', 0, 1);

-- 书目
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (1, '钱钟书', 'TP314.1', '红海早过了，船在印度洋面上开驶着，量是太阳依然不饶人地迟落早起，侵占去大部分的夜。夜仿佛纸浸了油，变成半透明体；它给太阳拥抱住了，分不出身来，也许是给太阳陶醉了，所以夕照晚霞隐褪后的夜色也带着酡红。到红消醉醒，船舱里的睡人也一身腻汗地醒来，洗了澡赶到甲板上吹海风，又是一天开始。这是七月下旬，合中国旧历的三伏，一年最热的时候。在中国热得更比常年利害，事后大家都说是兵戈之象，因为这就是民国二十六年（一九三七年）。', '9787020024759', 'chi', 246, '人民文学出版社', 23.50, '围城');

-- 馆藏
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (1, '11001100', 'TP314.1', 1, 0, 1, 1);

-- 读者
INSERT INTO `myLibrary`.`t_reader`(`reader_id`, `reader_card`, `gender`, `reader_name`, `status`, `reader_type_id`) VALUES (1, '20120612', 0, '梁大盛', 0, 1);