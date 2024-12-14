INSERT INTO "user" (name, password, point, created_at)
VALUES
('JohnDoe', 'password123', 100, NOW()),
('JaneSmith', 'password456', 150, NOW()),
('AlexBrown', 'password789', 200, NOW()),
('EmilyWhite', 'passwordabc', 50, NOW()),
('ChrisGreen', 'passworddef', 300, NOW()),
('TaylorBlack', 'passwordghi', 250, NOW()),
('JordanGray', 'passwordjkl', 400, NOW()),
('MorganBlue', 'passwordmno', 500, NOW()),
('CaseyYellow', 'passwordpqr', 350, NOW()),
('JamieRed', 'passwordstu', 450, NOW());


INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(1, ST_PointFromText('POINT(127.0780 37.5665)', 5181), 70, 50, '소음이 많은 환경', NOW()),
(1, ST_PointFromText('POINT(127.0779 37.5664)', 5181), 80, 60, '적당한 교통 소음', NOW()),
(1, ST_PointFromText('POINT(127.0782 37.5666)', 5181), 85, 55, '지속적인 소음', NOW()),
(1, ST_PointFromText('POINT(127.0781 37.5667)', 5181), 65, 45, '가끔 발생하는 교통 소음', NOW()),
(1, ST_PointFromText('POINT(127.0783 37.5668)', 5181), 90, 70, '출퇴근 시간 소음', NOW()),
(1, ST_PointFromText('POINT(127.0784 37.5664)', 5181), 80, 60, '아침 러시아워 소음', NOW()),
(1, ST_PointFromText('POINT(127.0778 37.5662)', 5181), 95, 75, '근처 공사 소음', NOW()),
(1, ST_PointFromText('POINT(127.0785 37.5669)', 5181), 70, 50, '저녁 소음', NOW()),
(1, ST_PointFromText('POINT(127.0787 37.5663)', 5181), 80, 60, '주말의 평온함', NOW()),
(1, ST_PointFromText('POINT(127.0786 37.5661)', 5181), 85, 55, '야간의 고요함', NOW());

-- User ID 2에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(2, ST_PointFromText('POINT(127.1150 37.5805)', 5181), 60, 40, '주택가 소음', NOW()),
(2, ST_PointFromText('POINT(127.1151 37.5806)', 5181), 70, 50, '가족 친화적인 소음 수준', NOW()),
(2, ST_PointFromText('POINT(127.1153 37.5807)', 5181), 75, 55, '약간의 소음 공해', NOW()),
(2, ST_PointFromText('POINT(127.1152 37.5804)', 5181), 55, 35, '낮은 소음 수준', NOW()),
(2, ST_PointFromText('POINT(127.1155 37.5808)', 5181), 80, 60, '오후의 소음', NOW()),
(2, ST_PointFromText('POINT(127.1156 37.5809)', 5181), 70, 45, '조용한 아침', NOW()),
(2, ST_PointFromText('POINT(127.1154 37.5803)', 5181), 65, 45, '새소리와 대화 소리', NOW()),
(2, ST_PointFromText('POINT(127.1149 37.5802)', 5181), 60, 40, '평온한 저녁', NOW()),
(2, ST_PointFromText('POINT(127.1147 37.5801)', 5181), 80, 55, '가끔 들리는 자동차 소리', NOW()),
(2, ST_PointFromText('POINT(127.1148 37.5800)', 5181), 50, 35, '조용한 밤', NOW());

-- User ID 3에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(3, ST_PointFromText('POINT(127.1301 37.5755)', 5181), 65, 45, '도심 소음', NOW()),
(3, ST_PointFromText('POINT(127.1302 37.5756)', 5181), 70, 50, '출퇴근 시간 소음', NOW()),
(3, ST_PointFromText('POINT(127.1303 37.5757)', 5181), 75, 55, '고속도로 근처 소음', NOW()),
(3, ST_PointFromText('POINT(127.1304 37.5758)', 5181), 60, 40, '조용한 주택가', NOW()),
(3, ST_PointFromText('POINT(127.1305 37.5759)', 5181), 85, 65, '주간 소음', NOW()),
(3, ST_PointFromText('POINT(127.1306 37.5760)', 5181), 70, 50, '주말 소리', NOW()),
(3, ST_PointFromText('POINT(127.1307 37.5761)', 5181), 90, 70, '시장 근처 소음', NOW()),
(3, ST_PointFromText('POINT(127.1308 37.5762)', 5181), 65, 45, '대중교통 소음', NOW()),
(3, ST_PointFromText('POINT(127.1309 37.5763)', 5181), 75, 55, '번화한 거리 소음', NOW()),
(3, ST_PointFromText('POINT(127.1310 37.5764)', 5181), 55, 35, '조용한 심야 소음', NOW());



INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(4, ST_PointFromText('POINT(127.1401 37.5835)', 5181), 70, 50, '저녁 산책 중 소음', NOW()),
(4, ST_PointFromText('POINT(127.1402 37.5836)', 5181), 85, 65, '아침 시장 소음', NOW()),
(4, ST_PointFromText('POINT(127.1403 37.5837)', 5181), 60, 40, '조용한 주말', NOW()),
(4, ST_PointFromText('POINT(127.1404 37.5838)', 5181), 75, 55, '공원에서 발생한 소음', NOW()),
(4, ST_PointFromText('POINT(127.1405 37.5839)', 5181), 65, 50, '아이들 놀이 소리', NOW()),
(4, ST_PointFromText('POINT(127.1406 37.5840)', 5181), 55, 35, '조용한 주택가', NOW()),
(4, ST_PointFromText('POINT(127.1407 37.5841)', 5181), 80, 60, '큰 도로 소음', NOW()),
(4, ST_PointFromText('POINT(127.1408 37.5842)', 5181), 85, 65, '출퇴근 시간 소음', NOW()),
(4, ST_PointFromText('POINT(127.1409 37.5843)', 5181), 60, 40, '밤의 고요함', NOW()),
(4, ST_PointFromText('POINT(127.1410 37.5844)', 5181), 65, 45, '비 오는 날의 조용함', NOW());

-- User ID 5에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(5, ST_PointFromText('POINT(127.0501 37.5905)', 5181), 65, 45, '도시 배경 소음', NOW()),
(5, ST_PointFromText('POINT(127.0502 37.5906)', 5181), 70, 50, '아침 공사 소음', NOW()),
(5, ST_PointFromText('POINT(127.0503 37.5907)', 5181), 75, 55, '고속도로 근처 소음', NOW()),
(5, ST_PointFromText('POINT(127.0504 37.5908)', 5181), 60, 40, '조용한 주택지', NOW()),
(5, ST_PointFromText('POINT(127.0505 37.5909)', 5181), 90, 70, '시장 소음', NOW()),
(5, ST_PointFromText('POINT(127.0506 37.5910)', 5181), 65, 45, '이웃집 활동 소리', NOW()),
(5, ST_PointFromText('POINT(127.0507 37.5911)', 5181), 85, 65, '바쁜 오후 소음', NOW()),
(5, ST_PointFromText('POINT(127.0508 37.5912)', 5181), 95, 75, '교통 혼잡 소음', NOW()),
(5, ST_PointFromText('POINT(127.0509 37.5913)', 5181), 55, 35, '조용한 밤', NOW()),
(5, ST_PointFromText('POINT(127.0510 37.5914)', 5181), 60, 40, '저녁 고요함', NOW());

-- User ID 6에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(6, ST_PointFromText('POINT(127.0601 37.6005)', 5181), 65, 45, '조용한 아침', NOW()),
(6, ST_PointFromText('POINT(127.0602 37.6006)', 5181), 70, 50, '오후의 활동 소음', NOW()),
(6, ST_PointFromText('POINT(127.0603 37.6007)', 5181), 75, 55, '저녁 퇴근길 소음', NOW()),
(6, ST_PointFromText('POINT(127.0604 37.6008)', 5181), 55, 35, '주말의 고요함', NOW()),
(6, ST_PointFromText('POINT(127.0605 37.6009)', 5181), 90, 70, '공공행사 소음', NOW()),
(6, ST_PointFromText('POINT(127.0606 37.6010)', 5181), 75, 50, '교통 체증 소음', NOW()),
(6, ST_PointFromText('POINT(127.0607 37.6011)', 5181), 60, 40, '평화로운 아침', NOW()),
(6, ST_PointFromText('POINT(127.0608 37.6012)', 5181), 100, 80, '저녁 활동 소음', NOW()),
(6, ST_PointFromText('POINT(127.0609 37.6013)', 5181), 65, 45, '공원 내 소음', NOW()),
(6, ST_PointFromText('POINT(127.0610 37.6014)', 5181), 85, 65, '밤의 고요함', NOW());

-- User ID 7에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(7, ST_PointFromText('POINT(127.0701 37.6105)', 5181), 65, 45, '도시 공원 소음', NOW()),
(7, ST_PointFromText('POINT(127.0702 37.6106)', 5181), 70, 50, '아침 교통 혼잡', NOW()),
(7, ST_PointFromText('POINT(127.0703 37.6107)', 5181), 90, 70, '오후 시장 소음', NOW()),
(7, ST_PointFromText('POINT(127.0704 37.6108)', 5181), 55, 35, '조용한 동네 소음', NOW()),
(7, ST_PointFromText('POINT(127.0705 37.6109)', 5181), 60, 45, '주택가의 고요함', NOW()),
(7, ST_PointFromText('POINT(127.0706 37.6110)', 5181), 100, 80, '야간의 고요함', NOW()),
(7, ST_PointFromText('POINT(127.0707 37.6111)', 5181), 75, 55, '시장 내 소음', NOW()),
(7, ST_PointFromText('POINT(127.0708 37.6112)', 5181), 95, 75, '번화가 소음', NOW()),
(7, ST_PointFromText('POINT(127.0709 37.6113)', 5181), 55, 35, '저녁 교통 소음', NOW()),
(7, ST_PointFromText('POINT(127.0710 37.6114)', 5181), 85, 65, '조용한 아침', NOW());

