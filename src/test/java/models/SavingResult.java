package models;

import enumerators.RiskLevel;

public class SavingResult {
  private String totalIncome;
  private String interestIncome;
  private RiskLevel risk;

  public String getTotalIncome() {
    return totalIncome;
  }

  public void setTotalIncome(String totalIncome) {
    this.totalIncome = totalIncome;
  }

  public String getInterestIncomed() {
    return interestIncome;
  }

  public void setInterestIncomed(String interestIncomed) {
    this.interestIncome = interestIncomed;
  }

  public RiskLevel getRisk() {
    return risk;
  }

  public void setRisk(RiskLevel risk) {
    this.risk = risk;
  }

}
