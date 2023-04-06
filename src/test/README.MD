# JUnit5와 AssertJ를 이용한 TEST 코드 작성 #
<hr/>

## 1. 테스트 파일 생성 ##
* 테스트 파일은 다음과 같이 생성할 수 있습니다.
  * 테스트 하려고 하는 클래스에서 클래스 이름을 클릭.
  * 단축키 'ctrl + shift + T' > 'Create New Test' 클릭.
  * 다음과 같이 설정합니다.
    *  Testing library : JUit5
    *  Class name : 테스트하려는 클래스 이름 + Test
    *  Destination package : test/(여기서부터는 테스트하려는 클래스와 경로가 같다 )

## 2. JUnit5 기본 어노테이션(*자주 사용하는 것 위주로 정리)
|   어노테이션명    | 내용                                                                                                                    |
|:-----------:|-----------------------------------------------------------------------------------------------------------------------|
|    @Test    | 어노테이션이 선언된 메소드의 테스트를 수행                                                                                               |
|  @AfterAll  | 테스트 클래스에 있는 테스트를 모두 실행하고 그 후에 딱 한 번만 실행 (static으로 선언)                                                                 |
| @AfterEach  | 테스트 각각의 끝에 한 번씩 실행                                                                                                    |
| @BeforeAll  | 테스트 클래스에 있는 모든 테스트를 실행하기 전 우선하여 딱 한 번만 실행 (static으로 선언)                                                               |
| @BeforeEach | 각각의 테스트를 실행하기 전에 우선하여 한 번씩 실행<br>테스트에 여러 초기화가 필요하다면, 여러 개의 @BeforeEach 사용 가능<br>순서가 필요한 경우, @Order 어노테이션을 사용해 순서를 지정. |
|  @Disabled  | 테스트 클래스 또는 테스트 메소드가 현재 비활성화되어 실행되지 않아야 함을 의미                                                                          |

## 3. JUnit5 Assertions ##
* Assertion은 테스트에서 검증하고자 하는 내용을 확인하는 기능을 제공하는 패키지입니다.
* JUnit에서 제공하는 Assertion 중 자주 사용하는 메소드만 정리하면 아래와 같습니다.

    | 메소드명                           |                              내용                              |
    |:------------------------------------------------------------:|:----------------------------------:|
    | assertTrue                     |                        조건이 성공이면 True                         |
    | assertFalse                    |                        조건이 실패이면 True                         |
    | assertNull                     |                       조건이 Null이면 True                        |
    | assertNotNull                  |                     조건이 Not Null이면 True                      |
    | assertEquals(expected, actual) |                 expected와 actual이 동일하면 True                  |
    | assertSame                     |                      동일한 Object이면 True                       |
    | assertThrows                   |                      예상한 에러가 발생하면 True                       |
    | assertTimeoutPreemptively      | 테스트가 예상한 시간보다 오래 걸리지 않으면 True<br>지정한 시간보다 오래 걸릴 경우 바로 테스트 종료 |
* Assertions에 대한 더 자세한 내용이 알고 싶다면 [JUnit5 Assertions](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html) 을 참조하시길 바랍니다.

## 4. AssertJ assert 메소드 체이닝
* org.assertj.core.api.Assertions로 임포트 되어있는지 확인해야 합니다.
* AssertJ의 모든 테스트 코드는 assertThat()이 사용됩니다.
* 메소드 체이닝이 기본 포맷은 다음과 같습니다.
   ```
  assertThat(타겟).메소드1().메소드2();
  ```
* 사용될 수 있는 메소드는 다음과 같습니다. (* 자주 사용하는 것 위주)
  ```
  isEqualTo(e)
  contains(e)
  doesNotContain(e)
  startsWith(e)
  endWith(e)
  isNotEmpty(e)
  isPositive(n)
  isGreaterThan(n)
  ```
* assert 메소드의 메소드 체이닝 사용 예시는 다음과 같습니다.
  ```
  @Test
  @DisplayName("테스트 코드 작성") 
  public void assertThatTest() {
  assertThat(3.14d)                       // 3.14는
            .isPositive().                // 양수이고
            .isGreaterThan(3).            // 3보다 크며
            .isLessThan(4).               // 4보다 작고
            .isEqualTo(3, offset(1d))     // 오프셋 1기준으로 3과 같으며 *offset : 상대주소
            .isEqualTo(3.14);             // 오프셋 없이는 3.14와 일치.
  }
  ```
  ```
  @Test
  @DisplayName("테스트 코드 작성")
  public void assertThatTest2() {
  assertThat("Hello, world!")             // "Hello, world!"는
            .isNotEmpty().                // 비어있지 않고
            .contains("Hello").           // "Hello"를 포함하며
            .doesNotContain("JAVA").      // "JAVA"는 포함하지 않고
            .startWith("Hel").            // "Hel"로 시작하고
            .endsWith("d!").              // "d!"로 끝나며
            .isEqualTo("Hello, world!");  // "Hello, world!" 와 일치
  }
* 더 자세한 내용을 알고 싶다면 [AssertJ](https://assertj.github.io/doc/)를 참조하시길 바랍니다.
