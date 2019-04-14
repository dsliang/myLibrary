-- 操作员
INSERT INTO `myLibrary`.`t_user`(`user_id`, `user_account`, `email`, `user_name`, `password`, `status`) VALUES (1, 'admin', 'dasheng.liang@outlook.com', '系统管理员', '123456', 0);

-- 馆藏地址
INSERT INTO `myLibrary`.`t_location`(`location_id`, `comment`, `location_name`) VALUES (1, '', '文化馆');
INSERT INTO `myLibrary`.`t_location`(`location_id`, `comment`, `location_name`) VALUES (2, '', '文瑜馆');

-- 借阅规则
INSERT INTO `myLibrary`.`t_rule`(`rule_id`, `borrow_days`, `borrow_number`, `rule_name`, `renewal_days`, `renewal_times`, `status`) VALUES (1, 30, 2, '2C30T2X15T', 15, 2, 0);
INSERT INTO `myLibrary`.`t_rule`(`rule_id`, `borrow_days`, `borrow_number`, `rule_name`, `renewal_days`, `renewal_times`, `status`) VALUES (2, 30, 6, '6C30T2X15T', 15, 2, 0);

-- 读者类型
INSERT INTO `myLibrary`.`t_reader_type`(`reader_type_id`, `comment`, `reader_type_name`, `status`, `rule_id`) VALUES (1, '', '小学生', 0, 1);
INSERT INTO `myLibrary`.`t_reader_type`(`reader_type_id`, `comment`, `reader_type_name`, `status`, `rule_id`) VALUES (2, '', '高级读者', 0, 2);

-- 书目
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (1, '钱钟书', 'TP314.1', '红海早过了，船在印度洋面上开驶着，量是太阳依然不饶人地迟落早起，侵占去大部分的夜。夜仿佛纸浸了油，变成半透明体；它给太阳拥抱住了，分不出身来，也许是给太阳陶醉了，所以夕照晚霞隐褪后的夜色也带着酡红。到红消醉醒，船舱里的睡人也一身腻汗地醒来，洗了澡赶到甲板上吹海风，又是一天开始。这是七月下旬，合中国旧历的三伏，一年最热的时候。在中国热得更比常年利害，事后大家都说是兵戈之象，因为这就是民国二十六年（一九三七年）。', '9787020024759', 'chi', 246, '人民文学出版社', 23.50, '围城');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (2, '张向荣', 'R473.5', '本书通过对老人的生理、心理特点的介绍，从日常生活可能遇到的问题出发，介绍了对老年人切实的护理和关怀照顾的方法和途径。', '9787500845652', 'chi', 156, '中国工人出版社', 12.00, '养老护理基本技能');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (3, '陈辉', 'G610',  '在成人看来，幼儿的行为有的呆萌可爱，有的顽皮捣蛋，也有的让人觉得不可理喻、忧心忡忡。实际上，不论呆萌顽皮还是不可理喻，幼儿的任何行为都是有原因的，而心理因素是影响幼儿行为的一个重要原因。本书从幼儿心理入手，分析幼儿行为背后的原因，为家长和教师提供科学的教养依据。', '9787568212205', 'chi', 14190, '北京理工大学出版社', 35.00, '幼儿行为问题应对');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (4, '童仝', 'I247.57',  '本书故事讲欧兰说在一千个男人里面，只有一个男人可以看见她的哭泣,而这个男人就是她要等的、要厮守一辈子的爱人……', '9787806237052', 'chi', 18, '河南文艺出版社', 18.00, '也许爱·B加X');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (5, '于元', 'K825.2',  '本书内容包括：“长大也当义和团”，小学和中学，清河陆军预备学校，保定陆军军官学校，借梯子上房，宁都起义等。', '9787547205730', 'chi', 128, '吉林文史出版社', 29.80, '董振堂');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (6, '李良明', 'K825.2',  '本书内容包括：奇男儿，伺候国家 伺候社会，改革中学教育，江汉潮涌，江城知音，利群助人，在安徽宣城等。', '9787547205075', 'chi', 128, '吉林文史出版社', 29.80, '恽代英');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (7, '尚品', 'TS935.5',  '本书介绍了近60种编绳基础结法，每种编绳结法皆附有浅显易懂的图解说明，而且还独立介绍各种常用材料和工具。', '9787535766922', 'chi', 115, '湖南科学技术出版社', 24.00, '编绳入门');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (8, '丁辉', 'R715.3',  '本书按妊娠历程的顺序，分别讲述了怀孕前的准备、如何避免流产、准爸爸的必修课、如何进行胎教、怀孕期的饮食和性爱、分娩前的准备、产后的身体复原、如何护理新生宝宝等。', '7539028343', 'chi', 14231, '江西科学技术出版社', 26.80, '怀孕优生全程指南');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (9, '丛树海', 'F830.91',  '本书共分8章，包括证券投资概述、有价证券的投资价值分析、证券投资的宏观经济分析、行业分析、公司分析、证券投资技术分析等。每章都有本章大纲、内容提要、复习题及参考答案。', '7810492527', 'chi', 269, '上海财经大学出版社', 17.00, '证券投资分析');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (10, '丛树海', 'F830.91',  '本书内容包括：证券投资基金概述、证券投资基金的当事人、基金管理公司、证券投资基金托管、证券投资基金的市场营销、证券投资基金的信息披露等。', '7810499882', 'chi', 381, '上海财经大学出版社', 22.00, '证券投资基金');
INSERT INTO `myLibrary`.`t_biblio`(`biblio_id`, `author`, `category_number`, `introduction`, `isbn`, `language`, `page`, `press`, `price`, `title`) VALUES (11, '陈先奎', 'D0',  '本书紧扣2003年政治考试大纲，全面覆盖考点，体现考试大纲、权威教材、马列原著、中央文件、有关专著、社会热点和教学经验的紧密结合，全面系统地讲解了所有考点。', '7502140166', 'chi', 206, '石油工业出版社', 20.00, '考研政治串讲讲义');

-- 馆藏
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (1, '11001100', 'TP314.1', 1, 0, 1, 1);
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (2, '11001101', 'TP314.1', 1, 0, 1, 1);
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (3, '11001102', 'TP314.1', 1, 0, 1, 1);
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (4, '11001103', 'TP314.1', 1, 0, 1, 1);
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (5, '11001110', 'K825.2', 1, 0, 5, 1);
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (7, '11001111', 'K825.2', 1, 0, 5, 1);
INSERT INTO `myLibrary`.`t_collection`(`collection_id`, `barcode`, `category_number`, `serial_number`, `status`, `biblio_id`, `location_id`) VALUES (6, '11001120', 'K825.2', 2, 0, 6, 1);

-- 读者
INSERT INTO `myLibrary`.`t_reader`(`reader_id`, `reader_card`, `gender`, `reader_name`, `status`, `reader_type_id`) VALUES (1, '20120612', 0, '梁大盛', 0, 1);
INSERT INTO `myLibrary`.`t_reader`(`reader_id`, `reader_card`, `gender`, `reader_name`, `status`, `reader_type_id`) VALUES (2, '20120613', 0, '张子豪', 0, 1);