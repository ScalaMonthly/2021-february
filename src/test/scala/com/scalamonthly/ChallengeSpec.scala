package com.scalamonthly

import cats.data.NonEmptyList

final class ChallengeSpec extends munit.FunSuite {

  import challenge._
  import com.scalamonthly.challenge.Employee._

  private object IC {
    def apply(name: String, salary: Int): IndividualContributor = IndividualContributor(EmployeeInfo(name, 0, salary))
    def apply(name: String, salary: Int, sales: Int): IndividualContributor = IndividualContributor(EmployeeInfo(name, sales, salary))
  }

  private object M {
    def apply(name: String, salary: Int, directReports: List[Employee]): Manager =
      Manager(EmployeeInfo(name, 0, salary), directReports)
    def apply(name: String, salary: Int, sales: Int, directReports: List[Employee]): Manager =
      Manager(EmployeeInfo(name, sales, salary), directReports)
  }

  private val scranton = {
    val warehouse = M("Darryl Philbin", 1234, List(
      IC("Lonnie Collins", 1234),
      IC("Madge Madsen", 1234),
      IC("Jerry DiCanio", 1234)
    ))
    val accounting = M("Angela Martin", 2345, List(
      IC("Kevin Malone", 2345),
      IC("Oscar Martinez", 2345)
    ))
    val sales = M("Andy Bernard", 3456, 9876, List(
      IC("Phyllis Lapin", 3456, 9876),
      IC("Stanley Hudson", 3456, 9876)
    ))
    val m = M("Michael Scott", 9999, List(
      IC("Dwight Schrute", 80000, 80000),
      IC("Jim Halpert", 23456, 18765),
      IC("Pam Beesly", 1234),
      IC("Creed Branton", 2345),
      IC("Kelly Kapoor", 4567),
      IC("Meredith Palmer", 2345),
      IC("Toby Flenderson", 1111),
      IC("Ryan Howard", 0),
      warehouse,
      accounting,
      sales
    ))
    Branch(BranchName("scranton"), m)
  }

  private val akron = {
    val m = M("Mark Chisholm", 200000, List(
      IC("Unknown Name", 400, 1002)
    ))
    Branch(BranchName("akron"), m)
  }

  private val nashua = {
    val m = M("Who Knows", 10001, List(
      IC("Holly Flax", 123456),
      IC("AJ", 5678, 2021)
    ))
    Branch(BranchName("nashua"), m)
  }

  private val rochester = {
    val m = M("???", 2021111, List.empty)
    Branch(BranchName("rochester"), m)
  }

  private val syracuse = {
    val m = M("Mike Schave", 34567, List(
      IC("Harry Jannerone", 2345667, 10222394),
      IC("Nelson Cardigan", 34522, 85933),
      IC("Ginelle", 223445),
      IC("Sharon", 67543),
      IC("Matt", 12001, 10)
    ))
    Branch(BranchName("syracuse"), m)
  }

  private val utica = {
    val m = M("Karen Filippelli", 22222, List(
      IC("Rolando", 89001),
      IC("Ben Nugent", 22032, 33456),
      IC("Mark", 23111)
    ))
    Branch(BranchName("utica"), m)
  }

  test("one input") {
    val result = determineBranchToShutDown(NonEmptyList.of(scranton))
    assertEquals(result, scranton.name)
  }

  test("scranton v. akron") {
    val result = determineBranchToShutDown(NonEmptyList.of(scranton, akron))
    assertEquals(result, akron.name)
  }

  test("scranton v. nashua") {
    val result = determineBranchToShutDown(NonEmptyList.of(scranton, nashua))
    assertEquals(result, nashua.name)
  }

  test("scranton v. syracuse") {
    val result = determineBranchToShutDown(NonEmptyList.of(scranton, syracuse))
    assertEquals(result, scranton.name)
  }

  test("scranton v. utica") {
    val result = determineBranchToShutDown(NonEmptyList.of(scranton, utica))
    assertEquals(result, utica.name)
  }

  test("all") {
    val result = determineBranchToShutDown(NonEmptyList.of(scranton, akron, nashua, rochester, syracuse, utica))
    assertEquals(result, rochester.name)
  }

}