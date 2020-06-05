# spring-test-redis-cache
redis cache test


### Spring-data-redis 이용한 redis cache 처리

- 그냥 하려니 HashMap 파라미터는 키로 serialize, deserialize할때 자꾸 에러가 났음
- Custom 하게 KeyGenerator 만들어서 하는 방식 적용의 샘플
