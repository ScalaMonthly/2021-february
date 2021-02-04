package com.scalamonthly

import java.time.Instant

final class FundamentalsSpec extends munit.FunSuite {

  import fundamentals._

  test("one") {
    val testCase = List(0, 1, 2, 3, 4, -5)
    val result = one(testCase)
    assertEquals(result, 5)
  }

  test("two") {
    val testCase = List('H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!')
    val result = two(testCase)
    assertEquals(result, "Hello, World!")
  }

  test("three") {
    val testCases = List(None, Some("Here!"))
    val result = testCases.map(three)
    assertEquals(result, List("None", "Some(Here!)"))
  }

  test("four") {
    val time = Instant.now
    val testCases = List(JobStatus.Stopped, JobStatus.Running(time))
    val result = testCases.map(four)
    assertEquals(result, List("Stopped", s"Started at $time"))
  }

  test("five") {
    val testCases = List(List("a", "s", "d", "f"), List.empty, List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0))
    val result = testCases.map(five)
    assertEquals(result, List(4, 0, 10))
  }

  test("six") {
    val testCases = List((List(1, 2, 3, 4), 4), (List(1, 2, 3, 4), 0), (List.empty, 1))
    val result = testCases.map((six _).tupled)
    assertEquals(result, List(true, false, false))
  }

  test("seven") {
    val input = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
    val result = seven(input)
    assertEquals(result, input.reverse)
  }

  test("eight") {
    val input = List(1, 2, 3, 4)
    val result = eight(input)
    import MyList._
    val expected = Cons(1, Cons(2, Cons(3, Cons(4, Empty))))
    assertEquals(result, expected)
  }

  test("foldLeft") {
    val testCase = List(1, 2, 3, 4, 5)
    val result = foldLeft(testCase)("0")(_ + _)
    assertEquals(result, "012345")
  }

  test("foldRight") {
    val testCase = List(1, 2, 3, 4, 5)
    val result =  foldRight(testCase)("0")(_ + _)
    assertEquals(result, "123450")
  }

}