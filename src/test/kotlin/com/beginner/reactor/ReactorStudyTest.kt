package com.beginner.reactor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono

/**
 * @author RJ
 */
class ReactorStudyTest {
  //private val logger = LoggerFactory.getLogger(ReactorStudyTest::class.java)

  /**
   * Conclusion:
   * 1. The map function is running in the main thread by default.
   * 2. The map function not run until subscribed.
   */
  @Test
  fun threadInMap() {
    var value: String
    val threadIdInMain = Thread.currentThread().id
    var threadIdInMap = 0L
    val mono = Mono.just(1)
      .map {
        value = "in map"
        threadIdInMap = Thread.currentThread().id
        it * 2
      }
    value = "in main"
    assertEquals(2, mono.block()!!)  // invoked block method to make the map function to run
    assertEquals(2, mono.block()!!)  // can block any times to get the origin value
    assertEquals("in map", value)
    assertNotEquals(threadIdInMap, 0L)
    assertEquals(threadIdInMap, threadIdInMain)
  }

  /**
   * Conclusion: subscribe() method is block.
   */
  @Test
  fun invokedBySubscribe() {
    var value: String
    val mono = Mono.just(1)
      .map {
        value = "in map"
      }
    value = "in main"
    mono.subscribe() // invoked subscribe method block to make the map function to run
    assertEquals("in map", value)
  }

  /**
   * Conclusion: map function follow 'empty' will never invoked.
   */
  @Test
  fun emptyFollowByMap() {
    var value = "in main"
    val mono = Mono.empty<Void>().map { value = "in map" }
    mono.block()
    assertEquals("in main", value)
  }

  /**
   * Conclusion: 'then' will run after 'empty'.
   */
  @Test
  fun emptyFollowByThen() {
    val mono = Mono.empty<Void>().then(Mono.just("in then"))
    assertEquals(mono.block(), "in then")
  }
}