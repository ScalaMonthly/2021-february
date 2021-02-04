package com.scalamonthly

import cats.data.NonEmptyList

object challenge {

  final case class EmployeeInfo(name: String, salesRevenue: Int, salary: Int)
  sealed abstract class Employee extends Product with Serializable
  object Employee {
    final case class Manager(info: EmployeeInfo, directReports: List[Employee]) extends Employee
    final case class IndividualContributor(info: EmployeeInfo) extends Employee
  }
  final case class BranchName(value: String) extends AnyVal
  final case class Branch(name: BranchName, manager: Employee.Manager)

  def determineBranchToShutDown(branches: NonEmptyList[Branch]): BranchName = ???

}