# 스프링 리액티브에서 첫번째 요청이 느린 이유 확인
## 문제 제기
첫번째 요청이 이후 요청에 비해 느린 현상 발견

## 해결 방법
### warm-up
최초 요청이 느리다면 미리 요청에 대한 워밍업 단계를 진행하는 방식을 사용할 수 있다.

## 기타
- https://github.com/spring-projects/spring-boot/issues/24210
- https://github.com/reactor/reactor-netty/issues/560 의 내용을 요약해보면
- https://github.com/reactor/reactor-netty/issues/1023
- https://github.com/reactor/reactor-netty/pull/1455
