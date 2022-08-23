# 스프링 리액티브에서 첫번째 요청이 느린 이유 확인

## 테스트 환경
- 자바 17
- 파이썬 3 (ssl 없는 거 테스트 해보기 위해 사용)
```shell
$ python3 -m http.server
```


## 문제 제기

첫번째 요청이 이후 요청에 비해 느린 현상 발견

- https://github.com/spring-projects/spring-boot/issues/24210

## 해결 방법

TLS handshake 때문에 느리고 이후엔 connection pool 에서 사용되는 거라서 (without handshake) 빠른거라고 생각함

### warm-up

## 단계 별 수행 내용

### `warmup` 사용하기

리액트 네티에서는 이를 위해 `warmup` 이라는 메서드를 `HttpClient` 에 추가한듯 보임

- https://projectreactor.io/docs/netty/release/reference/index.html#_eager_initialization_3
- https://github.com/reactor/reactor-netty/pull/1455

그래서 `warmup` 메서드를 추가했는데도 계속 같은 현상이 발견되었다.

- https://github.com/reactor/reactor-netty/issues/560

#### `resolver` 사용하기

`DnsResolver` 설정을 추가하면 된다고 해서 추가하였지만 해결되지 않음

```java
HttpClient httpClient = HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
```
이슈  를 읽어보니 자바17에선 개선될 거라고 기대된다고 하는데... 
이미 환경이 자바 17이였다.

#### `vpn` 때문인가?

vpn 끄고 해도 똑같았음...
아래 이슈를 보고 희망을 얻었건만 ㅠㅠ

-  https://github.com/reactor/reactor-netty/issues/1732

## 참고
- https://github.com/reactor/reactor-netty/issues/1023
