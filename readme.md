## 5가지 테스트
5가지 insert 성능 테스트를 했습니다.

- 2.5만건씩 multiple insert를 수행 하며
- 500회까지 걸린 시간을 기록 했습니다.
- [tsid](https://vladmihalcea.com/uuid-database-primary-key/) 는 time sorted 된 값 입니다
- uuid는 java의 randomUUID, 버전4 완전랜덤값을 입력 했습니다.


### varchar 에 입력속도 비교 - mysql
1. tsid - 107,247
2. uuid - 측정불가 (100회차 부터 90초 경과하여 종료함.. 이게 말이 되는것인가?)

### varchar 에 입력속도 비교 - postgresql
3. tsid - 59,680
4. uuid - 245,189

### uuid 타입에 입력속도 측정 (postgresql 만 있음)
5. uuid - 104,424



## 랭크순
1. postgresql, varchar(36) 타입, tsid insert - 59,680
2. postgresql, uuid 타입, uuid insert - 104,424
3. mysql, varchar(36) 타입, tsid insert - 107,247
4. postgresql, varchar(36) 타입, uuid insert - 245,189
5. mysql, varchar(36) 타입, uuid insert - 측정불가


### 특징 정리
- 같은 타입이면 postgresql 이 빠르다.
- uuid보다는 tsid가 빠르다
- postgresql의 uuid타입은 최적화가 잘되어있는듯 하다 (mysql의 tsid 보다도 빠르다) 하지만 역시 tsid 보단 느리다 