# 스프링 리액티브에서 첫번째 요청이 느린 이유 확인
## 문제 제기
첫번째 요청이 이후 요청에 비해 느린 현상 발견

## 해결 방법
### warm-up
최초 요청이 느리다면 미리 요청에 대한 워밍업 단계를 진행하는 방식을 사용할 수 있다.
그런데 `warmup` 메서드를 사용해도 계속 같은 현상이 발견되었다.
몇 가지 문서를 찾았는데 `vpn` 을 사용할 경우에 재현되는 듯 싶었음. (https://github.com/reactor/reactor-netty/issues/1732)


## 참고
- https://github.com/spring-projects/spring-boot/issues/24210
- https://github.com/reactor/reactor-netty/issues/560 의 내용을 요약해보면
- https://github.com/reactor/reactor-netty/issues/1023
- https://github.com/reactor/reactor-netty/pull/1455