-- User ID 8에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(8, ST_PointFromText('POINT(127.0801 37.6205)', 5181), 70, 50, '활기찬 도시의 아침', NOW()),
(8, ST_PointFromText('POINT(127.0802 37.6206)', 5181), 65, 45, '조용한 주말', NOW()),
(8, ST_PointFromText('POINT(127.0803 37.6207)', 5181), 75, 55, '저녁 소음', NOW()),
(8, ST_PointFromText('POINT(127.0804 37.6208)', 5181), 90, 70, '오후 퇴근길 소음', NOW()),
(8, ST_PointFromText('POINT(127.0805 37.6209)', 5181), 70, 50, '시장 활동 소음', NOW()),
(8, ST_PointFromText('POINT(127.0806 37.6210)', 5181), 55, 40, '평화로운 밤', NOW()),
(8, ST_PointFromText('POINT(127.0807 37.6211)', 5181), 50, 35, '교통 체증 소음', NOW()),
(8, ST_PointFromText('POINT(127.0808 37.6212)', 5181), 85, 65, '도시 중심 소음', NOW()),
(8, ST_PointFromText('POINT(127.0809 37.6213)', 5181), 95, 75, '조용한 주택가 소음', NOW()),
(8, ST_PointFromText('POINT(127.0810 37.6214)', 5181), 100, 80, '공원에서의 활동 소음', NOW());

-- User ID 9에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(9, ST_PointFromText('POINT(127.0901 37.6305)', 5181), 65, 45, '조용한 아침 소음', NOW()),
(9, ST_PointFromText('POINT(127.0902 37.6306)', 5181), 70, 50, '시장 소음', NOW()),
(9, ST_PointFromText('POINT(127.0903 37.6307)', 5181), 75, 55, '활기찬 오후 소음', NOW()),
(9, ST_PointFromText('POINT(127.0904 37.6308)', 5181), 90, 70, '저녁의 평화', NOW()),
(9, ST_PointFromText('POINT(127.0905 37.6309)', 5181), 70, 50, '평화로운 밤', NOW()),
(9, ST_PointFromText('POINT(127.0906 37.6310)', 5181), 55, 40, '교통 소음', NOW()),
(9, ST_PointFromText('POINT(127.0907 37.6311)', 5181), 50, 35, '도시의 번잡함', NOW()),
(9, ST_PointFromText('POINT(127.0908 37.6312)', 5181), 85, 65, '주말 소음', NOW()),
(9, ST_PointFromText('POINT(127.0909 37.6313)', 5181), 95, 75, '도시 활동 소음', NOW()),
(9, ST_PointFromText('POINT(127.0910 37.6314)', 5181), 100, 80, '시장의 활동 소음', NOW());

-- User ID 10에 대한 Noise 데이터
INSERT INTO noise (user_id, point, max_decibel, avg_decibel, review, created_at)
VALUES
(10, ST_PointFromText('POINT(127.1001 37.6405)', 5181), 70, 50, '평화로운 아침', NOW()),
(10, ST_PointFromText('POINT(127.1002 37.6406)', 5181), 75, 55, '교통 소음', NOW()),
(10, ST_PointFromText('POINT(127.1003 37.6407)', 5181), 90, 70, '공원 활동 소음', NOW()),
(10, ST_PointFromText('POINT(127.1004 37.6408)', 5181), 65, 45, '시장 소리', NOW()),
(10, ST_PointFromText('POINT(127.1005 37.6409)', 5181), 75, 50, '조용한 밤', NOW()),
(10, ST_PointFromText('POINT(127.1006 37.6410)', 5181), 55, 40, '평화로운 저녁', NOW()),
(10, ST_PointFromText('POINT(127.1007 37.6411)', 5181), 95, 75, '주택가의 소음', NOW()),
(10, ST_PointFromText('POINT(127.1008 37.6412)', 5181), 85, 65, '주말의 시장 소음', NOW()),
(10, ST_PointFromText('POINT(127.1009 37.6413)', 5181), 100, 80, '도시의 혼잡함', NOW()),
(10, ST_PointFromText('POINT(127.1010 37.6414)', 5181), 50, 35, '밤의 고요함', NOW());