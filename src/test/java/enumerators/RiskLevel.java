package enumerators;

public enum RiskLevel {
  LOW("Low", 1),
  MEDIUM("Medium", 2),
  HIGH("High", 3);

  public String getUiValue() {
    return uiValue;
  }

  public int getWeight() {
    return weight;
  }

  private String uiValue;
  private int weight;

  RiskLevel(String uiValue, int weight) {
    this.uiValue = uiValue;
    this.weight = weight;
  }

}
